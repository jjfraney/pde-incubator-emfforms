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
 * $Id: ComponentTest.java,v 1.2 2009/07/08 20:49:55 bcabe Exp $
 */
package org.eclipse.pde.ds.scr.tests;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.pde.ds.scr.Component;
import org.eclipse.pde.ds.scr.Properties;
import org.eclipse.pde.ds.scr.Property;
import org.eclipse.pde.ds.scr.ScrFactory;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.scr.Service;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getImplementation() <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getAllProperties() <em>All Properties</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getProperty() <em>Property</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getProperties() <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getService() <em>Service</em>}</li>
 *   <li>{@link org.eclipse.pde.ds.scr.Component#getReference() <em>Reference</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ComponentTest extends TestCase {

	/**
	 * The fixture for this Component test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Component fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComponentTest.class);
	}

	/**
	 * Constructs a new Component test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Component test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Component fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Component test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Component getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ScrFactory.eINSTANCE.createComponent());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#getImplementation() <em>Implementation</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#getImplementation()
	 * @generated NOT
	 */
	public void testGetImplementation() {
		assertNotNull(getFixture().getImplementation()) ;
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#setImplementation(org.eclipse.pde.ds.scr.Implementation) <em>Implementation</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#setImplementation(org.eclipse.pde.ds.scr.Implementation)
	 * @generated NOT
	 */
	public void testSetImplementation() {
		assertTrue(true);
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#getAllProperties() <em>All Properties</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#getAllProperties()
	 * @generated NOT
	 */
	public void testGetAllProperties() {
		Component c = getFixture() ;
		c.getAllProperties().add(ScrPackage.Literals.COMPONENT__PROPERTY, ScrFactory.eINSTANCE.createProperty()) ;
		c.getAllProperties().add(ScrPackage.Literals.COMPONENT__PROPERTIES, ScrFactory.eINSTANCE.createProperties()) ;
		c.getAllProperties().add(ScrPackage.Literals.COMPONENT__PROPERTY, ScrFactory.eINSTANCE.createProperty()) ;
		// check the allProperties collection preserves property/properties interlacing
		assertTrue(c.getAllProperties().get(0).getValue() instanceof Property) ;
		assertTrue(c.getAllProperties().get(1).getValue() instanceof Properties) ;
		assertTrue(c.getAllProperties().get(2).getValue() instanceof Property) ;
		// check that values added to "actual" collections are accessible via getAllProperties
		c.getProperties().add(ScrFactory.eINSTANCE.createProperties()) ;
		c.getProperty().add(ScrFactory.eINSTANCE.createProperty()) ;
		c.getProperties().add(ScrFactory.eINSTANCE.createProperties()) ;
		assertTrue(c.getAllProperties().get(3).getValue() instanceof Properties) ;
		assertTrue(c.getAllProperties().get(4).getValue() instanceof Property) ;
		assertTrue(c.getAllProperties().get(5).getValue() instanceof Properties) ;
		
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#getProperty() <em>Property</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#getProperty()
	 * @generated NOT
	 */
	public void testGetProperty() {
		assertTrue(getFixture().getProperty().isEmpty());
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#getProperties() <em>Properties</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#getProperties()
	 * @generated NOT
	 */
	public void testGetProperties() {
		assertTrue(getFixture().getProperties().isEmpty());
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#getService() <em>Service</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#getService()
	 * @generated NOT
	 */
	public void testGetService() {
		assertNull(getFixture().getService());
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#setService(org.eclipse.pde.ds.scr.Service) <em>Service</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#setService(org.eclipse.pde.ds.scr.Service)
	 * @generated NOT
	 */
	public void testSetService() {
		Service s = ScrFactory.eINSTANCE.createService();
		getFixture().setService(s);
		assertSame(s, getFixture().getService());
	}

	/**
	 * Tests the '{@link org.eclipse.pde.ds.scr.Component#getReference() <em>Reference</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.pde.ds.scr.Component#getReference()
	 * @generated NOT
	 */
	public void testGetReference() {
		assertTrue(getFixture().getReference().isEmpty());
	}

} //ComponentTest
