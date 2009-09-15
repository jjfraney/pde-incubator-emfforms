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
 * $Id: PropertiesDetailsPart.java,v 1.3 2009/07/07 09:36:46 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.detailpart.properties;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.PropertiesComposite2;
import org.eclipse.pde.emfforms.editor.EmfDetailsPart;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class PropertiesDetailsPart extends EmfDetailsPart {
	private PropertiesComposite2 propertiesComposite;

	public PropertiesDetailsPart(EmfFormEditor<?> parentEditor) {
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

		propertiesComposite = new PropertiesComposite2(section, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(propertiesComposite);

		getEditor().getToolkit().adapt(propertiesComposite);

		toolkit.adapt(propertiesComposite);
		propertiesComposite.setParent(section);

		section.setClient(propertiesComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);
	}

	protected List<Binding> bind(DataBindingContext bindingContext) {
		List<Binding> bindings = new ArrayList<Binding>();
		// Entry
		bindings.add(bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(propertiesComposite.getTextEntry()), EMFEditProperties.value(getEditingDomain(), ScrPackage.eINSTANCE.getProperties_Entry()).observeDetail(getCurrentSelection()), null, null));
		return bindings;

	}

	public void setFocus() {
		propertiesComposite.getTextEntry().setFocus();
		propertiesComposite.getTextEntry().selectAll();
	}
}
