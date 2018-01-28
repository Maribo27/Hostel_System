package by.tc.task31.dao.impl;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.UserDAO;
import by.tc.task31.dao.connector.ConnectionPool;
import by.tc.task31.entity.User;
import by.tc.task31.util.DAOUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    private static final String CONVERT_PASSWORD_ERROR_MESSAGE = "Can't convert password";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_ERROR_WHILE_SEARCHING_USER = "SQL error while searching user";
    private static final String SQL_ERROR_WHILE_CREATING_USER = "SQL error while creating user";
    private static final String SQL_ERROR_WHILE_CREATING_USERS = "SQL error while creating users";
    private static final String SQL_ERROR_WHILE_SEARCHING_BAN_REASONS = "SQL error while searching ban reasons";
    private static final String SQL_ERROR_WHILE_BLOCKING_USER = "SQL error while blocking user";
    private static final String SQL_ERROR_WHILE_UNLOCKING_USER = "SQL error while unlocking user";
    private static final String SQL_ERROR_WHILE_UPDATING_USER_DATA = "SQL error while updating user data";
    private static final String SQL_ERROR_WHILE_DELETING_USER = "SQL error while deleting user";

    @Override
    public User getUserInformation(String lang, String username, String password) throws DAOException{
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            password = DAOUtil.createPassword(password);
            return searchUserInDB(connection, username, password, lang);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_USER);
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private User searchUserInDB(Connection connection, String username, String password, String lang) throws SQLException {
        String validatePassword = "SELECT u.id_user, u.username, u.email, u.password, u.surname, u.name, " +
                "u.lastname, u.discount, u.balance, u.status, start_of_ban, end_of_ban, reason_type " +
                "FROM user AS u " +
                "LEFT JOIN (" +
                "SELECT b.start_of_ban, b.end_of_ban, br.reason_type, b.user_id FROM banned_user AS b " +
                "INNER JOIN ban_reason AS br ON (b.id_reason = br.banned_reason_id)" +
                "WHERE br.lang_name = ?) AS a " +
                "ON u.id_user = a.user_id " +
                "WHERE (username = ? OR email = ?) AND password = ?";
        PreparedStatement statement = connection.prepareStatement(validatePassword);
        statement.setString(1, lang);
        statement.setString(2, username);
        statement.setString(3, username);
        statement.setString(4, password);

        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            return null;
        }

        return DAOUtil.createUserFromDB(resultSet);
    }

    @Override
    public User addUser(String username, String password, String name, String lastname, String surname, String email) throws DAOException {
        Connection connection = null;
        try {
            password = DAOUtil.createPassword(password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setLastname(lastname);
            connection = connectionPool.takeConnection();
            addUserToDB(user);
            return user;
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_CREATING_USER);
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private void addUserToDB(User user) throws SQLException {
        Connection connection = connectionPool.takeConnection();
        String changeLogInData = "INSERT INTO user (username, email, password, name, " +
                "surname, lastname, discount, status, balance) VALUES (?,?,?,?,?,?,0,'user',0)";
        PreparedStatement statement = connection.prepareStatement(changeLogInData);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getName());
        statement.setString(5, user.getSurname());
        statement.setString(6, user.getLastname());
        statement.executeUpdate();
    }

    @Override
    public boolean userInDB(String username) throws DAOException{
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            String searchUser = "SELECT * FROM user WHERE user.username = ? OR user.email = ?";
            PreparedStatement statement = connection.prepareStatement(searchUser);
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_USER);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<User> getUsers(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            String query = "SELECT u.id_user, u.username, u.email, u.surname, u.name, " +
                    "u.lastname, u.discount, u.balance, u.status, start_of_ban, end_of_ban, reason_type " +
                    "FROM user AS u " +
                    "LEFT JOIN (" +
                    "SELECT b.start_of_ban, b.end_of_ban, br.reason_type, b.user_id FROM banned_user AS b " +
                    "INNER JOIN ban_reason AS br ON (b.id_reason = br.banned_reason_id)" +
                    "WHERE br.lang_name = ?) AS a " +
                    "ON u.id_user = a.user_id ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            return DAOUtil.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_CREATING_USERS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Map<Integer, String> getReasons(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            String query = "SELECT b.banned_reason_id, b.reason_type " +
                    "FROM ban_reason AS b WHERE b.lang_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            Map<Integer, String> reasons = new HashMap<>();
            DAOUtil.translateToMap(resultSet, reasons);
            return reasons;
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_BAN_REASONS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void blockUser(int id, Date date, int reason) throws DAOException {
        PreparedStatement userStat;
        PreparedStatement blockStat;
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            String userQuery = "UPDATE user SET status='banned' WHERE id_user=?";
            userStat = connection.prepareStatement(userQuery);
            userStat.setInt(1, id);
            userStat.executeUpdate();

            String blockQuery = "INSERT INTO banned_user (id_reason, user_id, start_of_ban, end_of_ban) " +
                    "VALUES (?,?,CURRENT_TIMESTAMP(),?)";
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, reason);
            blockStat.setInt(2, id);
            blockStat.setDate(3, date);
            blockStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_BLOCKING_USER);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void unlockUser(int id) throws DAOException {
        PreparedStatement userStat;
        PreparedStatement blockStat;
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            String userQuery = "UPDATE user SET status='user' WHERE id_user=?";
            userStat = connection.prepareStatement(userQuery);
            userStat.setInt(1, id);
            userStat.executeUpdate();

            String blockQuery = "DELETE FROM banned_user WHERE user_id=?";
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, id);
            blockStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_UNLOCKING_USER);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeUserData(int id, List<String> dataToUpdate) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            StringBuilder query = new StringBuilder("UPDATE user SET " + dataToUpdate.get(0));
            for (int index = 1; index < dataToUpdate.size(); index++){
                String parameter = ", " + dataToUpdate.get(index);
                query.append(parameter);
            }
            String endQuery = " WHERE id_user=" + id;
            query.append(endQuery);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_UPDATING_USER_DATA);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changePassword(int id, String password) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            password = DAOUtil.createPassword(password);
            String query = "UPDATE user SET password=? WHERE id_user=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, password);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_UPDATING_USER_DATA);
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            String query = "DELETE FROM user WHERE id_user=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_DELETING_USER);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}