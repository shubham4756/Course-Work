package com.database.operations.model;

/**
 * ColumnDetails Contain all metadata of Column which is present in database
 * table
 */
public class ColumnDetails {
	/**
	 * Represents column Name in table
	 */
	private String columnName;
	/**
	 * Represents column Size of column
	 */
	private int columnSize;
	/**
	 * Represents data Type of column
	 */
	private int dataType;
	/**
	 * Represents column is nullable or not
	 */
	private boolean nullable;
	/**
	 * Represents column value
	 */
	private String columnValue;
	/**
	 * Represents column is primaryKey or not
	 */
	private boolean primaryKey;
	/**
	 * Represents precision for some optional data type like Decimal and Numeric
	 */
	private int precision;

	/**
	 * Constructor without any parameters and set primaryKey false
	 */
	public ColumnDetails() {
		this.primaryKey = false;
	}

	/**
	 * To Get column Name in table
	 * 
	 * @return column Name in table
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * To Set column Name in table
	 * 
	 * @param columnName column Name in table
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * To Get column Size of column
	 * 
	 * @return column Size of column
	 */
	public int getColumnSize() {
		return columnSize;
	}

	/**
	 * To Set column Size of column
	 * 
	 * @param columnSize column Size of column
	 */
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	/**
	 * To Get data Type of column
	 * 
	 * @return data Type of column
	 */
	public int getDataType() {
		return dataType;
	}

	/**
	 * To Set data Type of column
	 * 
	 * @param dataType data Type of column
	 */
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	/**
	 * To Get Whether column is nullable or not
	 * 
	 * @return Whether column is nullable or not
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * To Set column is nullable or not
	 * 
	 * @param nullable column is nullable or not
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * To Get Whether column is primaryKey or not
	 * 
	 * @return Whether column is primaryKey or not
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * To Set column is primaryKey or not
	 * 
	 * @param primaryKey column is primaryKey or not
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * To Get column value
	 * 
	 * @return column value
	 */
	public String getColumnValue() {
		return columnValue;
	}

	/**
	 * To Set column value
	 * 
	 * @param columnValue column value
	 */
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	/**
	 * To Get precision for some optional data type like Decimal and Numeric
	 * 
	 * @return precision for some optional data type like Decimal and Numeric
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * To Set precision for some optional data type like Decimal and Numeric
	 * 
	 * @param precision precision for some optional data type like Decimal and
	 *                  Numeric
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 * To Set Details of Column except the columnName and columnValue
	 * 
	 * @param data Contains Metadata of database column which has same columnName
	 */
	public void setMetaData(ColumnDetails data) {
		this.columnSize = data.getColumnSize();
		this.dataType = data.getDataType();
		this.nullable = data.isNullable();
		this.precision = data.getPrecision();
		this.primaryKey = data.isPrimaryKey();
	}

	/**
	 * To Get all metadata of Current Column as string
	 * 
	 * @return metadata of Current Column as string
	 */
	@Override
	public String toString() {
		return "ColumnDetails{" + "columnName='" + columnName + '\'' + ", columnSize=" + columnSize + ", dataType="
				+ dataType + ", nullable=" + nullable + ", columnValue='" + columnValue + '\'' + ", primaryKey="
				+ primaryKey + ", precision=" + precision + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ColumnDetails))
			return false;
		ColumnDetails that = (ColumnDetails) o;
		return this.getColumnSize() == that.getColumnSize() && getDataType() == that.getDataType()
				&& isNullable() == that.isNullable() && isPrimaryKey() == that.isPrimaryKey()
				&& getPrecision() == that.getPrecision() && this.getColumnName().equals(that.getColumnName())
				&& ((this.getColumnValue() == null && that.getColumnValue() == null)
						|| this.getColumnValue().equals(that.getColumnValue()));
	}
}