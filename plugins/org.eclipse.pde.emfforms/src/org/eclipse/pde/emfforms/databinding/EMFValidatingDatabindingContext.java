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
 * $Id: EMFValidatingDatabindingContext.java,v 1.1 2009/07/05 20:21:49 bcabe Exp $
 */
package org.eclipse.pde.emfforms.databinding;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;

/**
 * A specialized {@link EMFDataBindingContext} intended to automatically perform EMF Validation as part of its model=>target {@link UpdateValueStrategy}
 */
public class EMFValidatingDatabindingContext extends EMFDataBindingContext {
	@Override
	protected UpdateValueStrategy createTargetToModelUpdateValueStrategy(IObservableValue fromValue, IObservableValue toValue) {
		return new EMFValidatingUpdateValueStrategy();
	}
}
