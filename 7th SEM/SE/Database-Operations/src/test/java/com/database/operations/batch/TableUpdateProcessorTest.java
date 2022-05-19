package com.database.operations.batch;

import static org.junit.Assert.*;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.database.operations.model.ColumnDetails;
import com.database.operations.model.RowDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TableUpdateProcessorTest {
	
	TableUpdateProcessor tableUpdateProcessor = new TableUpdateProcessor();

    private ColumnDetails getTempColumnDetails(String columnName, boolean nullable, boolean primaryKey, int columnSize, String columnValue) {
        ColumnDetails data = new ColumnDetails();
        data.setColumnName(columnName);
        data.setNullable(nullable);
        data.setPrimaryKey(primaryKey);
        data.setColumnSize(columnSize);
        data.setDataType(Types.VARCHAR);
        data.setColumnValue(columnValue);
        return data;
    }
    
    @Test
    public void testOtherOperation() {
        Map<String, ColumnDetails> rowData = new HashMap<>();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,true,0,null));
        rowData.put("col3",getTempColumnDetails("col2",false,true,2,"sdfasda"));
        RowDetails rowDetails = new RowDetails("tableName","Create",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
    }
    
    @Test
    public void testDeleteOperation() {
        Map<String, ColumnDetails> rowData = new HashMap<>();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,true,0,null));
        RowDetails rowDetails = new RowDetails("tableName","Delete",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
        
        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,true,0,null));
        rowData.put("col3",getTempColumnDetails("col3",false,true,2,"sdfasda"));
        rowDetails = new RowDetails("tableName","Delete",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
        
        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,true,2,"sdfasda"));
        rowDetails = new RowDetails("tableName","Delete",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
        
        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,true,2,""));
        rowDetails = new RowDetails("tableName","Delete",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
        
        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,false,0,null));
        rowData.put("col3",getTempColumnDetails("col3",false,true,2,"aa"));
        rowDetails = new RowDetails("tableName","Delete",rowData);
        assertNotNull(tableUpdateProcessor.process(rowDetails));
    }

    @Test
    public void testInsertAndUpdateOperation() {   // change processor conditions
        Map<String, ColumnDetails> rowData = new HashMap<>();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,true,0,null));
        RowDetails rowDetails = new RowDetails("tableName","Insert",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));

        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",true,false,0,null));
        rowDetails = new RowDetails("tableName","Update",rowData);
        assertNotNull(tableUpdateProcessor.process(rowDetails));
        
        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",false,false,0,null));
        rowDetails = new RowDetails("tableName","Update",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
        
        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",true,false,0,null));
        rowData.put("col3",getTempColumnDetails("col3",false,false,0,""));
        rowDetails = new RowDetails("tableName","Update",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
        
        rowData.clear();
        rowData.put("col1",getTempColumnDetails("col1",false,true,9,"123456789"));
        rowData.put("col2",getTempColumnDetails("col2",true,false,0,null));
        rowData.put("col3",getTempColumnDetails("col3",true,true,0,null));
        rowDetails = new RowDetails("tableName","Update",rowData);
        assertNull(tableUpdateProcessor.process(rowDetails));
    }

    private RowDetails getRowDetailsWithValueAndType(String value, int type) {
        // table name already tested in reader so No need to check it
        String tableName = "table_name";
        String operation = "Insert";
        Map<String, ColumnDetails> mp = new HashMap<>();
        ColumnDetails column = new ColumnDetails();
        column.setColumnName("ColumnName");
        column.setColumnSize(0);
        column.setColumnValue(value);
        column.setNullable(false);
        column.setDataType(type);
        column.setPrimaryKey(true);
        mp.put("ColumnName", column);
        return new RowDetails(tableName, operation, mp);
    }

    @Test
    public void testString() {
        RowDetails data = getRowDetailsWithValueAndType("temp variable",Types.VARCHAR);
        data.getRowData().get("ColumnName").setColumnSize(13);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("temp variable2",Types.VARCHAR);
        data.getRowData().get("ColumnName").setColumnSize(13);
        assertNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testBinary() {
        RowDetails data = getRowDetailsWithValueAndType("कैसे हो",Types.BINARY);
        data.getRowData().get("ColumnName").setColumnSize(4);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("How Are You?",Types.BINARY);
        data.getRowData().get("ColumnName").setColumnSize(12);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("010101001",Types.LONGVARBINARY);
        data.getRowData().get("ColumnName").setColumnSize(9);
        assertNotNull(tableUpdateProcessor.process(data));

    }

    @Test
    public void testDateAndTime() {
        RowDetails data = getRowDetailsWithValueAndType("0001-10-15",Types.DATE);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2022-13-15",Types.DATE);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2020-2-30",Types.DATE);
        assertNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType("2020-2-15 10:10:10",Types.DATE);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2020-10-20 10:10:10",Types.TIMESTAMP);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2020-10-20 25:10:10",Types.TIMESTAMP);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2020-10-20 20:10:100",Types.TIMESTAMP);
        assertNull(tableUpdateProcessor.process(data));
        

        data = getRowDetailsWithValueAndType("25:10:10",Types.TIME);
        assertNull(tableUpdateProcessor.process(data));
        

        data = getRowDetailsWithValueAndType("10:10:10",Types.TIME);
        assertNotNull(tableUpdateProcessor.process(data));
    }

    private RowDetails getRowDetailsOfDecimal(String value,int type,int size,int precision) {
        // table name already tested in reader so No need to check it
        String tableName = "table_name";
        String operation = "Insert";
        Map<String, ColumnDetails> mp = new HashMap<>();
        ColumnDetails column = new ColumnDetails();
        column.setColumnName("ID");
        column.setColumnSize(size);
        column.setColumnValue(value);
        column.setNullable(false);
        column.setDataType(type);
        column.setPrimaryKey(true);
        column.setPrecision(precision);
        mp.put("ID", column);
        return new RowDetails(tableName, operation, mp);
    }

    @Test
    public void testDecimalNumber() {
        RowDetails data = getRowDetailsOfDecimal("-11.1",Types.DECIMAL,3,2);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsOfDecimal("11.1101",Types.NUMERIC,3,2);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsOfDecimal("123456.1101",Types.NUMERIC,10,4);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsOfDecimal("-1245.45238",Types.DECIMAL,9,4);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsOfDecimal("-12ab45.45238",Types.DECIMAL,9,4);
        assertNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testRealNumber() {
        RowDetails data = getRowDetailsWithValueAndType("-1.0000000010", Types.REAL);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("-0.1234567", Types.REAL);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("123456.07", Types.REAL);
        assertNotNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType("1234adf56.07", Types.REAL);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("123.4567", Types.REAL);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("123456.7", Types.REAL);
        assertNotNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testFloatNumber() {
        RowDetails data = getRowDetailsWithValueAndType("-1.00000000000000017", Types.FLOAT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("-123456789.123456", Types.FLOAT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("123456789.1234567", Types.FLOAT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("340282346638528860000000000000000000001.000000", Types.FLOAT);
        assertNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType("-340282346638528860000000000000000000000.000001", Types.FLOAT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("123456789012345", Types.FLOAT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("12345678.9012345", Types.FLOAT);
        assertNotNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testDoubleNumber() {
        RowDetails data = getRowDetailsWithValueAndType("-1.00000000000000017", Types.DOUBLE);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("-123456789.123456", Types.DOUBLE);
        assertNotNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType(String.valueOf(Double.NEGATIVE_INFINITY), Types.DOUBLE);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("67000000000000000046513.1234567", Types.DOUBLE);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("100000000.000005", Types.DOUBLE);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("12345678.9012345", Types.DOUBLE);
        assertNotNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType("179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000001", Types.DOUBLE);
        assertNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType("-179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000001", Types.DOUBLE);
        assertNull(tableUpdateProcessor.process(data));
    }



    @Test
    public void testBitIntegerNumber() {
    	RowDetails data = getRowDetailsWithValueAndType("-1",Types.BOOLEAN);
        assertNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType("0",Types.BOOLEAN);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("1",Types.BOOLEAN);
        assertNotNull(tableUpdateProcessor.process(data));
        
        data = getRowDetailsWithValueAndType("-1",Types.BIT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("0",Types.BIT);    // change == to with >= and <= in processor
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2",Types.BIT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("1",Types.BIT);
        assertNotNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testTinyIntegerNumber() {
        RowDetails data = getRowDetailsWithValueAndType("-15",Types.TINYINT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("0",Types.TINYINT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("256",Types.TINYINT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("a",Types.TINYINT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("255",Types.TINYINT);
        assertNotNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testSmallIntegerNumber() {
        RowDetails data = getRowDetailsWithValueAndType("-32769", Types.SMALLINT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("-32768", Types.SMALLINT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("32769", Types.SMALLINT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("32767", Types.SMALLINT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("3200", Types.SMALLINT);
        assertNotNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testIntegerNumber() {
        RowDetails data = getRowDetailsWithValueAndType("-2147483649", Types.INTEGER);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("-2147483648", Types.INTEGER);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2147483648", Types.INTEGER);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("2147483647", Types.INTEGER);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("147483647", Types.INTEGER);
        assertNotNull(tableUpdateProcessor.process(data));
    }

    @Test
    public void testBigIntegerNumber() {
        RowDetails data = getRowDetailsWithValueAndType("-9223372036854775809", Types.BIGINT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("-9223372036854775808", Types.BIGINT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("9223372036854775808", Types.BIGINT);
        assertNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("9223372036854775807", Types.BIGINT);
        assertNotNull(tableUpdateProcessor.process(data));

        data = getRowDetailsWithValueAndType("92233720354775808", Types.BIGINT);
        assertNotNull(tableUpdateProcessor.process(data));
    }
    
    @Test
    public void testUnknowDataType() {
        RowDetails data = getRowDetailsWithValueAndType("temp variable",Types.CLOB);
        assertNull(tableUpdateProcessor.process(data));
    }

}