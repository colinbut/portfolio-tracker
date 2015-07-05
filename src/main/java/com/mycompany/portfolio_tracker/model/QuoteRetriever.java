/**
 * 
 */
package com.mycompany.portfolio_tracker.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
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
 * 
 * @author colin
 */
public class QuoteRetriever {

	private static boolean useProxy;
	
	/**
	 * This method gets the web page containing the share price information and
	 * sets up the data for reterival
	 * 
	 * @param useProxy true if you want to use the university proxy server
	 * 						else false not to.
	 * 
	 * @throws IOException
	 * @throws WebsiteDataException
	 * @throws NoSuchTickerException
	 * @throws MethodException
	 * 
	 * @requires: tickerSymbol != null || tickerSymbol != ""
	 * 
	 */
	public static List<String> getValues(String tickerSymbol, boolean useProxy) throws IOException,
			WebsiteDataException, NoSuchTickerException, MethodException {

		List<String> _shareDataList = new ArrayList<>();
		
		String url = "http://finance.yahoo.com/d/quotes.csv?s=";
		String fileFormat = "&f=nsl1d1t1c1ohgv&e=.csv";
		
		if (tickerSymbol == null || tickerSymbol == "") {

			throw new NoSuchTickerException(tickerSymbol);

		} else {

			BufferedReader webPage = null;

			String str = null;

			try {	
				if(useProxy){
					//TODO:
				}

				// open web page, read the file, and buffer it for speed
				webPage = new BufferedReader(
							new InputStreamReader(
									new URL(url + tickerSymbol + fileFormat).openStream()));

			} catch (Exception e) {
				e.printStackTrace();
				//throw new WebsiteDataException(url);
			}

			// read the line from the file
			str = webPage.readLine().toString();
			// strip the file of any punctuation
			String s = removePunc(str);
			
			// add the elements to an array with , as a deliminator
			_shareDataList = Arrays.asList(s.split(","));

			// close the file.
			webPage.close();

			// catches a non-existent share.
			// since the latest value on a non-existent will always be zero
			if (_shareDataList.get(2).equalsIgnoreCase("0.00")) {
				throw new NoSuchTickerException("No Such Ticker");
			}
		}
		
		return _shareDataList;

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
