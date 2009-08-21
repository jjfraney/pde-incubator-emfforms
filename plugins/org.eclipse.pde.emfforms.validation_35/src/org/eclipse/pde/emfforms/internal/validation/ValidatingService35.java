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
 * $Id: ValidatingService35.java,v 1.3 2009/08/21 20:25:45 bcabe Exp $
 */
package org.eclipse.pde.emfforms.internal.validation;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.pde.emfforms.editor.ValidatingService;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

public class ValidatingService35 implements ValidatingService {

	public void analyzeDiagnostic(DataBindingContext dataBindingContext, Diagnostic diagnostic,
			IMessageManager messageManager) {

		boolean atLeastOneErroneousBinding = false;
		for (Object o : dataBindingContext.getBindings()) {
			Binding binding = (Binding) o;
			IEMFObservable emfObservable = null;
			ISWTObservable swtObservable = null;
			if (binding.getModel() instanceof IEMFObservable
					&& binding.getTarget() instanceof ISWTObservable) {
				emfObservable = (IEMFObservable) binding.getModel();
				swtObservable = (ISWTObservable) binding.getTarget();
			} else if (binding.getTarget() instanceof IEMFObservable
					&& binding.getModel() instanceof ISWTObservable) {
				swtObservable = (ISWTObservable) binding.getModel();
				emfObservable = (IEMFObservable) binding.getTarget();
			}

			if (emfObservable != null && swtObservable != null)
				if (checkBinding(emfObservable, swtObservable, diagnostic,
						messageManager))
					atLeastOneErroneousBinding = true;
		}
		if (!atLeastOneErroneousBinding) {
			// add an error message anyways
			messageManager.addMessage(diagnostic, diagnostic.getMessage(),
					null, keyMap
							.getMessageProviderKey(diagnostic.getSeverity()));
		}

	}

	private boolean checkBinding(IEMFObservable emfObservable,
			ISWTObservable swtObservable, Diagnostic diagnostic,
			IMessageManager messageManager) {
		List<?> diagnosticData = diagnostic.getData();
		if (diagnosticData.size() >= 2) {
			if (diagnosticData.get(0) == emfObservable.getObserved()) {
				if (diagnosticData.get(1) == emfObservable
						.getStructuralFeature()) {
					if (swtObservable.getWidget() instanceof Control) {
						Control control = (Control) swtObservable.getWidget();

						// if (true || control.isVisible())
						messageManager.addMessage(swtObservable, diagnostic
								.getMessage(), null,
								keyMap.getMessageProviderKey(diagnostic
										.getSeverity()), control);
						// else
						// messageManager.addMessage(swtObservable,
						// diagnostic.getMessage(), null,
						// keyMap.getMessageProviderKey(diagnostic.getSeverity()));

						return true;
					}
				}
			}
		}
		return false;
	}

}
