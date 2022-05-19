package com.database.operations.batch;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import com.database.operations.model.ColumnDetails;
import com.database.operations.model.RowDetails;

/**
 * This Processor class validate the data based on required operation and for
 * all columns it will check appropriate value are present or not like same data
 * type and size
 */
public class TableUpdateProcessor implements ItemProcessor<RowDetails, RowDetails> {

	/**
	 * the Logger responsible for logging
	 */
	private static final Logger jsonLogger = LoggerFactory.getLogger(TableUpdateProcessor.class);

	private static final BigInteger BIT_MIN = BigInteger.valueOf(0);
	private static final BigInteger BIT_MAX = BigInteger.valueOf(1);

	private static final BigInteger TINYINT_MIN = BigInteger.valueOf(0);
	private static final BigInteger TINYINT_MAX = BigInteger.valueOf(255);

	private static final BigInteger SMALLINT_MIN = BigInteger.valueOf(Short.MIN_VALUE);
	private static final BigInteger SMALLINT_MAX = BigInteger.valueOf(Short.MAX_VALUE);

	private static final BigInteger INTEGER_MIN = BigInteger.valueOf(Integer.MIN_VALUE);
	private static final BigInteger INTEGER_MAX = BigInteger.valueOf(Integer.MAX_VALUE);

	private static final BigInteger BIGINT_MIN = BigInteger.valueOf(Long.MIN_VALUE);
	private static final BigInteger BIGINT_MAX = BigInteger.valueOf(Long.MAX_VALUE);

	private static final BigDecimal FLOAT_MAX = BigDecimal.valueOf(Float.MAX_VALUE);
	private static final BigDecimal FLOAT_MIN = BigDecimal.valueOf(-Float.MAX_VALUE);

	private static final BigDecimal DOUBLE_MAX = BigDecimal.valueOf(Double.MAX_VALUE);
	private static final BigDecimal DOUBLE_MIN = BigDecimal.valueOf(-Double.MAX_VALUE);

