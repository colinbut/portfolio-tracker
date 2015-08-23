/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.model;

/**
 * Abstract base class for Portfolio
 * 
 * @author colin
 *
 */
public abstract class AbstractBasePortfolio implements Portfolio {

	private String portfolioName;
	
	/**
	 * This will return the name of the portfolio.
	 * 
	 * @return
	 */
	public String getPortfolioName() {
		return portfolioName;
	}
	
	/**
	 * This will set the name of the portfolio to whatever has been entered for 
	 * the string value S.
	 * 
	 * @param s 
	 */
	public void setPortfolioName(String s){
		portfolioName = s;
	}
	
}
