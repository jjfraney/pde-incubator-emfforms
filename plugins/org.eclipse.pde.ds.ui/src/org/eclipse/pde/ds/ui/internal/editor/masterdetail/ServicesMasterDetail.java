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
 * $Id: ServicesMasterDetail.java,v 1.5 2009/07/13 19:45:41 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.masterdetail;

import org.eclipse.pde.ds.scr.Properties;

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
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.ds.scr.*;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.services.ProvideDetailsPart;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.services.ReferenceDetailsPart;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.pde.emfforms.editor.EmfMasterDetailBlock;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IDetailsPage;

public class ServicesMasterDetail extends EmfMasterDetailBlock {

	private TreeViewer _viewer;
	private EditingDomain _editingDomain;
	private DataBindingContext _databindingContext;

	private Button _btnAddProvided;
	private Button _btnAddRequired;

	public ServicesMasterDetail(EmfFormEditor<?> editor) {
		super(editor, "Services");
	}

	public void setComponentAndEditingDomain(IObservableValue iObservableValue, AdapterFactory adapterFactory, EditingDomain editingDomain, IEditorSite editorSite, DataBindingContext bindingContext) {
		_editingDomain = editingDomain;
		_databindingContext = bindingContext;

		getTreeViewer().setContentProvider(new AdapterFactoryContentProvider(adapterFactory) {
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
		getTreeViewer().setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		getTreeViewer().addFilter(new ViewerFilter() {
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

	public IDetailsPage getPage(Object key) {
		if (key instanceof Class<?>) {
			if (Reference.class.isAssignableFrom((Class<?>) key)) {
			return new ReferenceDetailsPart(parentEditor);
		}
			if (Provide.class.isAssignableFrom((Class<?>) key)) {
						return new ProvideDetailsPart(parentEditor);
		}
		}		return null;
	}

	public Button getBtnAddProvided() {
		return _btnAddProvided;
	}

	public Button getBtnAddRequired() {
		return _btnAddRequired;
	}

	@Override
	protected IFilter getContextMenuFilter() {
		return new IFilter() {
			public boolean select(Object toTest) {
				return true;
			}
		};
	}

	@Override
	protected ViewerFilter getTreeFilter() {
		return new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				Object unwrappedElement = AdapterFactoryEditingDomain.unwrap(element);
				return (unwrappedElement instanceof Reference || unwrappedElement instanceof Provide);
			}
		};
	}
}
