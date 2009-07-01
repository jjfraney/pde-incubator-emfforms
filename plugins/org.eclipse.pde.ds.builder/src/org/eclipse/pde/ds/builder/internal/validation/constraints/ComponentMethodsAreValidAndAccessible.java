package org.eclipse.pde.ds.builder.internal.validation.constraints;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.jdt.core.*;
import org.eclipse.pde.ds.builder.internal.validation.EnhancedConstraintStatus;
import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.ScrPackage;

public class ComponentMethodsAreValidAndAccessible extends
		AbstractModelConstraint {

	public ComponentMethodsAreValidAndAccessible() {
	}

	@Override
	public IStatus validate(IValidationContext ctx) {
		Component comp = (Component) ctx.getTarget();
		IJavaProject project = JavaCore.create(ResourcesPlugin.getWorkspace()
				.getRoot().getProject("/a"));
		IType type;
		try {
			type = project.findType(comp.getImplementation().getClass_());
			if (type != null && type.exists()) {
				// validate 'activate' method
				if (comp.getActivate() != null) {
					String activateMethod = comp.getActivate();
					IMethod m = type
							.getMethod(
									activateMethod,
									new String[] { "Qorg.osgi.service.component.ComponentContext;" });
					if (m != null
							&& (Flags.isPublic(m.getFlags()) || Flags
									.isProtected(m.getFlags()))) {
						return ctx.createSuccessStatus();
					}
				}

				ConstraintStatus s = (ConstraintStatus) ctx
						.createFailureStatus(comp);
				EnhancedConstraintStatus enhancedStatus = new EnhancedConstraintStatus(
						s.getConstraint(), s.getTarget(), s.getSeverity(), s
								.getCode(), s.getMessage(), s.getResultLocus());
				enhancedStatus
						.setResultStructuralFeature(ScrPackage.Literals.IMPLEMENTATION__CLASS);
				return enhancedStatus;
			} else
				return ctx.createSuccessStatus();
		} catch (JavaModelException e) {
			return ctx.createFailureStatus(comp);
		}
	}
}
