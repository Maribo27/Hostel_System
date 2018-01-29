package by.tc.task31.dao.impl;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.RequestDAO;
import by.tc.task31.dao.connector.ConnectionPool;
import by.tc.task31.entity.Request;
import by.tc.task31.util.DAOUtil;

import java.sql.*;
import java.util.List;

public class RequestDAOImpl implements RequestDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_ERROR_WHILE_SEARCHING_REQUESTS = "SQL error while searching requests";
    private static final String SQL_ERROR_WHILE_CREATING_REQUEST = "SQL error while creating request";
    private static final String SQL_ERROR_WHILE_DELETING_REQUESTS = "SQL error while deleting requests";
    private static final String SQL_ERROR_WHILE_CHANGING_REQUEST_STATUS = "SQL error while changing request status";

    @Override
    public List<Request> getRequests(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

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

            return DAOUtil.createRequests(resultSet);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_REQUESTS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Request> getRequests(String lang, int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

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

            return DAOUtil.createRequests(resultSet);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_REQUESTS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int addRequest(Request request, int balance) throws DAOException {
        Connection connection = null;
        int userId = request.getUserId();
        int hostelId = request.getHostelId();
        String type = request.getType();
        int numberOfRooms = request.getRoom();
        int days = request.getDays();
        int cost = request.getCost();
        Date requestDate = request.getDate();
        Date endDate = request.getEndDate();
        try {
            connection = connectionPool.takeConnection();

            String query = "INSERT INTO request (user_id, hostel_id, type, room_number, " +
                    "days_number, cost, status, reservation_date) VALUES (?,?,?,?,?,?,'processing',?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, hostelId);
            statement.setString(3, type);
            statement.setInt(4, numberOfRooms);
            statement.setInt(5, days);
            statement.setInt(6, cost);
            statement.setDate(7, requestDate);
            statement.executeUpdate();

            query = "SELECT DISTINCT r.room FROM hostel_room AS r" +
                    " LEFT JOIN request_room r3 ON r.hostel_id = r3.hostel AND r.room = r3.room" +
                    " WHERE r3.hostel = ? AND (r3.begin_date IS NULL AND r3.end_date IS NULL) OR" +
                    "   (r3.begin_date < ? AND r3.end_date < ? AND r3.end_date < ?) OR" +
                    "   (r3.begin_date > ? AND r3.end_date > ? AND r3.begin_date > ?)" +
                    "ORDER BY RAND() LIMIT ?";

            statement = connection.prepareStatement(query);
            statement.setInt(1, hostelId);
            statement.setDate(2, requestDate);
            statement.setDate(3, endDate);
            statement.setDate(4, requestDate);
            statement.setDate(5, requestDate);
            statement.setDate(6, endDate);
            statement.setDate(7, endDate);
            statement.setInt(8, numberOfRooms);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int room = resultSet.getInt(1);
                query = "INSERT INTO request_room (hostel, room, begin_date, end_date) " +
                        "VALUES (?,?,?,?)";

                statement = connection.prepareStatement(query);
                statement.setInt(1, hostelId);
                statement.setInt(2, room);
                statement.setDate(3, requestDate);
                statement.setDate(4, endDate);
                statement.executeUpdate();
            }

            query = "UPDATE user SET balance=? WHERE id_user=?";
            statement = connection.prepareStatement(query);
            balance -= cost;
            statement.setInt(1, balance);
            statement.setInt(2, userId);
            statement.executeUpdate();
            return balance;
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_CREATING_REQUEST);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int cancelRequest(int requestId, int userId, String status) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            String query = "UPDATE request SET status=? WHERE id_request=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, requestId);
            statement.executeUpdate();

            query = "SELECT DISTINCT r.cost, u.balance " +
                    "FROM request AS r, user AS u " +
                    "WHERE u.id_user = ? AND r.id_request = ?";

            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, requestId);
            ResultSet resultSet = statement.executeQuery();
            int balance = 0;
            while (resultSet.next()){
                int payment = resultSet.getInt(1);
                balance = resultSet.getInt(2);

                query = "UPDATE user SET balance=? WHERE id_user=?";
                statement = connection.prepareStatement(query);
                balance += payment;
                statement.setInt(1, balance);
                statement.setInt(2, userId);
                statement.executeUpdate();
            }
            return balance;
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_DELETING_REQUESTS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void approveRequest(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            String query = "UPDATE request SET status='approved' WHERE id_request=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_CHANGING_REQUEST_STATUS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}