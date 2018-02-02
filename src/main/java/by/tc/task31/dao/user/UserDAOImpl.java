package by.tc.task31.dao.user;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.CurrentEntityExist;
import by.tc.task31.dao.EntityNotFoundException;
import by.tc.task31.dao.connector.ConnectionPool;
import by.tc.task31.entity.User;
import by.tc.task31.service.user.UserExistException;
import by.tc.task31.util.DAOUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static by.tc.task31.dao.SQLQuery.*;

public class UserDAOImpl implements UserDAO {
    private static final String CONVERT_PASSWORD_ERROR_MESSAGE = "Can't convert password";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("query.user");

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_ERROR_WHILE_SEARCHING_USER = "SQL error while searching user";
    private static final String SQL_ERROR_WHILE_UPDATING_USER_DATA = "SQL error while updating user data";
    private static final String USER_NOT_FOUND = "User not found";

    @Override
    public void checkUser(String data, String bundle) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String searchUser = resourceBundle.getString(bundle);
            PreparedStatement statement = connection.prepareStatement(searchUser);
            statement.setString(1, data);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                throw new CurrentEntityExist("Current user exist in database");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL error while searching user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public User getUserInformation(String lang, String username, String password) throws DAOException{
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            password = DAOUtil.createPassword(password);
            String validatePassword = resourceBundle.getString(USER_ACCOUNT_INFORMATION);
            PreparedStatement statement = connection.prepareStatement(validatePassword);
            statement.setString(1, lang);
            statement.setString(2, username);
            statement.setString(3, username);
            statement.setString(4, password);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(USER_NOT_FOUND);
            }

            return DAOUtil.createUserFromDB(resultSet);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_USER);
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addUser(String username, String password, String name, String lastname, String surname, String email) throws DAOException, UserExistException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String searchUser = resourceBundle.getString(USER_SEARCH_INFORMATION);
            PreparedStatement statement = connection.prepareStatement(searchUser);
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                throw new UserExistException("Current user exist in database");
            }
            password = DAOUtil.createPassword(password);
            connection = connectionPool.getConnection();
            addUserToDB(username, password, email, name, surname, lastname, connection);
            createAccount(username);
        } catch (SQLException e) {
            throw new DAOException("SQL error while creating user");
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private void addUserToDB(String username, String password, String email, String name, String surname, String lastname, Connection connection) throws SQLException {
        String changeLogInData = resourceBundle.getString(USER_ADD_ACCOUNT);
        PreparedStatement statement = connection.prepareStatement(changeLogInData);
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.setString(4, name);
        statement.setString(5, surname);
        statement.setString(6, lastname);
        statement.executeUpdate();
    }

    private void createAccount(String username) throws SQLException, NoSuchAlgorithmException, EntityNotFoundException {
        Connection connection = connectionPool.getConnection();
        String searchUser = resourceBundle.getString(USER_SEARCH_ACCOUNT);
        PreparedStatement statement = connection.prepareStatement(searchUser);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        }
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String account = DAOUtil.createPassword(String.valueOf(id));
            String changeLogInData = resourceBundle.getString(USER_CREATE_ACCOUNT_NUMBER);
            statement = connection.prepareStatement(changeLogInData);
            statement.setString(1, account);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<User> getUsers(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(USER_SEARCH_USERS);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException("Users not found");
            }

            return DAOUtil.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DAOException("SQL error while creating users");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Map<Integer, String> getReasons(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(USER_BAN_INFORMATION);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(USER_NOT_FOUND);
            }

            Map<Integer, String> reasons = DAOUtil.translateToMap(resultSet);
            return reasons;
        } catch (SQLException e) {
            throw new DAOException("SQL error while searching ban reasons");
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
            connection = connectionPool.getConnection();

            String userQuery = resourceBundle.getString(USER_CHANGE_STATUS);
            userStat = connection.prepareStatement(userQuery);
            userStat.setString(1, "banned");
            userStat.setInt(2, id);
            userStat.executeUpdate();

            String blockQuery = resourceBundle.getString(USER_BAN);
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, reason);
            blockStat.setInt(2, id);
            blockStat.setDate(3, date);
            blockStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL error while blocking user");
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
            connection = connectionPool.getConnection();
            String userQuery = resourceBundle.getString(USER_CHANGE_STATUS);
            userStat = connection.prepareStatement(userQuery);
            userStat.setString(1,"user");
            userStat.setInt(2, id);
            userStat.executeUpdate();

            String blockQuery = resourceBundle.getString(USER_UNBAN);
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, id);
            blockStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL error while unlocking user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeUserData(int id, List<String> dataToUpdate) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

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
            connection = connectionPool.getConnection();
            password = DAOUtil.createPassword(password);
            String query = resourceBundle.getString(USER_UPDATE_PASSWORD);
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
            connection = connectionPool.getConnection();
            String query = resourceBundle.getString(USER_DELETE);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL error while deleting user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}