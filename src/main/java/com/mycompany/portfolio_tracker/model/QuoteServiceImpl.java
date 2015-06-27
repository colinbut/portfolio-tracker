/**
 * 
 */
package com.mycompany.portfolio_tracker.model;

import java.io.IOException;
import java.util.List;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;

/**
 * @author colin
 *
 */
public class QuoteServiceImpl implements QuoteService {


	/*
	 * Move to utility class
	 */
	private boolean ensureNotNull(String str) throws MethodException{
		//Objects.re
		if(str == null)
			throw new MethodException(str);
		else
			return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.QuoteService#getQuoteData(java.lang.String)
	 */
	@Override
	public Quote getQuoteData(String tickerSymbol) throws IOException, WebsiteDataException, 
											NoSuchTickerException, MethodException{
		
		List<String> shareDataList;
		
		try {
			
			shareDataList = QuoteRetriever.getValues(tickerSymbol, false);
			
			QuoteBuilder quoteBuilder = new QuoteBuilder();
			quoteBuilder.withStockName(ensureNotNull(shareDataList.get(0)) ? shareDataList.get(0) : null)
						.withTicker(ensureNotNull(shareDataList.get(1)) ? shareDataList.get(1) : null)
						.withDate(ensureNotNull(shareDataList.get(3)) ? shareDataList.get(3) : null)
						.withDate(ensureNotNull(shareDataList.get(4)) ? shareDataList.get(4):null)
						.withLatest(ensureNotNull(shareDataList.get(2)) ? Double.valueOf(shareDataList.get(2)):null)
						.withChange(ensureNotNull(shareDataList.get(5)) ? Double.valueOf(shareDataList.get(5)):null)
						.withOpen(ensureNotNull(shareDataList.get(6)) ? Double.valueOf(shareDataList.get(6)):null)
						.withMaxRange(ensureNotNull(shareDataList.get(7)) ? Double.valueOf(shareDataList.get(7)):null)
						.withMinRange(ensureNotNull(shareDataList.get(8)) ? Double.valueOf(shareDataList.get(8)):null)
						.withVolume(ensureNotNull(shareDataList.get(9)) ? Double.valueOf(shareDataList.get(9)):null);
			
			return quoteBuilder.build();
			
		} catch (IOException | WebsiteDataException | NoSuchTickerException e) {
			throw e;
		}
		
	}
}

