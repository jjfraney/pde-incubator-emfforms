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
 * $Id: EmfValidatorLabelDecorator.java,v 1.2 2009/07/18 13:16:15 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.emfforms.internal.Activator;
import org.eclipse.pde.emfforms.internal.editor.IEmfFormsImages;

public class EmfValidatorLabelDecorator implements ILightweightLabelDecorator {

	/**
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 *      org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object element, IDecoration decoration) {
		if (!(element instanceof EObject || element instanceof FeatureMap.Entry || element instanceof IWrapperItemProvider)) {
			return;
		}

		EObject object = null;
		if (element instanceof EObject) {
			object = (EObject) element;
		} else {
			object = (EObject) AdapterFactoryEditingDomain.unwrap(element);
		}

		Diagnostic validate = Diagnostician.INSTANCE.validate(object);

		if (validate.getSeverity() == Diagnostic.ERROR) {
			decoration.addOverlay(getErrorImageDescriptor());
		} else if (validate.getSeverity() == Diagnostic.WARNING) {
			decoration.addOverlay(getWarningImageDescriptor());
		}
	}

	/**
	 * The image descriptor of the error decoration
	 * 
	 * @return the ImageDescriptor of the error decoration
	 */
	protected ImageDescriptor getErrorImageDescriptor() {
		return Activator.getImageDescriptor(IEmfFormsImages.ERROR_DECORATOR);
	}

	/**
	 * The image descriptor of the error decoration
	 * 
	 * @return the ImageDescriptor of the error decoration
	 */
	protected ImageDescriptor getWarningImageDescriptor() {
		return Activator.getImageDescriptor(IEmfFormsImages.WARNING_DECORATOR);
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
	 *      java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
	}

}