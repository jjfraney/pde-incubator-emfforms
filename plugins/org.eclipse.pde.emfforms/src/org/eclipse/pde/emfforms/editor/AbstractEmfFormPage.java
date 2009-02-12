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
 * $Id: AbstractEmfFormPage.java,v 1.1 2009/02/12 22:20:32 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.pde.emfforms.internal.editor.MessageManagerListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.Form;

/**
 * Generic page for {@link EObject} edition
 * 
 */
public abstract class AbstractEmfFormPage extends FormPage {
	public static String ID;

	protected DataBindingContext _bindingContext;

	/**
	 * Constructor that creates the page and initializes it with the editor.
	 * 
	 * @param editor
	 *            the parent editor
	 */
	public AbstractEmfFormPage(FormEditor editor) {
		super(editor, "", ""); //$NON-NLS-1$ //$NON-NLS-2$

		this.setPartName(getPageName());
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		Composite body = managedForm.getForm().getBody();

		GridLayout gl = new GridLayout(getNumColumns(), true);
		gl.verticalSpacing = 20;

		createHeader();

		Composite actualContent = null;

		if (!isMasterDetail()) {
			actualContent = this.getEditor().getToolkit().createComposite(body, SWT.NONE);
			GridDataFactory.swtDefaults().span(getNumColumns(), 1).align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(actualContent);
			actualContent.setLayout(gl);
		}

		createContents(actualContent);

		body.setLayout(gl);

		adaptComposites();

		// TODO probably not the best way to do (i.e. having one DBC per page)
		_bindingContext = new EMFDataBindingContext();
		_bindingContext.getValidationStatusProviders().addListChangeListener(new MessageManagerListener(managedForm.getMessageManager()));
		bind(_bindingContext);
	}

	private void createHeader() {
		Form f = this.getManagedForm().getForm().getForm();
		f.setText(this.getPageName());
		this.getEditor().getToolkit().decorateFormHeading(f);
	}

	/**
	 * @return the editor form toolkit
	 */
	protected PDEFormToolkit getFormToolkit() {
		return (PDEFormToolkit) getEditor().getToolkit();
	}

	@Override
	public abstract String getId();

	protected abstract int getNumColumns();

	protected abstract String getPageName();

	protected abstract void createContents(Composite parent);

	protected abstract void adaptComposites();

	protected abstract void bind(DataBindingContext bindingContext);

	protected abstract boolean isMasterDetail();

	protected abstract Viewer getViewer();

}
