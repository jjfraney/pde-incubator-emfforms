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
 * $Id: ProvideDetailsPart.java,v 1.1 2009/07/05 20:22:53 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.detailpart.services;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.ProvideComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class ProvideDetailsPart implements IDetailsPage {

	private ProvideComposite provideComposite;
	private IManagedForm managedForm;
	private EditingDomain editingDomain;
	private DataBindingContext dataBindingContext;
	private IObservableValue currentProperty;

	public ProvideDetailsPart(IManagedForm managedForm, EditingDomain editingDomain, DataBindingContext dataBindingContext) {
		this.managedForm = managedForm;
		this.editingDomain = editingDomain;
		this.dataBindingContext = dataBindingContext;

		currentProperty = new WritableValue();
	}

	public void createContents(Composite parent) {
		GridLayoutFactory.fillDefaults().margins(0, 0).applyTo(parent);

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Details");
		section.marginWidth = 10;
		section.marginHeight = 5;

		provideComposite = new ProvideComposite(section, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(provideComposite);

		managedForm.getToolkit().adapt(provideComposite);

		toolkit.adapt(provideComposite);
		provideComposite.setParent(section);

		section.setClient(provideComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		bind(dataBindingContext);
	}

	protected void bind(DataBindingContext bindingContext) {
		// Interface
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(provideComposite.getTextInterface()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getProvide_Interface()).observeDetail(currentProperty), null, null);
	}

	public void commit(boolean onSave) {
	}

	public void dispose() {
	}

	public void initialize(IManagedForm form) {

	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	public void refresh() {
	}

	public void setFocus() {
		provideComposite.getTextInterface().setFocus();
		provideComposite.getTextInterface().selectAll();
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		Object unwrappedElement = AdapterFactoryEditingDomain.unwrap(sel.getFirstElement());
		currentProperty.setValue(unwrappedElement);
	}

}
