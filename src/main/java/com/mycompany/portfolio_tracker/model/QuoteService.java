/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.model;

import java.io.IOException;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;

/**
 * A service for querying data from a source
 * 
 * @author colin
 *
 */
public interface QuoteService {

	/**
	 * Gets the quote data
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
