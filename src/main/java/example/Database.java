package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
    String username = "root";
    String password = "1234";

    public Connection getConnected() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}