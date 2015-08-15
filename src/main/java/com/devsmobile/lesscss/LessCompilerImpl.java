package com.devsmobile.lesscss;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.Releasable;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

public class LessCompilerImpl implements LessCompiler{
	
	private String getLessJS(){
		String result = "";
		
		ClassLoader classLoader = getClass().getClassLoader();
		try {
		    result = IOUtils.toString(classLoader.getResourceAsStream("less.2.5.1.js"));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return result;
	}
	
	
	private String buildLessCmd(String cmd){
		StringBuilder sb = new StringBuilder("window.less.render('");
		sb.append(cmd);
		sb.append("', function (e, output) {print(output.css);});");
		return sb.toString();
	}

	public String compile(String less) {
		
		JavaVoidCallback callback = new JavaVoidCallback() {
			  public void invoke(final V8Object receiver, final V8Array parameters) {
			    if (parameters.length() > 0) {
			      Object arg1 = parameters.get(0);
			      System.out.println(arg1);
			      if (arg1 instanceof Releasable) {
			        ((Releasable) arg1).release();
			      }
			    }
			  }
			 };
		
		V8 runtime = V8.createV8Runtime();
		runtime.registerJavaMethod(callback, "print");
		runtime.executeVoidScript("var window = {}; var document = {}; window.document = document;");
		runtime.executeVoidScript("window.location = {}; window.location.protocol='file'; window.location.hostname='0.0.0.0';");
		runtime.executeVoidScript(getLessJS());
		runtime.executeScript(buildLessCmd(less));
		runtime.release();
		return "";
	}

}
