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
	void addStock(Stock stock);
	
	/**
	 * 
	 */
	void calculateTotal();
	
	/**
	 * The total value of the portfolio is returned
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
	 * This will remove all of the stocks that have been added to the portfolio.
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

	
}
