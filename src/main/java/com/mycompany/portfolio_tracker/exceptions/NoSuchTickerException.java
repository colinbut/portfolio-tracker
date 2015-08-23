/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.exceptions;

/**
 * 
 * @author colin
 */
public class NoSuchTickerException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param s the exception to be thrown.
	 */
	public NoSuchTickerException( String s ) {
		super(s);		
	}
	
	/**
	 * Default constructor
	 */
	public NoSuchTickerException() {
		super("No such Ticker");
	}

}
