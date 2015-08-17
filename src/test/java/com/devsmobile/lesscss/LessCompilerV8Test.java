package com.devsmobile.lesscss;

import static org.junit.Assert.*;

import org.junit.Test;

import com.devsmobile.lesscss.error.LessException;

/**
 * Unit test for simple App.
 */
public class LessCompilerV8Test {

    
    @Test
    public void testCode()
    {
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessCodeAsync(".class { width: (1 + 1) }", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
				assertTrue( true );
			}
		});
        
    }
    
    @Test
    public void testFile()
    {
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessFileAsync("test1/test1.less", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
				assertTrue( true );
			}
		});
    }
}
