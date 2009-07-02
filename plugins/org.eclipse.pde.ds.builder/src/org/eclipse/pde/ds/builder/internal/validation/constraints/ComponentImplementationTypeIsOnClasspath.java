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
 * $Id: ComponentImplementationTypeIsOnClasspath.java,v 1.3 2009/07/02 21:54:54 bcabe Exp $
 */
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
						s, ScrPackage.Literals.IMPLEMENTATION__CLASS);
				return enhancedStatus;
			} else
				return ctx.createSuccessStatus();
		} catch (JavaModelException e) {
			return ctx.createFailureStatus(impl);
		}

	}

}
