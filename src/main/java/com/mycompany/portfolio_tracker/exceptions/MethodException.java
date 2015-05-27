package com.mycompany.portfolio_tracker.exceptions;

/**
 * 
 * @author colin
 */
public class MethodException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param message error message
	 */
	public MethodException( String message ) {
		super(message);
	}

}
