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
 * $Id: EmfDetailsPart.java,v 1.6 2009/08/07 10:33:03 bcabe Exp $
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

	private Composite _mainDetailComposite;
	private EmfFormEditor<?> _parentEditor;
	private IObservableValue _selectedObject;

	public EmfDetailsPart(EmfFormEditor<?> parentEditor) {
		_parentEditor = parentEditor;
		_selectedObject = new WritableValue();
	}

	final public void createContents(Composite parent) {

		GridLayoutFactory.fillDefaults().margins(5, 5).applyTo(parent);

		_mainDetailComposite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(_mainDetailComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(_mainDetailComposite);

		createSpecificContent(_mainDetailComposite);

		getEditor().getToolkit().adapt(_mainDetailComposite);

		bind(getEditor().getDataBindingContext());
	}

	protected abstract void createSpecificContent(Composite parent);

	protected abstract void bind(DataBindingContext dataBindingContext);

	public void commit(boolean onSave) {
		// nothing
	}

	public void dispose() {
		// nothing
	}

	public void initialize(IManagedForm form) {
		// nothing
	}

	public boolean isDirty() {
		// TODO propose a better impl??
		return false;
	}

	public boolean isStale() {
		// TODO propose a better impl??
		return false;
	}

	public void refresh() {
	}

	public void setFocus() {
		_mainDetailComposite.setFocus();
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		if (!sel.isEmpty()) {
			getCurrentSelection().setValue(AdapterFactoryEditingDomain.unwrap(sel.getFirstElement()));
			getEditor().validate();
		}
	}

	protected EditingDomain getEditingDomain() {
		return getEditor().getEditingDomain();
	}

	protected FormToolkit getToolkit() {
		return getEditor().getToolkit();
	}

	public IObservableValue getCurrentSelection() {
		return _selectedObject;
	}

	public EmfFormEditor<?> getEditor() {
		return _parentEditor;
	}

}
