/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mycompany.portfolio_tracker.model.Portfolio;
import com.mycompany.portfolio_tracker.view.DeleteWindow;
import com.mycompany.portfolio_tracker.view.MainWindow;
import com.mycompany.portfolio_tracker.view.components.MyTableView;

/**
 * Controller for the Delete modal
 * 
 * @author colin
 *
 */
public class DeleteController implements ActionListener {

	private Portfolio portfolio;
	private MainWindow gui;
	private String tickerSymbol;
	private int rowNumber;
	private MyTableView tableView;
	private DeleteWindow deleteWindow;
	
	/**
	 * Constructor
	 * 
	 * @param gui
	 * @param portfolio
	 */
	public DeleteController(MainWindow gui, MyTableView tableView, String tickeySymbol, int rowNumber, DeleteWindow deleteWindow) {
		this.gui = gui;
		this.portfolio = tableView.getPortfolio();
		this.rowNumber = rowNumber;
		this.tableView = tableView;
		this.tickerSymbol = tickeySymbol;
		this.deleteWindow = deleteWindow; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt){
		
		//UPDATE MODEL 
		portfolio.removeStock(tickerSymbol);
		
		//Update GUI
		gui.deleteRow(tableView, rowNumber);
		deleteWindow.dispose();
	}
}

