package com.database.operations.model;

import java.util.HashMap;
import java.util.Map;

/**
 * RowDetails Contain Data of One Row of Excel Sheet
 */
public class RowDetails {

	/**
	 * Represents name of current sheetName/TableName
	 */
	private String tableName;
	/**
	 * Represents Operations like Insert, Update, Delete
	 */
	private String operation;
	/**
	 * Represents Row Data in Map where key = column name , value = column value
	 */
	private Map<String, ColumnDetails> rowData = new HashMap<>();

	/**
	 * Constructor with all parameters
	 * 
	 * @param tableName Represents name of current excel sheet
	 * @param operation Represents Operations like Insert, Update, Delete
	 * @param rowData   Represents Row Data in Map where key = column name , value =
	 *                  column value
	 */
	public RowDetails(String tableName, String operation, Map<String, ColumnDetails> rowData) {
		this.tableName = tableName;
		this.operation = operation;
		this.rowData = rowData;
	}

	/**
	 * To Get current sheetName/TableName
	 * 
	 * @return name of current sheet/table
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * To Get which Operation required on current Row
	 * 
	 * @return operation required on row
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * to Get current Row Data
	 * 
	 * @return Map of column name and column value
	 */
	public Map<String, ColumnDetails> getRowData() {
		return rowData;
	}

	/**
	 * To Get all detail of Current Row as string
	 * 
	 * @return current row detail as a string
	 */
	@Override
	public String toString() {
		return "RowDetails{" + "rowData=" + rowData + ", tableName='" + tableName + '\'' + ", operation='" + operation
				+ '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RowDetails))
			return false;
		RowDetails that = (RowDetails) o;
		return this.getTableName().equals(that.getTableName()) && this.getOperation().equals(that.getOperation())
				&& this.getRowData().equals(that.getRowData());
	}
}