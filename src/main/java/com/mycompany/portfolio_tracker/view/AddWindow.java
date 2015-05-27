package com.mycompany.portfolio_tracker.view;

import javax.swing.*;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;
import com.mycompany.portfolio_tracker.model.PortfolioImpl;
import com.mycompany.portfolio_tracker.model.QuoteImpl;
import com.mycompany.portfolio_tracker.model.StockImpl;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 *
 * @author Colin
 */
public class AddWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel tickerLabel;
    private JLabel numberOfShares_Label;
    public JTextField tickerTextField;
    public JTextField numberOfSharesTextField;
    private JButton ok;
    private JButton cancel;
    private QuoteImpl quote; 
    private PortfolioImpl p;
    private mainWindow gui;
    
    /**
     * Constructor
     */
    public AddWindow(mainWindow gui, PortfolioImpl p) {
        initComponents();
        this.gui = gui;
        this.p = p;
        quote = new QuoteImpl(true);
    }
    
    /*
     * 
     */
    private void initComponents() {
        tickerLabel = new JLabel("Ticker Symbol: ");
        numberOfShares_Label = new JLabel("Number of shares: ");
        tickerTextField = new JTextField();
        numberOfSharesTextField = new JTextField();
        
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener(){
       	    public void actionPerformed(ActionEvent evt){
       	    	readUpdateModelGUI();
        	}
        });
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		dispose();
        	}
        });

        
        setTitle("Add new stock");
        setResizable(false);
        setLocation(300,300);
       
        JPanel input = new JPanel(new GridLayout(3,0,20,10));
        input.add(tickerLabel);
        input.add(tickerTextField);
        input.add(numberOfShares_Label);
        input.add(numberOfSharesTextField);
                       
        JPanel confirm = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        confirm.add(ok);
        confirm.add(cancel);
        
        JPanel west = new JPanel();
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(35,30));
        JPanel north = new JPanel();
        JPanel south = new JPanel();
        
        JPanel grid = new JPanel(new GridLayout(2,0));
        grid.add(input);
        grid.add(confirm);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(grid, BorderLayout.CENTER);
        getContentPane().add(west, BorderLayout.WEST);
        getContentPane().add(east, BorderLayout.EAST);
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(south, BorderLayout.SOUTH);

        pack();
    }// end initComponents


    //------------------------METHODS to get data from this GUI window-----------------------
    
    /*
     * 
     */
    public String getTicker(){
    	String ticker = tickerTextField.getText();
    	return ticker;
    }
    
    /*
     * 
     */
    public String getNumberOfShares(){
    	String numberOfShares = numberOfSharesTextField.getText();
    	return numberOfShares;
    }
    
        
    //------------------------METHODS to UPDATE GUI!! ----------------------------
    
    /*
     * 
     */
    public void makeWindowVisible(){
    	setVisible(true);
    }
    

    /*
     * 
     */
    public void readUpdateModelGUI(){
    	//get input - read from gui
		String ticker = getTicker();
		String volumeStr = getNumberOfShares();
  		double currentPrice = 0;
		
		try {
			quote.setValues(ticker); //set the Ticker to check if it's valid?
			
			currentPrice = quote.getLatest(); //If Success then get share price..
			double change = quote.getChange();
			String stockName = quote.getStockName();
			
			try {
				double volume = Double.parseDouble(volumeStr);
			    	if(volume <= quote.getVolume()){
			    		//UPDATE MODEL
			    		int shareNo = Integer.parseInt(getNumberOfShares());
			    		StockImpl stock = new StockImpl(ticker, shareNo,currentPrice ,stockName);//Make Stock object
		        		stock.setChange(change);
		        		p.addStock(stock); //ADD to Portfolio (MODEL)
		        		//Update GUI
						gui.AddRow(ticker);
		        	}
			    	else{
			    		gui.produceDialogs("Number of shares available exceeded limit");
			    	}
			    } 
			    catch(NumberFormatException e1){
    			    gui.produceDialogs("Number of shares must be a valid positive number");
    		    }
				catch(Exception e){
					gui.produceDialogs(e.getMessage());
				}
				
		} 
		catch(IOException e1) {
			gui.produceDialogs(e1.getMessage());
		} 
		
		catch(WebsiteDataException e1) {
			gui.produceDialogs(e1.getMessage());
		} 
		catch(NoSuchTickerException e1) {
			gui.produceDialogs(e1.getMessage() +
					"\n" + "Remember: For UK shares; add .l");
		} 
		catch(MethodException e1) {
			gui.produceDialogs(e1.getMessage());
		} 
		catch(Exception e1){
			gui.produceDialogs("Input Error\n" + "Please check input...\n" +
					"Remember: For UK shares; add .l");
   		}
		    		
		dispose();
       }


}