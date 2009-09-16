package org.eclipse.pde.ds.builder.internal.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.pde.ds.scr.ScrPackage;

public class ClientSelector implements IClientSelector {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.validation.model.IClientSelector#selects(java.lang.Object
	 * )
	 */
	public boolean selects(Object object) {
		if (object instanceof EObject) {
			EObject eObject = (EObject) object;
			boolean inPackage = eObject.eClass().getEPackage() == ScrPackage.eINSTANCE;
			return inPackage;
		}
		return false;
	}
}
