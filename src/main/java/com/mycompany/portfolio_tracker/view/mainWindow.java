package com.mycompany.portfolio_tracker.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;

import com.mycompany.portfolio_tracker.controller.EventController;
import com.mycompany.portfolio_tracker.controller.EventController.MenuListeners;
import com.mycompany.portfolio_tracker.model.PortfolioImpl;
import com.mycompany.portfolio_tracker.model.StockImpl;

import java.awt.*;
import java.text.DecimalFormat;

	/**
	 * 
	 * @author Colin
	 */
public class mainWindow extends JFrame implements Runnable {
	    private static final long serialVersionUID = 1L;
		private JButton add;
	    private JButton delete;
	    private JButton modify;
	    private JButton close; //close a tab
	    private JButton update;
	    private JLabel label;
	    private JLabel totalValue_Label;
	    private JMenu File;
	    private JMenuBar menuBar;
	    private JMenuItem newMenuItem;
	    private JMenuItem openMenuItem;
	    private JMenuItem saveMenuItem;
	    private JMenuItem exitMenuItem;
	    private JPanel panel;
	    private JScrollPane scrollPane;
	    private JTabbedPane jTabbedPane1;
	    private PortfolioImpl portfolio;
	    private boolean start = true;
	    private DecimalFormat decimal;
	    private EventController listener = new EventController(this, portfolio);
	    private EventController.MenuListeners menuListener = listener.new MenuListeners(this);
	    
	    
	    /*
	     * Constructor
	     */
	    public mainWindow() {
	    	makeMenuBar();
	    	initComponents();
	    	portfolio = new PortfolioImpl();
	    	decimal = new DecimalFormat("0.00");
	    }
	    
	    /**
	     * 
	     * @param args
	     */
	    public static void main(String args[]) {
	        Thread t = new Thread(new mainWindow());
	    	t.start();
	    	//new mainWindow();
	    }
	    
	   
	    public JTabbedPane getTabs(){
	    	return jTabbedPane1;
	    }
	    
	    /*
	     * 
	     */
	    private void initComponents() {
	    	 setVisible(true);
	         setLocation(100,200);
	         setResizable(true);
	         setPreferredSize(new Dimension(660,480));
	         setTitle("PortFolio Tracker");
	         setDefaultCloseOperation(EXIT_ON_CLOSE);
	         
	         jTabbedPane1 = new JTabbedPane();
	       
	         add = new JButton("Add");
	         add.setToolTipText("Add a stock to the portfolio");
	         add.addActionListener(listener);
	         JPanel addPanel = new JPanel();
	         addPanel.add(add);
	               
	         delete = new JButton("Delete");
	         delete.setToolTipText("Delete a stock from the portfolio");
	         delete.addActionListener(listener);
	         JPanel deletePanel = new JPanel();
	         deletePanel.add(delete);
	         
	         modify = new JButton("Modify");
	         modify.addActionListener(listener);
	         modify.setToolTipText("Edit a selected stock");
	         JPanel modifyPanel = new JPanel();
	         modifyPanel.add(modify);
	         
	         close = new JButton("Close");
	         close.setToolTipText("Closes a portfolio");
	         close.addActionListener(listener);
	         JPanel closePanel = new JPanel();
	         closePanel.add(close);
	         
	         update = new JButton("Update");
	         update.setToolTipText("Updates the portfolio!");
	         update.addActionListener(listener);
	         JPanel updatePanel = new JPanel();
	         updatePanel.add(update);
	         
	           
	         label = new JLabel("No Stocks in your Portfolio");
	         label.setAlignmentX(LEFT_ALIGNMENT);
	         

	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        label.setText("<html>Total value of portfolio -          <b>");
	        JPanel labelPanel = new JPanel();
	        labelPanel.add(label);
	        
	        totalValue_Label = new JLabel("0.0");
	        JPanel totalValueLabelPanel = new JPanel();
	        totalValueLabelPanel.add(totalValue_Label);
	        
	        JPanel south = new JPanel();
	        south.add(labelPanel);
	        south.add(totalValueLabelPanel);

	         JPanel side = new JPanel(new GridLayout(0,1));
	         side.add(addPanel);
	         side.add(deletePanel);
	         side.add(modifyPanel);
	         side.add(closePanel);
	         side.add(updatePanel);
	       
	         jTabbedPane1.addChangeListener(new ChangeListener(){
	        	 
				public void stateChanged(ChangeEvent arg0) {
				
	        		 if(getTabs().getTabCount() != 0){
	        			 TableView ft = (TableView)jTabbedPane1.getSelectedComponent();
	        			 setTotalValueLabel(Double.toString(ft.getPortfolio().getTotalValue()));
	        		 }
	        		 else{
	        			 setTotalValueLabel("0.0");
	        		 }
				}
	        	 
	         });
	         JPanel temp = new JPanel();
	         temp.add(side);
	         panel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
	         panel.add(jTabbedPane1);
	         getContentPane().setLayout(new BorderLayout());
	         getContentPane().add(temp, BorderLayout.EAST);
	         getContentPane().add(panel, BorderLayout.CENTER);
	         getContentPane().add(south, BorderLayout.SOUTH);
	         pack();
	        
	    }
	    
