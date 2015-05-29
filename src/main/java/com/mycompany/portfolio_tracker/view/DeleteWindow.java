package com.mycompany.portfolio_tracker.view;

import javax.swing.*;

import com.mycompany.portfolio_tracker.controller.DeleteController;
import com.mycompany.portfolio_tracker.view.components.MyTableView;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Colin
 */
public class DeleteWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton yes;
    private JButton no;
    private JLabel stockName_Label;
    private JLabel currentValue_Label;
    private JLabel currentHolding_Label;
    private JLabel delete;
    private JLabel testStock1_Label;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private DeleteController deleteController;
   
    /**
     * Constructor
     * 
     * @param gui
     * @param tableView
     * @param rowNumber
     * @param tickerSymbol
     */
    public DeleteWindow(MainWindow gui, MyTableView tableView, int rowNumber,Object tickerSymbol) {
    	deleteController = new DeleteController(gui, tableView, (String)tickerSymbol, rowNumber, this);
    	initComponents();
    }
    
    /*
     * 
     */
    private void initComponents() {
        stockName_Label = new JLabel("Stock Name: ");
        currentValue_Label = new JLabel("Current Value: ");
        currentHolding_Label = new JLabel("Current Holding: ");
        testStock1_Label = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        delete = new JLabel("DELETE: ARE YOU SURE?");
        yes = new JButton("Yes");
        yes.addActionListener(deleteController);
        
        no = new JButton("No");
        no.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		dispose();
        	}
        });

        setResizable(false);
        setPreferredSize(new Dimension(360,250));
        setLocation(300,300);
        delete.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        
        JPanel grid = new JPanel(new GridLayout(3,0,10,10));
        grid.add(stockName_Label);
        grid.add(testStock1_Label);
        grid.add(currentValue_Label);
        grid.add(jLabel6);
        grid.add(currentHolding_Label);
        grid.add(jLabel7);
        
        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
        deletePanel.add(delete);
        
        JPanel confirm = new JPanel(new FlowLayout(FlowLayout.CENTER, 40,0));
        confirm.add(yes);
        confirm.add(no);
        
        JPanel bottom = new JPanel(new GridLayout(2,1,5,2));
        bottom.add(deletePanel);
        bottom.add(confirm);
        
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(20,20));
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(20,20));
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(20,20));
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(grid, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(west, BorderLayout.WEST);
        getContentPane().add(east, BorderLayout.EAST);
       
        
        pack();
    }
    
    //--------------CALLBACK METHODS---------------------------------------------------
    
    /*
     * 
     */
    public void setLabel6(String cv){
    	jLabel6.setText(cv);
    }
    
    /*
     * 
     */
    public void setLabel7(String ch, String hv){
    	jLabel7.setText(ch + " ( total value " + hv + " )");
    }
    
    /**
     * 
     * @param sn
     */
    public void setTestStockLabel(String sn){
    	 testStock1_Label.setText(sn);
    }
    
    public void makeWindowVisible(){
    	setVisible(true);
    }

}