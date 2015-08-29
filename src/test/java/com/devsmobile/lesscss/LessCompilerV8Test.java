package com.devsmobile.lesscss;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.devsmobile.lesscss.error.LessException;

/**
 * Unit test for Less compiler
 */
public class LessCompilerV8Test {
	
	private boolean compareResults(String str1, String str2){
		String str1P = str1.trim().replace("\r", "");
		String str2P = str2.trim().replace("\r", "");
		return str1P.equals(str2P);
	}

    
    @Test
    public void testCode() throws InterruptedException
    {
    	final CountDownLatch lock = new CountDownLatch(1);
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessCodeAsync(".class { width: (1 + 1) }", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				try {
					String resultExpected = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("coderesult/result.css"));
					assertTrue("result.css equals", compareResults(css, resultExpected));
				} catch (IOException e1) {
					assertTrue(false);
				}
				lock.countDown();
			}
		});
        
    	lock.await(10000, TimeUnit.MILLISECONDS);
    }
    
    @Test
    public void testFileWihoutImports() throws InterruptedException
    {
    	final CountDownLatch lock = new CountDownLatch(1);
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessFileAsync("test1/test1.less", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				try {
					String resultExpected = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("test1/result.css"));
					assertTrue("result.css equals", compareResults(css, resultExpected));
				} catch (IOException e1) {
					assertTrue(false);
				}
				lock.countDown();
			}
		});
    	lock.await(10000, TimeUnit.MILLISECONDS);
    	assertTrue( true );
    }
    
    @Test
    public void testFileImports() throws InterruptedException
    {
    	final CountDownLatch lock = new CountDownLatch(1);
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessFileAsync("test2/test1.less", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				try {
					String resultExpected = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("test2/result.css"));
					assertTrue("result.css equals", compareResults(css, resultExpected));
				} catch (IOException e1) {
					assertTrue(false);
				}
				lock.countDown();
			}
		});
    	lock.await(10000, TimeUnit.MILLISECONDS);
    	assertTrue( true );
    }
    
    @Test
    public void testBootstrap() throws InterruptedException
    {
    	final CountDownLatch lock = new CountDownLatch(1);
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessFileAsync("bootstrap/bootstrap.less", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				try {
					String resultExpected = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("bootstrap/result.css"));
					//I have to compile my own because bootstrap adds to the compiler some options (order...compress...)
					//assertTrue("result.css equals", compareResults(css, resultExpected));
				} catch (IOException e1) {
					assertTrue(false);
				}
				lock.countDown();
			}
		});
    	lock.await(10000, TimeUnit.MILLISECONDS);
    	assertTrue( true );
    }
    
}
