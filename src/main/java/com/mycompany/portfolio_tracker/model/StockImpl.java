package com.mycompany.portfolio_tracker.model;

/**
 * 
 * @author colin
 *
 */
public class StockImpl implements Stock{
	
	private String stockName;
	private String tickerSymbol;
	private int numberOfShares;
	private double holdingValue;
	private double currentPrice;
	private double change;
	private double oldPrice;
	
	/**
	 * Constructor
	 * 
	 * @param ticker
	 * @param numberofShares
	 * @param currentPrice
	 * @param name
	 */
	public StockImpl(String ticker, int numberofShares, double currentPrice, String name) {
		tickerSymbol = ticker;
		this.numberOfShares = numberofShares;
		this.currentPrice = currentPrice;
		oldPrice = 0.0;
		stockName = name;
		change = 0.0;
		calculateHoldingValue();
	}
	
	/*This method sets the name of the stock*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#setStockName(java.lang.String)
	 */
	@Override
	public void setStockName(String stockName){
		this.stockName = stockName;
	}
	
	/* This sets the ticker symbol to be the new ticker symbol*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#setTickerSymbol(java.lang.String)
	 */
	@Override
	public void setTickerSymbol(String newTicker){
		tickerSymbol = newTicker;
	}
	
	/* This will return the holding value*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#getHoldingValue()
	 */
	@Override
	public double getHoldingValue(){
		return holdingValue;
	}
	
	/* The stock name is returned*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#getStockName()
	 */
	@Override
	public String getStockName(){
		return stockName;
	}
	
	/* The ticker symbol is returned is returned*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#getTickerSymbol()
	 */
	@Override
	public String getTickerSymbol(){
		return tickerSymbol;
	}
	
	/* The number of shares is returned*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#getNumberOfShares()
	 */
	@Override
	public int getNumberOfShares(){
		return numberOfShares;
	}
	
	/* This sets the number of shares to be the new amount and then the holding value is calculated.*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#setNumberOfShares(int)
	 */
	@Override
	public void setNumberOfShares(int newAmount) {
		numberOfShares = newAmount;
		calculateHoldingValue();
	}
	
	/* This method calculates the holding value by multiplying the current price by the number of shares.*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#calculateHoldingValue()
	 */
	@Override
	public void calculateHoldingValue() {
		holdingValue = currentPrice * numberOfShares;
	}
	
	/* The old price is set to the current price and then the current price is et to the new price. The holding value is then calculated.*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#setCurrentPrice(double)
	 */
	@Override
	public void setCurrentPrice(double newPrice) {
		oldPrice = currentPrice;
		currentPrice = newPrice;
		calculateHoldingValue();
	}
	
	/* The current price of the stock is returned*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#getCurrentPrice()
	 */
	@Override
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	/* This will return the old price of the stock.*/
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#getOldPrice()
	 */
	@Override
	public double getOldPrice() {
		return oldPrice;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#setChange(double)
	 */
	@Override
	public void setChange(double change){
		this.change = change;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Stock#getChange()
	 */
	@Override
	public double getChange(){
		return change;
	}
	
}

