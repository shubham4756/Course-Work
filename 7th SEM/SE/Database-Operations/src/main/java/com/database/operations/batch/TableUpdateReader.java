package com.database.operations.batch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.database.operations.model.ColumnDetails;
import com.database.operations.model.RowDetails;
import com.database.operations.service.DatabaseService;

/**
 * The Reader read the data from excel sheet and meta data from database and
 * store it into rowDetails list
 */
public class TableUpdateReader implements ItemReader<RowDetails> {

	/**
	 * the Logger responsible for logging
	 */
	private static final Logger jsonLogger = LoggerFactory.getLogger(TableUpdateReader.class);

	/**
	 * databaseService is responsible for fetch all column metadata
	 */
	@Autowired
	private DatabaseService databaseService;

	/**
	 * index of rowDetailsList
	 */
	private int index = 0;

	/**
	 * This checkedTables stores the table name for which we tried to fetch table
	 * details in database
	 */
	private Set<String> checkedTables = new HashSet<>();

	/**
	 * databaseColumnInfo store the table all column metadata which is present in
	 * database
	 */
	private Map<String, List<ColumnDetails>> databaseColumnInfo = new HashMap<>();

	/**
	 * formatter is responsible to convert all cell value in string
	 */
	private DataFormatter formatter = new DataFormatter();

	/**
	 * defining a new threadLocal of a type-list of rowDetails
	 */
	private ThreadLocal<List<RowDetails>> rowDetailsList = ThreadLocal.withInitial(ArrayList::new);

	private boolean firstCall = true;

	private Resource resource = new ClassPathResource("TableUpdateDetails.xlsx");

	/**
	 * the method read input data and return each row details one by one
	 * 
	 * @return a rowDetail type object
	 */
	@Override
	public RowDetails read() {
		if (firstCall) {
			getRowDetails();
			index = 0;
			firstCall = false;
		}

		RowDetails data = null;

		if (index < rowDetailsList.get().size()) {
			data = rowDetailsList.get().get(index);
			index++;
			if (index == rowDetailsList.get().size()) {
				rowDetailsList.remove();
			}
		} else {
			index = 0;
		}
		return data;
	}

	/**
	 * gets the rowDetails by accessing the workbooks from the excel sheet
	 */
	private void getRowDetails() {
		try (InputStream inputstream = resource.getInputStream();
				XSSFWorkbook workbook = new XSSFWorkbook(inputstream)) {
			int numberOfSheets = workbook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {

				XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

				if (!canReadSheet(sheet.getSheetName()))
					continue;

				readSheet(sheet);
			}
		} catch (IOException e) {
			jsonLogger.warn(e.toString());
		}
	}

	/**
	 * reads the excel sheet to put the data in the variable rowData and calls the
	 * rowDetails function
	 * 
	 * @param sheet the excel sheet
	 */
	private void readSheet(XSSFSheet sheet) {
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		List<String> columnNames = new ArrayList<>();
		for (Cell cell : sheet.getRow(0))
			columnNames.add(formatter.formatCellValue(cell));

		for (int r = 1; r <= rows; r++) {
			XSSFRow row = sheet.getRow(r);

			Map<String, ColumnDetails> rowData = new HashMap<>();

			for (int j = 1; j < cols; j++) {
				ColumnDetails columnDetail = new ColumnDetails();
				columnDetail.setColumnValue(formatter.formatCellValue(row.getCell(j)));
				columnDetail.setColumnName(columnNames.get(j));
				rowData.put(columnNames.get(j), columnDetail);
			}

			RowDetails data = new RowDetails(sheet.getSheetName(), formatter.formatCellValue(row.getCell(0)), rowData);
			// set data type and all other field into column Details
			boolean canBeProcessed = setColumnInfo(data);
			if (canBeProcessed)
				rowDetailsList.get().add(data);
		}
	}

	/**
	 * try to set all column meta data from data base to excel row details
	 * 
	 * @param data contains one row details which is present in excel sheet
	 * @return return true if we can set all column meta data else false
	 */
	private boolean setColumnInfo(RowDetails data) {
		List<ColumnDetails> columnDetails = databaseColumnInfo.get(data.getTableName());
		for (ColumnDetails column : columnDetails) {
			if (data.getRowData().containsKey(column.getColumnName())) {
				data.getRowData().get(column.getColumnName()).setMetaData(column);
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * check in database if a table is present in database and load it to the map
	 * 
	 * @param tableName contains sheetName/tableName
	 * @return return true if same name table is present in database else false
	 */
	private boolean canReadSheet(String tableName) {
		if (!checkedTables.contains(tableName)) {
			List<ColumnDetails> columnDetails = databaseService.fetchColumnInfo(tableName);
			if (!columnDetails.isEmpty())
				databaseColumnInfo.put(tableName, columnDetails);
			checkedTables.add(tableName);
		}

		if (!databaseColumnInfo.containsKey(tableName)) {
			jsonLogger.info(tableName + " name table is not present in database !!");
			return false;
		}
		return true;
	}

	/**
	 * set the excel file resource
	 * 
	 * @param resource resource of excel file
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * set the firstCall variable
	 * 
	 * @param firstCall boolean variable for read method
	 */
	public void setFirstCall(boolean firstCall) {
		this.firstCall = firstCall;
	}
}