/**
 * 
 */
package com.mycompany.portfolio_tracker.model;

import java.io.IOException;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;

/**
 * @author colin
 *
 */
public interface QuoteService {

	/**
	 * 
	 * @param tickerSymbol
	 * @throws IOException
	 * @throws WebsiteDataException
	 * @throws NoSuchTickerException
	 * @throws MethodException
	 */
	Quote getQuoteData(String tickerSymbol) throws IOException, WebsiteDataException, 
	NoSuchTickerException, MethodException;
}
