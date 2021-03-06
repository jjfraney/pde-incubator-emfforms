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
 * $Id: PropertiesPage.java,v 1.13 2009/07/28 16:38:42 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.command.*;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.ds.scr.*;
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

		_propertiesMasterDetail.getAddButtonProperty().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection) _propertiesMasterDetail.getTreeViewer().getSelection()).getFirstElement();
				int idx = CommandParameter.NO_INDEX;
				if (sel != null) {
					Object unwrappedElement = ((IWrapperItemProvider) sel).getValue();
					idx = ((Component) getEditor().getInputObservable().getValue()).getAllProperties().indexOf(unwrappedElement);
				}

				Property p = ScrFactory.eINSTANCE.createProperty();
				Command command = AddCommand.create(editingDomain, getEditor().getInputObservable().getValue(), ScrPackage.Literals.COMPONENT__ALL_PROPERTIES, FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTY, p), idx);
				editingDomain.getCommandStack().execute(command);

				getViewer().setSelection(new StructuredSelection(AdapterFactoryEditingDomain.getWrapper(p, editingDomain)), true);
			}
		});

		_propertiesMasterDetail.getAddButtonProperties().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection) _propertiesMasterDetail.getTreeViewer().getSelection()).getFirstElement();
				int idx = CommandParameter.NO_INDEX;
				if (sel != null) {
					Object unwrappedElement = ((IWrapperItemProvider) sel).getValue();
					idx = ((Component) getEditor().getInputObservable().getValue()).getAllProperties().indexOf(unwrappedElement);
				}

				Properties p = ScrFactory.eINSTANCE.createProperties();
				Command command = AddCommand.create(editingDomain, getEditor().getInputObservable().getValue(), ScrPackage.Literals.COMPONENT__ALL_PROPERTIES, FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTIES, p), idx);
				editingDomain.getCommandStack().execute(command);

				getViewer().setSelection(new StructuredSelection(AdapterFactoryEditingDomain.getWrapper(p, editingDomain)), true);
			}
		});

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

}
