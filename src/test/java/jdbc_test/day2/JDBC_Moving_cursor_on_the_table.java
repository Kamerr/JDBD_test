package jdbc_test.day2;

import org.checkerframework.checker.fenum.qual.SwingTextOrientation;
import org.testng.annotations.Test;

import java.sql.*;

public class JDBC_Moving_cursor_on_the_table {
    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    String dbUserName = "postgres";
    String dbPassWord = "12345";

    @Test
    public void test1() throws SQLException{
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from locations");

        //how to find how many rows we have for the query
        resultSet.last();
        int rowCount=resultSet.getRow();
        System.out.println(rowCount);

        //how to go first time
        resultSet.first();
        int rowCountFirst=resultSet.getRow();
        System.out.println(rowCountFirst);

        //how to get "Ahmet" firstname directly
        resultSet.absolute(7);
       // System.out.println("resultSet.getString(\"firstname\") = " + resultSet.getString(7));

        resultSet.previous();


        resultSet.close();
        statement.close();
        connection.close();
    }

}
