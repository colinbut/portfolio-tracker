/**
 * 
 */
package com.mycompany.portfolio_tracker.controller;

import com.mycompany.portfolio_tracker.model.Quote;
import com.mycompany.portfolio_tracker.view.MainWindow;
import com.mycompany.portfolio_tracker.view.components.MyTableView;

/**
 * @author colin
 *
 */
public class AbstractController {

	protected MainWindow gui; //view
	private MyTableView ft; //Table View --> get model from there
	protected Quote quote;
	
	public MyTableView getCurrentSelectionTable(){
		ft = (MyTableView)gui.getTabs().getSelectedComponent();
		return ft;
	}
}
