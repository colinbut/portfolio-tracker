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
public class WebsiteDataException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public WebsiteDataException(){
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param s the exception to be thrown.
	 */
	public WebsiteDataException( String s ) {
		super(s);	
	}

}
