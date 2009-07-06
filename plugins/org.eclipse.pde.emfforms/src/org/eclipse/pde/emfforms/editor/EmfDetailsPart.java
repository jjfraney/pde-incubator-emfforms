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
 * $Id: EmfDetailsPart.java,v 1.2 2009/07/06 21:08:13 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class EmfDetailsPart implements IDetailsPage {

	private Composite mainDetailComposite;
	protected EmfFormEditor<?> parentEditor;
	protected IObservableValue selectedObject;

	public EmfDetailsPart(EmfFormEditor<?> parentEditor) {
		this.parentEditor = parentEditor;
		selectedObject = new WritableValue();
	}

	final public void createContents(Composite parent) {

		GridLayoutFactory.fillDefaults().margins(10, 5).applyTo(parent);

		mainDetailComposite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(mainDetailComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(mainDetailComposite);

		createSpecificContent(mainDetailComposite);

		parentEditor.getToolkit().adapt(mainDetailComposite);

		bind(parentEditor.getDataBindingContext());
	}

	protected abstract void createSpecificContent(Composite parent);

	protected abstract void bind(DataBindingContext dataBindingContext);

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
		mainDetailComposite.setFocus();
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		if (!sel.isEmpty())
			selectedObject.setValue(AdapterFactoryEditingDomain.unwrap(sel.getFirstElement()));
	}

	public EditingDomain getEditingDomain() {
		return parentEditor.getEditingDomain();
	}

	public FormToolkit getToolkit() {
		return parentEditor.getToolkit();
	}

}
