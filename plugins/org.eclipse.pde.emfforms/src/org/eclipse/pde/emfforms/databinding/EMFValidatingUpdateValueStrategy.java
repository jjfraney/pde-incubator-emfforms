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
 * $Id: EMFValidatingUpdateValueStrategy.java,v 1.1 2009/02/12 22:20:33 bcabe Exp $
 */
package org.eclipse.pde.emfforms.databinding;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;

/**
 * {@link UpdateValueStrategy} derived from {@link EMFUpdateValueStrategy}
 * that performs validation as soon as a new value is set
 * 
 */
public class EMFValidatingUpdateValueStrategy extends EMFUpdateValueStrategy {

	@Override
	protected IStatus doSet(IObservableValue observableValue, Object value) {
		EStructuralFeature eStructuralFeature = null;
		EObject eobject = null;

		IStatus currentstatus = super.doSet(observableValue, value);

		if (observableValue instanceof EObjectObservableValue) {

			EObjectObservableValue eObjectObservableValue = (EObjectObservableValue) observableValue;
			eobject = (EObject) eObjectObservableValue.getObserved();
			eStructuralFeature = (EStructuralFeature) eObjectObservableValue.getValueType();

			BasicDiagnostic dc = new BasicDiagnostic();
			Diagnostician.INSTANCE.validate(eobject, dc);
			IStatus ret = getMultiStatusForFeature(dc, eStructuralFeature, currentstatus);
			return ret;
		}
		// else
		return currentstatus;
	}

	private IStatus getMultiStatusForFeature(BasicDiagnostic dc, EStructuralFeature eStructuralFeature, IStatus currentstatus) {
		MultiStatus multiStatus = new MultiStatus(currentstatus.getPlugin(), currentstatus.getCode(), currentstatus.getMessage(), currentstatus.getException());
		for (Diagnostic d : dc.getChildren()) {
			/**
			 * according to
			 * <code>org.eclipse.emf.common.util.Diagnostic.getData()</code>,
			 * the second element of the array is typically the problematic
			 * feature
			 */
			if (d.getData().size() >= 2) {
				if (d.getData().get(1) == eStructuralFeature) {
					multiStatus.add(new Status(d.getSeverity(), currentstatus.getPlugin(), d.getCode(), d.getMessage(), d.getException()));
				}
			}
		}

		return multiStatus;
	}
}