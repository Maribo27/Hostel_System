package by.tc.hostel_system.dao.user;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.EntityExistException;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.user.UserExistException;
import by.tc.hostel_system.util.DAOUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static by.tc.hostel_system.dao.EntityMessageLocale.USERS;
import static by.tc.hostel_system.dao.EntityMessageLocale.USER_EXIST;
import static by.tc.hostel_system.dao.SQLQuery.*;

public class UserDAOImpl implements UserDAO {
    private static final String CONVERT_PASSWORD_ERROR_MESSAGE = "Can't convert password";
    private static final String SQL_ERROR_WHILE_UPDATING_USER_DATA = "SQL error while updating user data";

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("query.user");
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * @see UserDAO#getUserInformation(String, String, String)
     * @throws DAOException if nothing found or catch {@link SQLException}
     */
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
                throw new EntityNotFoundException(USERS.getMessage(lang));
            }

            return DAOUtil.createUserFromDB(resultSet);
        } catch (SQLException e) {
            throw new DAOException("SQL error while searching user");
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#getUsers(String)
     * @throws DAOException if nothing found or catch {@link SQLException}
     */
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

    /**
     * @see UserDAO#getReasons(String)
     * @throws DAOException if nothing found or catch {@link SQLException}
     */
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
                throw new EntityNotFoundException(USERS.getMessage(lang));
            }

            Map<Integer, String> reasons = DAOUtil.translateToMap(resultSet);
            return reasons;
        } catch (SQLException e) {
            throw new DAOException("SQL error while searching ban reasons");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#getUserDiscount(String, int)
     * @throws DAOException if nothing found or catch {@link SQLException}
     */
    @Override
    public int getUserDiscount(String lang, int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String query = resourceBundle.getString(USER_SEARCH_DISCOUNT);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(USERS.getMessage(lang));
            }
            int discount = 0;
            while (resultSet.next()) {
                discount = resultSet.getInt(1);
            }
            return discount;
        } catch (SQLException e) {
            throw new DAOException("Error while updating user discount");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#checkUser(String, String, String)
     * @throws DAOException if user found or catch {@link SQLException}
     */
    @Override
    public void checkUser(String lang, String data, String bundle) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String searchUser = resourceBundle.getString(bundle);
            PreparedStatement statement = connection.prepareStatement(searchUser);
            statement.setString(1, data);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                throw new EntityExistException(USER_EXIST.getMessage(lang));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL error while searching user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#addUser(String, String, String, String, String, String, String)
     * @throws DAOException if user found or catch {@link SQLException}
     */
    @Override
    public void addUser(String lang, String username, String password, String name, String lastName, String surname, String email) throws DAOException, UserExistException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String searchUser = resourceBundle.getString(USER_SEARCH_INFORMATION);
            PreparedStatement statement = connection.prepareStatement(searchUser);
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                throw new UserExistException(USER_EXIST.getMessage(lang));
            }
            password = DAOUtil.createPassword(password);
            connection.setAutoCommit(false);
            addUserToDB(username, password, email, name, surname, lastName, connection);
            createAccount(lang, username, connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL rollback error while creating user");
            }
            throw new DAOException("SQL error while creating user");
        } catch (NoSuchAlgorithmException e) {
            throw new DAOException(CONVERT_PASSWORD_ERROR_MESSAGE);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#blockUser(int, int)
     * @throws DAOException if catch {@link SQLException}
     */
    @Override
    public void blockUser(int id, int reason) throws DAOException {
        PreparedStatement userStat;
        PreparedStatement blockStat;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            String userQuery = resourceBundle.getString(USER_CHANGE_STATUS);
            userStat = connection.prepareStatement(userQuery);
            userStat.setString(1, "banned");
            userStat.setInt(2, id);
            userStat.executeUpdate();

            String blockQuery = resourceBundle.getString(USER_BAN);
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, reason);
            blockStat.setInt(2, id);
            blockStat.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL rollback error while blocking user");
            }
            throw new DAOException("SQL error while blocking user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#unlockUser(int)
     * @throws DAOException if catch {@link SQLException}
     */
    @Override
    public void unlockUser(int id) throws DAOException {
        PreparedStatement userStat;
        PreparedStatement blockStat;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            String userQuery = resourceBundle.getString(USER_CHANGE_STATUS);
            userStat = connection.prepareStatement(userQuery);
            userStat.setString(1,"user");
            userStat.setInt(2, id);
            userStat.executeUpdate();

            String blockQuery = resourceBundle.getString(USER_UNBAN);
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, id);
            blockStat.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL rollback error while unlocking user");
            }
            throw new DAOException("SQL error while unlocking user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#changeUserDiscount(int, int)
     * @throws DAOException if catch {@link SQLException}
     */
    @Override
    public void changeUserDiscount(int userId, int userDiscount) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String query = resourceBundle.getString(USER_UPDATE_DISCOUNT);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userDiscount);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while updating user discount");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see UserDAO#changeUserData(int, List)
     * @throws DAOException if catch {@link SQLException}
     */
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

    /**
     * @see UserDAO#changePassword(int, String)
     * @throws DAOException if catch {@link SQLException}
     */
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

    /**
     * @see UserDAO#deleteUser(int)
     * @throws DAOException if catch {@link SQLException}
     */
    @Override
    public void deleteUser(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            String query = resourceBundle.getString(USER_DELETE);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

            query = resourceBundle.getString(USER_AFTER_DELETING);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL rollback error while deleting user");
            }
            throw new DAOException("SQL error while deleting user");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * Adds {@link User} to database.
     *
     * @param username user username
     * @param password user password
     * @param email user email
     * @param name user name
     * @param surname user surname
     * @param lastName user last name
     * @param connection current connection
     *
     * @throws SQLException if error id database occurred
     */
    private void addUserToDB(String username, String password, String email, String name, String surname, String lastName, Connection connection) throws SQLException {
        String changeLogInData = resourceBundle.getString(USER_ADD_ACCOUNT);
        PreparedStatement statement = connection.prepareStatement(changeLogInData);
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.setString(4, name);
        statement.setString(5, surname);
        statement.setString(6, lastName);
        statement.executeUpdate();
    }

    /**
     * Creates {@link User#account} number for current user.
     *
     * @param lang current language
     * @param username user username
     * @param connection current connection
     *
     * @throws SQLException if error in database occurred
     * @throws NoSuchAlgorithmException if cannot find md5 algorithm
     * @throws EntityNotFoundException if user not found
     */
    private void createAccount(String lang, String username, Connection connection) throws SQLException, NoSuchAlgorithmException, EntityNotFoundException {
        String searchUser = resourceBundle.getString(USER_SEARCH_ACCOUNT);
        PreparedStatement statement = connection.prepareStatement(searchUser);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            throw new EntityNotFoundException(USERS.getMessage(lang));
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
}