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
		
		List<String> _shareDataList;
		
		try {
			_shareDataList = QuoteRetrieverUtil.getValues(tickerSymbol, false);
			
			QuoteBuilder quoteBuilder = new QuoteBuilder();
			quoteBuilder.withStockName(ensureNotNull(_shareDataList.get(0)) ? _shareDataList.get(0) : null)
						.withTicker(ensureNotNull(_shareDataList.get(1)) ? _shareDataList.get(1) : null)
						.withDate(ensureNotNull(_shareDataList.get(3)) ? _shareDataList.get(3) : null)
						.withDate(ensureNotNull(_shareDataList.get(4)) ? _shareDataList.get(4):null)
						.withLatest(ensureNotNull(_shareDataList.get(2)) ? Double.valueOf(_shareDataList.get(2)):null)
						.withChange(ensureNotNull(_shareDataList.get(5)) ? Double.valueOf(_shareDataList.get(5)):null)
						.withOpen(ensureNotNull(_shareDataList.get(6)) ? Double.valueOf(_shareDataList.get(6)):null)
						.withMaxRange(ensureNotNull(_shareDataList.get(7)) ? Double.valueOf(_shareDataList.get(7)):null)
						.withMinRange(ensureNotNull(_shareDataList.get(8)) ? Double.valueOf(_shareDataList.get(8)):null)
						.withVolume(ensureNotNull(_shareDataList.get(9)) ? Double.valueOf(_shareDataList.get(9)):null);
			
			return quoteBuilder.build();
			
		} catch (IOException | WebsiteDataException | NoSuchTickerException e) {
			throw e;
		}
		
	}
}

