package com.devsmobile.lesscss.error;

/**
 * Exception from the Less Compiler
 * @author usarasola
 *
 */
public class LessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1442680187228460215L;

	public LessException(String msg){
		super(msg);
	}
	
	public LessException(String msg, Throwable t){
		super(msg,t);
	}
	
}
