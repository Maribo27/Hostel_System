package by.tc.task31.dao.connector;

import by.tc.task31.dao.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ResourceBundle;

import static by.tc.task31.dao.connector.ConnectorConst.*;

public class ConnectorToDB {

    public static Connection getConnection() throws DAOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
        String url = resourceBundle.getString(URL);
        String user = resourceBundle.getString(USER);
        String password = resourceBundle.getString(PASSWORD);

        try {
            Class.forName(resourceBundle.getString(DRIVER));
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            throw new DAOException(JDBC_DRIVER_NOT_FOUND_MESSAGE);
        } catch (SQLException e) {
            throw new DAOException(CONNECTION_FAILED_MESSAGE);
        }
    }

    public static void closeConnection(Connection connection) throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(CONNECTION_CLOSING_FAILED_MESSAGE);
        }
    }
}