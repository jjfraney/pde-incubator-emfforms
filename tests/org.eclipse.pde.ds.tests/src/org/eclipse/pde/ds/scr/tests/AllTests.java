package org.eclipse.pde.ds.scr.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.pde.ds.scr.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(PropertiesTest.class);
		suite.addTestSuite(PropertyTest.class);
		suite.addTestSuite(ProvideTest.class);
		suite.addTestSuite(ComponentTest.class);
		suite.addTest(ScrTests.suite());
		suite.addTestSuite(ImplementationTest.class);
		suite.addTestSuite(ServiceTest.class);
		suite.addTestSuite(ReferenceTest.class);
		suite.addTest(ScrAllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
