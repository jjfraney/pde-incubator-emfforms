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
 * $Id: ProvideTest.java,v 1.1 2009/07/08 16:38:11 bcabe Exp $
 */
package org.eclipse.pde.ds.scr.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.pde.ds.scr.Provide;
import org.eclipse.pde.ds.scr.ScrFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Provide</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProvideTest extends TestCase {

	/**
	 * The fixture for this Provide test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Provide fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ProvideTest.class);
	}

	/**
	 * Constructs a new Provide test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvideTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Provide test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Provide fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Provide test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Provide getFixture() {
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
		setFixture(ScrFactory.eINSTANCE.createProvide());
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

} //ProvideTest