	/**
	 * the process function of return type RowDetails checks the operation for every
	 * row and validates the same according to their operations and returns the
	 * validated data
	 * 
	 * @param data represents data which is send by reader after reading each and
	 *             every rows from excel sheet
	 * @return one row Details if data is validate else if any error is there it
	 *         will return null
	 */
	@Override
	public RowDetails process(RowDetails data) {
		if ("Delete".equals(data.getOperation())) {
			if (validatePrimaryKeys(data.getRowData()))
				return data;
			else {
				jsonLogger.info("Invalid data -- " + data.toString());
				return null;
			}
		} else if ("Insert".equals(data.getOperation()) || "Update".equals(data.getOperation())) {
			if (validateRow(data.getRowData()))
				return data;
			else {
				jsonLogger.info("Invalid data -- " + data.toString());
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * the validatePrimaryKeys function checks if a column is a primary key, it
	 * shouldn't be null and should match the columns data type
	 * 
	 * @param rowData a rowDetail object that stores the data of the cells of a row
	 * @return return true if all primary key's value is valid else false
	 */
	private boolean validatePrimaryKeys(Map<String, ColumnDetails> rowData) {
		// all primary keys should be of proper data type
		for (Map.Entry<String, ColumnDetails> data : rowData.entrySet()) {
			ColumnDetails columnDetail = data.getValue();
			if (columnDetail.isPrimaryKey()) {
				if (null != columnDetail.getColumnValue() && !StringUtils.isEmpty(columnDetail.getColumnValue())) {
					if (!validateDataTypeAndSizes(columnDetail))
						return false;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * the function validateRow verifies that the rowData should not be empty if the
	 * column is not nullable and and try to convert values in column required data
	 * type if it's not able to convert return false else return false
	 * 
	 * @param rowData a rowDetail object that stores the data of the cells of a row
	 * @return return true if all column value is valid else false
	 */
	private boolean validateRow(Map<String, ColumnDetails> rowData) {
		for (ColumnDetails column : rowData.values()) {
			if ((null == column.getColumnValue() || StringUtils.isEmpty(column.getColumnValue()))
					&& (!column.isNullable() || column.isPrimaryKey()))
				return false;
			if (!StringUtils.isEmpty(column.getColumnValue()) && !validateDataTypeAndSizes(column)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Try to convert in required data type and check the size data
	 * 
	 * @param data a columnDetails that store all data of one column
	 * @return return true if column data is valid
	 */
	private boolean validateDataTypeAndSizes(ColumnDetails data) {
		int type = data.getDataType();
		String str = data.getColumnValue();
		switch (type) {
		case Types.BOOLEAN:
		case Types.BIT:
		case Types.TINYINT:
		case Types.SMALLINT:
		case Types.INTEGER:
		case Types.BIGINT:
			return isNumber(str, type);
		case Types.REAL:
		case Types.FLOAT:
		case Types.DOUBLE:
			return isDouble(str, type);
		case Types.DECIMAL:
		case Types.NUMERIC:
			return isDecimal(str, data.getPrecision(), data.getColumnSize());
		case Types.BINARY:
		case Types.VARBINARY:
		case Types.LONGVARBINARY:
		case Types.BLOB:
			return isBinary(str, data.getColumnSize());
		case Types.DATE:
		case Types.TIME:
		case Types.TIMESTAMP:
			return isDateAndTime(str, type);
		case Types.CHAR:
		case Types.VARCHAR:
		case Types.LONGNVARCHAR:
		default:
			return str.length() <= data.getColumnSize();
		}
	}

	/**
	 * check can be convert into binary data
	 * 
	 * @param str  the cell value
	 * @param size the specified size of the data type
	 * @return true/false
	 */
	private boolean isBinary(String str, int size) {
		byte[] byteArray = str.getBytes();
		return (byteArray.length <= size);
	}

	/**
	 * check can be convert into date and time
	 * 
	 * @param str  the cell value
	 * @param type the data type
	 * @return true/false
	 */
	private boolean isDateAndTime(String str, int type) {
		switch (type) {
		case Types.DATE:
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			if (str.length() > 10)
				return false;
			try {
				format.parse(str);
				return true;
			} catch (Exception e) {
				return false;
			}
		case Types.TIMESTAMP:
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setLenient(false);
			try {
				format.parse(str);
				return true;
			} catch (Exception e) {
				return false;
			}
		default: // check Types.TIME
			format = new SimpleDateFormat("HH:mm:ss");
			format.setLenient(false);
			try {
				format.parse(str);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * check can be convert into decimal data type
	 * 
	 * @param str       the cell value
	 * @param precision the precision value of that type
	 * @param size      the size of the data type
	 * @return true/false
	 */
	private boolean isDecimal(String str, int precision, int size) {
		try {
			BigDecimal number = new BigDecimal(str);
			int mantissa = number.precision();
			int decimalPoint = number.scale();
			return number.compareTo(DOUBLE_MIN) >= 0 && number.compareTo(DOUBLE_MAX) <= 0 && mantissa <= size
					&& decimalPoint <= precision;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * check can be convert into double data type
	 * 
	 * @param str  the cell value
	 * @param type the data type
	 * @return true/false
	 */
	private boolean isDouble(String str, int type) {
		try {
			BigDecimal number = new BigDecimal(str);
			switch (type) {
			case Types.REAL:
			case Types.FLOAT:
				return number.compareTo(FLOAT_MIN) >= 0 && number.compareTo(FLOAT_MAX) <= 0;
			default: // check Types.DOUBLE:
				return number.compareTo(DOUBLE_MIN) >= 0 && number.compareTo(DOUBLE_MAX) <= 0;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * check can be convert into Number data type
	 * 
	 * @param str  the cell value
	 * @param type the data type
	 * @return true/false
	 */
	private boolean isNumber(String str, int type) {
		try {
			BigInteger number = new BigInteger(str);
			switch (type) {
			case Types.BOOLEAN:
				return (number.compareTo(BIT_MIN) == 0 || number.compareTo(BIT_MAX) == 0);
			case Types.BIT:
				return (number.compareTo(BIT_MIN) >= 0 && number.compareTo(BIT_MAX) <= 0);
			case Types.TINYINT:
				return (number.compareTo(TINYINT_MIN) >= 0 && number.compareTo(TINYINT_MAX) <= 0);
			case Types.SMALLINT:
				return (number.compareTo(SMALLINT_MIN) >= 0 && number.compareTo(SMALLINT_MAX) <= 0);
			case Types.INTEGER:
				return (number.compareTo(INTEGER_MIN) >= 0 && number.compareTo(INTEGER_MAX) <= 0);
			default: // check Types.BIGINT:
				return (number.compareTo(BIGINT_MIN) >= 0 && number.compareTo(BIGINT_MAX) <= 0);
			}
		} catch (Exception e) {
			return false;
		}
	}

}