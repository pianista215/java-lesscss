package com.devsmobile.lesscss;

import static org.junit.Assert.*;

import org.junit.Test;

import com.devsmobile.lesscss.error.LessException;

/**
 * Unit test for simple App.
 */
public class LessCompilerV8Test {

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp()
    {
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessCodeAsync(".class { width: (1 + 1) }", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
			}
		});
        assertTrue( true );
    }
}
