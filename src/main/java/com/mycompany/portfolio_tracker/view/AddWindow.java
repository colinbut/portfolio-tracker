package com.mycompany.portfolio_tracker.view;

import javax.swing.*;

import com.mycompany.portfolio_tracker.controller.AddController;
import com.mycompany.portfolio_tracker.model.Portfolio;
import com.mycompany.portfolio_tracker.model.QuoteService;

import java.awt.*;
import java.awt.event.*;

/**
 * View class for Add modal
 * 
 * @author Colin
 */
public class AddWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel tickerLabel;
    private JLabel numberOfShares_Label;
    public JTextField tickerTextField;
    public JTextField numberOfSharesTextField;
    private JButton okButton;
    private JButton cancelButton;
    private AddController addController;
    
    /**
     * Constructor
     * 
     * @param gui
     * @param portfolio
     */
    public AddWindow(MainWindow gui, Portfolio portfolio, QuoteService quoteService) {
    	addController = new AddController(gui, portfolio, this, quoteService);
    	initComponents();
    }
    
    /*
     * 
     */
    private void initComponents() {
        tickerLabel = new JLabel("Ticker Symbol: ");
        numberOfShares_Label = new JLabel("Number of shares: ");
        tickerTextField = new JTextField();
        numberOfSharesTextField = new JTextField();
        
        okButton = new JButton("OK");
        okButton.addActionListener(addController);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener(){
        	/*
        	 * (non-Javadoc)
        	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
        	 */
        	@Override
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
        confirm.add(okButton);
        confirm.add(cancelButton);
        
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
    }

    //------------------------METHODS to get data from this GUI window-----------------------
    
    /**
     * 
     * @return
     */
    public String getTicker(){
    	return tickerTextField.getText();
    }
    
    /**
     * 
     * @return
     */
    public String getNumberOfShares(){
    	return numberOfSharesTextField.getText();
    }
    
        
    //------------------------METHODS to UPDATE GUI!! ----------------------------
    
    /**
     * 
     */
    public void makeWindowVisible(){
    	setVisible(true);
    }
    
}