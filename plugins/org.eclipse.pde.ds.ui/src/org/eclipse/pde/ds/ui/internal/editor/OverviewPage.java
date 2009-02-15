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
 * $Id: OverviewPage.java,v 1.2 2009/02/15 00:42:33 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.Properties;
import org.eclipse.pde.ds.scr.Property;
import org.eclipse.pde.ds.scr.ScrFactory;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.ComponentComposite;
import org.eclipse.pde.ds.ui.internal.editor.composites.OptionsComposite;
import org.eclipse.pde.ds.ui.internal.editor.composites.PropertiesComposite;
import org.eclipse.pde.emfforms.databinding.EMFValidatingUpdateValueStrategy;
import org.eclipse.pde.emfforms.editor.AbstractEmfFormPage;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.Section;

public class OverviewPage extends AbstractEmfFormPage {

	public final static String ID = "ds.overview"; //$NON-NLS-1$

	// Composites
	private ComponentComposite _componentComposite;
	private OptionsComposite _optionsComposite;
	private PropertiesComposite _propertiesComposite;

	/**
	 * @param editor
	 */
	public OverviewPage(FormEditor editor) {
		super(editor);
	}

	@Override
	protected void adaptComposites() {
		getFormToolkit().adapt(_componentComposite);
		getFormToolkit().adapt(_optionsComposite);
		getFormToolkit().adapt(_propertiesComposite);
	}

	@Override
	protected void bind(DataBindingContext bindingContext) {
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

		/**
		 * Bind Properties composite
		 */
		final StructuredViewer listViewer = _propertiesComposite.getPropertiesViewer().left;
		listViewer.setContentProvider(new AdapterFactoryContentProvider(((EmfFormEditor<?>) getEditor()).getAdapterFactory()));
		listViewer.setLabelProvider(new AdapterFactoryLabelProvider(((EmfFormEditor<?>) getEditor()).getAdapterFactory()));
		listViewer.setInput(getObservedValue().getValue());
		// FIXME this is not very clean to need to observe the observable...
		getObservedValue().addChangeListener(new IChangeListener() {
			public void handleChange(ChangeEvent event) {
				listViewer.setInput(getObservedValue().getValue());
			}
		});

		// install D&D support
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] {LocalTransfer.getInstance()};
		listViewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(listViewer));
		listViewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(editingDomain, listViewer));
		// only display properties...
		listViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof WrapperItemProvider) {
					WrapperItemProvider wip = (WrapperItemProvider) element;
					Object o = wip.getEditableValue(element);
					return (o instanceof Property || o instanceof Properties);
				}
				return false;
			}
		});

		_propertiesComposite.getPropertiesViewer().right.left.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection) listViewer.getSelection()).getFirstElement();
				int idx = CommandParameter.NO_INDEX;
				if (sel != null) {
					if (sel instanceof WrapperItemProvider) {
						WrapperItemProvider wip = (WrapperItemProvider) sel;
						idx = ((Component) getObservedValue().getValue()).getAllProperties().indexOf(wip.getValue());
					}
				}
				Command c = null;
				if (System.currentTimeMillis() % 2 == 0) {
					Property p = ScrFactory.eINSTANCE.createProperty();
					p.setName("property" + System.currentTimeMillis()); //$NON-NLS-1$
					c = AddCommand.create(editingDomain, getObservedValue().getValue(), null, FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTY, p), idx);
				} else {
					Properties p = ScrFactory.eINSTANCE.createProperties();
					p.setEntry("/foo/bar/" + System.currentTimeMillis()); //$NON-NLS-1$
					c = AddCommand.create(editingDomain, getObservedValue().getValue(), null, FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTIES, p), idx);
				}
				editingDomain.getCommandStack().execute(c);
			}

		});

		_propertiesComposite.getPropertiesViewer().right.right.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection) listViewer.getSelection()).getFirstElement();
				if (sel != null) {
					Command c = DeleteCommand.create(editingDomain, sel);
					editingDomain.getCommandStack().execute(c);
				}
			}

		});

		// perform bindings to get a message manager up to date
		// FIXME this is just not good to do such a thing :)
		bindingContext.updateModels();
		editingDomain.getCommandStack().flush();
	}

	@Override
	protected void createContents(Composite parent) {
		createComponentSection(parent);
		createOptionsSection(parent);
		createPropertiesSection(parent);

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

	private void createPropertiesSection(Composite parent) {
		Section s = getFormToolkit().createSection(parent, Section.TITLE_BAR | Section.DESCRIPTION | Section.TWISTIE);
		s.setDescription(Messages.OverviewPage_Properties_Section_desc);
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1).applyTo(s);

		s.setText(Messages.OverviewPage_Properties_Section);
		_propertiesComposite = new PropertiesComposite(s, SWT.NONE);
		s.setClient(_propertiesComposite);
		s.setExpanded(false);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	protected int getNumColumns() {
		return 2;
	}

	@Override
	protected String getPageName() {
		return Messages.OverviewPage_Title;
	}

	@Override
	protected boolean isMasterDetail() {
		return false;
	}

	@Override
	public Viewer getViewer() {
		return null;
	}

	private IObservableValue getObservedValue() {
		return ((DSEditor) getEditor()).getInputObservable();
	}

}
