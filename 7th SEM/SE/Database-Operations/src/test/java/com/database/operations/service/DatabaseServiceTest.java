package com.database.operations.service;

import static org.junit.Assert.assertEquals;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.database.operations.model.ColumnDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class DatabaseServiceTest {

    @Autowired
    DatabaseService databaseService;

    @Test
    public void testDatabaseServiceNormal() {
        List<ColumnDetails> actual = databaseService.fetchColumnInfo("TEST1");
        List<ColumnDetails> expected = getDatabaseColumnInfo();
        assertEquals(expected,actual);
    }
    
    @Test
    public void testDatabaseServiceWithUnknownTable() {
        List<ColumnDetails> actual = databaseService.fetchColumnInfo("Unknow_table_name");
        List<ColumnDetails> expected = new ArrayList<>();
        assertEquals(expected,actual);
    }
    
    @Test
    public void testDatabaseServiceWithException() {
    	databaseService.setPassword("wrong password");
    	assertEquals(new ArrayList<>(),databaseService.fetchColumnInfo("TEST"));
    	databaseService.setPassword("");
    }

    private List<ColumnDetails> getDatabaseColumnInfo() {
        List<ColumnDetails> databaseColumnInfo = new ArrayList<>();
        databaseColumnInfo.add(getColumnDetails("ID", 10, null, false, true));
        databaseColumnInfo.add(getColumnDetails("NAME", 100, null, true, false));
        databaseColumnInfo.add(getColumnDetails("EMAIL", 100, null, false, false));
        databaseColumnInfo.add(getColumnDetails("SCORE", 0, null, false, false));
        databaseColumnInfo.add(getColumnDetails("MARKS", 0, null, false, false));
        return databaseColumnInfo;
    }

    private ColumnDetails getColumnDetails(String columnName,int columnSize,String columnValue,
                                           boolean nullable, boolean primaryKey) {
        ColumnDetails data = new ColumnDetails();
        data.setColumnName(columnName);
        data.setColumnSize(columnSize);
        data.setDataType(Types.VARCHAR);
        data.setColumnValue(columnValue);
        data.setNullable(nullable);
        data.setPrimaryKey(primaryKey);
    	data.setPrecision(0);
        if(columnName.equals("SCORE") || columnName.equals("MARKS")) {
        	data.setPrecision(1);
            data.setDataType(Types.DECIMAL);
            data.setColumnSize(3);
        }
        return data;
    }
}