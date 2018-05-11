package com.utils;


import com.exception.DAOException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionFactory {
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String connectionString = "jdbc:mysql://127.0.0.1:3306/aimprosoft";
    private static final String login = "root";
    private static final String password = "root";

    public static Connection getConnection() throws DAOException{
        Connection connection;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionString, login, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Fail to get connection",e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) throws DAOException{
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close connection",e);
            }
        }
    }
}





