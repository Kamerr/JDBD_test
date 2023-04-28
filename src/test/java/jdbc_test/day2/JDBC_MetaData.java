package jdbc_test.day2;

import org.testng.annotations.Test;

import java.sql.*;

public class JDBC_MetaData {
    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUserName = "postgres";
    String dbPassWord = "12345";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");


        //get the database relates data inside the dbMetaData object
        DatabaseMetaData databaseMetaData= connection.getMetaData();
        System.out.println(databaseMetaData.getUserName());
        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());
        System.out.println(databaseMetaData.getDriverName());
        System.out.println(databaseMetaData.getDriverVersion());

        //get the result set object metadata
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        //how many columns we have
       int columncount=resultSetMetaData.getColumnCount();
        System.out.println("columncount = " + columncount);

        System.out.println("resultSetMetaData.getColumnName() = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName() = " + resultSetMetaData.getColumnName(2));

        //print all the column  dinamıcally
        for (int i = 1; i < columncount; i++) {
            System.out.println(resultSetMetaData.getColumnName(i));

        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
