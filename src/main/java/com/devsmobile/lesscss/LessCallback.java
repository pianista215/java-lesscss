package com.devsmobile.lesscss;

import com.devsmobile.lesscss.error.LessException;

/**
 * Less callback to be implemented if you want async calls to less compiler
 * @author usarasola
 *
 */
public interface LessCallback {
	
	/**
	 * Invoked when the less is compiled
	 * @param css The less compiled
	 * @param e Exception if an error in the less is found
	 */
	public void onLessCompiled(String css, LessException e);
}
