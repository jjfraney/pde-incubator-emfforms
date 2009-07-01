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
 * $Id: AdvancedScrValidator.java,v 1.1 2009/07/01 15:46:43 bcabe Exp $
 */
package org.eclipse.pde.ds.builder.internal.validation;

import java.util.Map;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.pde.ds.scr.util.ScrValidator;

/**
 * An extension of the basic Scr validation using the EMF Model Validation
 * Service API.
 */
public class AdvancedScrValidator extends ScrValidator {
	/**
	 * Initializes me.
	 */
	public AdvancedScrValidator() {
		super();
	}

	@Override
	public boolean validate(EObject eObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate(eObject.eClass(), eObject, diagnostics, context);
	}

	/**
	 * Implements validation by delegation to the EMF validation framework.
	 */
	@Override
	public boolean validate(EClass eClass, EObject eObject,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		IBatchValidator batchValidator = ModelValidationService.getInstance()
				.newValidator(EvaluationMode.BATCH);
		batchValidator.setIncludeLiveConstraints(true);
		batchValidator.setReportSuccesses(false);

		// first, do whatever the basic EcoreValidator does
		super.validate(eClass, eObject, diagnostics, context);

		IStatus status = Status.OK_STATUS;

		// no point in validating if we can't report results
		if (diagnostics != null) {
			// if EMF Mode Validation Service already covered the sub-tree,
			// which it does for efficient computation and error reporting,
			// then don't repeat (the Diagnostician does the recursion
			// externally). If there is no context map, then we can't
			// help it
			if (!hasProcessed(eObject, context)) {
				status = batchValidator.validate(eObject,
						new NullProgressMonitor());

				processed(eObject, context, status);

				appendDiagnostics(status, diagnostics);
			}
		}

		return status.isOK();
	}

	/**
	 * Direct validation of {@link EDataType}s is not supported by the EMF
	 * validation framework; they are validated indirectly via the
	 * {@link EObject}s that hold their values.
	 */
	@Override
	public boolean validate(EDataType eDataType, Object value,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return super.validate(eDataType, value, diagnostics, context);
	}

	/**
	 * If we have a context map, record this object's <code>status</code> in it
	 * so that we will know later that we have processed it and its sub-tree.
	 * 
	 * @param eObject
	 *            an element that we have validated
	 * @param context
	 *            the context (may be <code>null</code>)
	 * @param status
	 *            the element's validation status
	 */
	private void processed(EObject eObject, Map<Object, Object> context,
			IStatus status) {
		if (context != null) {
			context.put(eObject, status);
		}
	}

	/**
	 * Determines whether we have processed this <code>eObject</code> before, by
	 * automatic recursion of the EMF Model Validation Service. This is only
	 * possible if we do, indeed, have a context.
	 * 
	 * @param eObject
	 *            an element to be validated (we hope not)
	 * @param context
	 *            the context (may be <code>null</code>)
	 * @return <code>true</code> if the context is not <code>null</code> and the
	 *         <code>eObject</code> or one of its containers has already been
	 *         validated; <code>false</code>, otherwise
	 */
	private boolean hasProcessed(EObject eObject, Map<Object, Object> context) {
		boolean result = false;

		if (context != null) {
			// this is O(NlogN) but there's no helping it
			while (eObject != null) {
				if (context.containsKey(eObject)) {
					result = true;
					eObject = null;
				} else {
					eObject = eObject.eContainer();
				}
			}
		}

		return result;
	}

	/**
	 * Converts a status result from the EMF validation service to diagnostics.
	 * 
	 * @param status
	 *            the EMF validation service's status result
	 * @param diagnostics
	 *            a diagnostic chain to accumulate results on
	 */
	private void appendDiagnostics(IStatus status, DiagnosticChain diagnostics) {
		if (status.isMultiStatus()) {
			IStatus[] children = status.getChildren();

			for (IStatus element : children) {
				appendDiagnostics(element, diagnostics);
			}
		} else if (status instanceof IEnhancedConstraintStatus) {
			diagnostics.add(new BasicDiagnostic(status.getSeverity(), status
					.getPlugin(), status.getCode(), status.getMessage(),
					new Object[] {
							((IConstraintStatus) status).getResultLocus()
									.toArray()[0],
							((IEnhancedConstraintStatus) status)
									.getResultStructuralFeature() }));
		} else if (status instanceof IConstraintStatus) {
			diagnostics.add(new BasicDiagnostic(status.getSeverity(), status
					.getPlugin(), status.getCode(), status.getMessage(),
					((IConstraintStatus) status).getResultLocus().toArray()));
		}
	}
}
