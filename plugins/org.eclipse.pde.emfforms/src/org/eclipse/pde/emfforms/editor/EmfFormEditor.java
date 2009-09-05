/**
 * Copyright (c) 2009 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anyware Technologies - initial API and implementation
 *
 * $Id: EmfFormEditor.java,v 1.21 2009/09/04 16:48:23 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.command.*;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.*;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.dnd.*;
import org.eclipse.emf.edit.ui.provider.*;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.action.*;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.pde.emfforms.editor.IEmfFormEditorConfig.VALIDATE_ON_SAVE;
import org.eclipse.pde.emfforms.internal.Activator;
import org.eclipse.pde.emfforms.internal.editor.*;
import org.eclipse.pde.emfforms.internal.validation.ValidatingEContentAdapter;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * A {@link FormEditor} allowing to edit an EMF {@link EObject} in a convenient
 * way
 * 
 * @param <T>
 *            The type of the {@link EObject} to edit.
 */
public abstract class EmfFormEditor<T extends EObject> extends FormEditor implements IEditingDomainProvider, ISelectionProvider, IViewerProvider, IResourceChangeListener, IGotoMarker {

	/**
	 * This keeps track of the editing domain that is used to track all changes
	 * to the model.
	 */
	private EditingDomain _editingDomain;

	/**
	 */
	private IEmfFormEditorConfig _editorConfig;

	/**
	 * This is the one adapter factory used for providing views of the model.
	 */
	private ComposedAdapterFactory _adapterFactory;

	private final IObservableValue _observableValue = new WritableValue();

	private T _currentEObject;

	/**
	 * This keeps track of all the
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are
	 * listening to this editor.
	 */
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

	protected ISelection editorSelection = StructuredSelection.EMPTY;

	private CommandStackListener _commandStackListener = new CommandStackListener() {
		public void commandStackChanged(final EventObject event) {
			getContainer().getDisplay().asyncExec(new Runnable() {
				public void run() {
					handleCommandStackChanged(event);
				}
			});
		}
	};

	private PropertySheetPage propertySheetPage;

	/**
	 * The MarkerHelper is responsible for creating workspace resource markers presented in Eclipse's Problems View.
	 */
	protected MarkerHelper markerHelper = new EditUIMarkerHelper();

	private DataBindingContext _bindingContext;

	private ValidatingEContentAdapter _validator;

	private boolean isSaving = false;

	public EmfFormEditor() {
		this._editorConfig = getFormEditorConfig();
		init();
	}

	/**
	 * @return the {@link IEmfFormEditorConfig} the editor will use as its configuration
	 */
	protected IEmfFormEditorConfig getFormEditorConfig() {
		return new DefaultEmfFormEditorConfig();
	}

	private void init() {
		_editingDomain = createEditingDomain();
		_bindingContext = new EMFDataBindingContext();
		_validator = new ValidatingEContentAdapter(_observableValue, _bindingContext, this);

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		//
		getEditingDomain().getCommandStack().addCommandStackListener(_commandStackListener);

	}

	/**
	 * This is called during startup.
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) {
		setSite(site);
		setInputWithNotify(editorInput);

		setPartName(getPartName());
		// -- To manage Copy/Cut/Paste
		site.setSelectionProvider(this);
	}

	@Override
	public void dispose() {
		getEditingDomain().getCommandStack().removeCommandStackListener(_commandStackListener);
		if (_adapterFactory != null) {
			_adapterFactory.dispose();
		}

		super.dispose();
	}

	/**
	 * This sets up the editing domain for the model editor.
	 */
	protected EditingDomain createEditingDomain() {
		_adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		_adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		_adapterFactory.addAdapterFactory(this.getSpecificAdapterFactory());
		_adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		// Create the command stack that will notify this editor as commands are
		// executed.
		BasicCommandStack commandStack = new BasicCommandStack();

		// Create the editing domain with a special command stack, and if it's
		// asked a shared Clipboard
		if (isUsingSharedClipboard())
			return new SharedClipboardAdapterFactoryEditingDomain(_adapterFactory, commandStack, new HashMap<Resource, Boolean>());
		// else
		return new AdapterFactoryEditingDomain(_adapterFactory, commandStack, new HashMap<Resource, Boolean>());
	}

