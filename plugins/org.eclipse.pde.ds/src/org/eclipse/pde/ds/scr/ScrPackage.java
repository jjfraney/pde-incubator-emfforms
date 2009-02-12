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
 * $Id: ScrPackage.java,v 1.1 2009/02/12 17:06:39 bcabe Exp $
 */
package org.eclipse.pde.ds.scr;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.pde.ds.scr.ScrFactory
 * @model kind="package"
 * @generated
 */
public interface ScrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "scr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.osgi.org/xmlns/scr/v1.1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "scr";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "org.eclipse.pde.ds.content-type";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScrPackage eINSTANCE = org.eclipse.pde.ds.scr.impl.ScrPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.impl.ComponentImpl
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__FACTORY = 1;

	/**
	 * The feature id for the '<em><b>Immediate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__IMMEDIATE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAME = 3;

	/**
	 * The feature id for the '<em><b>Activate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ACTIVATE = 4;

	/**
	 * The feature id for the '<em><b>Deactivate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DEACTIVATE = 5;

	/**
	 * The feature id for the '<em><b>Configuration Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONFIGURATION_POLICY = 6;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__IMPLEMENTATION = 7;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PROPERTY = 8;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PROPERTIES = 9;

	/**
	 * The feature id for the '<em><b>Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SERVICE = 10;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__REFERENCE = 11;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.impl.ImplementationImpl <em>Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.impl.ImplementationImpl
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getImplementation()
	 * @generated
	 */
	int IMPLEMENTATION = 1;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION__CLASS = 0;

	/**
	 * The number of structural features of the '<em>Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.impl.PropertyImpl
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.impl.PropertiesImpl <em>Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.impl.PropertiesImpl
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getProperties()
	 * @generated
	 */
	int PROPERTIES = 3;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__ENTRY = 0;

	/**
	 * The number of structural features of the '<em>Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.impl.ServiceImpl
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 4;

	/**
	 * The feature id for the '<em><b>Provide</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PROVIDE = 0;

	/**
	 * The feature id for the '<em><b>Servicefactory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__SERVICEFACTORY = 1;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.impl.ProvideImpl <em>Provide</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.impl.ProvideImpl
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getProvide()
	 * @generated
	 */
	int PROVIDE = 5;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDE__INTERFACE = 0;

	/**
	 * The number of structural features of the '<em>Provide</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.impl.ReferenceImpl
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 6;

	/**
	 * The feature id for the '<em><b>Bind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__BIND = 0;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__CARDINALITY = 1;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__NAME = 3;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__POLICY = 4;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__TARGET = 5;

	/**
	 * The feature id for the '<em><b>Unbind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__UNBIND = 6;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.Policy <em>Policy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Policy
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getPolicy()
	 * @generated
	 */
	int POLICY = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.Cardinality <em>Cardinality</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Cardinality
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getCardinality()
	 * @generated
	 */
	int CARDINALITY = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.pde.ds.scr.ConfigurationPolicy <em>Configuration Policy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.ConfigurationPolicy
	 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getConfigurationPolicy()
	 * @generated
	 */
	int CONFIGURATION_POLICY = 9;


	/**
	 * Returns the meta object for class '{@link org.eclipse.pde.ds.scr.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.eclipse.pde.ds.scr.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Component#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#isEnabled()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Component#getFactory <em>Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factory</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getFactory()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Factory();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Component#isImmediate <em>Immediate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Immediate</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#isImmediate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Immediate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Component#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getName()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Component#getActivate <em>Activate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activate</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getActivate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Activate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Component#getDeactivate <em>Deactivate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deactivate</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getDeactivate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Deactivate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Configuration Policy</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getConfigurationPolicy()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_ConfigurationPolicy();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.pde.ds.scr.Component#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Implementation</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getImplementation()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Implementation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.pde.ds.scr.Component#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getProperty()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Property();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.pde.ds.scr.Component#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getProperties()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Properties();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.pde.ds.scr.Component#getService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Service</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getService()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Service();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.pde.ds.scr.Component#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reference</em>'.
	 * @see org.eclipse.pde.ds.scr.Component#getReference()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Reference();

	/**
	 * Returns the meta object for class '{@link org.eclipse.pde.ds.scr.Implementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implementation</em>'.
	 * @see org.eclipse.pde.ds.scr.Implementation
	 * @generated
	 */
	EClass getImplementation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Implementation#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see org.eclipse.pde.ds.scr.Implementation#getClass_()
	 * @see #getImplementation()
	 * @generated
	 */
	EAttribute getImplementation_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.pde.ds.scr.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.pde.ds.scr.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.pde.ds.scr.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.pde.ds.scr.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Property#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.pde.ds.scr.Property#getType()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.pde.ds.scr.Properties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Properties</em>'.
	 * @see org.eclipse.pde.ds.scr.Properties
	 * @generated
	 */
	EClass getProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Properties#getEntry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Entry</em>'.
	 * @see org.eclipse.pde.ds.scr.Properties#getEntry()
	 * @see #getProperties()
	 * @generated
	 */
	EAttribute getProperties_Entry();

	/**
	 * Returns the meta object for class '{@link org.eclipse.pde.ds.scr.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see org.eclipse.pde.ds.scr.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.pde.ds.scr.Service#getProvide <em>Provide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Provide</em>'.
	 * @see org.eclipse.pde.ds.scr.Service#getProvide()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Provide();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Service#isServicefactory <em>Servicefactory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Servicefactory</em>'.
	 * @see org.eclipse.pde.ds.scr.Service#isServicefactory()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Servicefactory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.pde.ds.scr.Provide <em>Provide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provide</em>'.
	 * @see org.eclipse.pde.ds.scr.Provide
	 * @generated
	 */
	EClass getProvide();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Provide#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see org.eclipse.pde.ds.scr.Provide#getInterface()
	 * @see #getProvide()
	 * @generated
	 */
	EAttribute getProvide_Interface();

	/**
	 * Returns the meta object for class '{@link org.eclipse.pde.ds.scr.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Reference#getBind <em>Bind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bind</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference#getBind()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Bind();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Reference#getCardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cardinality</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference#getCardinality()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Cardinality();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Reference#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference#getInterface()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Interface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Reference#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference#getName()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Reference#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Policy</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference#getPolicy()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Policy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Reference#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference#getTarget()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.pde.ds.scr.Reference#getUnbind <em>Unbind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unbind</em>'.
	 * @see org.eclipse.pde.ds.scr.Reference#getUnbind()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Unbind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.pde.ds.scr.Policy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Policy</em>'.
	 * @see org.eclipse.pde.ds.scr.Policy
	 * @generated
	 */
	EEnum getPolicy();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.pde.ds.scr.Cardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cardinality</em>'.
	 * @see org.eclipse.pde.ds.scr.Cardinality
	 * @generated
	 */
	EEnum getCardinality();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.pde.ds.scr.ConfigurationPolicy <em>Configuration Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Configuration Policy</em>'.
	 * @see org.eclipse.pde.ds.scr.ConfigurationPolicy
	 * @generated
	 */
	EEnum getConfigurationPolicy();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ScrFactory getScrFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.impl.ComponentImpl
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ENABLED = eINSTANCE.getComponent_Enabled();

		/**
		 * The meta object literal for the '<em><b>Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__FACTORY = eINSTANCE.getComponent_Factory();

		/**
		 * The meta object literal for the '<em><b>Immediate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__IMMEDIATE = eINSTANCE.getComponent_Immediate();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__NAME = eINSTANCE.getComponent_Name();

		/**
		 * The meta object literal for the '<em><b>Activate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ACTIVATE = eINSTANCE.getComponent_Activate();

		/**
		 * The meta object literal for the '<em><b>Deactivate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__DEACTIVATE = eINSTANCE.getComponent_Deactivate();

		/**
		 * The meta object literal for the '<em><b>Configuration Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CONFIGURATION_POLICY = eINSTANCE.getComponent_ConfigurationPolicy();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__IMPLEMENTATION = eINSTANCE.getComponent_Implementation();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PROPERTY = eINSTANCE.getComponent_Property();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PROPERTIES = eINSTANCE.getComponent_Properties();

		/**
		 * The meta object literal for the '<em><b>Service</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__SERVICE = eINSTANCE.getComponent_Service();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__REFERENCE = eINSTANCE.getComponent_Reference();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.impl.ImplementationImpl <em>Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.impl.ImplementationImpl
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getImplementation()
		 * @generated
		 */
		EClass IMPLEMENTATION = eINSTANCE.getImplementation();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION__CLASS = eINSTANCE.getImplementation_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.impl.PropertyImpl
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__TYPE = eINSTANCE.getProperty_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.impl.PropertiesImpl <em>Properties</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.impl.PropertiesImpl
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getProperties()
		 * @generated
		 */
		EClass PROPERTIES = eINSTANCE.getProperties();

		/**
		 * The meta object literal for the '<em><b>Entry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTIES__ENTRY = eINSTANCE.getProperties_Entry();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.impl.ServiceImpl
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Provide</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__PROVIDE = eINSTANCE.getService_Provide();

		/**
		 * The meta object literal for the '<em><b>Servicefactory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__SERVICEFACTORY = eINSTANCE.getService_Servicefactory();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.impl.ProvideImpl <em>Provide</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.impl.ProvideImpl
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getProvide()
		 * @generated
		 */
		EClass PROVIDE = eINSTANCE.getProvide();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVIDE__INTERFACE = eINSTANCE.getProvide_Interface();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.impl.ReferenceImpl
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Bind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__BIND = eINSTANCE.getReference_Bind();

		/**
		 * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__CARDINALITY = eINSTANCE.getReference_Cardinality();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__INTERFACE = eINSTANCE.getReference_Interface();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__NAME = eINSTANCE.getReference_Name();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__POLICY = eINSTANCE.getReference_Policy();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__TARGET = eINSTANCE.getReference_Target();

		/**
		 * The meta object literal for the '<em><b>Unbind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__UNBIND = eINSTANCE.getReference_Unbind();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.Policy <em>Policy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.Policy
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getPolicy()
		 * @generated
		 */
		EEnum POLICY = eINSTANCE.getPolicy();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.Cardinality <em>Cardinality</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.Cardinality
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getCardinality()
		 * @generated
		 */
		EEnum CARDINALITY = eINSTANCE.getCardinality();

		/**
		 * The meta object literal for the '{@link org.eclipse.pde.ds.scr.ConfigurationPolicy <em>Configuration Policy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.pde.ds.scr.ConfigurationPolicy
		 * @see org.eclipse.pde.ds.scr.impl.ScrPackageImpl#getConfigurationPolicy()
		 * @generated
		 */
		EEnum CONFIGURATION_POLICY = eINSTANCE.getConfigurationPolicy();

	}

} //ScrPackage
