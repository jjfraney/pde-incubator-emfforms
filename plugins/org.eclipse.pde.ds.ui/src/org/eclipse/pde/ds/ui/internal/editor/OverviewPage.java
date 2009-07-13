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
 * $Id: OverviewPage.java,v 1.12 2009/07/13 19:46:28 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.ComponentComposite;
import org.eclipse.pde.ds.ui.internal.editor.composites.OptionsComposite;
import org.eclipse.pde.emfforms.editor.AbstractEmfFormPage;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class OverviewPage extends AbstractEmfFormPage {

	public final static String ID = "ds.overview"; //$NON-NLS-1$

	// Composites
	private ComponentComposite _componentComposite;
	private OptionsComposite _optionsComposite;

	/**
	 * @param editor
	 */
	public OverviewPage(EmfFormEditor<?> editor) {
		super(editor, 2, false);
	}

	public void bind(DataBindingContext bindingContext) {
		final EditingDomain editingDomain = ((DSEditor) getEditor()).getEditingDomain();

		/**
		 * Bind Component composite
		 */
		// component name
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextName()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Name()).observeDetail(getObservedValue()), null, null);
		// component impl
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextImplementation()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Implementation()).value(ScrPackage.eINSTANCE.getImplementation_Class()).observeDetail(getObservedValue()), new EMFUpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (((String) value).trim().equals(""))
					return null;
				return super.convert(value);
			}
		}, null);

		// component activate
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextActivate()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Activate()).observeDetail(getObservedValue()), null, null);

		// component deactivate
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextDeactivate()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Deactivate()).observeDetail(getObservedValue()), null, null);

		// component modified
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextModified()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Modified()).observeDetail(getObservedValue()), null, null);

		/**
		 * Bind Options composite
		 */
		// component factory ID
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_optionsComposite.getTextFactory()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Factory()).observeDetail(getObservedValue()), null, null);
		// component configuration policy
		bindingContext.bindValue(ViewerProperties.singleSelection().observe(_optionsComposite.getComboViewerConfigurationPolicy()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_ConfigurationPolicy()).observeDetail(getObservedValue()), null, null);
		// component enablement
		bindingContext.bindValue(WidgetProperties.selection().observe(_optionsComposite.getButtonEnabled()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Enabled()).observeDetail(getObservedValue()), null, null);
		// component immediacy
		bindingContext.bindValue(WidgetProperties.selection().observe(_optionsComposite.getButtonImmediate()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getComponent_Immediate()).observeDetail(getObservedValue()), null, null);

		// perform bindings to get a message manager up to date
		// FIXME this is just not good to do such a thing :)
		bindingContext.updateModels();
		editingDomain.getCommandStack().flush();
	}

	public void createContents(Composite parent) {
		createComponentSection(parent);
		createOptionsSection(parent);

		addToolbarActions();
	}

	private void addToolbarActions() {
		IToolBarManager toolBarManager = getManagedForm().getForm().getToolBarManager();

		// add actions here

		toolBarManager.update(true);
	}

	private void createComponentSection(Composite parent) {
		Section s = getFormToolkit().createSection(parent, Section.TITLE_BAR | Section.DESCRIPTION);
		s.setDescription(Messages.OverviewPage_Component_Section_desc);
		GridDataFactory.fillDefaults().grab(true, false).span(1, 1).applyTo(s);

		s.setText(Messages.OverviewPage_Component_Section);
		_componentComposite = new ComponentComposite(s, SWT.NONE);
		s.setClient(_componentComposite);
	}

	private void createOptionsSection(Composite parent) {
		Section s = getFormToolkit().createSection(parent, Section.TITLE_BAR | Section.DESCRIPTION);
		s.setDescription(Messages.OverviewPage_Options_Section_desc);
		GridDataFactory.fillDefaults().grab(true, false).span(1, 1).applyTo(s);

		s.setText(Messages.OverviewPage_Options_Section);
		_optionsComposite = new OptionsComposite(s, SWT.NONE);
		s.setClient(_optionsComposite);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getPartName() {
		return Messages.OverviewPage_Title;
	}

	@Override
	public Viewer getViewer() {
		return null;
	}

	private IObservableValue getObservedValue() {
		return ((DSEditor) getEditor()).getInputObservable();
	}

}
