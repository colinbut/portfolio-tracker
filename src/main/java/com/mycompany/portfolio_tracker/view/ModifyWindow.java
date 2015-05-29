package com.mycompany.portfolio_tracker.view;

import java.awt.*;

import javax.swing.*;

import com.mycompany.portfolio_tracker.controller.ModifyController;
import com.mycompany.portfolio_tracker.view.components.MyTableView;

import java.awt.event.*;


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
    
    private JTextField jTextField1;
    private JButton ok;
    private JButton cancel;
    
    private ModifyController modifyController;
        
    /**
     * Constructor
     */
    public ModifyWindow(MainWindow gui, MyTableView ft, String ticker) {
        initComponents();
        modifyController = new ModifyController(ft.getPortfolio(), gui, ticker, this);
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
        ok.addActionListener(modifyController);
        
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
    	return jTextField1.getText();
    }
    
    public void makeWindowVisible(){
    	setVisible(true);
    }

}