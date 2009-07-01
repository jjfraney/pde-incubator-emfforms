package org.eclipse.pde.ds.builder.internal.validation;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.model.IConstraintStatus;

public interface IEnhancedConstraintStatus extends IConstraintStatus {
	EStructuralFeature getResultStructuralFeature();
}
