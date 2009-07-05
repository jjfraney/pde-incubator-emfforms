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
 * $Id: ServicesMasterDetail.java,v 1.2 2009/07/05 20:35:27 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.masterdetail;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.*;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.ds.scr.*;
import org.eclipse.pde.ds.ui.internal.editor.Messages;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.services.ProvideDetailsPart;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.services.ReferenceDetailsPart;
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

public class ServicesMasterDetail extends MasterDetailsBlock implements IDetailsPageProvider {

	private TreeViewer _viewer;
	private EditingDomain _editingDomain;
	private DataBindingContext _databindingContext;
	private IManagedForm _managedForm;

	private Button _btnAddProvided;
	private Button _btnAddRequired;
	private Button _btnRemove;

	public ServicesMasterDetail() {
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

	public void setComponentAndEditingDomain(IObservableValue iObservableValue, AdapterFactory adapterFactory, EditingDomain editingDomain, IEditorSite editorSite, DataBindingContext bindingContext) {
		_editingDomain = editingDomain;
		_databindingContext = bindingContext;

		getViewer().setContentProvider(new AdapterFactoryContentProvider(adapterFactory) {
			@Override
			public Object[] getElements(Object object) {
				List<Object> l = new ArrayList<Object>();
				Object[] elems = super.getElements(object);
				for (Object o : elems)
					l.add(o);
				Service service = ((Component) object).getService();
				if (service != null) {
					Object[] children = super.getChildren(service);
					for (Object o : children) {
						l.add(AdapterFactoryEditingDomain.getWrapper(o, _editingDomain));
					}
				}
				return l.toArray();
			}
		});
		getViewer().setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		getViewer().addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				Object unwrappedElement = AdapterFactoryEditingDomain.unwrap(element);
				return (unwrappedElement instanceof Reference || unwrappedElement instanceof Provide);
			}
		});
		_databindingContext.bindValue(ViewerProperties.input().observe(_viewer), iObservableValue);

		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] {LocalTransfer.getInstance()};
		_viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(_viewer));
		_viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(_editingDomain, _viewer));

		_viewer.expandAll();
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

		_btnAddProvided = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(_btnAddProvided);
		_btnAddProvided.setText("Add Provided Service"); //$NON-NLS-1$

		_btnAddRequired = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(_btnAddRequired);
		_btnAddRequired.setText("Add Required Service"); //$NON-NLS-1$

		_btnRemove = new Button(buttonComposite, SWT.FLAT | SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(_btnRemove);
		_btnRemove.setText("Remove"); //$NON-NLS-1$

		GridDataFactory.fillDefaults().applyTo(buttonComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(browseComposite);

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
		detailsPart.registerPage(Reference.class, new ReferenceDetailsPart(_managedForm, _editingDomain, _databindingContext));
		detailsPart.registerPage(Provide.class, new ProvideDetailsPart(_managedForm, _editingDomain, _databindingContext));

		detailsPart.setPageProvider(this);
	}

	public IDetailsPage getPage(Object key) {
		if (key instanceof Reference) {
			return new ReferenceDetailsPart(_managedForm, _editingDomain, _databindingContext);
		}
		if (key instanceof Provide) {
			return new ProvideDetailsPart(_managedForm, _editingDomain, _databindingContext);
		}
		return null;
	}

	public Object getPageKey(Object object) {
		return AdapterFactoryEditingDomain.unwrap(object);
	}

	public Button getBtnAddProvided() {
		return _btnAddProvided;
	}

	public Button getBtnAddRequired() {
		return _btnAddRequired;
	}

	public Button getBtnRemove() {
		return _btnRemove;
	}
}
