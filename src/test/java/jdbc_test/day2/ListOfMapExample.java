package jdbc_test.day2;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {
    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUserName = "postgres";
    String dbPassWord = "12345";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select firstName,lastName,salary,jobid from employees");

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        List<Map<String,Object>> queryData=new ArrayList<>();

        Map<String,Object> row1=new HashMap<>();
        row1.put("firstName","Eren");
        row1.put("lastName","Cengel");
        row1.put("salary","10000");
        row1.put("jobID","QA");
        System.out.println("row1 = " + row1);

        Map<String,Object> row2=new HashMap<>();
        row2.put("firstName","Kamer");
        row2.put("lastName","Aypay");
        row2.put("salary","10000");
        row2.put("jobID","QA");

        System.out.println("row1 = " + row2);
        queryData.add(row1);
        queryData.add(row2);

        //get the Eren's lastname directly from the list
        System.out.println("queryData.get(0).get(\"lastname\") = " + queryData.get(0).get("lastName"));
        System.out.println("queryData.get(0).get(\"salary\") = " + queryData.get(0).get("salary"));

        //how fill out a list of maps with
        List<Map<String,Object>> queryData2=new ArrayList<>();
        resultSet.next();
        Map<String,Object> newrow1=new HashMap<>();
        newrow1.put(resultSetMetaData.getColumnName(1),resultSet.getString("firstName"));
        newrow1.put(resultSetMetaData.getColumnName(2),resultSet.getString("lastName"));
        newrow1.put(resultSetMetaData.getColumnName(3),resultSet.getString("salary"));
        newrow1.put(resultSetMetaData.getColumnName(4),resultSet.getString("jobId"));

        resultSet.next();
        Map<String,Object> newrow2=new HashMap<>();
        newrow2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        newrow2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        newrow2.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        newrow2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));

        System.out.println("newrow1 = " + newrow1);
        System.out.println("newrow2 = " + newrow2);


        resultSet.close();
        statement.close();
        connection.close();
    }
}