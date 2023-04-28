package jdbc_test.day1;

import java.sql.*;

public class DBBC_Practise {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String dbUserName = "postgres";
        String dbPassWord = "12345";

        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from locations");

        //get the locationsId, streetAddress,postcode of first row
        resultSet.next();
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));
        System.out.println(resultSet.getString(3));

        resultSet.next();
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString("streetAddress"));
        System.out.println(resultSet.getString("city"));
        System.out.println(resultSet.getString("region"));

        resultSet.next();
        resultSet.next();
        resultSet.next();
        for (int i = 1; i <= 6; i++) {
            System.out.print(resultSet.getString(i) + " ");

        }
//get the city ,region
        resultSet.next();
        System.out.println(resultSet.getString("city"));
        System.out.println(resultSet.getString("region"));
        System.out.println(resultSet.getString("country"));

        resultSet.next();
        resultSet.next();
        for (int i = 1; i <= 6; i++) {
            System.out.print(resultSet.getString(i) + " ");

        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
