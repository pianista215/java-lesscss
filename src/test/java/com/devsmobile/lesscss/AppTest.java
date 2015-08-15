package com.devsmobile.lesscss;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( App.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	LessCompiler p = new LessCompilerImpl();

    	String result = p.compile(".class { width: (1 + 1) }");
    	System.out.println(result);
        assertTrue( true );
    }
}
