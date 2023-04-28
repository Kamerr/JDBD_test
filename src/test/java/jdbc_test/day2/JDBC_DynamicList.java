package jdbc_test.day2;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC_DynamicList {
    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUserName = "postgres";
    String dbPassWord = "12345";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select firstName,lastName,salary,jobid from employees");
        //get the result set metadata
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        List<Map<String,Object>> queryData=new ArrayList<>();

        int colConut=resultSetMetaData.getColumnCount();

        while (resultSet.next()){
            Map<String,Object> row=new HashMap<>();
            //fill out the map
            //add your map to list
            queryData.add(row);

            for (int i = 1; i <=colConut ; i++) {
                row.put(resultSetMetaData.getColumnName(i),resultSet.getString(i));

            }
            queryData.add(row);
        }
        System.out.println("queryData = " + queryData);


        resultSet.close();
        statement.close();
        connection.close();
    }
}
