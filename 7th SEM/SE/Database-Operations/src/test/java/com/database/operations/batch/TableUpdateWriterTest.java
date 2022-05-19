package com.database.operations.batch;

import static org.junit.Assert.assertEquals;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.database.operations.model.ColumnDetails;
import com.database.operations.model.RowDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(JUnit4.class)
public class TableUpdateWriterTest {

    @InjectMocks
    TableUpdateWriter tableUpdateWriter;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWriterNormal() {
        List<RowDetails> list = getExpectedRowDetails();
        Mockito.RETURNS_DEFAULTS.equals(0);

        getJdbcResponse(list);

        tableUpdateWriter.write(list);

        assertEquals(3,tableUpdateWriter.getNumberOfInsertions());
        assertEquals(2,tableUpdateWriter.getNumberOfUpdations());
        assertEquals(1,tableUpdateWriter.getNumberOfDeletions());
        assertEquals(0,tableUpdateWriter.getInvalidOperations());
    }

    @Test
    public void testWriterRepeatedAndUnexpectedOperation() {
        List<RowDetails> list = getExpectedRowDetails();

        getJdbcResponse(list);

        Map<String, ColumnDetails> rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0003", false, true));
        rowData.put("Location", getColumnDetails("Location", 10, "surat", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name3@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 3", true, false));
        list.add(new RowDetails("TEST","Update",rowData));
        list.add(new RowDetails("TEST","Create",rowData));
        tableUpdateWriter.write(list);

        assertEquals(3,tableUpdateWriter.getNumberOfInsertions());
        assertEquals(2,tableUpdateWriter.getNumberOfUpdations());
        assertEquals(1,tableUpdateWriter.getNumberOfDeletions());
        assertEquals(1,tableUpdateWriter.getInvalidOperations());
    }

    private void getJdbcResponse(List<RowDetails> list) {
        for (RowDetails data : list) {
            if ("Insert".equals(data.getOperation())) { // insert
                dbInsert(data);
            } else if ("Update".equals(data.getOperation())) { // update
                dbUpdate(data);
            } else if ("Delete".equals(data.getOperation())) { // delete
                dbDelete(data);
            }
        }
    }

    private void dbInsert(RowDetails data) {
        Map<String , ColumnDetails> mp = data.getRowData();
        String sqlQuery ="INSERT INTO " + data.getTableName() + " (";
        for(String columnName:mp.keySet()) {
            sqlQuery += columnName + ",";
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
        sqlQuery += ") VALUES (";
        Object[] obj= new Object[mp.size()];
        int i=0;
        for(String columnName:mp.keySet()) {
            sqlQuery += "?,";
            obj[i++] = mp.get(columnName).getColumnValue();
        }
        sqlQuery = sqlQuery.substring(0,sqlQuery.length()-1);
        sqlQuery+=')';
        Mockito.when(jdbcTemplate.update(sqlQuery,obj)).thenReturn(1);
    }

    private void dbUpdate(RowDetails data) {
        Map<String, ColumnDetails> mp = data.getRowData();
        String sqlQuery = "UPDATE " + data.getTableName() + " SET ";
        Object[] obj = new Object[mp.size()];
        int index = 0;
        for (String columnName : mp.keySet()) {
            if (!mp.get(columnName).isPrimaryKey()) {
                sqlQuery += columnName + " = ?, ";
                obj[index++] = mp.get(columnName).getColumnValue();
            }
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + " WHERE ";
        for (String columnName : mp.keySet()) {
            if (!mp.get(columnName).isPrimaryKey())
                continue;
            sqlQuery += columnName + " = ?";
            if (index != mp.size() - 1) {
                sqlQuery += " AND ";
            }
            obj[index++] = mp.get(columnName).getColumnValue();
        }
        Mockito.when(jdbcTemplate.update(sqlQuery, obj)).thenReturn(1);
    }


    private void dbDelete(RowDetails data) {
        String sqlQuery = "DELETE FROM " + data.getTableName() + " WHERE ";
        Map<String, ColumnDetails> mp = data.getRowData();
        int len=0;
        for(String columnName:mp.keySet()) {
            if(mp.get(columnName).isPrimaryKey())
                len++;
        }
        Object[] obj = new Object[len];
        int i=0;
        for (String columnName:mp.keySet()) {
            if(!mp.get(columnName).isPrimaryKey())
                continue;
            sqlQuery += columnName + " = ?";
            if (i != len - 1) {
                sqlQuery += " AND ";
            }
            obj[i++] = mp.get(columnName).getColumnValue();
        }
        Mockito.when(jdbcTemplate.update(sqlQuery, obj)).thenReturn(1);
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

    private List<RowDetails> getExpectedRowDetails() {
        String tableName = "TEST";
        String operation = "Insert";
        List<RowDetails> expected = new ArrayList<>();
        Map<String, ColumnDetails> rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0001", false, true));
        rowData.put("Location", getColumnDetails("Location", 10, "surat", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name1@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 1", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0002", false, true));
        rowData.put("Location", getColumnDetails("Location", 10, "surat", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name2@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0003", false, true));
        rowData.put("Location", getColumnDetails("Location", 10, "surat", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name3@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 3", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        operation = "Update";
        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0001", false, true));
        rowData.put("Location", getColumnDetails("Location", 10, "surat", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name10@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 1", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0002", false, true));
        rowData.put("Location", getColumnDetails("Location", 10, "surat", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "name2@gmail.com", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "name 2", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        operation = "Delete";
        rowData = new HashMap<>();
        rowData.put("ID", getColumnDetails("ID", 10, "e0001", false, true));
        rowData.put("Location", getColumnDetails("Location", 10, "surat", false, true));
        rowData.put("EMAIL", getColumnDetails("EMAIL", 100, "", false, false));
        rowData.put("NAME", getColumnDetails("NAME", 100, "", true, false));
        expected.add(new RowDetails(tableName, operation, rowData));

        return expected;
    }
}
