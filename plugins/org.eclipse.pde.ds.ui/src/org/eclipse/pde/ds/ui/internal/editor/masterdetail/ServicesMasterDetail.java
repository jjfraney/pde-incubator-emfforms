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
 * $Id: ServicesMasterDetail.java,v 1.11 2009/09/11 22:08:47 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.masterdetail;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.pde.ds.scr.*;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.services.ProvideDetailsPart;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.services.ReferenceDetailsPart;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.pde.emfforms.editor.EmfMasterDetailBlock;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;

public class ServicesMasterDetail extends EmfMasterDetailBlock {
	private Button _btnAddProvided;
	private Button _btnAddRequired;

	public ServicesMasterDetail(EmfFormEditor<?> editor) {
		super(editor, "Services", EmfMasterDetailBlock.USE_CUSTOM_PUSH_BUTTONS);
	}

	@Override
	protected void createMasterPart(IManagedForm managedForm, Composite parent) {
		super.createMasterPart(managedForm, parent);
		// use a custom content provider because we want to display both Provide and Reference elements
		getTreeViewer().setContentProvider(new AdapterFactoryContentProvider(parentEditor.getAdapterFactory()) {
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
						l.add(AdapterFactoryEditingDomain.getWrapper(o, parentEditor.getEditingDomain()));
					}
				}
				return l.toArray();
			}
		});
	}

	public IDetailsPage getPage(Object key) {
		if (key instanceof Class<?>) {
			if (Reference.class.isAssignableFrom((Class<?>) key)) {
				return new ReferenceDetailsPart(parentEditor);
			}
			if (Provide.class.isAssignableFrom((Class<?>) key)) {
				return new ProvideDetailsPart(parentEditor);
			}
		}
		return null;
	}

	@Override
	protected void createCustomButtons(Composite parent) {
		_btnAddRequired = createButton(parent, "Add Reference");
		_btnAddProvided = createButton(parent, "Add Provided");
		setRemoveButton(createButton(parent, "Remove"));
	}

	public Button getBtnAddProvided() {
		return _btnAddProvided;
	}

	public Button getBtnAddRequired() {
		return _btnAddRequired;
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
