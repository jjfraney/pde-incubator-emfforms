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
 * $Id: PropertiesPage.java,v 1.10 2009/07/07 09:36:47 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.pde.ds.ui.internal.editor.masterdetail.PropertiesMasterDetail;
import org.eclipse.pde.emfforms.editor.AbstractEmfFormPage;
import org.eclipse.pde.emfforms.editor.EmfFormEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

public class PropertiesPage extends AbstractEmfFormPage {

	private PropertiesMasterDetail _propertiesMasterDetail;

	public final static String ID = "ds.properties"; //$NON-NLS-1$

	public PropertiesPage(EmfFormEditor<?> editor) {
		super(editor, 1, true);
	}

	public void bind(DataBindingContext bindingContext) {
		final EditingDomain editingDomain = ((DSEditor) getEditor()).getEditingDomain();

		bindingContext.bindValue(ViewerProperties.input().observe(_propertiesMasterDetail.getTreeViewer()), getEditor().getInputObservable());

		/*
				_propertiesMasterDetail.getAddButtonProperty().addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						Object sel = ((IStructuredSelection) _propertiesMasterDetail.getTreeViewer().getSelection()).getFirstElement();
						int idx = CommandParameter.NO_INDEX;
						if (sel != null) {
							Object unwrappedElement = AdapterFactoryEditingDomain.unwrap(sel);
							idx = ((Component) getObservedValue().getValue()).getAllProperties().indexOf(unwrappedElement);
						}

						Property p = ScrFactory.eINSTANCE.createProperty();
						p.setName("property" + System.currentTimeMillis()); //$NON-NLS-1$
						Entry entryP = FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTY, p);
						Command command = AddCommand.create(editingDomain, getObservedValue().getValue(), null, entryP, idx);
						editingDomain.getCommandStack().execute(command);

						getViewer().setSelection(new StructuredSelection(AdapterFactoryEditingDomain.getWrapper(p, editingDomain)), true);
					}
				});

				_propertiesMasterDetail.getAddButtonProperties().addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						Properties p = ScrFactory.eINSTANCE.createProperties();
						p.setEntry("properties" + System.currentTimeMillis()); //$NON-NLS-1$
						Command command = AddCommand.create(editingDomain, getObservedValue().getValue(), null, FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTIES, p), 0);
						editingDomain.getCommandStack().execute(command);

						getViewer().setSelection(new StructuredSelection(AdapterFactoryEditingDomain.getWrapper(p, editingDomain)), true);
					}
				});
		*/
		_propertiesMasterDetail.getRemoveButton().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection) _propertiesMasterDetail.getTreeViewer().getSelection()).getFirstElement();
				if (sel != null) {
					Command c = DeleteCommand.create(editingDomain, sel);
					editingDomain.getCommandStack().execute(c);
				}
			}
		});

	}

	public void createContents(Composite parent) {
		createDataMasterDetailSection(parent);
	}

	private void createDataMasterDetailSection(Composite parent) {
		_propertiesMasterDetail = new PropertiesMasterDetail(getEditor());
		_propertiesMasterDetail.createContent(this.getManagedForm());
		// it is bad to manipulate editor here, but to manage Cut/Copy/Paste,
		// the editor shall add a listener the viewer, and this is a way for him
		// to know that viewer exists.
		((DSEditor) getEditor()).addViewerToListenTo(_propertiesMasterDetail.getTreeViewer());
	}

	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			// force the selection, to avoid a bug on the ContextMenu (on tab
			// changed, display menu was unconsistent)
			IStructuredSelection selection = (IStructuredSelection) _propertiesMasterDetail.getTreeViewer().getSelection();
			_propertiesMasterDetail.getTreeViewer().setSelection(selection);
			_propertiesMasterDetail.getTreeViewer().refresh();
		}
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getPartName() {
		return Messages.PropertyPage_Title;
	}

	@Override
	public Viewer getViewer() {
		return _propertiesMasterDetail.getTreeViewer();
	}

	private IObservableValue getObservedValue() {
		return ((DSEditor) getEditor()).getInputObservable();
	}

}
