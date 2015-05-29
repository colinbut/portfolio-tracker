/**
 * 
 */
package com.mycompany.portfolio_tracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;
import com.mycompany.portfolio_tracker.model.Portfolio;
import com.mycompany.portfolio_tracker.model.Quote;
import com.mycompany.portfolio_tracker.model.QuoteImpl;
import com.mycompany.portfolio_tracker.model.StockImpl;
import com.mycompany.portfolio_tracker.view.AddWindow;
import com.mycompany.portfolio_tracker.view.MainWindow;

/**
 * @author colin
 *
 */
public class AddController implements ActionListener {
	
	private Quote quote; 
	private MainWindow gui;
	private Portfolio portfolio;
	private AddWindow addWindow;
	
	/**
	 * Constructor
	 * 
	 * @param gui
	 * @param portfolio
	 * @param addWindow
	 */
	public AddController(MainWindow gui, Portfolio portfolio, AddWindow addWindow) {
		quote = new QuoteImpl(true);
		this.portfolio = portfolio;
		this.gui = gui;
		this.addWindow = addWindow;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		// get input - read from gui
		String ticker = addWindow.getTicker();
		String volumeStr = addWindow.getNumberOfShares();
		double currentPrice = 0;

		try {
			quote.setValues(ticker); // set the Ticker to check if it's valid?

			currentPrice = quote.getLatest(); // If Success then get share
			// price..
			double change = quote.getChange();
			String stockName = ((QuoteImpl)quote).getStockName();

			try {
				double volume = Double.parseDouble(volumeStr);
				if (volume <= quote.getVolume()) {
					// UPDATE MODEL
					int shareNo = Integer.parseInt(addWindow.getNumberOfShares());
					StockImpl stock = new StockImpl(ticker, shareNo,
							currentPrice, stockName);// Make Stock object
					stock.setChange(change);
					portfolio.addStock(stock); // ADD to Portfolio (MODEL)
					// Update GUI
					gui.AddRow(ticker);
				} else {
					gui.produceDialogs("Number of shares available exceeded limit");
				}
			} catch (NumberFormatException e1) {
				gui.produceDialogs("Number of shares must be a valid positive number");
			} catch (Exception e) {
				gui.produceDialogs(e.getMessage());
			}

		} catch (IOException e1) {
			gui.produceDialogs(e1.getMessage());
		}

		catch (WebsiteDataException e1) {
			gui.produceDialogs(e1.getMessage());
		} catch (NoSuchTickerException e1) {
			gui.produceDialogs(e1.getMessage() + "\n"
					+ "Remember: For UK shares; add .l");
		} catch (MethodException e1) {
			gui.produceDialogs(e1.getMessage());
		} catch (Exception e1) {
			gui.produceDialogs("Input Error\n" + "Please check input...\n"
					+ "Remember: For UK shares; add .l");
		}

		addWindow.dispose();
	}

}
