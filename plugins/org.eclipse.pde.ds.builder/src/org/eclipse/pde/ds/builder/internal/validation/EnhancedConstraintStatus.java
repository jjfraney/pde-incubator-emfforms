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
 * $Id: EnhancedConstraintStatus.java,v 1.2 2009/07/02 09:34:02 bcabe Exp $
 */
 package org.eclipse.pde.ds.builder.internal.validation;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.model.ConstraintStatus;

public class EnhancedConstraintStatus extends ConstraintStatus implements
		IEnhancedConstraintStatus {

	private EStructuralFeature _resultStructuralFeature;

	public EnhancedConstraintStatus(ConstraintStatus status,
			EStructuralFeature resultStructuralFeature) {
		super(status.getConstraint(), status.getTarget(), status.getMessage(),
				status.getResultLocus());
		_resultStructuralFeature = resultStructuralFeature;
	}

	public EStructuralFeature getResultStructuralFeature() {
		return this._resultStructuralFeature;
	}

}
