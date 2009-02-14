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
 * $Id: Component.java,v 1.2 2009/02/14 19:43:46 bcabe Exp $
 */
package org.eclipse.pde.ds.scr;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#isImmediate <em>Immediate</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getActivate <em>Activate</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getDeactivate <em>Deactivate</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getAllProperties <em>All Properties</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getService <em>Service</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent()
 * @model extendedMetaData="name='component' kind='element'"
 * @generated
 */
public interface Component extends EObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Enabled()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.pde.ds.scr.Component#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	void unsetEnabled();

	/**
	 * Returns whether the value of the '{@link org.eclipse.pde.ds.scr.Component#isEnabled <em>Enabled</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enabled</em>' attribute is set.
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	boolean isSetEnabled();

	/**
	 * Returns the value of the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factory</em>' attribute.
	 * @see #setFactory(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Factory()
	 * @model
	 * @generated
	 */
	String getFactory();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#getFactory <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factory</em>' attribute.
	 * @see #getFactory()
	 * @generated
	 */
	void setFactory(String value);

	/**
	 * Returns the value of the '<em><b>Immediate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Immediate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Immediate</em>' attribute.
	 * @see #isSetImmediate()
	 * @see #unsetImmediate()
	 * @see #setImmediate(boolean)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Immediate()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isImmediate();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#isImmediate <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Immediate</em>' attribute.
	 * @see #isSetImmediate()
	 * @see #unsetImmediate()
	 * @see #isImmediate()
	 * @generated
	 */
	void setImmediate(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.pde.ds.scr.Component#isImmediate <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetImmediate()
	 * @see #isImmediate()
	 * @see #setImmediate(boolean)
	 * @generated
	 */
	void unsetImmediate();

	/**
	 * Returns whether the value of the '{@link org.eclipse.pde.ds.scr.Component#isImmediate <em>Immediate</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Immediate</em>' attribute is set.
	 * @see #unsetImmediate()
	 * @see #isImmediate()
	 * @see #setImmediate(boolean)
	 * @generated
	 */
	boolean isSetImmediate();

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
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Activate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activate</em>' attribute.
	 * @see #setActivate(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Activate()
	 * @model
	 * @generated
	 */
	String getActivate();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#getActivate <em>Activate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activate</em>' attribute.
	 * @see #getActivate()
	 * @generated
	 */
	void setActivate(String value);

	/**
	 * Returns the value of the '<em><b>Deactivate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deactivate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deactivate</em>' attribute.
	 * @see #setDeactivate(String)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Deactivate()
	 * @model
	 * @generated
	 */
	String getDeactivate();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#getDeactivate <em>Deactivate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deactivate</em>' attribute.
	 * @see #getDeactivate()
	 * @generated
	 */
	void setDeactivate(String value);

	/**
	 * Returns the value of the '<em><b>Configuration Policy</b></em>' attribute.
	 * The default value is <code>"optional"</code>.
	 * The literals are from the enumeration {@link org.eclipse.pde.ds.scr.ConfigurationPolicy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuration Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration Policy</em>' attribute.
	 * @see org.eclipse.pde.ds.scr.ConfigurationPolicy
	 * @see #setConfigurationPolicy(ConfigurationPolicy)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_ConfigurationPolicy()
	 * @model default="optional"
	 *        extendedMetaData="kind='attribute' name='configuration-policy'"
	 * @generated
	 */
	ConfigurationPolicy getConfigurationPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration Policy</em>' attribute.
	 * @see org.eclipse.pde.ds.scr.ConfigurationPolicy
	 * @see #getConfigurationPolicy()
	 * @generated
	 */
	void setConfigurationPolicy(ConfigurationPolicy value);

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' containment reference.
	 * @see #setImplementation(Implementation)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Implementation()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Implementation getImplementation();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#getImplementation <em>Implementation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' containment reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(Implementation value);

	/**
	 * Returns the value of the '<em><b>All Properties</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Properties</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Properties</em>' attribute list.
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_AllProperties()
	 * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:1'"
	 * @generated
	 */
	FeatureMap getAllProperties();

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.pde.ds.scr.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Property()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="group='#group:1'"
	 * @generated
	 */
	EList<Property> getProperty();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.pde.ds.scr.Properties}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Properties()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="group='#group:1'"
	 * @generated
	 */
	EList<Properties> getProperties();

	/**
	 * Returns the value of the '<em><b>Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service</em>' containment reference.
	 * @see #setService(Service)
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Service()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='service'"
	 * @generated
	 */
	Service getService();

	/**
	 * Sets the value of the '{@link org.eclipse.pde.ds.scr.Component#getService <em>Service</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service</em>' containment reference.
	 * @see #getService()
	 * @generated
	 */
	void setService(Service value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.pde.ds.scr.Reference}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' containment reference list.
	 * @see org.eclipse.pde.ds.scr.ScrPackage#getComponent_Reference()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='reference'"
	 * @generated
	 */
	EList<Reference> getReference();

} // Component
