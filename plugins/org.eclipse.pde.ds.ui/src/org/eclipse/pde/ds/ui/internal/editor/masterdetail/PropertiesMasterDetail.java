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
 * $Id: PropertiesMasterDetail.java,v 1.13 2009/07/28 16:19:13 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.masterdetail;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.ds.scr.Properties;
import org.eclipse.pde.ds.scr.Property;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.properties.PropertiesDetailsPart;
import org.eclipse.pde.ds.ui.internal.editor.detailpart.properties.PropertyDetailsPart;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.pde.emfforms.editor.EmfMasterDetailBlock;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;

public class PropertiesMasterDetail extends EmfMasterDetailBlock {

	private Button addButtonProperty;
	private Button addButtonProperties;

	public PropertiesMasterDetail(EmfFormEditor<?> editor) {
		super(editor, "Properties");
		useGenericAddButton = false;
	}

	public IDetailsPage getPage(Object key) {
		if (key instanceof Class<?>) {
			if (Properties.class.isAssignableFrom((Class<?>) key)) {
				return new PropertiesDetailsPart(parentEditor);
			}
			if (Property.class.isAssignableFrom((Class<?>) key)) {
				return new PropertyDetailsPart(parentEditor);
			}
		}
		return null;
	}

	@Override
	protected void createCustomAddButtons(Composite parent) {
		addButtonProperty = createButton(parent, "Add Property");
		addButtonProperties = createButton(parent, "Add Properties");
	}

	public Button getAddButtonProperty() {
		return addButtonProperty;
	}

	public Button getAddButtonProperties() {
		return this.addButtonProperties;
	}

	@Override
	protected ViewerFilter getTreeFilter() {
		return new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				Object unwrappedElement = AdapterFactoryEditingDomain.unwrap(element);
				return unwrappedElement instanceof Properties || unwrappedElement instanceof Property;
			}
		};
	}

	@Override
	protected IFilter getCreateSiblingContextMenuFilter() {
		return new IFilter() {

			public boolean select(Object toTest) {
				if (toTest instanceof FeatureMap.Entry) {
					Object val = ((FeatureMap.Entry) toTest).getValue();
					return (val instanceof Property || val instanceof Properties);
				}
				return true;

			}
		};
	}
}
