package com.devsmobile.lesscss;

/**
 * Less compiler methods available
 * @author usarasola
 *
 */
public interface LessCompiler {
	
	/**
	 * Compile the less code provided using an async operation
	 * @param less
	 * @param callback
	 * @return
	 */
	public void compileLessCodeAsync(String less, LessCallback callback);
	
	/**
	 * Compile the less file provided as relative to the classLoader (Ex: /ex/proof.less) using an async operation
	 * @param fileUri
	 * @param callback
	 * @return
	 */
	public void compileLessFileAsync(String fileUri, LessCallback callback);
	
	/**
	 * Clean all the V8 objects attached to the lib (must only invoked if you won't use more this object to compile less)
	 */
	public void release();
	
}
