package com.mycompany.portfolio_tracker.view;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModifiyWindow.java
 *
 * Created on 27-Nov-2008, 19:15:45
 */

import java.awt.*;

import javax.swing.*;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.exceptions.NoSuchTickerException;
import com.mycompany.portfolio_tracker.exceptions.WebsiteDataException;
import com.mycompany.portfolio_tracker.model.PortfolioImpl;
import com.mycompany.portfolio_tracker.model.QuoteImpl;
import com.mycompany.portfolio_tracker.model.Stock;

import java.awt.event.*;
import java.io.IOException;

/**
 *
 * @author Colin
 */
public class ModifyWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private JLabel stockName_Label;
    private JLabel currentValue_Label;
    private JLabel currentHolding_Label;
    private JLabel newHolding_Label;
    private JLabel stockName;
    private JLabel currentValue;
    private JLabel totalValue;
    private PortfolioImpl p;
    private String tickerSymbol;
    private JTextField jTextField1;
    private JButton ok;
    private JButton cancel;
    private mainWindow gui;
    private mainWindow.TableView ft;
    private QuoteImpl quote;
        
    /**
     * Constructor
     */
    public ModifyWindow(mainWindow m, mainWindow.TableView ft, String ticker) {
        initComponents();
        this.ft = ft;
        p = ft.getPortfolio();
        tickerSymbol = ticker;
        gui = m;
        quote = new QuoteImpl(true);
    }
    
    /*
     * 
     */
    private void initComponents() {
        stockName_Label = new JLabel("Stock Name: ");
        currentValue_Label = new JLabel("Current Value: ");
        currentHolding_Label = new JLabel("Current Holding: ");
        newHolding_Label = new JLabel("New Holding: ");
        jTextField1 = new JTextField();
        stockName = new JLabel();
        currentValue = new JLabel();
        totalValue = new JLabel();
        
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		//update model
        		Stock selectedStock = p.getStock(tickerSymbol);
        		double volume = Double.parseDouble(getInputFromModify());
        		try {
        			try {
						quote.setValues(tickerSymbol);
					} 
        			catch (IOException e) {
        				gui.produceDialogs(e.getMessage());
					}
        			catch (WebsiteDataException e) {
						gui.produceDialogs(e.getMessage());
					}
        			catch (NoSuchTickerException e) {
        				gui.produceDialogs(e.getMessage());
					}
					if(volume <= quote.getVolume()){
						selectedStock.setNumberOfShares((int)volume);
					}
					else{
						gui.produceDialogs("Exceeded maximum number of available shares");
					}
				} 
        		catch(MethodException e) {
					gui.produceDialogs("Error, Please check and try again");
				}
        		
        		p.calculateTotal();
        		//update GUI
        		gui.editRow(ft, tickerSymbol);
        		dispose();
        	}
        });
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		dispose();
        	}
        });
              
        setLocation(300,300);
        setPreferredSize(new Dimension(380,270));
        setResizable(false);
        makeStuff();          
        pack();
    }
    
    /*
     * Makes the components
     */
    private void makeStuff(){
    	Container contentpane = getContentPane();
    	jTextField1.setPreferredSize(new Dimension(80,25));
    	    	
    	JPanel top = new JPanel(new GridLayout(0,2,10,10));
    	top.add(stockName_Label);
    	top.add(stockName);
    	top.add(currentValue_Label);
    	top.add(currentValue);
    	top.add(currentHolding_Label);
    	top.add(totalValue);
    	
    	JPanel empty1 = new JPanel();
    	JPanel empty2 = new JPanel();
    	top.add(empty1);
    	top.add(empty2);
    	
    	JPanel topPanel = new JPanel(new GridLayout(0,1, 20,5));
    	topPanel.add(top);
    	
    	JPanel bottom = new JPanel();
    	bottom.add(newHolding_Label);
    	bottom.add(jTextField1);
    	    	
    	JPanel confirm = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,5));
    	confirm.setPreferredSize(new Dimension(2,50));
    	confirm.add(ok);
    	confirm.add(cancel);
    	
    	JPanel outside = new JPanel(new GridLayout(2,0));	
    	outside.add(topPanel);
    	outside.add(bottom);
    	   	
    	JPanel left= new JPanel();
    	left.setPreferredSize(new Dimension(20,20));
    	JPanel right = new JPanel();
    	right.setPreferredSize(new Dimension(20,20));
    	JPanel north = new JPanel();
    	north.setPreferredSize(new Dimension(20,20));
    	JPanel south = new JPanel();
    	south.setPreferredSize(new Dimension(20,20));
    	
    	contentpane.setLayout(new BorderLayout());
    	contentpane.add(north, BorderLayout.NORTH);
    	contentpane.add(outside, BorderLayout.CENTER);
    	contentpane.add(confirm, BorderLayout.SOUTH);
    	contentpane.add(left, BorderLayout.WEST);
    	contentpane.add(right, BorderLayout.EAST);
    }
    
    //---------------------------GUI UPDATE & CALLBACK methods------------------------
    
    public void setStockNameLabel(String sn){
    	stockName.setText(sn);
    }
    
    public void setCurrentValueLabel(String cv){
    	currentValue.setText(cv);
    }
    
    public void setTotalValueLabel(String ch, String tv){
    	totalValue.setText(ch + " ( total value: " +  tv + ")");
    }
    
    public String getInputFromModify(){
    	String input = jTextField1.getText();
    	return input;
    }
    
    public void makeWindowVisible(){
    	setVisible(true);
    }

}