package com.mycompany.portfolio_tracker.model;

import java.util.*;

/**
 * Implementation of the Portfolio interface
 * 
 * @author colin
 *
 */
public class PortfolioImpl extends AbstractBasePortfolio {
	
	private int totalValue;
	private List<Stock> stocks;
	
	/**
	 * Constructor
	 */
	public PortfolioImpl() {
		stocks = new ArrayList<Stock>();
		totalValue = 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addStock(Stock stock){
		stocks.add(stock);
		calculateTotal();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculateTotal() {
		totalValue = 0;
		for(int i =0; i<stocks.size(); i++) {
			totalValue += stocks.get(i).getHoldingValue();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTotalValue(){
		return totalValue;
	}
	
	/**
	 * {@inheritDoc}
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAllStock(){
		stocks.removeAll(stocks);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Stock getStock(String ticker){
        Stock temp = new Stock("", 0, 0.0, "");
		for(int i =0; i<stocks.size(); i++) {
			if(stocks.get(i).getTickerSymbol().equals(ticker)) {
				temp = stocks.get(i);
			}
		}
		return temp;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Stock> getAllStocks(){
		return stocks;
	}
	
}
