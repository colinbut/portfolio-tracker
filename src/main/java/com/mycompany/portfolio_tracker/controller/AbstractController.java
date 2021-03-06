/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.controller;

import com.mycompany.portfolio_tracker.model.Quote;
import com.mycompany.portfolio_tracker.view.MainWindow;
import com.mycompany.portfolio_tracker.view.components.MyTableView;

/**
 * AbstractController class
 * 
 * Common controller containing common resources
 * 
 * @author colin
 *
 */
public class AbstractController {

	protected MainWindow gui; //view
	private MyTableView ft; //Table View --> get model from there
	protected Quote quote;
	
	/**
	 * Returns the current selection table
	 * 
	 * @return MyTableView
	 */
	public MyTableView getCurrentSelectionTable(){
		ft = (MyTableView)gui.getTabs().getSelectedComponent();
		return ft;
	}
}
