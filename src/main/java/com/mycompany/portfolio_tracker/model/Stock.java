/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.model;

/**
 * Stock class
 * 
 * POJO represents a stock
 * 
 * @author colin
 *
 */
public class Stock {
	
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
	public Stock(String ticker, int numberofShares, double currentPrice, String name) {
		tickerSymbol = ticker;
		this.numberOfShares = numberofShares;
		this.currentPrice = currentPrice;
		oldPrice = 0.0;
		stockName = name;
		change = 0.0;
		calculateHoldingValue();
	}
	
	/** 
	 * This method sets the name of the stock
	 */
	public void setStockName(String stockName){
		this.stockName = stockName;
	}
	
	/** 
	 * This sets the ticker symbol to be the new ticker symbol
	 */
	public void setTickerSymbol(String newTicker){
		tickerSymbol = newTicker;
	}
	
	/** 
	 * This will return the holding value
	 */
	public double getHoldingValue(){
		return holdingValue;
	}
	
	/** 
	 * The stock name is returned
	 */
	public String getStockName(){
		return stockName;
	}
	
	/** 
	 * The ticker symbol is returned is returned
	 */
	public String getTickerSymbol(){
		return tickerSymbol;
	}
	
	/** 
	 * The number of shares is returned
	 */
	public int getNumberOfShares(){
		return numberOfShares;
	}
	
	/** 
	 * This sets the number of shares to be the new amount and then the holding value is calculated.
	 */
	public void setNumberOfShares(int newAmount) {
		numberOfShares = newAmount;
		calculateHoldingValue();
	}
	
	/** 
	 * This method calculates the holding value by multiplying the current price by the number of shares.
	 */
	public void calculateHoldingValue() {
		holdingValue = currentPrice * numberOfShares;
	}
	
	/** 
	 * The old price is set to the current price and then the current price is et to the new price. The holding value is then calculated.
	 */
	public void setCurrentPrice(double newPrice) {
		oldPrice = currentPrice;
		currentPrice = newPrice;
		calculateHoldingValue();
	}
	
	/** 
	 * The current price of the stock is returned
	 * 
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	/** 
	 * This will return the old price of the stock.
	 */
	public double getOldPrice() {
		return oldPrice;
	}
	
	/**
	 * Sets the change in stock price
	 * 
	 * @param change
	 */
	public void setChange(double change){
		this.change = change;
	}
	
	/**
	 * Gets the change in stock price
	 * 
	 * @return
	 */
	public double getChange(){
		return change;
	}
	
}