	    /*
	     * Makes the menubar... 
	     */
	    private void makeMenuBar(){
	    	menuBar = new JMenuBar();
	        File = new JMenu("File");
	        newMenuItem = new JMenuItem("New");
	        newMenuItem.addActionListener(menuListener);
	        openMenuItem = new JMenuItem("Open");
	        openMenuItem.addActionListener(menuListener);
	        saveMenuItem = new JMenuItem("Save");
	        saveMenuItem.addActionListener(menuListener);
	        exitMenuItem = new JMenuItem("Exit");
	        exitMenuItem.addActionListener(menuListener);
	       
	        //Adds to file menu
	        File.add(newMenuItem);
	        File.add(openMenuItem);
	        File.add(saveMenuItem);
	        File.addSeparator();
	        File.add(exitMenuItem);

	        menuBar.add(File);
	        setJMenuBar(menuBar);
	    }
	    
	    //-------------------------------------------------------------------------

	    public void produceDialogs(){
	    	JOptionPane.showMessageDialog(this, "No more Portfolios", 
					"Closing Error!", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    public void produceDialogs(String errorMessage){
	    	JOptionPane.showMessageDialog(this, errorMessage, 
					"ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    public void producePickDialogs(){
	    	JOptionPane.showMessageDialog(this, "Pick a stock to delete", 
					"Stock delete error!", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    public void produceCloseDialogs(String title){
	    	int n = JOptionPane.showConfirmDialog(this,"Close this portfolio? ",
	    		    title,JOptionPane.YES_NO_OPTION);
	    	
	    	if(n == 0){
	    		//tell GUI to update itself
	    		closeTab();
	    	}
	    }
	    
	      
	    public void run() {
	    	while(start){
	    		try{
	    			Thread.sleep(5000);
	    		}
	    		catch(Exception e){
	    			
	    		}
	    		listener.constantUpdate();
	    	 	repaint();
	    		
	    		
	    	}
	    }
	 	    
	    //**********************************Update GUI*****************************
	    //CALLBACK METHODS............
	    
	    /**
	     * effects: sets the total value of the label to show the total value.
	     * 
	     */
	    public void setTotalValueLabel(String totalValue){
	    	totalValue_Label.setText(totalValue);
	    }
	    
	    /**
	     * 
	     * 
	     */
	    //public void AddRow(String ticker, Portfolio p){
	    public void AddRow(String ticker){
	    	
	    	TableView tableView = (TableView)jTabbedPane1.getSelectedComponent();
	    	portfolio = tableView.getPortfolio();
	    	StockImpl stock = portfolio.getStock(ticker);
	    	String tickerSymbol = stock.getTickerSymbol();
	    	String stockName = stock.getStockName();
	    	String numberOfShares = Integer.toString(stock.getNumberOfShares());
	    	String sharePrice = decimal.format(stock.getCurrentPrice());
	    	String change = decimal.format(stock.getChange());
	    	String holdingValue = decimal.format(stock.getHoldingValue());
	    	
	        Object[] data = {tickerSymbol,stockName,numberOfShares,
	        		sharePrice,change,holdingValue};
	    	//If table model is not nothing..    		
	    	if(tableView.getModel() != null){
	    		tableView.getModel().addRow(data);
	    		setTotalValueLabel(decimal.format(portfolio.getTotalValue()));
	    	}
	    }
	    
	    /**
	     * 
	     * @param ticker
	     * @param portfolio
	     */
	    public void AddRow2(String ticker, PortfolioImpl portfolio){
	        this.portfolio = portfolio;
	    	StockImpl stock = portfolio.getStock(ticker);
	    	String tickerSymbol = stock.getTickerSymbol();
	    	String stockName = stock.getStockName();
	    	String numberOfShares = Integer.toString(stock.getNumberOfShares());
	    	String sharePrice = decimal.format(stock.getCurrentPrice());
	    	String change = decimal.format(stock.getChange());
	    	String holdingValue = decimal.format(stock.getHoldingValue());
	    	
	        Object[] data = {tickerSymbol,stockName,numberOfShares,
	        		sharePrice,change,holdingValue};
	    	TableView ft = (TableView)jTabbedPane1.getComponent(jTabbedPane1.getComponentCount() - 1);
	    		
	    	if(ft.getModel() != null){
	        	ft.getModel().addRow(data);
	    		setTotalValueLabel(decimal.format(portfolio.getTotalValue()));
	    	}
	    }
	   
	    /*
	     * Delete a row of data from the JTable
	     */
	    public void deleteRow(TableView tableView, int t){
	    	tableView.getModel().removeRow(t);//REMOVE ROW from TABLE
	    	//UPDATE total value label
	    	setTotalValueLabel(decimal.format(tableView.getPortfolio().getTotalValue())); 
	    }
	    
	    /**
	     * 
	     * @param st
	     * @param p
	     */
	    public void addTab(String portfolioName, PortfolioImpl p){
	    	TableView tableView = new TableView(p);
	    	portfolio = tableView.getPortfolio();
	   		if(portfolioName != null){
	   			jTabbedPane1.add(portfolioName, tableView);
	   		}
	   	}
	    
	    /*
	     * Closes a tab.
	     */
	    public void closeTab(){
	    	jTabbedPane1.remove(jTabbedPane1.getSelectedComponent());
	    }
	    
	    /*
	     * Updates all data in this tab
	     */
	    public void updateTab(int row, TableView tableView, String ticker){
	    	StockImpl stock = tableView.getPortfolio().getStock(ticker);
	    	//double sharePrice = stock.getCurrentPrice();
	    	double change = stock.getChange();
	    	double holdingValue = stock.getHoldingValue();
	    	tableView.getTable().getModel().setValueAt(decimal.format(stock.getCurrentPrice()), row, 3); 
	    	tableView.getTable().getModel().setValueAt(decimal.format(change), row, 4);//set price of shares
	    	tableView.getTable().getModel().setValueAt(decimal.format(holdingValue), row, 5); //set new holding value
	    	setTotalValueLabel(decimal.format(tableView.getPortfolio().getTotalValue()));
	    }
	    
	    /*
	     * Edit a row
	     * @param 
	     */
	    public void editRow(mainWindow.TableView tableView, String tickerSymbol){
	    	int row = tableView.getTable().getSelectedRow(); //get the selected row
	    	String shareNum = Integer.toString(tableView.getPortfolio().getStock(tickerSymbol).getNumberOfShares());
	    	String change = decimal.format(tableView.getPortfolio().getStock(tickerSymbol).getChange());
	    	String newHoldingValue = decimal.format(tableView.getPortfolio().getStock(tickerSymbol).getHoldingValue());
	    	tableView.getTable().getModel().setValueAt(shareNum, row, 2); //set price of shares
	    	tableView.getTable().getModel().setValueAt(change, row, 4);
	    	tableView.getTable().getModel().setValueAt(newHoldingValue, row, 5); //set new holding value
	    	setTotalValueLabel(decimal.format(tableView.getPortfolio().getTotalValue()));
	    }
	    
	    //****************************************************************
	    
	    private class MyTableModel extends DefaultTableModel{
				
	    	private static final long serialVersionUID = 1L;
	    	
	    	/*
	    	 * Constructor..
	    	 */
			public MyTableModel(String[][] data, String[] columns){
	    		super(data, columns);
	    	}
			
			/*
			 * (non-Javadoc)
			 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
			 */
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
	
	    }
	    
	    //***************************************************************
	    
	    public class TableView extends JPanel {
	    	   	
			private static final long serialVersionUID = 1L;
			private JPanel panel;
	    	private JTable table;
	    	private MyTableModel model1;
	    	private PortfolioImpl p2;
	    	
	    	/*
	    	 * Constructor..
	    	 */
	    	public TableView(PortfolioImpl portfolio){
	    		panel = new JPanel();
	        	this.p2 = portfolio;   	
	        	String[] columnNames = {
	        			"Ticker Symbol", "Stock Name", "Number of Shares",
	        			"Price per Share","Change", "Value of Holding"
	        	};
	        	String[][] date = new String[0][6];
	        	
	        	table = new JTable();
	        	model1 = new MyTableModel(date, columnNames);
	        	table.setModel(model1);
	        	table.setPreferredScrollableViewportSize(new Dimension(520, 310));
	        	table.setFillsViewportHeight(true);
	        	table.setAutoCreateRowSorter(true);
	        	table.setForeground(Color.BLACK);
	        	table.setGridColor(Color.BLUE);
	        	scrollPane = new JScrollPane(table);
	        	add(scrollPane);
	        }
	    	
	    	/*
	    	 * 
	    	 */
	    	public Container getTableViewPanel(){
	    		return panel;
	    	}
	    	
	    	public PortfolioImpl getPortfolio(){
	    		return p2;
	    	}
	    	
	    	/*
	    	 * 
	    	 */
	    	public JTable getTable(){
	    		return table;
	    	}
	    	
	    	/*
	    	 * 
	    	 */
	    	public MyTableModel getModel(){
	    		return model1;
	    	}
	    	
	    	
	    }
	    
	}

