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
 * $Id: Service.java,v 1.1 2009/02/12 17:06:39 bcabe Exp $
 */
package org.eclipse.pde.ds.scr;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.pde.ds.scr.Service#getProvide <em>Provide</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Service#isServicefactory <em>Servicefactory</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.pde.ds.scr.ScrPackage#getService()
 * @model
 * @generated
 */
public interface Service extends EObject {
	/**
	 * Returns the value of the '<em><b>Provide</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.pde.ds.scr.Provide}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provide</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provide</em>' containment reference list.
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getService_Provide()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='provide'"
	 * @generated
	 */
	EList<Provide> getProvide();

	/**
	 * Returns the value of the '<em><b>Servicefactory</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servicefactory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servicefactory</em>' attribute.
	 * @see #isSetServicefactory()
	 * @see #unsetServicefactory()
	 * @see #setServicefactory(boolean)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getService_Servicefactory()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isServicefactory();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Service#isServicefactory <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Servicefactory</em>' attribute.
	 * @see #isSetServicefactory()
	 * @see #unsetServicefactory()
	 * @see #isServicefactory()
	 * @generated
	 */
	void setServicefactory(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.pde.ds.scr.Service#isServicefactory <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetServicefactory()
	 * @see #isServicefactory()
	 * @see #setServicefactory(boolean)
	 * @generated
	 */
	void unsetServicefactory();

	/**
	 * Returns whether the value of the '{@link org.eclipse.pde.ds.scr.Service#isServicefactory <em>Servicefactory</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Servicefactory</em>' attribute is set.
	 * @see #unsetServicefactory()
	 * @see #isServicefactory()
	 * @see #setServicefactory(boolean)
	 * @generated
	 */
	boolean isSetServicefactory();

} // Service
