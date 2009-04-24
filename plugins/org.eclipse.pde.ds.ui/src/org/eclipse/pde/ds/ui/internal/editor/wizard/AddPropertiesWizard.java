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
 * $Id: AddPropertiesWizard.java,v 1.2 2009/04/24 12:16:23 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.wizard;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.pde.ds.ui.internal.editor.DSEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;

public class AddPropertiesWizard extends Wizard {

	private TypeSelectionPage typeSelectionPage;
	private EditingDomain editingDomain;
	private IObservableValue observedValue;
	private NewPropertyPage newPropertyPage;

	private Command command;
	private NewPropertiesPage newPropertiesPage;

	public AddPropertiesWizard(IWorkbenchPage workbenchPage) {
		super();
		this.setWindowTitle("Add Properties Wizard");

		this.editingDomain = ((DSEditor) workbenchPage.getActiveEditor()).getEditingDomain();
		this.observedValue = ((DSEditor) workbenchPage.getActiveEditor()).getInputObservable();

	}

	@Override
	public boolean performFinish() {
		if (this.typeSelectionPage.getType().getCombo().getSelectionIndex() == 0)
			this.newPropertyPage.createProperty();
		else if (this.typeSelectionPage.getType().getCombo().getSelectionIndex() == 1)
			this.newPropertiesPage.createProperties();
		return true;
	}

	@Override
	public void addPages() {
		typeSelectionPage = new TypeSelectionPage();
		this.addPage(typeSelectionPage);
		newPropertyPage = new NewPropertyPage("Create Property");
		this.addPage(newPropertyPage);
		newPropertiesPage = new NewPropertiesPage("Create Properties");
		this.addPage(newPropertiesPage);
	}

	@Override
	public boolean canFinish() {
		return newPropertiesPage.isPageComplete() || newPropertyPage.isPageComplete();
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		this.typeSelectionPage.createControl(pageContainer);
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public IObservableValue getObservedValue() {
		return observedValue;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

}
