package com.database.operations.batch;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.database.operations.model.ColumnDetails;
import com.database.operations.model.RowDetails;

/**
 * This Writer class do Insertion, Updation and Deletion operation on database
 */
public class TableUpdateWriter implements ItemWriter<RowDetails> {
	/**
	 * the Logger responsible for logging
	 */
	private static final Logger jsonLogger = LoggerFactory.getLogger(TableUpdateWriter.class);

	/**
	 * This JdbcTemplate is responsible for operation with database
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private int numberOfInsertions;
	private int numberOfUpdations;
	private int numberOfDeletions;
	private int invalidOperations;

	/**
	 * this function will call the appropriate method based on required operation on
	 * the data and in last it shows how much insertion, updation and deletions
	 * happened in database
	 * 
	 * @param list is the list of data which is validated in processor
	 */
	@Override
	public void write(List<? extends RowDetails> list) {
		numberOfInsertions = 0;
		numberOfUpdations = 0;
		numberOfDeletions = 0;
		invalidOperations = 0;
		for (RowDetails data : list) {
			if ("Insert".equals(data.getOperation())) { // insert
				numberOfInsertions += dbInsert(data);
			} else if ("Update".equals(data.getOperation())) { // update
				numberOfUpdations += dbUpdate(data);
			} else if ("Delete".equals(data.getOperation())) { // delete
				numberOfDeletions += dbDelete(data);
			} else {
				invalidOperations++;
			}
		}
		jsonLogger.info("Number of Insertions " + numberOfInsertions);
		jsonLogger.info("Number of Updations " + numberOfUpdations);
		jsonLogger.info("Number of Deletions " + numberOfDeletions);
		jsonLogger.info("Number of Invalid Operations " + invalidOperations);
	}

	/**
	 * it will create the SqlQuery using column names for Insert operation and using
	 * JdbcTemplate it will do insertion on database
	 * 
	 * @param data it contains data of one row which we want to insert in database
	 * @return the number of inserted row
	 */
	private int dbInsert(RowDetails data) {
		Map<String, ColumnDetails> mp = data.getRowData();
		String sqlQuery = "INSERT INTO " + data.getTableName() + " (";
		for (String columnName : mp.keySet()) {
			sqlQuery += columnName + ",";
		}
		sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
		sqlQuery += ") VALUES (";
		Object[] obj = new Object[mp.size()];
		int i = 0;

		for (Map.Entry<String, ColumnDetails> rowData : mp.entrySet()) {
			sqlQuery += "?,";
			obj[i++] = rowData.getValue().getColumnValue();
		}
		sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
		sqlQuery += ')';
		return jdbcTemplate.update(sqlQuery, obj);
	}

	/**
	 * it will create the SqlQuery using primary key's column and other columns for
	 * update operation and using JdbcTemplate it will do updation on database
	 * 
	 * @param data It contains data of one row which we want to update in database
	 * @return the number of updated row
	 */
	private int dbUpdate(RowDetails data) {
		Map<String, ColumnDetails> mp = data.getRowData();
		String sqlQuery = "UPDATE " + data.getTableName() + " SET ";
		Object[] obj = new Object[mp.size()];
		int index = 0;
		for (Map.Entry<String, ColumnDetails> rowData : mp.entrySet()) {
			String columnName = rowData.getKey();
			ColumnDetails columnDetail = rowData.getValue();
			if (!columnDetail.isPrimaryKey()) {
				sqlQuery += columnName + " = ?, ";
				obj[index++] = columnDetail.getColumnValue();
			}
		}
		sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + " WHERE ";
		for (Map.Entry<String, ColumnDetails> rowData : mp.entrySet()) {
			String columnName = rowData.getKey();
			ColumnDetails columnDetail = rowData.getValue();
			if (!columnDetail.isPrimaryKey())
				continue;
			sqlQuery += columnName + " = ?";
			if (index != mp.size() - 1) {
				sqlQuery += " AND ";
			}
			obj[index++] = columnDetail.getColumnValue();
		}
		return jdbcTemplate.update(sqlQuery, obj);
	}

	/**
	 * it will create the SqlQuery using only primary key's column for delete
	 * operation and using JbbcTemplate it will do deletion on database
	 * 
	 * @param data It contains data of one row which we want to delete from database
	 * @return the number of deleted row
	 */
	private int dbDelete(RowDetails data) {
		String sqlQuery = "DELETE FROM " + data.getTableName() + " WHERE ";
		Map<String, ColumnDetails> mp = data.getRowData();
		int len = 0;
		for (Map.Entry<String, ColumnDetails> rowData : mp.entrySet()) {
			ColumnDetails columnDetail = rowData.getValue();
			if (columnDetail.isPrimaryKey())
				len++;
		}
		Object[] obj = new Object[len];
		int i = 0;
		for (Map.Entry<String, ColumnDetails> rowData : mp.entrySet()) {
			String columnName = rowData.getKey();
			ColumnDetails columnDetail = rowData.getValue();
			if (!columnDetail.isPrimaryKey())
				continue;
			sqlQuery += columnName + " = ?";
			if (i != len - 1) {
				sqlQuery += " AND ";
			}
			obj[i++] = columnDetail.getColumnValue();
		}
		return jdbcTemplate.update(sqlQuery, obj);
	}

	/**
	 * get number of insertion operation
	 * 
	 * @return number of insertion operation made by writer on database
	 */
	public int getNumberOfInsertions() {
		return numberOfInsertions;
	}

	/**
	 * get number of updation operation
	 * 
	 * @return number of updation operation made by writer on database
	 */
	public int getNumberOfUpdations() {
		return numberOfUpdations;
	}

	/**
	 * get number of deletion operation
	 * 
	 * @return number of deletion operation made by writer on database
	 */
	public int getNumberOfDeletions() {
		return numberOfDeletions;
	}

	/**
	 * get number of Invalid operation
	 * 
	 * @return number of Invalid operation try to made by writer on database
	 */
	public int getInvalidOperations() {
		return invalidOperations;
	}

}