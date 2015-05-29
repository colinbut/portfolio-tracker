package com.mycompany.portfolio_tracker.model;

/**
 * 
 * @author colin
 *
 */
public interface Stock {
	
	/**
	 * 
	 * @param stockName
	 */
	void setStockName(String stockName);
	
	/**
	 * 
	 * @param newTicker
	 */
	void setTickerSymbol(String newTicker);
	
	/**
	 * 
	 * @return
	 */
	double getHoldingValue();
	
	/**
	 * 
	 * @return
	 */
	String getStockName();
	
	/**
	 * 
	 * @return
	 */
	String getTickerSymbol();
	
	/**
	 * 
	 * @return
	 */
	int getNumberOfShares();
	
	/**
	 * 
	 * @param newAmount
	 */
	void setNumberOfShares(int newAmount);
	
	/**
	 * 
	 */
	void calculateHoldingValue();
	
	/**
	 * 
	 * @param newPrice
	 */
	void setCurrentPrice(double newPrice);
	
	/**
	 * 
	 * @return
	 */
	double getCurrentPrice();
	
	/**
	 * 
	 * @return
	 */
	double getOldPrice();
	
	/**
	 * 
	 * @param change
	 */
	void setChange(double change);
	
	/**
	 * 
	 * @return
	 */
	double getChange();

}
