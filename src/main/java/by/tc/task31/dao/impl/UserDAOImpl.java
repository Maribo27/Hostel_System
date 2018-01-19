package by.tc.task31.dao.impl;

import by.tc.task31.dao.DAO;
import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.connector.ConnectorToDB;
import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements DAO {
    private static final String MD_5 = "MD5";
    private static final String USER_STATUS = "user";

    private static final String SQL_EXCEPTION_MESSAGE = "SQL error";
    private static final String CONVERT_PASSWORD_ERROR_MESSAGE = "Can't convert password";

    private Connection connection;
    private PreparedStatement statement;
    private User user;

    public User getUserInformation(String lang, String username, String password) throws DAOException{
        connection = null;
        try {
            password = createPassword(password);
            return searchUserInDB(username, password, lang);
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

    private User searchUserInDB(String username, String password, String lang) throws DAOException, SQLException {
        connection = ConnectorToDB.getConnection();

        String validatePassword = "SELECT u.id_user, u.username, u.email, u.password, u.surname, u.name, " +
                "u.lastname, u.discount, u.balance, u.status, start_of_ban, end_of_ban, reason_type " +
                "FROM user AS u " +
                "LEFT JOIN (" +
                "SELECT b.start_of_ban, b.end_of_ban, br.reason_type, b.user_id FROM banned_user AS b " +
                "INNER JOIN ban_reason AS br ON (b.id_reason = br.banned_reason_id)" +
                "WHERE br.lang_name = ?) AS a " +
                "ON u.id_user = a.user_id " +
                "WHERE (username = ? OR email = ?) AND password = ?";
        statement = connection.prepareStatement(validatePassword);
        statement.setString(1, lang);
        statement.setString(2, username);
        statement.setString(3, username);
        statement.setString(4, password);

        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            return null;
        }

        return createUserFromDB(resultSet);
    }

    public User addUser(String username, String password, String name, String lastname, String surname, String email) throws DAOException {
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
            user.setBalance(0);
            user.setSurname(surname);
            user.setLastname(lastname);
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

    private void addUserToDB(User user) throws SQLException {
        String changeLogInData = "INSERT INTO user (username, email, password, name, " +
                "surname, lastname, discount, status, balance) VALUES (?,?,?,?,?,?,0,'user',0)";
        statement = connection.prepareStatement(changeLogInData);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getName());
        statement.setString(5, user.getSurname());
        statement.setString(6, user.getLastname());
        statement.executeUpdate();
    }

    private User createUserFromDB(ResultSet resultSet) throws SQLException {
        user = new User();
        while (resultSet.next()){
            int column = 1;
            user.setId(resultSet.getInt(column++));
            user.setUsername(resultSet.getString(column++));
            user.setEmail(resultSet.getString(column++));
            user.setPassword(resultSet.getString(column++));
            user.setSurname(resultSet.getString(column++));
            user.setName(resultSet.getString(column++));
            user.setLastname(resultSet.getString(column++));
            user.setDiscount(resultSet.getInt(column++));
            user.setBalance(resultSet.getInt(column++));
            user.setStatus(resultSet.getString(column++));
            user.setBlockDate(resultSet.getTimestamp(column++));
            user.setUnlockDate(resultSet.getTimestamp(column++));
            user.setBlockReason(resultSet.getString(column));
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
            String searchUser = "SELECT * FROM user WHERE user.username = ? OR user.email = ?";
            statement = connection.prepareStatement(searchUser);
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
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

    @Override
    public List<Request> getRequests(String lang) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();

            String query = "SELECT r.id_request, r.user_id, r.hostel_id, CONCAT ('\"', a.hostel_name,'\", ', a.country, ', ', a.city, ', ', a.address) AS hostel_info, " +
                    "r.type, r.room_number, r.days_number, r.cost, r.status, r.reservation_date " +
                    "FROM request AS r LEFT JOIN (" +
                    "SELECT hl.hostel_name, c.country, c.city, hl.address, h.id_hostel FROM hostel AS h " +
                    "INNER JOIN hostel_local AS hl ON (h.id_hostel = hl.hostel_id) " +
                    "INNER JOIN city AS c ON (h.hostel_city = c.id_city) " +
                    "WHERE c.lang_name = ? AND hl.lang_name = ?) AS a " +
                    "ON r.hostel_id = a.id_hostel";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            return createRequests(resultSet);
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

    @Override
    public List<Request> getRequests(String lang, int id) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();

            String query = "SELECT r.id_request, r.user_id, r.hostel_id, CONCAT ('\"', a.hostel_name,'\", ', a.country, ', ', a.city, ', ', a.address) AS hostel_info, " +
                    "r.type, r.room_number, r.days_number, r.cost, r.status, r.reservation_date " +
                    "FROM request AS r LEFT JOIN (" +
                    "SELECT hl.hostel_name, c.country, c.city, hl.address, h.id_hostel FROM hostel AS h " +
                    "INNER JOIN hostel_local AS hl ON (h.id_hostel = hl.hostel_id) " +
                    "INNER JOIN city AS c ON (h.hostel_city = c.id_city) " +
                    "WHERE c.lang_name = ? AND hl.lang_name = ?) AS a " +
                    "ON r.hostel_id = a.id_hostel " +
                    "WHERE r.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            statement.setInt(3, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            return createRequests(resultSet);
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

    @Override
    public List<User> getUsers(String lang) throws DAOException {
        try {

            connection = ConnectorToDB.getConnection();

            String query = "SELECT u.id_user, u.username, u.email, u.surname, u.name, " +
                    "u.lastname, u.discount, u.balance, u.status, start_of_ban, end_of_ban, reason_type " +
                    "FROM user AS u " +
                    "LEFT JOIN (" +
                    "SELECT b.start_of_ban, b.end_of_ban, br.reason_type, b.user_id FROM banned_user AS b " +
                    "INNER JOIN ban_reason AS br ON (b.id_reason = br.banned_reason_id)" +
                    "WHERE br.lang_name = ?) AS a " +
                    "ON u.id_user = a.user_id ";
            statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            return createUsers(resultSet);
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

    @Override
    public List<Hostel> getHostels(String lang) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();

            String query =
                    "SELECT h.id_hostel, hl.hostel_name, c.country, c.city, hl.address, " +
                    "h.free_room_number, h.booking_possibility, h.room_cost, h.hostel_email " +
                    "FROM hostel AS h " +
                    "INNER JOIN hostel_local AS hl ON (h.id_hostel = hl.hostel_id) " +
                    "INNER JOIN city AS c ON (h.hostel_city = c.id_city) " +
                    "WHERE hl.lang_name = ? AND c.lang_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            return createHostels(resultSet);
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

    @Override
    public List<Hostel> getHostels(String lang, int city, int room) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();

            String query =
                    "SELECT h.id_hostel, hl.hostel_name, c.country, c.city, hl.address, " +
                            "h.free_room_number, h.booking_possibility, h.room_cost, h.hostel_email " +
                            "FROM hostel AS h " +
                            "INNER JOIN hostel_local AS hl ON (h.id_hostel = hl.hostel_id) " +
                            "INNER JOIN city AS c ON (h.hostel_city = c.id_city) " +
                            "WHERE hl.lang_name = ? AND c.lang_name = ? AND h.free_room_number >= ? AND h.hostel_city = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            statement.setInt(3, room);
            statement.setInt(4, city);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            return createHostels(resultSet);
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

    @Override
    public Map<Integer, String> getReasons(String lang) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();

            String query = "SELECT b.banned_reason_id, b.reason_type " +
                    "FROM ban_reason AS b WHERE b.lang_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            Map<Integer, String> reasons = new HashMap<>();
            createMapFromDB(resultSet, reasons);
            return reasons;
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

    @Override
    public Map<Integer, String> getCities(String lang) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();

            String query = "SELECT c.id_city, c.city " +
                    "FROM city AS c WHERE c.lang_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            Map<Integer, String> cities = new HashMap<>();
            createMapFromDB(resultSet, cities);
            return cities;
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

    private void createMapFromDB(ResultSet resultSet, Map<Integer, String> cities) throws SQLException {
        while (resultSet.next()){
            int column = 1;
            Integer number = resultSet.getInt(column++);
            String reason = resultSet.getString(column);
            cities.put(number, reason);
        }
    }

    @Override
    public void addRequest(Request request) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();

            String query = "INSERT INTO request (user_id, hostel_id, type, room_number, " +
                    "days_number, cost, status, reservation_date) VALUES (?,?,?,?,?,?,'in_process',?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, request.getUserId());
            statement.setInt(2, request.getHostelId());
            statement.setString(3, request.getType());
            statement.setInt(4, request.getRoom());
            statement.setInt(5, request.getDays());
            statement.setInt(6, request.getCost());
            statement.setDate(7, request.getDate());
            statement.executeUpdate();

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

    @Override
    public void changeRequestStatus(int id, String status) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();
            String query = "UPDATE request SET status=? WHERE id_request=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
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

    @Override
    public void blockUser(int id, Timestamp date, int reason) throws DAOException {
        PreparedStatement userStat;
        PreparedStatement blockStat;
        try {
            connection = ConnectorToDB.getConnection();

            String userQuery = "UPDATE user SET status='banned' WHERE id_user=?";
            userStat = connection.prepareStatement(userQuery);
            userStat.setInt(1, id);
            userStat.executeUpdate();

            String blockQuery = "INSERT INTO banned_user (id_reason, user_id, start_of_ban, end_of_ban) " +
                    "VALUES (?,?,CURRENT_TIMESTAMP(),?)";
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, reason);
            blockStat.setInt(2, id);
            blockStat.setTimestamp(3, date);
            blockStat.executeUpdate();
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

    @Override
    public void unlockUser(int id) throws DAOException {
        PreparedStatement userStat;
        PreparedStatement blockStat;
        try {
            connection = ConnectorToDB.getConnection();
            String userQuery = "UPDATE user SET status='user' WHERE id_user=?";
            userStat = connection.prepareStatement(userQuery);
            userStat.setInt(1, id);
            userStat.executeUpdate();

            String blockQuery = "DELETE FROM banned_user WHERE user_id=?";
            blockStat = connection.prepareStatement(blockQuery);
            blockStat.setInt(1, id);
            blockStat.executeUpdate();
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

    @Override
    public void changeUserData(int id, String username, String password, String name, String lastname, String surname, String email) throws DAOException {
        try {
            password = createPassword(password);
            connection = ConnectorToDB.getConnection();
            String query = "UPDATE user SET username=?, email=?, password=?, name=?, surname=?, lastname=? WHERE id_user=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, name);
            statement.setString(5, surname);
            statement.setString(6, lastname);
            statement.setInt(7, id);
            statement.executeUpdate();
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

    @Override
    public void deleteUser(int id) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();
            String query = "DELETE FROM user WHERE id_user=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
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

    @Override
    public void deleteHostel(int id) throws DAOException {
        try {
            connection = ConnectorToDB.getConnection();
            String query = "DELETE FROM hostel WHERE id_hostel=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
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

    private List<Hostel> createHostels(ResultSet resultSet) throws SQLException {
        List<Hostel> hostels = new ArrayList<>();
        while (resultSet.next()){
            int column = 1;
            Hostel hostel = new Hostel();
            hostel.setId(resultSet.getInt(column++));
            hostel.setName(resultSet.getString(column++));
            hostel.setCountry(resultSet.getString(column++));
            hostel.setCity(resultSet.getString(column++));
            hostel.setAddress(resultSet.getString(column++));
            hostel.setRoom(resultSet.getInt(column++));
            hostel.setBooking(resultSet.getString(column++));
            hostel.setCost(resultSet.getInt(column++));
            hostel.setEmail(resultSet.getString(column));
            hostels.add(hostel);
        }
        return hostels;
    }

    private List<Request> createRequests(ResultSet resultSet) throws SQLException {
        List<Request> requests = new ArrayList<>();
        while (resultSet.next()){
            int column = 1;
            Request req = new Request();
            req.setId(resultSet.getInt(column++));
            req.setUserId(resultSet.getInt(column++));
            req.setHostelId(resultSet.getInt(column++));
            req.setHostelInfo(resultSet.getString(column++));
            req.setType(resultSet.getString(column++));
            req.setRoom(resultSet.getInt(column++));
            req.setDays(resultSet.getInt(column++));
            req.setCost(resultSet.getInt(column++));
            req.setStatus(resultSet.getString(column++));
            req.setDate(resultSet.getDate(column));
            requests.add(req);
        }
        return requests;
    }

    private List<User> createUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            int column = 1;
            user.setId(resultSet.getInt(column++));
            user.setUsername(resultSet.getString(column++));
            user.setEmail(resultSet.getString(column++));
            user.setSurname(resultSet.getString(column++));
            user.setName(resultSet.getString(column++));
            user.setLastname(resultSet.getString(column++));
            user.setDiscount(resultSet.getInt(column++));
            user.setBalance(resultSet.getInt(column++));
            user.setStatus(resultSet.getString(column++));
            user.setBlockDate(resultSet.getTimestamp(column++));
            user.setUnlockDate(resultSet.getTimestamp(column++));
            user.setBlockReason(resultSet.getString(column));
            users.add(user);
        }
        return users;
    }
}