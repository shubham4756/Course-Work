package com.database.operations.batch;

import com.database.operations.model.ColumnDetails;
import com.database.operations.model.RowDetails;
import com.database.operations.service.DatabaseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TableUpdateReaderTest {

    @InjectMocks
    TableUpdateReader tableUpdateReader;

    @Mock
    DatabaseService databaseService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    // this test will work only for TableUpdateDetails initial file
    @Test
    public void testReaderNormal() {
        Mockito.when(databaseService.fetchColumnInfo("TEST")).thenReturn(getDatabaseColumnInfo());
        Mockito.when(databaseService.fetchColumnInfo("TGIAIEA")).thenReturn(new ArrayList<>());

        List<RowDetails> actual = new ArrayList<>();
        while(true) {
            RowDetails rowDetails = tableUpdateReader.read();
            if(rowDetails==null)
                break;
            if(rowDetails.getTableName().equals("TEST")) {
                actual.add(rowDetails);
            }
        }
        
        tableUpdateReader.setResource(new ClassPathResource("Test.xlsx"));
        tableUpdateReader.setFirstCall(true);
        while(true) {
            RowDetails rowDetails = tableUpdateReader.read();
            if(rowDetails==null)
                break;
            if(rowDetails.getTableName().equals("TEST")) {
                actual.add(rowDetails);
            }
        }
        
        List<RowDetails> expected = getExpectedRowDetails();

        assertEquals(actual,expected);
    }

    @Test
    public void testReaderTableNotExist() {
        Mockito.when(databaseService.fetchColumnInfo("TEST")).thenReturn(new ArrayList<>());

        List< RowDetails > actual = new ArrayList<>();
        while(true) {
            RowDetails rowDetails = tableUpdateReader.read();
            if(rowDetails==null)
                break;
            if(rowDetails.getTableName().equals("TEST")) {
                actual.add(rowDetails);
            }
        }

        List<RowDetails> expected = new ArrayList<>();
        assertEquals(actual,expected);
    }
    
    @Test
    public void testReaderTableHasOneColumnExtra() {

        List<ColumnDetails> databaseColumnInfo = getDatabaseColumnInfo();
        databaseColumnInfo.add(getColumnDetails("MobileNo", 10, null, true, false));
        Mockito.when(databaseService.fetchColumnInfo("TEST")).thenReturn(databaseColumnInfo);

        List< RowDetails > actual = new ArrayList<>();
        while(true) {
            RowDetails rowDetails = tableUpdateReader.read();
            if(rowDetails==null)
                break;
            if(rowDetails.getTableName().equals("TEST")) {
                actual.add(rowDetails);
            }
        }

        List<RowDetails> expected = new ArrayList<>();
        assertEquals(actual,expected);
    }
    
    @Test
    public void testReaderWithoutExcel() {
    	tableUpdateReader.setResource(new ClassPathResource("UnknowExcelFile.xlsx"));
    	Assert.assertEquals(null, tableUpdateReader.read());
    }

    private ColumnDetails getColumnDetails(String columnName,int columnSize,String columnValue,
                                          boolean nullable, boolean primaryKey) {
        ColumnDetails data = new ColumnDetails();
        data.setColumnName(columnName);
        data.setDataType(Types.VARCHAR);
        data.setColumnSize(columnSize);
        data.setColumnValue(columnValue);
        data.setNullable(nullable);
        data.setPrimaryKey(primaryKey);
        data.setPrecision(0);
        return data;
    }

    private List<ColumnDetails> getDatabaseColumnInfo() {
        List<ColumnDetails> databaseColumnInfo = new ArrayList<>();
        databaseColumnInfo.add(getColumnDetails("ID", 10, null, false, true));
        databaseColumnInfo.add(getColumnDetails("EMAIL", 100, null, false, false));
        databaseColumnInfo.add(getColumnDetails("NAME", 100, null, true, false));
        return databaseColumnInfo;
    }

    private List<RowDetails> getExpectedRowDetails() {
        String tableName = "TEST";
        String operation = "Insert";
        List<RowDetails> expected = new ArrayList<>();
        Map<String, ColumnDetails> rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0001", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name1@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 1", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0002", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name2@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0003", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name3@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 3", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        operation = "Update";
        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0001", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name10@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 1", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0002", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name2@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 2", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        operation = "Delete";
        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0001", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));
        
        operation = "Insert";
        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0001", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name1@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 1", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));


        return expected;
    }

}