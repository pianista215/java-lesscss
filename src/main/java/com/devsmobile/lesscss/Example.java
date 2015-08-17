package com.devsmobile.lesscss;

import com.devsmobile.lesscss.error.LessException;

public class Example {

	public static void main(String[] args){
		LessCompiler c = new LessCompilerV8();
		c.compileLessCodeAsync(".class { width: (1 + 1) }", new LessCallback() {
			
			@Override
			public void onLessCompiled(String css, LessException e) {
				System.out.println(css);
			}
		});
	}
}
