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
 * $Id: NewPropertyPage.java,v 1.8 2009/07/05 20:22:53 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.wizard;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.ds.scr.*;
import org.eclipse.pde.ds.ui.internal.editor.composites.PropertyComposite;
import org.eclipse.pde.emfforms.databinding.EMFValidatingUpdateValueStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NewPropertyPage extends WizardPage {

	private PropertyComposite propertyComposite;
	private Property p;

	protected NewPropertyPage(String pageName) {
		super(pageName);
		setPageComplete(false);
	}

	public void createControl(Composite parent) {

		propertyComposite = new PropertyComposite(parent, SWT.NULL);

		EditingDomain ed = ((AddPropertiesWizard) getWizard()).getEditingDomain();

		p = ScrFactory.eINSTANCE.createProperty();
		setControl(propertyComposite);

		//binding
		DataBindingContext bindingContext = new DataBindingContext();
		WizardPageSupport.create(this, bindingContext);

		IObservableValue iov = new WritableValue();
		iov.setValue(p);
		//Name
		bindingContext.bindValue(SWTObservables.observeText(propertyComposite.getTextName(), SWT.Modify), EMFEditProperties.value(ed, ScrPackage.eINSTANCE.getProperty_Name()).observeDetail(iov), new EMFValidatingUpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value != null && !"".equals((String) value)) //$NON-NLS-1$
					setPageComplete(true);
				return super.convert(value);
			}
		}, null);

		//Value
		bindingContext.bindValue(SWTObservables.observeText(propertyComposite.getTextValue(), SWT.FocusOut), EMFEditProperties.value(ed, ScrPackage.eINSTANCE.getProperty_Value()).observeDetail(iov), null, null);
	}

	public void createProperty() {

		EditingDomain ed = ((AddPropertiesWizard) getWizard()).getEditingDomain();
		IObservableValue ov = ((AddPropertiesWizard) getWizard()).getObservedValue();

		Entry entryP = FeatureMapUtil.createEntry(ScrPackage.Literals.COMPONENT__PROPERTY, p);
		Command c = AddCommand.create(ed, ov.getValue(), null, entryP, CommandParameter.NO_INDEX);
		ed.getCommandStack().execute(c);

	}

	@Override
	public IWizardPage getNextPage() {
		return null;
	}

}
