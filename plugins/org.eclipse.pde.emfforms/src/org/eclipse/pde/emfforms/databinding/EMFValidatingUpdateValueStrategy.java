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
 * $Id: EMFValidatingUpdateValueStrategy.java,v 1.5 2009/07/05 16:59:54 bcabe Exp $
 */
package org.eclipse.pde.emfforms.databinding;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

/**
 * {@link UpdateValueStrategy} derived from {@link EMFUpdateValueStrategy}
 * that performs validation as soon as a new value is set
 * 
 */
public class EMFValidatingUpdateValueStrategy extends EMFUpdateValueStrategy {

	public EMFValidatingUpdateValueStrategy() {
		super();
	}

	private static ComposedAdapterFactory _adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	@Override
	protected IStatus doSet(IObservableValue observableValue, Object value) {
		EStructuralFeature eStructuralFeature = null;
		EObject eobject = null;

		IStatus currentstatus = super.doSet(observableValue, value);

		if (observableValue instanceof IObserving) {
			IObserving observing = (IObserving) observableValue;

			if (observing.getObserved() instanceof EObject) {
				eobject = (EObject) observing.getObserved();
				eStructuralFeature = (EStructuralFeature) observableValue.getValueType();

				BasicDiagnostic dc = validate(eobject);
				IStatus ret = getMultiStatusForFeature(dc, eobject, eStructuralFeature, currentstatus);
				return ret;
			}
		}
		// else
		return currentstatus;

	}

	private BasicDiagnostic validate(EObject eobject) {
		// Subclass the default Diagnostician to customize the String
		// representation of each validated EObject
		Diagnostician diagnostician = new Diagnostician() {
			@Override
			public String getObjectLabel(EObject eObject) {
				if (!eObject.eIsProxy()) {
					IItemLabelProvider itemLabelProvider = (IItemLabelProvider) _adapterFactory.adapt(eObject, IItemLabelProvider.class);
					if (itemLabelProvider != null) {
						return itemLabelProvider.getText(eObject);
					}
				}

				return super.getObjectLabel(eObject);
			}
		};

		BasicDiagnostic dc = new BasicDiagnostic();
		diagnostician.validate(eobject, dc);
		return dc;
	}

	private IStatus getMultiStatusForFeature(BasicDiagnostic dc, EObject eobject, EStructuralFeature eStructuralFeature, IStatus currentstatus) {
		MultiStatus multiStatus = new MultiStatus(currentstatus.getPlugin(), currentstatus.getCode(), currentstatus.getMessage(), currentstatus.getException());
		for (Diagnostic d : dc.getChildren()) {
			/**
			 * according to
			 * <code>org.eclipse.emf.common.util.Diagnostic.getData()</code>,
			 * the second element of the array is typically the problematic
			 * feature, and the first one is the object that is the source of
			 * the problem => let's check that!
			 */
			if (d.getData().size() >= 2) {
				if (d.getData().get(0) == eobject && d.getData().get(1) == eStructuralFeature) {
					multiStatus.add(new Status(d.getSeverity(), currentstatus.getPlugin(), d.getCode(), d.getMessage(), d.getException()));
				}
			}
		}

		return multiStatus;
	}
}