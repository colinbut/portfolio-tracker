/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.portfolio_tracker.view.components;

import javax.swing.table.DefaultTableModel;

/**
 * View table model
 * 
 * @author colin
 *
 */
public class MyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param data
	 * @param columns
	 */
	public MyTableModel(String[][] data, String[] columns) {
		super(data, columns);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
