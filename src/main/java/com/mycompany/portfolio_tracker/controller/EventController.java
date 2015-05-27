package com.mycompany.portfolio_tracker.controller;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;
import com.mycompany.portfolio_tracker.io.Reader;
import com.mycompany.portfolio_tracker.io.Writer;
import com.mycompany.portfolio_tracker.model.PortfolioImpl;
import com.mycompany.portfolio_tracker.model.QuoteImpl;
import com.mycompany.portfolio_tracker.model.StockImpl;
import com.mycompany.portfolio_tracker.view.AddWindow;
import com.mycompany.portfolio_tracker.view.DeleteWindow;
import com.mycompany.portfolio_tracker.view.ModifyWindow;
import com.mycompany.portfolio_tracker.view.mainWindow;

/**
 * @author Colin
 *
 */
public class EventController implements ActionListener{

	private mainWindow.TableView ft; //Table View --> get model from there
	private mainWindow gui; //view
	private QuoteImpl quote;
	private JFileChooser fc;
		
	/**
	 * Constructor
	 */
	public EventController(mainWindow gui, PortfolioImpl p){
	    this.gui = gui;
	    quote = new QuoteImpl(true);
	}
	
	/*
	 * 
	 */
	public mainWindow.TableView getCurrentSelectionTable(){
		ft = (mainWindow.TableView)gui.getTabs().getSelectedComponent();
		return ft;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent evt){
		if(evt.getActionCommand().equals("Close")){
			if(gui.getTabs().getTabCount() != 0){
				String portfolioName = getCurrentSelectionTable().getPortfolio().getPortfolioName();
				gui.produceCloseDialogs(portfolioName);
			}
			else{
				gui.produceDialogs();
			}
		}
		else if(evt.getActionCommand().equals("Add")){
			if(gui.getTabs().getTabCount() != 0){
			    AddWindow window = new AddWindow(gui, getCurrentSelectionTable().getPortfolio());
			    //Pass event handling to child window..
        		window.makeWindowVisible();
        		
        	}
		}
		else if(evt.getActionCommand().equals("Modify")){
			if(gui.getTabs().getTabCount() != 0){
				int row = getCurrentSelectionTable().getTable().getSelectedRow(); //get the selected row
				//If a row is selected..    
				if(row != -1){
					//GET DATA FROM GUI
					Object[] rowData = readDataFromTable(row, getCurrentSelectionTable());
				     ModifyWindow window = new ModifyWindow(gui, getCurrentSelectionTable(), (String)rowData[0]);
				     window.setTitle("Modify for " + (String)rowData[1]);
				     window.setTotalValueLabel((String)rowData[2], (String)rowData[5]);
	         		 window.setStockNameLabel((String)rowData[1]);
	         		 window.setCurrentValueLabel((String)rowData[3]);
	         		 window.makeWindowVisible();
				}
        	 }
		}
		else if(evt.getActionCommand().equals("Update")){
			//while we have iterated through all rows in table.
//			if(gui.getTabs().getTabCount() != 0){
//				 for(int index = 0; index < getCurrentSelectionTable().getTable().getRowCount(); index++){
//		 		    	double newPrice = 0.0;
//		 		    	double change = 0.0;
//		 		    	Object[] rowData = readDataFromTable(index, getCurrentSelectionTable());
//		 		    	String tickerSymbol = (String)rowData[0];
//						
//		 		    	try {
//							quote.setValues(tickerSymbol);
//							newPrice = quote.getLatest();
//							change = quote.getChange();
//				     	} 
//						catch(IOException e) {
//							gui.produceDialogs(e.getMessage());
//						} 
//						catch(WebsiteDataException e) {
//							gui.produceDialogs(e.getMessage());
//						} 
//						catch(NoSuchTickerException e) {
//							gui.produceDialogs(e.getMessage());
//						}
//						catch(MethodException e) {
//							gui.produceDialogs(e.getMessage());
//						}
//		 		    	
//		 		    	//UPDATE MODEL
//						Portfolio portfolio = getCurrentSelectionTable().getPortfolio();
//		 		    	String ticker = (String)getCurrentSelectionTable().getTable().getModel().getValueAt(index, 0);
//		 		    	Stock stock = portfolio.getStock(ticker);
//		 		    	stock.setChange(change);
//		 		    	stock.setCurrentPrice(newPrice);
//		 		    	portfolio.calculateTotal(); 	
//		 		    	//UPDATE GUI
//		 		    	gui.updateTab(index, getCurrentSelectionTable(), ticker);//CALLBACK to GUI to UPDATE
//		 		      }
			
			//}
			constantUpdate();
 		   
			
		}
		else if(evt.getActionCommand().equals("Delete")){
			if(gui.getTabs().getTabCount() != 0){
				//Then read in from GUI
     		   int t = getCurrentSelectionTable().getTable().getSelectedRow();
     		    if(t != -1){
      		    //pass in those details to that window..
     		        Object[] rowData = readDataFromTable(t, getCurrentSelectionTable());
         		    DeleteWindow window = new DeleteWindow(gui, getCurrentSelectionTable(),t,rowData[0]);
         		    window.setTitle("Delete " + (String)rowData[1]);
         		    window.setLabel6((String)rowData[3]);
         		    window.setLabel7((String)rowData[2], (String)rowData[5]);
         		    window.setTestStockLabel((String)rowData[1]);
         		    window.makeWindowVisible();
     		    }
     		     else{
     			//produce error dialogs
     			gui.producePickDialogs();
     		    }
     		}
		}
	}
	
