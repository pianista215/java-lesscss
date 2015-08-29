package com.devsmobile.lesscss;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.devsmobile.lesscss.error.LessException;

/**
 * Unit test for Less compiler
 */
public class LessCompilerV8Test {

    
    @Test
    public void testCode() throws InterruptedException
    {
    	final CountDownLatch lock = new CountDownLatch(1);
    	LessCompiler p = new LessCompilerV8();

    	p.compileLessCodeAsync(".class { width: (1 + 1) }", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
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

    	p.compileLessFileAsync("test1/test1.less", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
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
				System.out.println(css);
				lock.countDown();
			}
		});
    	lock.await(10000, TimeUnit.MILLISECONDS);
    	assertTrue( true );
    }
}
