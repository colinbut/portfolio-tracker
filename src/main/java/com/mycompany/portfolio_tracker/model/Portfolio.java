package com.mycompany.portfolio_tracker.model;

import java.util.Collection;


public interface Portfolio {

	public void addStock(StockImpl stock);
	public void calculateTotal();
	public int getTotalValue();
	public void removeStock(String ticker);
	public void removeAllStock();
	public StockImpl getStock(String ticker);
	public Collection<StockImpl> getAllStocks();
	public String getPortfolioName();
	public void setPortfolioName(String s);
}
