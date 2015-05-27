package com.mycompany.portfolio_tracker.exceptions;

/**
 * 
 * @author colin
 */
public class NoSuchTickerException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * 
	 * @param s the exception to be thrown.
	 */
	public NoSuchTickerException( String s ) {
		super(s);		
	}
	
	public NoSuchTickerException() {
		super("No such Ticker");
	}

}
