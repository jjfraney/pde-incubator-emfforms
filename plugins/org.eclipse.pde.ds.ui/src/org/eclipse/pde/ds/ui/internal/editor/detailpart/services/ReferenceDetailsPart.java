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
 * $Id: ReferenceDetailsPart.java,v 1.3 2009/07/07 09:36:47 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.detailpart.services;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.pde.ds.scr.*;
import org.eclipse.pde.ds.ui.internal.editor.composites.ReferenceComposite;
import org.eclipse.pde.emfforms.editor.EmfDetailsPart;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class ReferenceDetailsPart extends EmfDetailsPart {

	private ReferenceComposite referenceComposite;

	public ReferenceDetailsPart(EmfFormEditor<?> parentEditor) {
		super(parentEditor);
	}

	@Override
	protected void createSpecificContent(Composite parent) {
		GridLayoutFactory.fillDefaults().margins(0, 0).applyTo(parent);

		FormToolkit toolkit = getEditor().getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Details");
		section.marginWidth = 10;
		section.marginHeight = 5;

		referenceComposite = new ReferenceComposite(section, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(referenceComposite);

		getEditor().getToolkit().adapt(referenceComposite);

		toolkit.adapt(referenceComposite);
		referenceComposite.setParent(section);

		section.setClient(referenceComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		referenceComposite.getComboCardinality().setInput(Cardinality.values());
		referenceComposite.getComboPolicy().setInput(Policy.values());
	}

	protected void bind(DataBindingContext bindingContext) {
		// Name
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(referenceComposite.getTextName()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getReference_Name()).observeDetail(getCurrentSelection()), null, null);

		// Interface
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(referenceComposite.getTextInterface()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getReference_Interface()).observeDetail(getCurrentSelection()), null, null);

		// Cardinality
		bindingContext.bindValue(ViewerProperties.singleSelection().observe(referenceComposite.getComboCardinality()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getReference_Cardinality()).observeDetail(getCurrentSelection()), null, null);

		// Policy
		bindingContext.bindValue(ViewerProperties.singleSelection().observe(referenceComposite.getComboPolicy()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getReference_Policy()).observeDetail(getCurrentSelection()), null, null);

		// Target
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(referenceComposite.getTextTarget()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getReference_Target()).observeDetail(getCurrentSelection()), null, null);

		// Bind
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(referenceComposite.getTextBind()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getReference_Bind()).observeDetail(getCurrentSelection()), null, null);

		// Unbind
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(referenceComposite.getTextUnbind()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getReference_Unbind()).observeDetail(getCurrentSelection()), null, null);

	}

	public void setFocus() {
		referenceComposite.getTextName().setFocus();
		referenceComposite.getTextName().selectAll();
	}

}
