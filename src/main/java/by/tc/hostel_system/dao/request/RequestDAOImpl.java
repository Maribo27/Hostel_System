package by.tc.hostel_system.dao.request;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.util.DAOUtil;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import static by.tc.hostel_system.dao.SQLQuery.*;

public class RequestDAOImpl implements RequestDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("query.request");
    private static final String SQL_ERROR_WHILE_SEARCHING_REQUESTS = "SQL error while searching requests";
    private static final String REQUESTS_NOT_FOUND = "Requests not found";

    @Override
    public List<Request> getRequests(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(REQUEST_SEARCH_ALL_REQUESTS);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(REQUESTS_NOT_FOUND);
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
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(REQUEST_SEARCH_USER_REQUESTS);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            statement.setInt(3, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(REQUESTS_NOT_FOUND);
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
        int numberOfRooms = request.getRoom();
        int days = request.getDays();
        int cost = request.getCost();
        String type = request.getType();
        Date requestDate = request.getDate();
        Date endDate = request.getEndDate();
        try {
            connection = connectionPool.getConnection();
            updateRequests(connection, userId, hostelId, numberOfRooms, days, cost, type, requestDate);
            searchFreeRoom(connection, hostelId, numberOfRooms, requestDate, endDate);
            balance = updateBalance(balance, connection, userId, cost);
            return balance;
        } catch (SQLException e) {
            throw new DAOException("SQL error while creating request");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int cancelRequest(int requestId, int userId, String status, int balance) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            updateRequestStatus(requestId, status, connection);
            balance = getNewBalance(requestId, userId, balance, connection);
            return balance;
        } catch (SQLException e) {
            throw new DAOException("SQL error while deleting requests");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void approveRequest(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            updateRequestStatus(id, "approved", connection);
        } catch (SQLException e) {
            throw new DAOException("SQL error while changing request status");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private int updateBalance(int balance, Connection connection, int userId, int cost) throws SQLException {
        String query = resourceBundle.getString(REQUEST_UPDATE_BALANCE);
        PreparedStatement statement = connection.prepareStatement(query);
        balance -= cost;
        statement.setInt(1, balance);
        statement.setInt(2, userId);
        statement.executeUpdate();
        return balance;
    }

    private void searchFreeRoom(Connection connection, int hostelId, int numberOfRooms, Date requestDate, Date endDate) throws SQLException, EntityNotFoundException {
        String query = resourceBundle.getString(REQUEST_SEARCH_ROOMS);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, hostelId);
        statement.setDate(2, requestDate);
        statement.setDate(3, endDate);
        statement.setDate(4, requestDate);
        statement.setDate(5, requestDate);
        statement.setDate(6, endDate);
        statement.setDate(7, endDate);
        statement.setInt(8, numberOfRooms);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.isBeforeFirst()) {
            throw new EntityNotFoundException("Cannot find rooms");
        }

        while (resultSet.next()) {
            createRoomRequest(connection, hostelId, requestDate, endDate, resultSet);
        }
    }

    private void createRoomRequest(Connection connection, int hostelId, Date requestDate, Date endDate, ResultSet resultSet) throws SQLException {
        int room = resultSet.getInt(1);
        String query = resourceBundle.getString(REQUEST_ADD_ROOM_REQUEST);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, hostelId);
        statement.setInt(2, room);
        statement.setDate(3, requestDate);
        statement.setDate(4, endDate);
        statement.executeUpdate();
    }

    private void updateRequests(Connection connection, int userId, int hostelId, int numberOfRooms, int days, int cost, String type, Date requestDate) throws SQLException {
        String query = resourceBundle.getString(REQUEST_ADD_REQUEST);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setInt(2, hostelId);
        statement.setString(3, type);
        statement.setInt(4, numberOfRooms);
        statement.setInt(5, days);
        statement.setInt(6, cost);
        statement.setDate(7, requestDate);
        statement.executeUpdate();
    }

    private int getNewBalance(int requestId, int userId, int balance, Connection connection) throws SQLException, EntityNotFoundException {
        String query = resourceBundle.getString(REQUEST_SEARCH_COST);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setInt(2, requestId);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            throw new EntityNotFoundException("Cannot find cost and balance");
        }
        while (resultSet.next()) {
            int payment = resultSet.getInt(1);
            balance = resultSet.getInt(2);
            balance = updateBalance(balance, connection, userId, -payment);
        }
        return balance;
    }

    private void updateRequestStatus(int requestId, String status, Connection connection) throws SQLException {
        String query = resourceBundle.getString(REQUEST_UPDATE_STATUS);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, status);
        statement.setInt(2, requestId);
        statement.executeUpdate();
    }
}