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
 * $Id: AbstractEmfFormPage.java,v 1.3 2009/04/24 12:16:08 bcabe Exp $
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
public abstract class AbstractEmfFormPage extends FormPage implements IEmfFormPage {
	public static String ID;

	protected DataBindingContext _bindingContext;

	private int numColumns = 1;

	private boolean isMasterDetail = false;

	/**
	 * Constructor that creates the page and initializes it with the editor.
	 * By default the page contains only one column.
	 * 
	 * @param editor
	 *            the parent editor
	 */
	public AbstractEmfFormPage(FormEditor editor) {
		super(editor, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
		this.setPartName(getPartName());
	}

	/**
	 * Constructor that creates the page and initializes it with the editor.
	 * Allows to customize the number of columns, and to indicate that the content will consist in a MasterDetailsBlock 
	 * 
	 * @param editor
	 * @param numColumns
	 * @param isMasterDetail
	 */
	public AbstractEmfFormPage(FormEditor editor, int numColumns, boolean isMasterDetail) {
		this(editor);
		this.numColumns = numColumns;
		this.isMasterDetail = isMasterDetail;
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

		getFormToolkit().adapt(actualContent);

		// TODO probably not the best way to do (i.e. having one DBC per page)
		_bindingContext = new EMFDataBindingContext();
		_bindingContext.getValidationStatusProviders().addListChangeListener(new MessageManagerListener(managedForm.getMessageManager()));
		bind(_bindingContext);
	}

	private void createHeader() {
		Form f = this.getManagedForm().getForm().getForm();
		f.setText(this.getPartName());
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

	/**
	 * {@inheritDoc}
	 */
	public int getNumColumns() {
		return numColumns;
	}

	public boolean isMasterDetail() {
		return isMasterDetail;
	}

	/**
	 * {@inheritDoc} <br><br>
	 * The default implementation of this method just returns <code>null</code>.
	 * It must be overriden if the actual page contains an interesting viewer.
	 */
	public Viewer getViewer() {
		return null;
	}

}
