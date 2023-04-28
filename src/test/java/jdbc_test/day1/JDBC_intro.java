package jdbc_test.day1;

import java.sql.*;

public class JDBC_intro {
    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:postgresql://localhost:5432/postgres";
        String dbUserName="postgres";
        String dbPassWord="12345";

        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUserName,dbPassWord);
        //create statement
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from employees");

        resultSet.next();
        System.out.println(resultSet.getString("employeeId"));
        System.out.println(resultSet.getString("firstName"));
        resultSet.getString(1);
        resultSet.getString(2);



        resultSet.close();
        statement.close();
        connection.close();


    }
}
