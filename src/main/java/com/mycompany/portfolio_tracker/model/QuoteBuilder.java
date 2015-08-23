/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.model;

/**
 * QuoteBuilder class that represents the 'builder' to construct a
 * Quote model object. 
 * 
 * This is the 'Builder' design pattern
 * 
 * @author colin
 *
 */
public class QuoteBuilder {
	
	private String stockName;
	private String ticker;
	private Double latest;
	private String date;
	private String time;
	private Double change;
	private Double open;
	private Double maxRange;
	private Double minRange;
	private Double volume;
	
	/**
	 * @param stockName the stockName to set
	 */
	public QuoteBuilder withStockName(String stockName) {
		this.stockName = stockName;
		return this;
	}


	/**
	 * @param ticker the ticker to set
	 */
	public QuoteBuilder withTicker(String ticker) {
		this.ticker = ticker;
		return this;
	}

	/**
	 * @param latest the latest to set
	 */
	public QuoteBuilder withLatest(Double latest) {
		this.latest = latest;
		return this;
	}

	/**
	 * @param date the date to set
	 */
	public QuoteBuilder withDate(String date) {
		this.date = date;
		return this;
	}

	/**
	 * @param time the time to set
	 */
	public QuoteBuilder withTime(String time) {
		this.time = time;
		return this;
	}

	/**
	 * @param change the change to set
	 */
	public QuoteBuilder withChange(Double change) {
		this.change = change;
		return this;
	}

	/**
	 * @param open the open to set
	 */
	public QuoteBuilder withOpen(Double open) {
		this.open = open;
		return this;
	}

	/**
	 * @param maxRange the maxRange to set
	 */
	public QuoteBuilder withMaxRange(Double maxRange) {
		this.maxRange = maxRange;
		return this;
	}

	/**
	 * @param minRange the minRange to set
	 */
	public QuoteBuilder withMinRange(Double minRange) {
		this.minRange = minRange;
		return this;
	}

	/**
	 * @param volume the volume to set
	 */
	public QuoteBuilder withVolume(Double volume) {
		this.volume = volume;
		return this;
	}
	
	/**
	 * Constructs the Quote object
	 * 
	 * @return the quote object
	 */
	public Quote build(){
		
		Quote quote = new Quote();
		quote.setStockName(stockName);
		quote.setChange(change);
		quote.setDate(date);
		quote.setLatest(latest);
		quote.setMaxRange(maxRange);
		quote.setMinRange(minRange);
		quote.setOpen(open);
		quote.setTicker(ticker);
		quote.setTime(time);
		quote.setVolume(volume);
		
		return quote;
	}
	
}
