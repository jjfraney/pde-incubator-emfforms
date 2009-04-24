package org.eclipse.pde.ds.ui.internal.editor.masterdetail;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.Properties;
import org.eclipse.pde.ds.scr.Property;
import org.eclipse.pde.ds.scr.provider.ScrItemProviderAdapterFactory;
import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.PropertiesDetailsPart;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.PropertyDetailsPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class PropertiesMasterDetail extends MasterDetailsBlock implements IDetailsPageProvider {

	private TreeViewer _viewer;
	private Component _component;
	private EditingDomain _editingDomain;
	private DataBindingContext _databindingContext;
	private IEditorSite _site;
	private IManagedForm _managedForm;
	private Object groupDataAction;
	private Button addButtonProperty;
	private Button addButtonProperties;
	private Button removeButton;
	private Button wizardAddButton;

	public PropertiesMasterDetail() {
		super();
	}

	public void createContent(IManagedForm managedForm) {
		super.createContent(managedForm);
		sashForm.setLayout(new FillLayout(SWT.FILL));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(sashForm);

		Composite actualContent = managedForm.getToolkit().createComposite(sashForm, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(actualContent);
		actualContent.setLayout(new FillLayout());

	}

	public StructuredViewer getViewer() {
		return _viewer;
	}

	public void setComponentAndEditingDomain(Component c, EditingDomain editingDomain, IEditorSite editorSite, DataBindingContext bindingContext) {
		_component = c;
		_editingDomain = editingDomain;
		_databindingContext = bindingContext;
		_viewer.setInput(_component);
		_viewer.expandAll();
		_site = editorSite;

		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] {LocalTransfer.getInstance()};
		_viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(_viewer));
		_viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(_editingDomain, _viewer));

		/*		getViewer().getControl().addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						((NumericalActionBarContributor) _site.getActionBarContributor()).enableGlobalHandlers();
					}

					@Override
					public void focusLost(FocusEvent e) {
						((NumericalActionBarContributor) _site.getActionBarContributor()).disableGlobalHandlers();
					}
				});*/
	}

	public void registerContextMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createMasterPart(IManagedForm managedForm, Composite parent) {
		_managedForm = managedForm;
		FormToolkit toolkit = managedForm.getToolkit();

		Section section = toolkit.createSection(parent, Section.DESCRIPTION | Section.TITLE_BAR);
		section.setText(Messages.PropertyPage_Title);
		section.setDescription("Sélectionnez ci-dessous la donnée à éditer."); //$NON-NLS-1$
		section.marginWidth = 10;
		section.setLayout(new FillLayout());
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(client);

		Composite browseComposite = new Composite(client, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(browseComposite);

		FilteredTree ft = new FilteredTree(browseComposite, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);
		_viewer = ft.getViewer();

		GridDataFactory.fillDefaults().grab(true, false).span(1, 2).applyTo(_viewer.getControl());

		Composite buttonComposite = new Composite(browseComposite, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(buttonComposite);

		addButtonProperty = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(addButtonProperty);
		addButtonProperty.setText("Add Property"); //$NON-NLS-1$

		addButtonProperties = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(addButtonProperties);
		addButtonProperties.setText("Add Properties"); //$NON-NLS-1$

		wizardAddButton = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(wizardAddButton);
		wizardAddButton.setText("Add..."); //$NON-NLS-1$

		removeButton = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(removeButton);
		removeButton.setText("Remove..."); //$NON-NLS-1$

		GridDataFactory.fillDefaults().grab(true, false).applyTo(buttonComposite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(browseComposite);

		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ScrItemProviderAdapterFactory());
		//adapterFactory.addAdapterFactory(new ConfigurationItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		getViewer().setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		getViewer().setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		getViewer().addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof WrapperItemProvider) {
					WrapperItemProvider wip = (WrapperItemProvider) element;
					Object o = wip.getEditableValue(element);
					return (o instanceof Properties || o instanceof Property);
				}
				return false;
			}
		});

		//toolkit.paintBordersFor(client);

		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);

		getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				_managedForm.fireSelectionChanged(spart, event.getSelection());
				//updateActionsEnablement();
			}

			/*	private void updateActionsEnablement() {
					// if(_managedForm.getMessageManager().)

					if (_component.getVersion() == null) {
						IStructuredSelection sel = ((IStructuredSelection) getViewer().getSelection());
						// the tree root may be a DataList => then disable action
						boolean hasAllSameParent = (!sel.isEmpty()) && sel.toArray()[0] instanceof AbstractData;
						Object o = null;
						int i = 0;
						while (i < sel.size() && hasAllSameParent) {
							AbstractData ad = (AbstractData) sel.toArray()[i];
							if (o == null)
								o = ad.eContainer();
							else {
								hasAllSameParent = (ad.eContainer() == o);
							}
							i++;
						}
						_groupDataAction.setEnabled(hasAllSameParent);

						// set enable the ungroupdataAction when a DataBlock is
						// selected and only one selection and datablock has element
						boolean isDataBlockEnabled = !sel.isEmpty() && sel.getFirstElement() instanceof DataBlock && sel.size() == 1 && ((DataBlock) sel.getFirstElement()).getContents().size() > 0;
						_ungroupDataAction.setEnabled(isDataBlockEnabled);
					}

				}*/

		});

		getViewer().addOpenListener(new IOpenListener() {

			public void open(OpenEvent event) {
				detailsPart.setFocus();
			}
		});

		//createMasterSectionActions(section);
		section.setClient(client);

	}

	private void createMasterSectionActions(Section section) {
		ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
		ToolBar toolbar = toolBarManager.createControl(section);
		final Cursor handCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_HAND);
		toolbar.setCursor(handCursor);
		// Cursor needs to be explicitly disposed
		toolbar.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if ((handCursor != null) && (handCursor.isDisposed() == false)) {
					handCursor.dispose();
				}
			}

		});

		/*		groupDataAction = new Action() {
					@Override
					public void run() {
						IStructuredSelection sel = (IStructuredSelection) getViewer().getSelection();
						Property selectedElement = (Property) sel.getFirstElement();
						Object container = selectedElement.eContainer();

						DataBlock newBlock = NumericalFactory.eINSTANCE.createDataBlock();
						newBlock.setName("bloc_" + System.currentTimeMillis());
						CompoundCommand c = new CompoundCommand();

						// then add them to their new block
						c.append(AddCommand.create(_editingDomain, newBlock, null, sel.toList()));

						// then add the blocl to the case
						// no need to tell the feature to use to perform the "add"; EMF
						// deduces it...
						c.append(AddCommand.create(_editingDomain, container, null, newBlock));

						_editingDomain.getCommandStack().execute(c);

						getViewer().refresh();
						getViewer().setSelection(new StructuredSelection(newBlock), true);
					}

					@Override
					public ImageDescriptor getImageDescriptor() {
						return ImageDescriptor.createFromURL(Activator.getDefault().getBundle().getResource("/icons/group.png"));
					}
				};
				_groupDataAction.setText("Grouper");
				_groupDataAction.setToolTipText("Grouper les données sélectionnées");
				_groupDataAction.setEnabled(false);
				toolBarManager.add(_groupDataAction);

				_ungroupDataAction = new Action() {

					@Override
					public void run() {
						IStructuredSelection sel = (IStructuredSelection) getViewer().getSelection();

						DataBlock block = (DataBlock) sel.getFirstElement();

						// get parent container of the selected object
						Object container = block.eContainer();

						Command c;

						// add all children to the parent container (assuming parent
						// container is DataList of DataBlock)
						if (container instanceof DataList)
							c = AddCommand.create(_editingDomain, container, NumericalPackage.eINSTANCE.getDataList_Data(), block.getContents());
						else
							c = AddCommand.create(_editingDomain, container, NumericalPackage.eINSTANCE.getDataBlock_Contents(), block.getContents());

						_editingDomain.getCommandStack().execute(c);

						getViewer().refresh();
						getViewer().setSelection(new StructuredSelection(container), true);

					}

					@Override
					public ImageDescriptor getImageDescriptor() {
						return ImageDescriptor.createFromURL(Activator.getDefault().getBundle().getResource("/icons/ungroup.png"));
					}

				};
			
				_ungroupDataAction.setText("Degrouper");
				_ungroupDataAction.setToolTipText("Dégrouper le bloc");
				_ungroupDataAction.setEnabled(false);
				toolBarManager.add(_ungroupDataAction);
				*/

		toolBarManager.update(true);
		section.setTextClient(toolbar);

	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		sashForm.setWeights(new int[] {40, 60});

	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(Properties.class, new PropertiesDetailsPart(_managedForm, _editingDomain, _databindingContext));
		detailsPart.registerPage(Property.class, new PropertyDetailsPart(_managedForm, _editingDomain, _databindingContext));

		detailsPart.setPageProvider(this);

	}

	public IDetailsPage getPage(Object key) {
		if (key instanceof Properties) {
			System.out.println("Properties detected");
			return new PropertiesDetailsPart(_managedForm, _editingDomain, _databindingContext);
		}
		if (key instanceof Property) {
			return new PropertyDetailsPart(_managedForm, _editingDomain, _databindingContext);
		}
		return null;
	}

	public Object getPageKey(Object object) {
		if (object instanceof WrapperItemProvider) {
			WrapperItemProvider wip = (WrapperItemProvider) object;
			EObject o = (EObject) wip.getEditableValue(object);
			System.out.println("Key Is : " + o + " class : " + o.eClass());
			return o;
		}
		return null;
	}

	public Button getAddButtonProperty() {
		return addButtonProperty;
	}

	public Button getRemoveButton() {
		return removeButton;
	}

	public Button getAddButtonProperties() {
		return this.addButtonProperties;
	}

	public Button getWizardAddButton() {
		return wizardAddButton;
	}
}
