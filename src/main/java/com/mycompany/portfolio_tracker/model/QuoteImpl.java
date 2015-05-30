package com.mycompany.portfolio_tracker.model;

import java.io.IOException;
import java.util.List;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;

/** 
 * 
 *
 * @author Colin
 */
public class QuoteImpl implements Quote {

	
	private List<String> _shareDataList;
	private String tickerSymbol;
	
	/**
	 * Constructor
	 */
	public QuoteImpl() {
		
	}

	/**
	 * 
	 * @param tickerSymbol
	 */
	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}
	
	/**
	 * 	
	 * @return
	 * @throws IOException, WebsiteDataException, NoSuchTickerException, MethodException 
	 */
	public String getStockName() throws IOException, WebsiteDataException, 
										NoSuchTickerException, MethodException {
		
		List<String> _shareDataList;
		String stockName = null;
		
		try {
			_shareDataList = QuoteRetrieverUtil.getValues(tickerSymbol, false);
			
			stockName = ensureNotNull(_shareDataList.get(0)) ? _shareDataList.get(0) : null;
			
		} catch (IOException | WebsiteDataException | NoSuchTickerException e) {
			throw e;
		}
		
		return stockName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getTicker()
	 */
	@Override
	public String getTicker() throws MethodException {
		return ensureNotNull(_shareDataList.get(1)) ? _shareDataList.get(1):null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getLatest()
	 */
	@Override
	public Double getLatest() throws MethodException {
		return ensureNotNull(_shareDataList.get(2)) ? Double.valueOf(_shareDataList.get(2)):null;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getDate()
	 */
	@Override
	public String getDate() throws MethodException {
		return ensureNotNull(_shareDataList.get(3)) ? _shareDataList.get(3):null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getTime()
	 */
	@Override
	public String getTime() throws MethodException {
		return ensureNotNull(_shareDataList.get(4)) ? _shareDataList.get(4):null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getChange()
	 */
	@Override
	public Double getChange() throws MethodException {
		return ensureNotNull(_shareDataList.get(5)) ? Double.valueOf(_shareDataList.get(5)):null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getOpen()
	 */
	@Override
	public Double getOpen() throws MethodException {
		return ensureNotNull(_shareDataList.get(6)) ? Double.valueOf(_shareDataList.get(6)):null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getRangeMax()
	 */
	@Override
	public Double getRangeMax() throws MethodException {
		return ensureNotNull(_shareDataList.get(7)) ? Double.valueOf(_shareDataList.get(7)):null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mycompany.portfolio_tracker.model.Quote#getRangeMin()
	 */
	@Override
	public Double getRangeMin() throws MethodException {
		return ensureNotNull(_shareDataList.get(8)) ? Double.valueOf(_shareDataList.get(8)):null;
	}

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
		//Objects.re
		if(str == null)
			throw new MethodException(str);
		else
			return true;
	}

}

