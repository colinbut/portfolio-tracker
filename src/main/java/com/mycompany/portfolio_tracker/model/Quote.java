package com.mycompany.portfolio_tracker.model;

import java.io.IOException;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;

/**
 * public interface that allows the setting of and retrieval of share stock values
 * 
 * @author colin
 *
 * @version v1 - interface added to complement <code>Quote</code> class
 *
 * All rights reserved. Copyright: 21 Nov 2008 15:06:18
 */
public interface Quote {

	/**
	 * This method returns the value of the ticker
	 * 
	 * @effects String ticker: if ticker == null throws MethodException else
	 *          returns the ticker symbol for the company
	 * 
	 * @throws MethodException
	 * 
	 */
	String getTicker() throws MethodException;

	/**
	 * This method returns the latest stock price
	 * 
	 * @effects Double latestValue: if latestValue == null throw MethodException
	 *          else return the latest stock value
	 * 
	 * @throws MethodException
	 * 
	 */
	Double getLatest() throws MethodException;

	/**
	 * This method returns the date of the share values in format mm/dd/yy
	 * 
	 * @effects Double date: if date == null throw MethodException else returns
	 *          the date of the share values
	 * 
	 * @throws MethodException
	 * 
	 */
	String getDate() throws MethodException;

	/**
	 * This Double returns the time of the latest share transactions in format HH:MM:AM/PM
	 * 
	 * @effects String time: if time == null throw MethodException else returns
	 *          the time of the latest share transaction
	 * 
	 * @throws MethodException
	 * 
	 */
	String getTime() throws MethodException ;

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
	Double getChange() throws MethodException ;

	/**
	 * This method returns the days opening share prive
	 * 
	 * @effects Double open: if open == null throw MethodException else returns
	 *          the days opening share price
	 * 
	 * @throws MethodException
	 * 
	 */
	Double getOpen() throws MethodException;
	
	/**
	 * This method returns the days range maximum value
	 * 
	 * @effects Double rangeMax: if rangeMax == null throw MethodException else
	 *          returns the days range maximum value
	 * 
	 * @throws MethodException
	 * 
	 */
	Double getRangeMax() throws MethodException ;

	/**
	 * This method returns the days range minimum value
	 * 
	 * @effects Double rangeMin: if rangeMin == null throw Method Exception else
	 *          returns the days range minimum value
	 * 
	 * @throws MethodException
	 * 
	 */
	Double getRangeMin() throws MethodException;

	/**
	 * This method returns the volume of shares available
	 * 
	 * @effects Double volume: if volume == null throw MethodException else
	 *          returns the volume of the shares available
	 * 
	 * @throws MethodException
	 * 
	 */
	Double getVolume() throws MethodException;
	
}
