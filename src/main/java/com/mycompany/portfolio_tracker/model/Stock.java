package com.mycompany.portfolio_tracker.model;


public interface Stock {
	
	public void setStockName(String stockName);
	public void setTickerSymbol(String newTicker);
	public double getHoldingValue();
	public String getStockName();
	public String getTickerSymbol();
	public int getNumberOfShares();
	public void setNumberOfShares(int newAmount);
	public void calculateHoldingValue();
	public void setCurrentPrice(double newPrice);
	public double getCurrentPrice();
	public double getOldPrice();
	public void setChange(double change);
	public double getChange();

}
