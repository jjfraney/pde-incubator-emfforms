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
 * $Id: OverviewPage.java,v 1.7 2009/05/29 23:52:32 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.ComponentComposite;
import org.eclipse.pde.ds.ui.internal.editor.composites.OptionsComposite;
import org.eclipse.pde.emfforms.databinding.EMFValidatingUpdateValueStrategy;
import org.eclipse.pde.emfforms.editor.AbstractEmfFormPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.Section;

public class OverviewPage extends AbstractEmfFormPage {

	public final static String ID = "ds.overview"; //$NON-NLS-1$

	// Composites
	private ComponentComposite _componentComposite;
	private OptionsComposite _optionsComposite;

	/**
	 * @param editor
	 */
	public OverviewPage(FormEditor editor) {
		super(editor, 2, false);
	}

	public void bind(DataBindingContext bindingContext) {
		final EditingDomain editingDomain = ((DSEditor) getEditor()).getEditingDomain();

		/**
		 * Bind Component composite
		 */
		// component name
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextName()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Name()), null, null);
		// component impl
		IObservableValue implementationObservable = EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Implementation());
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextImplementation()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, implementationObservable, ScrPackage.eINSTANCE.getImplementation_Class()), new EMFValidatingUpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (((String) value).trim().equals(""))
					return null;
				return super.convert(value);
			}
		}, null);

		// component activate
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextActivate()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Activate()), null, null);

		// component deactivate
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextDeactivate()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Deactivate()), null, null);

		// component modified
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_componentComposite.getTextModified()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Modified()), null, null);

		/**
		 * Bind Options composite
		 */
		// component factory ID
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(_optionsComposite.getTextFactory()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Factory()), null, null);
		// component configuration policy
		bindingContext.bindValue(ViewerProperties.singleSelection().observe(_optionsComposite.getComboViewerConfigurationPolicy()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_ConfigurationPolicy()), null, null);
		// component enablement
		bindingContext.bindValue(WidgetProperties.selection().observe(_optionsComposite.getButtonEnabled()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Enabled()), null, null);
		// component immediacy
		bindingContext.bindValue(WidgetProperties.selection().observe(_optionsComposite.getButtonImmediate()), EMFEditObservables.observeDetailValue(Realm.getDefault(), editingDomain, getObservedValue(), ScrPackage.eINSTANCE.getComponent_Immediate()), null, null);

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
