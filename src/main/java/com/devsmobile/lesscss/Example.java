package com.devsmobile.lesscss;

import com.devsmobile.lesscss.error.LessException;

public class Example {

	public static void main(String[] args){
		LessCompiler c = new LessCompilerV8();
		asyncCode(c);
		asyncFile(c);
		c.release();
	}
	
	/**
	 * Async with code demo
	 * @param compiler
	 */
	private static void asyncCode(LessCompiler compiler){
		compiler.compileLessCodeAsync(".class { width: (1 + 1) }", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
			}
		});
	}
	
	/**
	 * Async with file demo
	 * @param compiler
	 */
	private static void asyncFile(LessCompiler compiler){
		compiler.compileLessFileAsync("ex/proof.less", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
			}
		});
	}
	
}
