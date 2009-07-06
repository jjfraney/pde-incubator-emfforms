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
 * $Id: PropertyDetailsPart.java,v 1.2 2009/07/06 21:08:14 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.detailpart.properties;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.pde.ds.scr.JavaType;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.PropertyComposite;
import org.eclipse.pde.emfforms.editor.EmfDetailsPart;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class PropertyDetailsPart extends EmfDetailsPart {

	private PropertyComposite propertyComposite;

	public PropertyDetailsPart(EmfFormEditor<?> parentEditor) {
		super(parentEditor);
	}

	@Override
	protected void createSpecificContent(Composite parent) {
		GridLayoutFactory.fillDefaults().margins(0, 0).applyTo(parent);

		FormToolkit toolkit = parentEditor.getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Details");
		section.marginWidth = 10;
		section.marginHeight = 5;

		propertyComposite = new PropertyComposite(section, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(propertyComposite);

		parentEditor.getToolkit().adapt(propertyComposite);

		toolkit.adapt(propertyComposite);
		propertyComposite.setParent(section);

		section.setClient(propertyComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		propertyComposite.getComboType().setInput(JavaType.values());
	}

	protected void bind(DataBindingContext bindingContext) {
		// Name
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(propertyComposite.getTextName()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getProperty_Name()).observeDetail(selectedObject), null, null);

		// Type
		bindingContext.bindValue(ViewersObservables.observeSingleSelection(propertyComposite.getComboType()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getProperty_Type()).observeDetail(selectedObject), null, null);

		//Value
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(propertyComposite.getTextValue()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getProperty_Value()).observeDetail(selectedObject), null, null);

	}

	public void setFocus() {
		propertyComposite.getTextName().setFocus();
		propertyComposite.getTextName().selectAll();
	}

}
