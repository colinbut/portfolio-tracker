/**
 * 
 */
package com.mycompany.portfolio_tracker.controller;

import com.mycompany.portfolio_tracker.model.QuoteImpl;
import com.mycompany.portfolio_tracker.view.mainWindow;

/**
 * @author colin
 *
 */
public class AbstractController {

	protected mainWindow gui; //view
	private mainWindow.TableView ft; //Table View --> get model from there
	protected QuoteImpl quote;
	
	public mainWindow.TableView getCurrentSelectionTable(){
		ft = (mainWindow.TableView)gui.getTabs().getSelectedComponent();
		return ft;
	}
}