	protected abstract AdapterFactory getSpecificAdapterFactory();

	@Override
	protected void addPages() {
		// Creates the model from the editor input
		createModel();

		try {
			for (IEmfFormPage page : getPagesToAdd())
				addPage(page);
			addSourcePage();

			int i = 0;
			for (Image img : getPagesImages())
				setPageImage(i++, img);

		} catch (PartInitException e) {
			Activator.logException(e, Messages.EmfFormEditor_InitError);
		}
	}

	private void addSourcePage() throws PartInitException {
		if (!_editorConfig.isShowSourcePage())
			return;

		boolean isXmlUiBundlePresent = false;
		BundleContext bc = Activator.getDefault().getBundle().getBundleContext();
		for (Bundle b : bc.getBundles()) {
			if ("org.eclipse.wst.xml.ui".equals(b.getSymbolicName())) { //$NON-NLS-1$
				isXmlUiBundlePresent = true;
				break;
			}
		}

		if (isXmlUiBundlePresent)
			addPage(new XmlSourcePage(this));
		else
			addPage(new SimpleSourcePage(this));

	}

	/**
	 * @throws PartInitException
	 */
	protected abstract List<? extends IEmfFormPage> getPagesToAdd() throws PartInitException;

	protected abstract List<Image> getPagesImages();

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			isSaving = true;

			internalDoValidate(monitor);

