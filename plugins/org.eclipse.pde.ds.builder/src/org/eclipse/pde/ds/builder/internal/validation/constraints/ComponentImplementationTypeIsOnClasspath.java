package org.eclipse.pde.ds.builder.internal.validation.constraints;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.jdt.core.*;
import org.eclipse.pde.ds.builder.internal.validation.EnhancedConstraintStatus;
import org.eclipse.pde.ds.scr.Implementation;
import org.eclipse.pde.ds.scr.ScrPackage;

public class ComponentImplementationTypeIsOnClasspath extends
		AbstractModelConstraint {

	public ComponentImplementationTypeIsOnClasspath() {
	}

	@Override
	public IStatus validate(IValidationContext ctx) {
		Implementation impl = (Implementation) ctx.getTarget();
		IJavaProject project = JavaCore.create(ResourcesPlugin.getWorkspace()
				.getRoot().getProject("/a"));
		IType type;
		try {
			type = project.findType(impl.getClass_());
			if (type == null || !type.exists()) {
				ConstraintStatus s = (ConstraintStatus) ctx
						.createFailureStatus(impl.getClass_());
				EnhancedConstraintStatus enhancedStatus = new EnhancedConstraintStatus(
						s.getConstraint(), s.getTarget(), s.getSeverity(), s
								.getCode(), s.getMessage(), s.getResultLocus());
				enhancedStatus
						.setResultStructuralFeature(ScrPackage.Literals.IMPLEMENTATION__CLASS);
				return enhancedStatus;
			} else
				return ctx.createSuccessStatus();
		} catch (JavaModelException e) {
			return ctx.createFailureStatus(impl);
		}

	}

}
