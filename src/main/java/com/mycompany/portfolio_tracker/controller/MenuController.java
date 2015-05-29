/**
 * 
 */
package com.mycompany.portfolio_tracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mycompany.portfolio_tracker.io.Reader;
import com.mycompany.portfolio_tracker.io.Writer;
import com.mycompany.portfolio_tracker.model.PortfolioImpl;
import com.mycompany.portfolio_tracker.model.Stock;
import com.mycompany.portfolio_tracker.model.StockImpl;
import com.mycompany.portfolio_tracker.view.mainWindow;

/**
 * @author colin
 *
 */
public class MenuController extends AbstractController implements ActionListener{

	private mainWindow mainGUI;
	private JFileChooser fc;
	
	
	
			
	/**
	 * Constructor
	 */
	public MenuController(mainWindow gui){
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
        	   Stock stock = p.getAllStocks().get(i);
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
