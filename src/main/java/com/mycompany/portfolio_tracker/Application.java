package com.mycompany.portfolio_tracker;

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
		Thread t = new Thread(new MainWindow());
		t.start();
		// new mainWindow();
	}
	
}
