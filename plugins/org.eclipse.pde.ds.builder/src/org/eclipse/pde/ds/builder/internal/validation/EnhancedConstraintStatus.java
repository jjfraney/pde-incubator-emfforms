package org.eclipse.pde.ds.builder.internal.validation;

import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.IModelConstraint;

public class EnhancedConstraintStatus extends ConstraintStatus implements
		IEnhancedConstraintStatus {

	private EStructuralFeature _resultStructuralFeature;

	public EnhancedConstraintStatus(IModelConstraint constraint,
			EObject target, int severity, int code, String message,
			Set<? extends EObject> resultLocus) {
		super(constraint, target, severity, code, message, resultLocus);
	}

	public void setResultStructuralFeature(
			EStructuralFeature resultStructuralFeature) {
		_resultStructuralFeature = resultStructuralFeature;
	}

	public EStructuralFeature getResultStructuralFeature() {
		return this._resultStructuralFeature;
	}

}
