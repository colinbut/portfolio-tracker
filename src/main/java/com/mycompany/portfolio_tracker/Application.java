/**
 * 
 */
package com.mycompany.portfolio_tracker;

import com.mycompany.portfolio_tracker.view.MainWindow;

/**
 * @author colin
 *
 */
public class Application {

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Thread t = new Thread(new MainWindow());
		t.start();
		// new mainWindow();
	}
	
}
