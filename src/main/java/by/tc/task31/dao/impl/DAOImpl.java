package by.tc.task31.dao.impl;

import by.tc.task31.dao.DAO;
import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.connector.ConnectorToDB;
import by.tc.task31.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DAOImpl implements DAO {
    private static final String MD_5 = "MD5";
    private static final String USER_STATUS = "user";

    private static final String SQL_EXCEPTION_MESSAGE = "SQL error";
    private static final String CONVERT_PASSWORD_ERROR_MESSAGE = "Can't convert password";

    private Connection connection;
    private PreparedStatement statement;
    private User user;

    public User getUserInformation(String username, String password) throws DAOException{
        connection = null;
        try {
            password = createPassword(password);
            User user = searchUserInDB(username, password);
            return user;
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION_MESSAGE);
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                ConnectorToDB.closeConnection(connection);
            }
        }
    }

    private User searchUserInDB(String username, String password) throws DAOException, SQLException {
        connection = ConnectorToDB.getConnection();

        String validatePassword = "SELECT login_data.username, password, email, name, discount, status FROM login_data " +
                "INNER JOIN users ON login_data.username = users.username " +
                "WHERE (login_data.username = ? OR login_data.email = ?) " +
                "AND login_data.password = ?";
        statement = connection.prepareStatement(validatePassword);
        statement.setString(1, username);
        statement.setString(2, username);
        statement.setString(3, password);

        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            return null;
        }

        User user = createUserFromDB(resultSet);
        return user;
    }

    private ResultSet searchUsername(String username) throws SQLException {
        String searchUser = "SELECT * FROM login_data " +
                "WHERE login_data.username = ? OR login_data.email = ?";
        statement = connection.prepareStatement(searchUser);
        statement.setString(1, username);
        statement.setString(2, username);
        return statement.executeQuery();
    }

    public User addUserInformation(String username, String password, String name, String email) throws DAOException {
        connection = null;

        try {
            password = createPassword(password);

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setDiscount(0);
            user.setStatus(USER_STATUS);
            connection = ConnectorToDB.getConnection();
            addUserToDB(user);
            return user;
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION_MESSAGE);
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                ConnectorToDB.closeConnection(connection);
            }
        }
    }

    private void addUserToDB(User user) throws DAOException, SQLException {
        String changeLogInData = "INSERT INTO login_data VALUES (?,?,?)";
        statement = connection.prepareStatement(changeLogInData);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.executeUpdate();

        String changeUsers = "INSERT INTO users VALUES (?,?,0,'user')";
        statement = connection.prepareStatement(changeUsers);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getName());
        statement.executeUpdate();
    }

    private User createUserFromDB(ResultSet resultSet) throws SQLException {

        user = new User();
        while (resultSet.next()){
            int column = 1;
            user.setUsername(resultSet.getString(column++));
            user.setPassword(resultSet.getString(column++));
            user.setEmail(resultSet.getString(column++));
            user.setName(resultSet.getString(column++));
            user.setDiscount(resultSet.getInt(column++));
            user.setStatus(resultSet.getString(column));
        }
        return user;
    }

    public User getUser() {
        return user;
    }

    public boolean userInDB(String username) throws DAOException{
        connection = null;
        try {
            connection = ConnectorToDB.getConnection();
            ResultSet resultSet = searchUsername(username);
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                ConnectorToDB.closeConnection(connection);
            }
        }
    }

    private static String createPassword(String password) throws NoSuchAlgorithmException {
        StringBuilder code = new StringBuilder();
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance(MD_5);
        byte bytes[] = password.getBytes();
        byte digest[] = messageDigest.digest(bytes);
        for (byte aDigest : digest) {
            code.append(Integer.toHexString(0x0100 + (aDigest & 0x00FF)).substring(1));
        }

        password = code.toString();
        return password;
    }
}