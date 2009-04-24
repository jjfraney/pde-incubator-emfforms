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
 * $Id: PropertiesMasterDetail.java,v 1.4 2009/04/24 22:01:01 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.masterdetail;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.*;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.dnd.*;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.ds.scr.*;
import org.eclipse.pde.ds.scr.provider.ScrItemProviderAdapterFactory;
import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.PropertiesDetailsPart;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.PropertyDetailsPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.*;
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

	@Override
	public void createContent(IManagedForm managedForm) {
		super.createContent(managedForm);
		sashForm.setLayout(new FillLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(sashForm);
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
		section.setDescription("Select the property/properties you want to edit"); //$NON-NLS-1$
		section.marginWidth = 10;
		section.setLayout(new FillLayout());
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(client);

		Composite browseComposite = new Composite(client, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(browseComposite);

		FilteredTree ft = new FilteredTree(browseComposite, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);
		_viewer = ft.getViewer();

		GridDataFactory.fillDefaults().grab(true, true).span(1, 2).applyTo(_viewer.getControl());

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

		GridDataFactory.fillDefaults().applyTo(buttonComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(browseComposite);

		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ScrItemProviderAdapterFactory());
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
				updateActionsEnablement();
			}

			private void updateActionsEnablement() {
			}

		});

		getViewer().addOpenListener(new IOpenListener() {

			public void open(OpenEvent event) {
				detailsPart.setFocus();
			}
		});

		//createMasterSectionActions(section);
		section.setClient(client);

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
