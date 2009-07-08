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
 * $Id: ScrTests.java,v 1.1 2009/07/08 16:38:11 bcabe Exp $
 */
package org.eclipse.pde.ds.scr.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>scr</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScrTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new ScrTests("scr Tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(PropertiesTest.class);
		suite.addTestSuite(PropertyTest.class);
		suite.addTestSuite(ProvideTest.class);
		suite.addTestSuite(ComponentTest.class);
		suite.addTestSuite(ImplementationTest.class);
		suite.addTestSuite(ServiceTest.class);
		suite.addTestSuite(ReferenceTest.class);
		//$JUnit-END$
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScrTests(String name) {
		super(name);
	}

} //ScrTests
