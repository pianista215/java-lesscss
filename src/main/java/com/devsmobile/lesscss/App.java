package com.devsmobile.lesscss;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class App {

	
	private static String getLessProof(){
		String result = "";
		
		ClassLoader classLoader = App.class.getClassLoader();
		try {
		    result = IOUtils.toString(classLoader.getResourceAsStream("proof.less"));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return result;
	}
	
	public static void main(String[] args){
		LessCompiler p = new LessCompilerImpl();
		
		System.out.println(p.compile(StringEscapeUtils.escapeEcmaScript(getLessProof())));
	}
}
