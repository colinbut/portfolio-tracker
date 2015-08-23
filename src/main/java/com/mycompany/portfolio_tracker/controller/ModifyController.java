/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.model.Portfolio;
import com.mycompany.portfolio_tracker.model.Quote;
import com.mycompany.portfolio_tracker.model.Stock;
import com.mycompany.portfolio_tracker.view.ModifyWindow;
import com.mycompany.portfolio_tracker.view.MainWindow;
import com.mycompany.portfolio_tracker.view.components.MyTableView;

/**
 * @author colin
 *
 */
public class ModifyController implements ActionListener {

	private Portfolio portfolio;
    private String tickerSymbol;
    private MainWindow gui;
    private MyTableView ft;
    private Quote quote;
    private ModifyWindow modifyWindow;
    
    /**
     * Constructor
     * 
     * @param portfolio
     * @param gui
     * @param ticker
     * @param modifyWindow
     */
    public ModifyController(Portfolio portfolio, MainWindow gui, String ticker, ModifyWindow modifyWindow) {
    	this.modifyWindow = modifyWindow;
    	this.tickerSymbol = ticker;
    	this.gui = gui;
    	quote = new Quote();
    }
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt){
		//update model
		Stock selectedStock = portfolio.getStock(tickerSymbol);
		double volume = Double.parseDouble(modifyWindow.getInputFromModify());
		//try {
			
			quote.setTickerSymbol(tickerSymbol);
			
			if(volume <= quote.getVolume()){
				selectedStock.setNumberOfShares((int)volume);
			}
			else{
				gui.produceDialogs("Exceeded maximum number of available shares");
			}
		//} 
//		catch(MethodException e) {
//			gui.produceDialogs("Error, Please check and try again");
//		}
		
		portfolio.calculateTotal();
		
		//update GUI
		gui.editRow(ft, tickerSymbol);
		modifyWindow.dispose();
		
	}
}
