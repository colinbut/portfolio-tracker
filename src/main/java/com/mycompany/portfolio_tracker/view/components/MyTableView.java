/**
 * 
 */
package com.mycompany.portfolio_tracker.view.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mycompany.portfolio_tracker.model.Portfolio;

/**
 * @author colin
 *
 */
public class MyTableView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTable table;
	private MyTableModel model1;
	private Portfolio portfolio;
	private JScrollPane scrollPane;
	
	/**
	 * Constructor
	 * 
	 * @param portfolio
	 */
	public MyTableView(Portfolio portfolio) {
		panel = new JPanel();
		this.portfolio = portfolio;
		String[] columnNames = { 
				"Ticker Symbol", 
				"Stock Name",
				"Number of Shares", 
				"Price per Share", 
				"Change",
				"Value of Holding" 
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

	/**
	 * 
	 * @return
	 */
	public Container getTableViewPanel() {
		return panel;
	}

	
	public Portfolio getPortfolio() {
		return portfolio;
	}

	/**
	 * 
	 * @return
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * 
	 * @return
	 */
	public MyTableModel getModel() {
		return model1;
	}

}

