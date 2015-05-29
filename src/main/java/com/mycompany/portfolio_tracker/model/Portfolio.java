package com.mycompany.portfolio_tracker.model;

import java.util.List;

/**
 * 
 * @author colin
 *
 */
public interface Portfolio {

	/**
	 * 
	 * @param stock
	 */
	void addStock(StockImpl stock);
	
	/**
	 * 
	 */
	void calculateTotal();
	
	/**
	 * 
	 * @return
	 */
	int getTotalValue();
	
	/**
	 * 
	 * @param ticker
	 */
	void removeStock(String ticker);
	
	/**
	 * 
	 */
	void removeAllStock();
	
	/**
	 * 
	 * 
	 * @param ticker
	 * @return
	 */
	Stock getStock(String ticker);
	
	/**
	 * 
	 * @return
	 */
	List<Stock> getAllStocks();
	
	/**
	 * 
	 * @return
	 */
	String getPortfolioName();
	
	/**
	 * 
	 * @param s
	 */
	void setPortfolioName(String s);
}
