package com.mycompany.portfolio_tracker.model;

import java.util.*;

public class PortfolioImpl implements Portfolio{
	
	private String portfolioName;
	private int totalValue;
	private ArrayList<StockImpl> stocks;
	
	public PortfolioImpl() {
		//this.portfolioName = portfolioName;
		stocks = new ArrayList<StockImpl>();
		totalValue = 0;
	}
	
	/*
	 * Ads
	 * @param stock is passed in
	 */
	public void addStock(StockImpl stock){
		stocks.add(stock);
		calculateTotal();
	}
	
	/*
	 * (non-Javadoc)
	 * @see IPortfolio#calculateTotal()
	 */
	public void calculateTotal() {
		totalValue = 0;
		for(int i =0; i<stocks.size(); i++) {
			totalValue += stocks.get(i).getHoldingValue();
		}
	}
	/* The total value of the portfolio is returned*/
	public int getTotalValue(){
		return totalValue;
	}
	
	public void removeStock(String ticker) {
		
		for(int i =0; i<stocks.size(); i++) {
			if(stocks.get(i).getTickerSymbol().equals(ticker)) {
				stocks.remove(stocks.get(i));
				calculateTotal();
			}
		}
	}
	/* This will remove all of the stocks that have been added to the portfolio.*/
	public void removeAllStock(){
		stocks.removeAll(stocks);
	}
	
	public StockImpl getStock(String ticker){
        StockImpl temp = new StockImpl("", 0, 0.0, "");
		for(int i =0; i<stocks.size(); i++) {
			if(stocks.get(i).getTickerSymbol().equals(ticker)) {
				temp = stocks.get(i);
			}
		}
		return temp;
		
	}
	
	public ArrayList<StockImpl> getAllStocks(){
		return stocks;
	}
	
	/* This will return the name of the portfolio. */
	public String getPortfolioName() {
		
		return portfolioName;
	}
	/* This will set the name of the portfolio to whatever has been entered for the string value S. */
	public void setPortfolioName(String s){
		portfolioName = s;
	}
	
	
}
