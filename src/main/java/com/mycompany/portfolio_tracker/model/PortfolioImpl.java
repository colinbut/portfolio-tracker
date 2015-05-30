package com.mycompany.portfolio_tracker.model;

import java.util.*;

/**
 * 
 * @author colin
 *
 */
public class PortfolioImpl extends AbstractBasePortfolio implements Portfolio{
	
	private int totalValue;
	private List<Stock> stocks;
	
	/**
	 * Constructor
	 */
	public PortfolioImpl() {
		//this.portfolioName = portfolioName;
		stocks = new ArrayList<Stock>();
		totalValue = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Portfolio#addStock(com.mycompany.portfolio_tracker.model.StockImpl)
	 */
	@Override
	public void addStock(Stock stock){
		stocks.add(stock);
		calculateTotal();
	}
	
	/*
	 * (non-Javadoc)
	 * @see IPortfolio#calculateTotal()
	 */
	@Override
	public void calculateTotal() {
		totalValue = 0;
		for(int i =0; i<stocks.size(); i++) {
			totalValue += stocks.get(i).getHoldingValue();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Portfolio#getTotalValue()
	 */
	@Override
	public int getTotalValue(){
		return totalValue;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Portfolio#removeStock(java.lang.String)
	 */
	@Override
	public void removeStock(String ticker) {
		
		for(int i =0; i<stocks.size(); i++) {
			if(stocks.get(i).getTickerSymbol().equals(ticker)) {
				stocks.remove(stocks.get(i));
				calculateTotal();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Portfolio#removeAllStock()
	 */
	@Override
	public void removeAllStock(){
		stocks.removeAll(stocks);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Portfolio#getStock(java.lang.String)
	 */
	@Override
	public Stock getStock(String ticker){
        Stock temp = new StockImpl("", 0, 0.0, "");
		for(int i =0; i<stocks.size(); i++) {
			if(stocks.get(i).getTickerSymbol().equals(ticker)) {
				temp = stocks.get(i);
			}
		}
		return temp;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Portfolio#getAllStocks()
	 */
	@Override
	public List<Stock> getAllStocks(){
		return stocks;
	}
	
}
