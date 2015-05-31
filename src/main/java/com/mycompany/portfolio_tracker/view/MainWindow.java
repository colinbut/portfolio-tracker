package com.mycompany.portfolio_tracker.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mycompany.portfolio_tracker.controller.EventController;
import com.mycompany.portfolio_tracker.controller.MenuController;
import com.mycompany.portfolio_tracker.model.Portfolio;
import com.mycompany.portfolio_tracker.model.PortfolioImpl;
import com.mycompany.portfolio_tracker.model.Stock;
import com.mycompany.portfolio_tracker.view.components.MyTableView;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * 
 * @author Colin
 */
public class MainWindow extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JButton addButton;
	private JButton deleteButton;
	private JButton modifyButton;
	private JButton closeButton; // close a tab
	private JButton updateButton;
	private JLabel label;
	private JLabel totalValue_Label;
	private JMenu FileMenu;
	private JMenuBar menuBar;
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	private JPanel panel;
	private JTabbedPane jTabbedPane1;
	private Portfolio portfolio;
	private boolean start = true;
	private DecimalFormat decimal;
	private EventController eventController;
	private MenuController menuController;

	/**
	 * Constructor
	 */
	public MainWindow() {
		
		eventController = new EventController(this);
		menuController = new MenuController(this);
		
		makeMenuBar();
		initComponents();
		portfolio = new PortfolioImpl();
		decimal = new DecimalFormat("0.00");
		
	}

	/*
	 * 
	 */
	private void initComponents() {
		setVisible(true);
		setLocation(100, 200);
		setResizable(true);
		setPreferredSize(new Dimension(660, 480));
		setTitle("PortFolio Tracker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		jTabbedPane1 = new JTabbedPane();

		addButton = new JButton("Add");
		addButton.setToolTipText("Add a stock to the portfolio");
		addButton.addActionListener(eventController);
		JPanel addPanel = new JPanel();
		addPanel.add(addButton);

		deleteButton = new JButton("Delete");
		deleteButton.setToolTipText("Delete a stock from the portfolio");
		deleteButton.addActionListener(eventController);
		JPanel deletePanel = new JPanel();
		deletePanel.add(deleteButton);

		modifyButton = new JButton("Modify");
		modifyButton.addActionListener(eventController);
		modifyButton.setToolTipText("Edit a selected stock");
		JPanel modifyPanel = new JPanel();
		modifyPanel.add(modifyButton);

		closeButton = new JButton("Close");
		closeButton.setToolTipText("Closes a portfolio");
		closeButton.addActionListener(eventController);
		JPanel closePanel = new JPanel();
		closePanel.add(closeButton);

		updateButton = new JButton("Update");
		updateButton.setToolTipText("Updates the portfolio!");
		updateButton.addActionListener(eventController);
		JPanel updatePanel = new JPanel();
		updatePanel.add(updateButton);

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

		JPanel side = new JPanel(new GridLayout(0, 1));
		side.add(addPanel);
		side.add(deletePanel);
		side.add(modifyPanel);
		side.add(closePanel);
		side.add(updatePanel);

		jTabbedPane1.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {

				if (getTabs().getTabCount() != 0) {
					MyTableView ft = (MyTableView) jTabbedPane1
							.getSelectedComponent();
					setTotalValueLabel(Double.toString(ft.getPortfolio()
							.getTotalValue()));
				} else {
					setTotalValueLabel("0.0");
				}
			}

		});
		JPanel temp = new JPanel();
		temp.add(side);
		panel = new JPanel(new BorderLayout());
		panel.add(jTabbedPane1, BorderLayout.CENTER);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(temp, BorderLayout.EAST);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
		pack();

	}

	/*
	 * Makes the menubar...
	 */
	private void makeMenuBar() {
		menuBar = new JMenuBar();
		FileMenu = new JMenu("File");
		newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(menuController);
		openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(menuController);
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(menuController);
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(menuController);

		// Adds to file menu
		FileMenu.add(newMenuItem);
		FileMenu.add(openMenuItem);
		FileMenu.add(saveMenuItem);
		FileMenu.addSeparator();
		FileMenu.add(exitMenuItem);

		menuBar.add(FileMenu);
		setJMenuBar(menuBar);
	}

	// -------------------------------------------------------------------------

	public void produceDialogs() {
		JOptionPane.showMessageDialog(this, "No more Portfolios",
				"Closing Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void produceDialogs(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "ERROR",
				JOptionPane.ERROR_MESSAGE);
	}

	public void producePickDialogs() {
		JOptionPane.showMessageDialog(this, "Pick a stock to delete",
				"Stock delete error!", JOptionPane.ERROR_MESSAGE);
	}

	public void produceCloseDialogs(String title) {
		int n = JOptionPane.showConfirmDialog(this, "Close this portfolio? ",
				title, JOptionPane.YES_NO_OPTION);

		if (n == 0) {
			// tell GUI to update itself
			closeTab();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (start) {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {

			}
			eventController.constantUpdate();
			repaint();

		}
	}

	// **********************************Update GUI*****************************
	// CALLBACK METHODS............

	/**
	 * effects: sets the total value of the label to show the total value.
	 * 
	 */
	public void setTotalValueLabel(String totalValue) {
		totalValue_Label.setText(totalValue);
	}

	/**
	 * 
	 * @param ticker
	 */
	public void AddRow(String ticker) {

		MyTableView tableView = (MyTableView) jTabbedPane1.getSelectedComponent();
		portfolio = tableView.getPortfolio();
		Stock stock = portfolio.getStock(ticker);
		String tickerSymbol = stock.getTickerSymbol();
		String stockName = stock.getStockName();
		String numberOfShares = Integer.toString(stock.getNumberOfShares());
		String sharePrice = decimal.format(stock.getCurrentPrice());
		String change = decimal.format(stock.getChange());
		String holdingValue = decimal.format(stock.getHoldingValue());

		Object[] data = { tickerSymbol, stockName, numberOfShares, sharePrice,
				change, holdingValue };
		// If table model is not nothing..
		if (tableView.getModel() != null) {
			tableView.getModel().addRow(data);
			setTotalValueLabel(decimal.format(portfolio.getTotalValue()));
		}
	}

	/**
	 * 
	 * @param ticker
	 * @param portfolio
	 */
	public void AddRow2(String ticker, Portfolio portfolio) {
		this.portfolio = portfolio;
		Stock stock = portfolio.getStock(ticker);
		String tickerSymbol = stock.getTickerSymbol();
		String stockName = stock.getStockName();
		String numberOfShares = Integer.toString(stock.getNumberOfShares());
		String sharePrice = decimal.format(stock.getCurrentPrice());
		String change = decimal.format(stock.getChange());
		String holdingValue = decimal.format(stock.getHoldingValue());

		Object[] data = { tickerSymbol, stockName, numberOfShares, sharePrice,
				change, holdingValue };
		MyTableView ft = (MyTableView) jTabbedPane1.getComponent(jTabbedPane1
				.getComponentCount() - 1);

		if (ft.getModel() != null) {
			ft.getModel().addRow(data);
			setTotalValueLabel(decimal.format(portfolio.getTotalValue()));
		}
	}

	/*
	 * Delete a row of data from the JTable
	 */
	public void deleteRow(MyTableView tableView, int rowNumber) {
		tableView.getModel().removeRow(rowNumber);// REMOVE ROW from TABLE
		// UPDATE total value label
		setTotalValueLabel(decimal.format(tableView.getPortfolio()
				.getTotalValue()));
	}

	/**
	 * 
	 * @param st
	 * @param p
	 */
	public void addTab(String portfolioName, Portfolio p) {
		MyTableView tableView = new MyTableView(p);
		portfolio = tableView.getPortfolio();
		if (portfolioName != null) {
			jTabbedPane1.add(portfolioName, tableView);
		}
	}

	/*
	 * Closes a tab.
	 */
	public void closeTab() {
		jTabbedPane1.remove(jTabbedPane1.getSelectedComponent());
	}

	/*
	 * Updates all data in this tab
	 */
	public void updateTab(int row, MyTableView tableView, String ticker) {
		Stock stock = tableView.getPortfolio().getStock(ticker);
		// double sharePrice = stock.getCurrentPrice();
		double change = stock.getChange();
		double holdingValue = stock.getHoldingValue();
		tableView.getTable().getModel()
				.setValueAt(decimal.format(stock.getCurrentPrice()), row, 3);
		tableView.getTable().getModel()
				.setValueAt(decimal.format(change), row, 4);// set price of
															// shares
		tableView.getTable().getModel()
				.setValueAt(decimal.format(holdingValue), row, 5); // set new
																	// holding
																	// value
		setTotalValueLabel(decimal.format(tableView.getPortfolio()
				.getTotalValue()));
	}

	/**
	 * Edit a row
	 * 
	 * @param
	 */
	public void editRow(MyTableView tableView, String tickerSymbol) {
		int row = tableView.getTable().getSelectedRow(); // get the selected row
		String shareNum = Integer.toString(tableView.getPortfolio().getStock(tickerSymbol).getNumberOfShares());
		String change = decimal.format(tableView.getPortfolio()
				.getStock(tickerSymbol).getChange());
		String newHoldingValue = decimal.format(tableView.getPortfolio()
				.getStock(tickerSymbol).getHoldingValue());
		tableView.getTable().getModel().setValueAt(shareNum, row, 2); // set
																		// price
																		// of
																		// shares
		tableView.getTable().getModel().setValueAt(change, row, 4);
		tableView.getTable().getModel().setValueAt(newHoldingValue, row, 5); // set
																				// new
																				// holding
																				// value
		setTotalValueLabel(decimal.format(tableView.getPortfolio()
				.getTotalValue()));
	}

	/**
	 * 
	 * @return
	 */
	public JTabbedPane getTabs() {
		return jTabbedPane1;
	}
	
}
