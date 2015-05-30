/**
 * 
 */
package com.mycompany.portfolio_tracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mycompany.portfolio_tracker.model.Portfolio;
import com.mycompany.portfolio_tracker.model.PortfolioImpl;
import com.mycompany.portfolio_tracker.model.Stock;
import com.mycompany.portfolio_tracker.model.StockImpl;
import com.mycompany.portfolio_tracker.view.MainWindow;

/**
 * @author colin
 *
 */
public class MenuController extends AbstractController implements ActionListener{

	private MainWindow mainGUI;
	private JFileChooser fc;
			
	/**
	 * Constructor
	 */
	public MenuController(MainWindow gui){
		mainGUI = gui;
	}
	
	public void doSave(File file){
		try{
		Portfolio p = getCurrentSelectionTable().getPortfolio();
           try {
           BufferedWriter fileEditor = new BufferedWriter(new FileWriter(file));
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
        	   mainGUI.produceDialogs("ERROR");
           }
		}
		catch(Exception e){
			mainGUI.produceDialogs("ERROR");
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
			 mainGUI.addTab(st, portfolio);
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
	                	BufferedReader fileReader = new BufferedReader(new FileReader(file));
	                	
	                	String str = null;
	                	if((str = fileReader.readLine()) != null) {
	                		p.setPortfolioName(str);
	                	}
	                	mainGUI.addTab(p.getPortfolioName(),p);
                		
	                	while((str = fileReader.readLine()) != null) {
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
	          			mainGUI.produceDialogs(e.getMessage());
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
