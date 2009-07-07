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
 * $Id: ProvideDetailsPart.java,v 1.2 2009/07/07 09:36:47 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.detailpart.services;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.ProvideComposite;
import org.eclipse.pde.emfforms.editor.EmfDetailsPart;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class ProvideDetailsPart extends EmfDetailsPart {

	private ProvideComposite provideComposite;

	public ProvideDetailsPart(EmfFormEditor<?> parentEditor) {
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

		provideComposite = new ProvideComposite(section, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(provideComposite);

		getEditor().getToolkit().adapt(provideComposite);

		toolkit.adapt(provideComposite);
		provideComposite.setParent(section);

		section.setClient(provideComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);
	}

	protected void bind(DataBindingContext bindingContext) {
		// Interface
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(provideComposite.getTextInterface()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getProvide_Interface()).observeDetail(getCurrentSelection()), null, null);
	}

	public void setFocus() {
		provideComposite.getTextInterface().setFocus();
		provideComposite.getTextInterface().selectAll();
	}
}
