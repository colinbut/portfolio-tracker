package com.mycompany.portfolio_tracker.model;

import java.net.URL;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;

/** 
 * This class Quote.java retrieves a file from the yahoo webpage, and reads
 * in the sharedata and makes it avaliable for use with a share application.
 * 
 * <a href="http://finance.yahoo.com">
 * 
 *
 * @author Colin
 * @date 31/10/2005.
 * @version 1.2
 */
public class QuoteImpl implements Quote {

	/**
	 * Quote is a utility class that allows calling code to retrieve the latest
	 * market value of a given stock by ticker symbol. The Quote obtains the
	 * stock value by using the website, and opens a .csv (comma separated values) file: <a
	 * href="http://finance.yahoo.com">
	 * <tt>http://finance.yahoo.com/d/quotes.csv?s</tt></a> <Br>
	 * Examples of ticker symbols are <tt>MSFT</tt> and <tt>orcl</tt>. (Note
	 * that ticker symbols are not case sensitive. That is that "MSFT" and
	 * "msft" are functionally equivalent.)
	 * <p>
	 * 
	 * A valid ticker symbol is one that is currently registered with any world
	 * market
	 */
	private List<String> _shareDataList;
	private boolean _useProxy;
	
	/**
	 * Default Constructor
	 * 
	 * @param useProxy true if you want to use the university proxy server
	 * 						else false not to.
	 */
	public QuoteImpl(boolean useProxy) {
		_useProxy = useProxy;
	}

	/**
	 * This method gets the web page containing the share price information and
	 * sets up the data for reterival
	 * 
	 * @throws IOException
	 * @throws WebsiteDataException
	 * @throws NoSuchTickerException
	 * @throws MethodException
	 * 
	 * @requires: tickerSymbol != null || tickerSymbol != ""
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#setValues(java.lang.String)
	 */
	@Override
	public void setValues(String tickerSymbol) throws IOException,
			WebsiteDataException, NoSuchTickerException, MethodException {

		String url = "http://finance.yahoo.com/d/quotes.csv?s=";
		String fileFormat = "&f=nsl1d1t1c1ohgv&e=.csv";
		
		if (tickerSymbol == null || tickerSymbol == "") {

			throw new NoSuchTickerException(tickerSymbol);

		} else {

			BufferedReader webPage = null;

			String str = null;

			try {	
				if(_useProxy){//University proxy settings
					System.getProperties().put("proxySet", "true");

					System.getProperties().put("proxyHost",
						"www-cache2.strath.ac.uk");
	
					System.getProperties().put("proxyPort", "8080");
				}//end if


				// open web page, read the file, and buffer it for speed
				webPage = new BufferedReader(
							new InputStreamReader(
									new URL(url + tickerSymbol + fileFormat).openStream()));

			} catch (Exception e) {
				throw new WebsiteDataException(url);
			}// end try catch

			// read the line from the file
			str = webPage.readLine().toString();
			System.out.println(str);
			// strip the file of any punctuation
			String s = removePunc(str);
			
			// add the elements to an array with , as a deliminator
			_shareDataList = Arrays.asList(s.split(","));

			// close the file.
			webPage.close();

			// catches a non-existent share.
			// since the latest value on a non-existent will always be zero
			if (_shareDataList.get(2).equalsIgnoreCase("0.00"))
				throw new NoSuchTickerException("No Such Ticker");
		}

	}
		
	public String getStockName() throws MethodException {
		return ensureNotNull(_shareDataList.get(0)) ? _shareDataList.get(0):null;
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
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getTicker()
	 */
	@Override
	public String getTicker() throws MethodException {
		return ensureNotNull(_shareDataList.get(1)) ? _shareDataList.get(1):null;
	}

	/**
	 * This method returns the latest stock price
	 * 
	 * @effects String latestValue: if latestValue == null throw MethodException
	 *          else returns the latest stock value
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getLatest()
	 */
	@Override
	public Double getLatest() throws MethodException {
		return ensureNotNull(_shareDataList.get(2)) ? Double.valueOf(_shareDataList.get(2)):null;
	}

	/**
	 * This method returns the date of the share values in format mm/dd/yy
	 * 
	 * @effects String date: if date == null throw MethodException else returns
	 *          the date of the share values
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getDate()
	 */
	@Override
	public String getDate() throws MethodException {
		return ensureNotNull(_shareDataList.get(3)) ? _shareDataList.get(3):null;
	}

	/**
	 * This method returns the time of the latest share transactions in format HH:MM:AM/PM
	 * 
	 * @effects String time: if time == null throw MethodException else returns
	 *          the time of the latest share transaction
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getTime()
	 */
	@Override
	public String getTime() throws MethodException {
		return ensureNotNull(_shareDataList.get(4)) ? _shareDataList.get(4):null;
	}

	/**
	 * This method returns the change value of the share, if the share value has
	 * risen then it will return a value of +x.xx, however if the share value
	 * has fallen it will return x.xx
	 * 
	 * @effects String change: if change == null throw MethodException else
	 *          returns the change value of the share
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getChange()
	 */
	@Override
	public Double getChange() throws MethodException {
		return ensureNotNull(_shareDataList.get(5)) ? Double.valueOf(_shareDataList.get(5)):null;
	}

	/**
	 * This method returns the days opening share prive
	 * 
	 * @effects String open: if open == null throw MethodException else returns
	 *          the days opening share price
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getOpen()
	 */
	@Override
	public Double getOpen() throws MethodException {
		return ensureNotNull(_shareDataList.get(6)) ? Double.valueOf(_shareDataList.get(6)):null;
	}
	
	/**
	 * This method returns the days range maximum value
	 * 
	 * @effects String rangeMax: if rangeMax == null throw MethodException else
	 *          returns the days range maximum value
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getRangeMax()
	 */
	@Override
	public Double getRangeMax() throws MethodException {
		return ensureNotNull(_shareDataList.get(7)) ? Double.valueOf(_shareDataList.get(7)):null;
	}

	/**
	 * This method returns the days range minimum value
	 * 
	 * @effects String rangeMin: if rangeMin == null throw Method Exception else
	 *          returns the days range minimum value
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getRangeMin()
	 */
	@Override
	public Double getRangeMin() throws MethodException {
		return ensureNotNull(_shareDataList.get(8)) ? Double.valueOf(_shareDataList.get(8)):null;
	}

	/**
	 * This method returns the voulme of shares available
	 * 
	 * @effects String volume: if volume == null throw MethodException else
	 *          returns the volume of the shares avaliable
	 * 
	 * @throws MethodException
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getVolume()
	 */
	@Override
	public Double getVolume() throws MethodException {
			return ensureNotNull(_shareDataList.get(9)) ? Double.valueOf(_shareDataList.get(9)):null;
	}

	// -------------------- Private Methods  --------------------
	
	/*
	 * 
	 */
	private boolean ensureNotNull(String str) throws MethodException{
		if(str == null)
			throw new MethodException(str);
		else
			return true;
	}

	/**
	 * Method removePunc: removes all characters we dont't want in our string
	 * 
	 * @throws MethodException
	 * 
	 * @requires String str != null
	 * 
	 * @effects if str == null throws method exception else returns a new string
	 *          with the characters removed that we don't want
	 * 
	 * @modifies str
	 */
	private static String removePunc(String str) throws MethodException {
		// use assertion to ensure not parameter value is != null
		assert str != null : "str == null";

		// handle if str == null
		if (str == null)
			throw new MethodException(str);

		// Strip out the characters we don't want namely " and +
		return Pattern.compile("[\"]").matcher(str).replaceAll("");
	}

}

