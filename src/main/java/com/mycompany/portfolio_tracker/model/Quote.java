package com.mycompany.portfolio_tracker.model;

/** 
 * Quote class that represents a stock 'quote'
 *
 * @author Colin
 */
public class Quote {

	private String tickerSymbol;
	
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
	 * @return the tickerSymbol
	 */
	public String getTickerSymbol() {
		return tickerSymbol;
	}

	/**
	 * @param tickerSymbol the tickerSymbol to set
	 */
	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	/**
	 * @return the stockName
	 */
	public String getStockName() {
		return stockName;
	}

	/**
	 * @param stockName the stockName to set
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	/**
	 * This method returns the value of the ticker
	 * 
	 * @effects String ticker: if ticker == null throws MethodException else
	 *          returns the ticker symbol for the company
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * This method returns the latest stock price
	 * 
	 * @effects Double latestValue: if latestValue == null throw MethodException
	 *          else return the latest stock value
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the latest
	 */
	public Double getLatest() {
		return latest;
	}

	
	/**
	 * @param latest the latest to set
	 */
	public void setLatest(Double latest) {
		this.latest = latest;
	}

	/**
	 * This method returns the date of the share values in format mm/dd/yy
	 * 
	 * @effects Double date: if date == null throw MethodException else returns
	 *          the date of the share values
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * This Double returns the time of the latest share transactions in format HH:MM:AM/PM
	 * 
	 * @effects String time: if time == null throw MethodException else returns
	 *          the time of the latest share transaction
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * This method returns the change value of the share, if the share value has
	 * risen then it will return a value of +x.xx, however if the share value
	 * has fallen it will return -x.xx
	 * 
	 * @effects Double change: if change == null throw MethodException else
	 *          returns the change value of the share
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the change
	 */
	public Double getChange() {
		return change;
	}

	/**
	 * @param change the change to set
	 */
	public void setChange(Double change) {
		this.change = change;
	}

	/**
	 * This method returns the days opening share prive
	 * 
	 * @effects Double open: if open == null throw MethodException else returns
	 *          the days opening share price
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the open
	 */
	public Double getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Double open) {
		this.open = open;
	}

	/**
	 * This method returns the days range maximum value
	 * 
	 * @effects Double rangeMax: if rangeMax == null throw MethodException else
	 *          returns the days range maximum value
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the maxRange
	 */
	public Double getMaxRange() {
		return maxRange;
	}

	/**
	 * @param maxRange the maxRange to set
	 */
	public void setMaxRange(Double maxRange) {
		this.maxRange = maxRange;
	}

	/**
	 * This method returns the days range minimum value
	 * 
	 * @effects Double rangeMin: if rangeMin == null throw Method Exception else
	 *          returns the days range minimum value
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the minRange
	 */
	public Double getMinRange() {
		return minRange;
	}

	/**
	 * @param minRange the minRange to set
	 */
	public void setMinRange(Double minRange) {
		this.minRange = minRange;
	}

	/**
	 * This method returns the volume of shares available
	 * 
	 * @effects Double volume: if volume == null throw MethodException else
	 *          returns the volume of the shares available
	 * 
	 * @throws MethodException
	 * 
	 */
	/**
	 * @return the volume
	 */
	public Double getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
	}

}