			// Do the work within an operation because this is a long running
			// activity that modifies the workbench.
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				// This is the method that gets invoked when the operation runs.
				@Override
				public void execute(IProgressMonitor monitor) throws CoreException {
					try {
						internalDoSave(monitor);
					} catch (IOException ioe) {
						throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, Messages.EmfFormEditor_SaveError, ioe));
					}
				}
			};

			try {
				// This runs the options, and shows progress.
				new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

				// Refresh the necessary state.
				// if we have a BasicCommandStack (that's to say almost all the time), don't flush it but call saveIsDone instead  
				if (getEditingDomain().getCommandStack() instanceof BasicCommandStack)
					((BasicCommandStack) getEditingDomain().getCommandStack()).saveIsDone();
				else
					getEditingDomain().getCommandStack().flush();
				firePropertyChange(IEditorPart.PROP_DIRTY);

			} catch (Exception exception) {
				// Something went wrong that shouldn't.
				MessageDialog.openError(getSite().getShell(), Messages.EmfFormEditor_SaveError_Title, Messages.EmfFormEditor_SaveError_Msg);
				Activator.logException(exception, Messages.EmfFormEditor_SaveError_Exception);
			}

		} catch (OperationCanceledException oce) {
			// Do nothing
		} finally {
			isSaving = false;
		}

	}

	/**
	 * @param monitor
	 * @throws IOException
	 */
	protected void internalDoSave(IProgressMonitor monitor) throws IOException {
		// Save only resources that have actually changed.
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		// Save the resources to the file system.
		boolean first = true;
		for (Resource resource : getEditingDomain().getResourceSet().getResources()) {
			if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !getEditingDomain().isReadOnly(resource)) {
				resource.save(saveOptions);

				first = false;
			}
		}
	}

	/**
	 * @param monitor
	 */
	protected void internalDoValidate(IProgressMonitor monitor) {
		VALIDATE_ON_SAVE validateOnSave = validateOnSave();

		// result of the Validation
		Diagnostic diagnostic = Diagnostic.OK_INSTANCE;

		// if the validation is asked by the user
		if (validateOnSave != VALIDATE_ON_SAVE.NO_VALIDATION)
			diagnostic = _validator.validate(getCurrentEObject());

		if (diagnostic.getSeverity() != Diagnostic.OK) {
			switch (validateOnSave) {
				case VALIDATE_AND_WARN :
					int dialogResult = new DiagnosticDialog(getSite().getShell(), Messages.EmfFormEditor_DiaDialog_InvalidModel_Title, null, diagnostic, Diagnostic.ERROR | Diagnostic.WARNING) {
						@Override
						protected Control createMessageArea(Composite composite) {
							message = Messages.EmfFormEditor_ValidationWarn_Msg;
							return super.createMessageArea(composite);
						}

						@Override
						protected void createButtonsForButtonBar(Composite parent) {
							// create OK and Details buttons
							createButton(parent, IDialogConstants.OK_ID, IDialogConstants.YES_LABEL, true);
							createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.NO_LABEL, true);
							createDetailsButton(parent);
						}
					}.open();

					if (dialogResult != Window.OK) {
						if (monitor != null)
							monitor.setCanceled(true);
						throw new OperationCanceledException();
					}
					break;
				case VALIDATE_AND_ABORT :
					new DiagnosticDialog(getSite().getShell(), Messages.EmfFormEditor_DiaDialog_InvalidModel_Title, null, diagnostic, Diagnostic.ERROR | Diagnostic.WARNING) {
						@Override
						protected Control createMessageArea(Composite composite) {
							message = Messages.EmfFormEditor_ValidationError_Msg;
							return super.createMessageArea(composite);
						}
					}.open();

					monitor.setCanceled(true);

					throw new OperationCanceledException();
				default :
					// fall through
			}

		}
	}

	/**
	 * Return a member of {@link VALIDATE_ON_SAVE}, in order to say if a
	 * validation must be executed before saving resource
	 * 
	 * <ul>
	 * <li>
	 * {@link VALIDATE_ON_SAVE.NO_VALIDATION} no validation executed before
	 * saving resource</li>
	 * <li>{@link VALIDATE_ON_SAVE.VALIDATE_AND_WARN} a validation is executed,
	 * and only warn the user if the validation is not OK</li>
	 * <li>
	 * {@link VALIDATE_ON_SAVE.VALIDATE_AND_ABORT} a validation is executed, and
	 * the resource cannot be saved</li>
	 * </ul>
	 * 
	 * Clients can override this method, by default return
	 * {@link VALIDATE_ON_SAVE.NO_VALIDATION}
	 * 
	 * @return {@link VALIDATE_ON_SAVE}
	 */
	protected final VALIDATE_ON_SAVE validateOnSave() {
		return _editorConfig.getValidateOnSave();
	}

	/**
	 * This returns whether something has been persisted to the URI of the
	 * specified resource. The implementation uses the URI converter from the
	 * editor's resource set to try to open an input stream.
	 */
	protected boolean isPersisted(Resource resource) {
		boolean result = false;
		try {
			InputStream stream = getEditingDomain().getResourceSet().getURIConverter().createInputStream(resource.getURI());
			if (stream != null) {
				result = true;
				stream.close();
			}
		} catch (IOException e) {
			// Ignore
		}
		return result;
	}

	/**
	 * This also changes the editor's input.
	 */
	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				doSaveAs(URI.createPlatformResourceURI(file.getFullPath().toString(), true), new FileEditorInput(file));
			}
		}
	}

	protected void doSaveAs(URI uri, IEditorInput editorInput) {
		(getEditingDomain().getResourceSet().getResources().get(0)).setURI(uri);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		IProgressMonitor progressMonitor = getActionBars().getStatusLineManager() != null ? getActionBars().getStatusLineManager().getProgressMonitor() : new NullProgressMonitor();
		doSave(progressMonitor);
	}

	/**
	 * This is for implementing {@link IEditorPart} and simply tests the command
	 * stack.
	 */
	@Override
	public boolean isDirty() {
		return ((BasicCommandStack) getEditingDomain().getCommandStack()).isSaveNeeded();
	}

	@Override
	public final boolean isSaveAsAllowed() {
		return this._editorConfig.isSaveAsAllowed();
	}

	@Override
	protected PDEFormToolkit createToolkit(Display display) {
		return this._editorConfig.createPDEFormToolkit(display);
	}

	/**
	 * This is the method called to load a resource into the editing domain's
	 * resource set based on the editor's input.
	 */
	public void createModel() {
		URI resourceURI = EditUIUtil.getURI(getEditorInput());
		Resource resource;
		try {
			// Load the resource through the editing domain.
			resource = getEditingDomain().getResourceSet().getResource(resourceURI, true);
		} catch (Exception e) {
			resource = getEditingDomain().getResourceSet().getResource(resourceURI, false);
		}

		setMainResource(resource);

		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	protected void setMainResource(Resource resource) {
		if (_currentEObject != null)
			_currentEObject.eAdapters().remove(_validator);

		_currentEObject = getRootObject(resource);
		_observableValue.setValue(_currentEObject);
		_currentEObject.eAdapters().add(_validator);
	}

	protected T getRootObject(Resource resource) {
		return (T) resource.getContents().get(0);
	}

	/**
	 * This returns the editing domain as required by the
	 * {@link IEditingDomainProvider} interface. This is important for
	 * implementing the static methods of {@link AdapterFactoryEditingDomain}
	 * and for supporting {@link org.eclipse.emf.edit.ui.action.CommandAction}.
	 */
	public EditingDomain getEditingDomain() {
		return _editingDomain;
	}

	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite().getActionBarContributor();
	}

	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}

	/**
	 * @return The {@link T} currently edited
	 */
	public T getCurrentEObject() {
		return _currentEObject;
	}

	public IObservableValue getInputObservable() {
		return _observableValue;
	}

	/**
	 * @param viewer
	 */
	public void addViewerToListenTo(StructuredViewer viewer) {
		// This listener is used to manage copy/paste...
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
				setSelection(selectionChangedEvent.getSelection());
			}
		});
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	public ISelection getSelection() {
		return editorSelection;
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		editorSelection = selection;

		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
	}

	public Viewer getViewer() {
		AbstractEmfFormPage page = (AbstractEmfFormPage) pages.get(getActivePage());
		return page.getViewer();
	}

	/**
	 * @return <code>true</code> if this editor must use a shared clipboard,
	 *         which will allow copy/paste actions between different instance of
	 *         the same editor. The default return value is <code>false</code>
	 */
	public final boolean isUsingSharedClipboard() {
		return this._editorConfig.isUsingSharedClipboard();
	}

	/**
	 * @param event
	 */
	protected void handleCommandStackChanged(final EventObject event) {
		// the edited object has been changed
		firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
		// since the title of the editor is, most of the time,
		// computed using some object attribute, ask the editor
		// to refresh it!
		firePropertyChange(IWorkbenchPartConstants.PROP_TITLE);

		IFormPage activePage = getActivePageInstance();
		if (activePage != null) {
			if (((AbstractEmfFormPage) activePage).getViewer() != null) {
				// Select the affected objects in the viewer
				Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
				if (mostRecentCommand != null) {
					if (mostRecentCommand instanceof CreateChildCommand) {
						Collection<?> objects = mostRecentCommand.getAffectedObjects();
						if (objects != null && !objects.isEmpty())
							((AbstractEmfFormPage) activePage).getViewer().setSelection(new StructuredSelection(objects.toArray()[objects.size() - 1]), true);
					}
				}
			}
		}
	}

	private static class SharedClipboardAdapterFactoryEditingDomain extends AdapterFactoryEditingDomain {
		private static Collection<Object> clipboard;

		/**
		 * @param adapterFactory
		 * @param commandStack
		 * @param resourceToReadOnlyMap
		 */
		public SharedClipboardAdapterFactoryEditingDomain(AdapterFactory adapterFactory, CommandStack commandStack, Map<Resource, Boolean> resourceToReadOnlyMap) {
			super(adapterFactory, commandStack, resourceToReadOnlyMap);

		}

		@Override
		public Collection<Object> getClipboard() {
			return SharedClipboardAdapterFactoryEditingDomain.clipboard;
		}

		@Override
		public void setClipboard(Collection<Object> clipboard) {
			SharedClipboardAdapterFactoryEditingDomain.clipboard = clipboard;
		}
	}

	private class ResourceDeltaVisitor implements IResourceDeltaVisitor {
		public boolean visit(IResourceDelta delta) throws CoreException {
			if (delta.getResource().getType() == IResource.FILE) {
				if (delta.getKind() == IResourceDelta.REMOVED) {
					String fullPath = delta.getFullPath().toString();
					final URI changedURI = URI.createPlatformResourceURI(fullPath, false);

					Resource currentResource = getCurrentEObject().eResource();
					if (currentResource.getURI().equals(changedURI)) {
						Shell currentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

						currentShell.getDisplay().asyncExec(new Runnable() {
							public void run() {
								getSite().getPage().closeEditor(getCurrentInstance(), false);
							}
						});
					}
				} else if (delta.getKind() == IResourceDelta.CHANGED) {
					String fullPath = delta.getFullPath().toString();
					final URI changedURI = URI.createPlatformResourceURI(fullPath, false);

					SWTObservables.getRealm(Display.getDefault()).asyncExec(new Runnable() {

						public void run() {
							EObject currentEObject = (EObject) getInputObservable().getValue();
							Resource currentResource = currentEObject.eResource();
							boolean isMainResource = currentResource.getURI().equals(changedURI);
							Resource changedResource = currentResource.getResourceSet().getResource(changedURI, false);

							// The changed resource is contained in the
							// resourceset, it must be reloaded
							if (changedResource != null && changedResource.isLoaded() && !isSaving) {

								// The editor has pending changes, we
								// must
								// inform the user, the content is going
								// to be
								// reloaded
								if (isMainResource && isDirty()) {
									getEditingDomain().getCommandStack().flush();
								}

								try {
									changedResource.unload();
									changedResource.load(Collections.EMPTY_MAP);

									// If the modified resource is the
									// main resource, we update the
									// current object
									if (isMainResource) {
										setMainResource(changedResource);
									}
								} catch (IOException ioe) {
									Activator.log(ioe);
								}
							}
						}
					});
				}

			}

			return true;
		}
	}

	/**
	 * This is the content outline page.
	 */
	protected IContentOutlinePage contentOutlinePage;

	protected IStatusLineManager contentOutlineStatusLineManager;

	/**
	 * This is the content outline page's viewer.
	 */
	protected TreeViewer contentOutlineViewer;

	private ResourceDeltaVisitor _visitor;

	/**
	 * This accesses a cached version of the content outliner.
	 * @generated
	 */
	public IContentOutlinePage getContentOutlinePage() {
		if (contentOutlinePage == null) {
			// The content outline is just a tree.
			class MyContentOutlinePage extends ContentOutlinePage {
				@Override
				public void createControl(Composite parent) {
					super.createControl(parent);
					contentOutlineViewer = getTreeViewer();
					contentOutlineViewer.addSelectionChangedListener(this);

					// Set up the tree viewer.
					contentOutlineViewer.setContentProvider(new AdapterFactoryContentProvider(getAdapterFactory()));
					contentOutlineViewer.setLabelProvider(new DecoratingLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()), PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator()));
					contentOutlineViewer.setInput(getCurrentEObject().eResource());
					contentOutlineViewer.addFilter(new ViewerFilter() {
						@Override
						public boolean select(Viewer viewer, Object parentElement, Object element) {
							return (!(AdapterFactoryEditingDomain.unwrap(element) instanceof String));
						}
					});

					// Make sure our popups work.
					createContextMenuFor(contentOutlineViewer);

					if (!getEditingDomain().getResourceSet().getResources().isEmpty()) {
						// Select the root object in the view.
						contentOutlineViewer.setSelection(new StructuredSelection(getEditingDomain().getResourceSet().getResources().get(0)), true);
					}

				}

				@Override
				public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
					super.makeContributions(menuManager, toolBarManager, statusLineManager);
					contentOutlineStatusLineManager = statusLineManager;
				}

				@Override
				public void setActionBars(IActionBars actionBars) {
					super.setActionBars(actionBars);
					if (getActionBarContributor() != null) {
						getActionBarContributor().shareGlobalActions(this, actionBars);
					}
				}
			}

			contentOutlinePage = new MyContentOutlinePage();

			// Listen to selection so that we can handle it is a special way.
			contentOutlinePage.addSelectionChangedListener(new ISelectionChangedListener() {
				// This ensures that we handle selections correctly.
				public void selectionChanged(SelectionChangedEvent event) {
					handleContentOutlineSelection(event.getSelection(), getViewer());
				}
			});

			addSelectionChangedListener(new ISelectionChangedListener() {
				// This ensures that we handle selections correctly.
				public void selectionChanged(SelectionChangedEvent event) {
					handleContentOutlineSelection(event.getSelection(), contentOutlineViewer);
				}
			});
		}

		return contentOutlinePage;
	}

	/**
	 * This deals with how we want selection in the outline to affect the other views.
	 */
	public void handleContentOutlineSelection(ISelection selection, Viewer viewerToSnych) {
		if (!selection.isEmpty() && selection instanceof IStructuredSelection && viewerToSnych != null) {
			if (((IStructuredSelection) selection).getFirstElement() != ((IStructuredSelection) viewerToSnych.getSelection()).getFirstElement()) {
				viewerToSnych.setSelection(new StructuredSelection(((IStructuredSelection) selection).getFirstElement()));
			}
		}
	}

	/**
	 * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
	 */
	protected void createContextMenuFor(StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		// TODO
		// contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] {LocalTransfer.getInstance()};
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(getEditingDomain(), viewer));
	}

	private EmfFormEditor<T> getCurrentInstance() {
		return this;
	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			_visitor = new ResourceDeltaVisitor();
			delta.accept(_visitor);
		} catch (CoreException ce) {
			Activator.log(ce);
		}
	}

	public ComposedAdapterFactory getAdapterFactory() {
		return _adapterFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return getFormEditorConfig().isShowOutlinePage() ? getContentOutlinePage() : null;
		}
		if (key.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		}
		if (key.equals(IGotoMarker.class)) {
			return this;
		}
		return super.getAdapter(key);
	}

	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage = new ExtendedPropertySheetPage((AdapterFactoryEditingDomain) _editingDomain) {
				@Override
				public void setSelectionToViewer(List<?> selection) {
					// TODO
					//	EmfFormEditor.this.setSelectionToViewer(selection);
					EmfFormEditor.this.setFocus();
				}

				@Override
				public void setActionBars(IActionBars actionBars) {
					super.setActionBars(actionBars);
					if (getActionBarContributor() != null) {
						getActionBarContributor().shareGlobalActions(this, actionBars);
					}
				}
			};
			propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(_adapterFactory));
		}

		return propertySheetPage;
	}

	/**
	 * {@inheritDoc}
	 */
	public void gotoMarker(IMarker marker) {
		if (getViewer() == null)
			return; // atm we only handle in-viewer selection

		try {
			if (marker.getType().equals(EValidator.MARKER)) {
				String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
				if (uriAttribute != null) {
					URI uri = URI.createURI(uriAttribute);
					EObject eObject = getEditingDomain().getResourceSet().getEObject(uri, true);
					if (eObject != null && (getEditingDomain() instanceof AdapterFactoryEditingDomain)) {
						AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) getEditingDomain();
						if (getViewer() != null) {
							getViewer().setSelection(new StructuredSelection(Collections.singleton(editingDomain.getWrapper(eObject)).toArray()));
						}
					}
				}
			}
		} catch (CoreException exception) {
			Activator.log(exception);
		}
	}

	public DataBindingContext getDataBindingContext() {
		return _bindingContext;
	}

	@Override
	public void setFocus() {
		super.setFocus();
		// for some reason, the code below is needed to workaround a bug on GTK where the actual
		// content of the first page of the editor doesn't correctly get focus when the editor is opened :-/)
		if (getActivePageInstance() != null)
			getActivePageInstance().setFocus();
	}

	public abstract String getID();

	/* package */void validate() {
		// TODO perform validation in a separate job
		_validator.validate();
	}
}
