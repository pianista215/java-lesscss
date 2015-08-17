package com.devsmobile.lesscss;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.Releasable;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

public class LessCompilerV8 implements LessCompiler{
	
	
	private V8 runtime;
	
	public LessCompilerV8(){
		//Prepare the runtime to compile less
		runtime = createRuntime();
	}
	
	/**
	 * Get the less 2.5.1 version modified for be used from V8
	 * The entries modified of this .js are commented with //U:
	 * @return
	 */
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
	
	/**
	 * Create the less command for compile
	 * @param less The less to be compiled
	 * @param functionName The functionName for the callback
	 * @return
	 */
	private String renderLessCmd(String less, String functionName){
		StringBuilder sb = new StringBuilder("window.less.render('");
		sb.append(StringEscapeUtils.escapeEcmaScript(less));
		//TODO: Error handlers
		sb.append("', function(e, output){").append(StringEscapeUtils.escapeEcmaScript(functionName)).append("(output.css);});");
		return sb.toString();
	}
	
	/**
	 * Create the runtime for the less compiler
	 * @return
	 */
	private V8 createRuntime(){
		V8 runtime = V8.createV8Runtime();
		//Recreate a window/document environment as a browser
		runtime.executeVoidScript("var window = {}; var document = {}; window.document = document;");
		runtime.executeVoidScript("window.location = {}; window.location.protocol='file'; window.location.hostname='0.0.0.0';");
		runtime.executeVoidScript(getLessJS());
		return runtime;
	}

	@Override
	public void compileLessCodeAsync(String less, final LessCallback callback) {
		
		//Generate an uuid for the v8Callback. This allows us to invoke multiple times with the same object
		String uuid = "_"+UUID.randomUUID().toString().replace("-","");
		JavaVoidCallback v8Callback = new JavaVoidCallback() {
			public void invoke(final V8Object receiver, final V8Array parameters) {
				String css = null; //TODO: Error handler
				try{
					if (parameters.length() > 0) {
						Object arg1 = parameters.get(0);
						css = (String) arg1;
						if (arg1 instanceof Releasable) {
							((Releasable) arg1).release();
						}
				    }
				} catch (Exception e){
					e.printStackTrace(); //TODO: REMOVE
				}
				callback.onLessCompiled(css, null);
			}
			//REMOVE CALLBACK
		};
		
		runtime.registerJavaMethod(v8Callback, uuid);
		runtime.executeScript(renderLessCmd(less,uuid));
	}

	@Override
	public String compileLessCode(String less) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void compileLessFileAsync(String fileUri, LessCallback callback) {
		
	}

	@Override
	public String compileLessFile(String fileUri) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void release() {
		if(runtime!=null){
			runtime.release();
		}
	}

}