	/*
	 * 
	 */
	public Object[] readDataFromTable(int row, mainWindow.TableView ft){
		Object[] ROW = new Object[6];
		
		ROW[0] = (String)ft.getTable().getModel().getValueAt(row, 0);
		ROW[1] = (String)ft.getTable().getModel().getValueAt(row, 1);
 		ROW[3] = (String)ft.getTable().getModel().getValueAt(row, 3); //price per share
 		ROW[2] = (String)ft.getTable().getModel().getValueAt(row, 2); //no. of shares
 		ROW[5] = (String)ft.getTable().getModel().getValueAt(row, 5);
 		ROW[4] = (String)ft.getTable().getModel().getValueAt(row, 4);
		
 		System.out.println(ROW[3]);
 		return ROW;
	}
	
	public void getLatestStockDataS(){
		//contact latest from quote.java
		//return data
	}
	
	public void constantUpdate(){
		if(gui.getTabs().getTabCount() != 0){
			 for(int index = 0; index < getCurrentSelectionTable().getTable().getRowCount(); index++){
	 		    	//share price change --> change share price column!
	 		    	double newPrice = 0.0;
	 		    	double change = 0.0;
	 		    	Object[] rowData = readDataFromTable(index, getCurrentSelectionTable());
	 		    	String tickerSymbol = (String)rowData[0];
					
	 		    	try {
						quote.setValues(tickerSymbol);
						newPrice = quote.getLatest();
						change = quote.getChange();
					} 
					catch(IOException e) {
						gui.produceDialogs(e.getMessage());
					} 
					catch(WebsiteDataException e) {
						gui.produceDialogs(e.getMessage());
					} 
					catch(NoSuchTickerException e) {
						gui.produceDialogs(e.getMessage());
					}
					catch(MethodException e) {
						gui.produceDialogs(e.getMessage());
					}
	 		    	
	 		    	//UPDATE MODEL
					PortfolioImpl portfolio = getCurrentSelectionTable().getPortfolio();
	 		    	String ticker = (String)getCurrentSelectionTable().getTable().getModel().getValueAt(index, 0);
	 		    	StockImpl stock = portfolio.getStock(ticker);
	 		    	stock.setChange(change);
	 		    	stock.setCurrentPrice(newPrice);
	 		    	portfolio.calculateTotal(); 	
	 		    	//UPDATE GUI
	 		    	gui.updateTab(index, getCurrentSelectionTable(), ticker);//CALLBACK to GUI to UPDATE
	 		      }
		}
	}
	
	//-------------------------------------------------------------------------------------
	
	public class MenuListeners implements ActionListener{
		
		private mainWindow mainGUI;
		
		/*
		 * Constructor..
		 */
		public MenuListeners(mainWindow gui){
			mainGUI = gui;
		}
		
		public void doSave(File file){
			try{
			PortfolioImpl p = getCurrentSelectionTable().getPortfolio();
	           try {
	           Writer fileEditor = new Writer(file);
	           String portfolioName = p.getPortfolioName();
	           fileEditor.write(portfolioName);
	           fileEditor.newLine();
	           for(int i = 0; i < p.getAllStocks().size(); i++){
	        	   StockImpl stock = p.getAllStocks().get(i);
	        	   String ticker = stock.getTickerSymbol();
	        	   String name = stock.getStockName();
	        	   String numberOfShares = Integer.toString(stock.getNumberOfShares());
	        	   fileEditor.write(ticker + "," + name + "," + numberOfShares);
	        	   fileEditor.newLine();
	        	   }
	           		fileEditor.close();
	           }
	           catch (IOException e) {
	        	   gui.produceDialogs("ERROR");
	           }
			}
			catch(Exception e){
				gui.produceDialogs("ERROR");
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent evt){
			if(evt.getActionCommand().equals("New")){
				String st = JOptionPane.showInputDialog(null, "Enter PortFolio Name.");
				 PortfolioImpl portfolio = new PortfolioImpl();
				 portfolio.setPortfolioName(st);
	       		 gui.addTab(st, portfolio);
			}
			else if(evt.getActionCommand().equals("Exit")){
				System.exit(0);
			}
			else if(evt.getActionCommand().equals("Open")){
				  fc = new JFileChooser();
				  int returnVal = fc.showOpenDialog(mainGUI);
		          if (returnVal == JFileChooser.APPROVE_OPTION) {
		                File file = fc.getSelectedFile();
		                PortfolioImpl p = new PortfolioImpl();
		                try {
		                	Reader fileReader = new Reader(file);
		                	String str;
		                	if((str = fileReader.read()) != null) {
		                		p.setPortfolioName(str);
		                	}
		                	mainGUI.addTab(p.getPortfolioName(),p);
	                		
		                	while((str = fileReader.read()) != null) {
		                		String [] temp = null;
		                		temp = str.split(",");
		                		quote.setValues(temp[0]);
		                		StockImpl stock = new StockImpl(temp[0], Integer.parseInt(temp[2]), (quote.getLatest()), temp[1]);
		                		stock.setChange(quote.getChange());
		                		p.addStock(stock);

		                		mainGUI.AddRow2(temp[0],p);
		                	}
		                	fileReader.close();
		                }
		          		catch(Exception e) {
		          			gui.produceDialogs(e.getMessage());
		          		}
		          }
			}
			else if(evt.getActionCommand().equals("Save")){
				fc = new JFileChooser();
		    	int returnVal = fc.showSaveDialog(gui);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		           File file = fc.getSelectedFile();
		           if(file.exists() == true){
		        	
		        	   int n = JOptionPane.showConfirmDialog(gui,"Overrite File? ",
		   	    		    "Warning",JOptionPane.YES_NO_OPTION);
		   	    	   	if(n == 0){
		   	    	   	    doSave(file);
		   	    	    }
		        	   
		           }
		           else{
		        	   doSave(file);
		           }
		           
		       }
			}
			
		}
	}
	
}

