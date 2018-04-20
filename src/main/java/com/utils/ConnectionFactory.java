package com.utils;


import com.exception.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String connectionString = "jdbc:mysql://127.0.0.1:3306/aimprosoft";
    private static final String login = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionString, login, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException(" Connection problems",e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection == null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(" Connection problems",e);
            }
        }
    }
}





