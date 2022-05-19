package com.database.operations.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.database.operations.model.ColumnDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * the Database service will fetch the required table column metadata
 */
@Service
public class DatabaseService {

	@Value("${job-repository.datasource.jdbcUrl}")
	private String databaseUrl;

	@Value("${job-repository.datasource.username}")
	private String username;

	@Value("${job-repository.datasource.password}")
	private String password;

	/**
	 * establishes a connection to the database and fetches the metadata from the
	 * database
	 * 
	 * @param tableName contains sheetName/tableName
	 * @return column metadata which is present in database
	 */
	public List<ColumnDetails> fetchColumnInfo(String tableName) {
		List<ColumnDetails> columnInfo = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(databaseUrl, username, password)) {
			DatabaseMetaData databaseMetaData = conn.getMetaData();
			columnInfo = getColumnInfo(databaseMetaData, tableName);
		} catch (SQLException e) {
			Map<String, String> loggerMap = new HashMap<>();
			loggerMap.put("warning", e.toString());
		}
		return columnInfo;
	}

	/**
	 * stores the metadata in the column details
	 * 
	 * @param databaseMetaData database metadata instance
	 * @param tableName        current table and sheet name
	 * @return column metadata which is present in database
	 * @throws SQLException if Column metadata cannot be read
	 */
	private List<ColumnDetails> getColumnInfo(DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
		// Primary keys name
		ResultSet primaryKeysResultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
		List<String> primaryKeysNames = new ArrayList<>();
		while (primaryKeysResultSet.next()) {
			primaryKeysNames.add(primaryKeysResultSet.getString("COLUMN_NAME"));
		}

		// All columns Info and names
		ResultSet columns = databaseMetaData.getColumns(null, null, tableName, null);
		List<ColumnDetails> columnInfoList = new ArrayList<>();
		while (columns.next()) {
			ColumnDetails info = readColumnInfo(columns);
			info.setPrimaryKey(primaryKeysNames.contains(info.getColumnName()));
			columnInfoList.add(info);
		}
		return columnInfoList;
	}

	/**
	 * reads all the specifications of each column and stores in a map of type
	 * ColumnDetails
	 * 
	 * @param column resultSet of all column details which is present in database
	 * @return ColumnDetails map
	 * @throws SQLException if in appropriate data will be fetch
	 */
	private ColumnDetails readColumnInfo(ResultSet column) throws SQLException {
		ColumnDetails info = new ColumnDetails();
		info.setColumnName(column.getString("COLUMN_NAME"));
		info.setColumnSize(column.getInt("COLUMN_SIZE"));
		info.setDataType(column.getInt("DATA_TYPE"));
		info.setNullable(column.getBoolean("IS_NULLABLE"));
		if (info.getDataType() == Types.DECIMAL)
			info.setPrecision(column.getInt("DECIMAL_DIGITS"));
		return info;
	}

	/**
	 * set the password for database
	 * 
	 * @param password string of database password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}