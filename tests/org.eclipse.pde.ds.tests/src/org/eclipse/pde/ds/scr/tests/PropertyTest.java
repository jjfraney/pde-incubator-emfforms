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
 * $Id: PropertyTest.java,v 1.1 2009/07/08 16:38:11 bcabe Exp $
 */
package org.eclipse.pde.ds.scr.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.pde.ds.scr.Property;
import org.eclipse.pde.ds.scr.ScrFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyTest extends TestCase {

	/**
	 * The fixture for this Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Property fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PropertyTest.class);
	}

	/**
	 * Constructs a new Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Property fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Property getFixture() {
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
		setFixture(ScrFactory.eINSTANCE.createProperty());
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

} //PropertyTest
