package com.mycompany.portfolio_tracker;

import com.mycompany.portfolio_tracker.model.QuoteService;
import com.mycompany.portfolio_tracker.model.QuoteServiceImpl;
import com.mycompany.portfolio_tracker.view.MainWindow;

/**
 * Application 
 * 
 * @author colin
 *
 */
public class Application {

	/**
	 * Main entry
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		
		QuoteService quoteService = new QuoteServiceImpl();
		
		Thread t = new Thread(new MainWindow(quoteService));
		t.start();
		
	}
	
}
