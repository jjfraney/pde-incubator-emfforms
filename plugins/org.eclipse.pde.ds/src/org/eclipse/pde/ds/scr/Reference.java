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
 * $Id: Reference.java,v 1.1 2009/02/12 17:06:39 bcabe Exp $
 */
package org.eclipse.pde.ds.scr;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.pde.ds.scr.Reference#getBind <em>Bind</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Reference#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Reference#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Reference#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Reference#getPolicy <em>Policy</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Reference#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Reference#getUnbind <em>Unbind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference()
 * @model
 * @generated
 */
public interface Reference extends EObject {
	/**
	 * Returns the value of the '<em><b>Bind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bind</em>' attribute.
	 * @see #setBind(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference_Bind()
	 * @model
	 * @generated
	 */
	String getBind();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getBind <em>Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bind</em>' attribute.
	 * @see #getBind()
	 * @generated
	 */
	void setBind(String value);

	/**
	 * Returns the value of the '<em><b>Cardinality</b></em>' attribute.
	 * The default value is <code>"1..1"</code>.
	 * The literals are from the enumeration {@link org.eclipse.pde.ds.scr.Cardinality}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardinality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cardinality</em>' attribute.
	 * @see org.eclipse.pde.ds.scr.Cardinality
	 * @see #isSetCardinality()
	 * @see #unsetCardinality()
	 * @see #setCardinality(Cardinality)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference_Cardinality()
	 * @model default="1..1" unsettable="true"
	 * @generated
	 */
	Cardinality getCardinality();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getCardinality <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardinality</em>' attribute.
	 * @see org.eclipse.pde.ds.scr.Cardinality
	 * @see #isSetCardinality()
	 * @see #unsetCardinality()
	 * @see #getCardinality()
	 * @generated
	 */
	void setCardinality(Cardinality value);

	/**
	 * Unsets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getCardinality <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCardinality()
	 * @see #getCardinality()
	 * @see #setCardinality(Cardinality)
	 * @generated
	 */
	void unsetCardinality();

	/**
	 * Returns whether the value of the '{@link org.eclipse.pde.ds.scr.Reference#getCardinality <em>Cardinality</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cardinality</em>' attribute is set.
	 * @see #unsetCardinality()
	 * @see #getCardinality()
	 * @see #setCardinality(Cardinality)
	 * @generated
	 */
	boolean isSetCardinality();

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' attribute.
	 * @see #setInterface(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference_Interface()
	 * @model required="true"
	 * @generated
	 */
	String getInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getInterface <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' attribute.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Policy</b></em>' attribute.
	 * The default value is <code>"static"</code>.
	 * The literals are from the enumeration {@link org.eclipse.pde.ds.scr.Policy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' attribute.
	 * @see org.eclipse.pde.ds.scr.Policy
	 * @see #isSetPolicy()
	 * @see #unsetPolicy()
	 * @see #setPolicy(Policy)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference_Policy()
	 * @model default="static" unsettable="true"
	 * @generated
	 */
	Policy getPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getPolicy <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' attribute.
	 * @see org.eclipse.pde.ds.scr.Policy
	 * @see #isSetPolicy()
	 * @see #unsetPolicy()
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(Policy value);

	/**
	 * Unsets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getPolicy <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPolicy()
	 * @see #getPolicy()
	 * @see #setPolicy(Policy)
	 * @generated
	 */
	void unsetPolicy();

	/**
	 * Returns whether the value of the '{@link org.eclipse.pde.ds.scr.Reference#getPolicy <em>Policy</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Policy</em>' attribute is set.
	 * @see #unsetPolicy()
	 * @see #getPolicy()
	 * @see #setPolicy(Policy)
	 * @generated
	 */
	boolean isSetPolicy();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference_Target()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

	/**
	 * Returns the value of the '<em><b>Unbind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unbind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unbind</em>' attribute.
	 * @see #setUnbind(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getReference_Unbind()
	 * @model
	 * @generated
	 */
	String getUnbind();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Reference#getUnbind <em>Unbind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unbind</em>' attribute.
	 * @see #getUnbind()
	 * @generated
	 */
	void setUnbind(String value);

} // Reference
