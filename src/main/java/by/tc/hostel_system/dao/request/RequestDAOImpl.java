package by.tc.hostel_system.dao.request;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.util.DAOUtil;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import static by.tc.hostel_system.dao.EntityMessageLocale.REQUESTS;
import static by.tc.hostel_system.dao.SQLQuery.*;

public class RequestDAOImpl implements RequestDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("query.request");
    private static final String SQL_ERROR_WHILE_SEARCHING_REQUESTS = "SQL error while searching requests";

    /**
     * @see RequestDAO#getRequests(String, String)
     * @throws DAOException if nothing found or catch {@link SQLException}
     */
    @Override
    public List<Request> getRequests(String lang, String bundle) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(bundle);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(REQUESTS.getMessage(lang));
            }

            return DAOUtil.createRequests(resultSet);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_REQUESTS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see RequestDAO#getRequests(String, int, String)
     * @throws DAOException if nothing found or catch {@link SQLException}
     */
    @Override
    public List<Request> getRequests(String lang, int id, String bundle) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(bundle);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            statement.setInt(3, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(REQUESTS.getMessage(lang));
            }

            final List<Request> requests = DAOUtil.createRequests(resultSet);

            if (requests.size() == 0) {
                throw new EntityNotFoundException(REQUESTS.getMessage(lang));
            }
            return requests;
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_REQUESTS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see RequestDAO#addRequest(Request, int)
     * @throws DAOException if catch {@link SQLException}
     */
    @Override
    public int addRequest(Request request, int balance) throws DAOException {
        Connection connection = null;
        int userId = request.getUserId();
        int hostelId = request.getHostelId();
        int numberOfRooms = request.getRoom();
        int days = request.getDays();
        int cost = request.getCost();
        Hostel.Booking type = request.getType();
        Date requestDate = request.getDate();
        Date endDate = request.getEndDate();
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            updateRequests(connection, userId, hostelId, numberOfRooms, days, cost, type, requestDate);
            searchFreeRoom(connection, hostelId, numberOfRooms, requestDate, endDate);
            balance = updateBalance(balance, connection, userId, cost);
            connection.commit();
            connection.setAutoCommit(true);
            return balance;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL rollback error while creating request");
            }
            throw new DAOException("SQL error while creating request");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see RequestDAO#cancelRequest(int, int, String, int, Date, Date, int, int)
     * @throws DAOException if catch {@link SQLException}
     */
    @Override
    public int cancelRequest(int requestId, int userId, String status, int balance, Date start, Date end, int rooms, int hostelId) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            updateRequestStatus(requestId, status, connection);
            updateRoomsStatus(hostelId, start, end, connection, rooms);
            balance = getNewBalance(requestId, userId, balance, connection);
            connection.commit();
            connection.setAutoCommit(true);
            return balance;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL rollback error while deleting request");
            }
            throw new DAOException("SQL error while deleting requests");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    /**
     * @see RequestDAO#approveRequest(int)
     * @throws DAOException if catch {@link SQLException}
     */
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

    /**
     * Updates {@link by.tc.hostel_system.entity.User#balance} in database.
     *
     * @param balance new user balance
     * @param connection current connection to database
     * @param userId user id
     * @param cost request cost
     *
     * @return new {@link by.tc.hostel_system.entity.User#balance}
     *
     * @throws SQLException if error in database occurred
     */
	private int updateBalance(int balance, Connection connection, int userId, int cost) throws SQLException {
        String query = resourceBundle.getString(REQUEST_UPDATE_BALANCE);
        PreparedStatement statement = connection.prepareStatement(query);
        balance -= cost;
        statement.setInt(1, balance);
        statement.setInt(2, userId);
        statement.executeUpdate();
        return balance;
    }

    /**
     * Searches for free room in current {@link Hostel}.
     *
     * @param connection current connection
     * @param hostelId hostel id
     * @param numberOfRooms number of rooms
     * @param requestDate date of booking start
     * @param endDate date of booking end
     *
     * @throws SQLException if error in database occurred
     * @throws EntityNotFoundException if nothing found
     */
    private void searchFreeRoom(Connection connection, int hostelId, int numberOfRooms, Date requestDate, Date endDate) throws SQLException, EntityNotFoundException {
        String query = resourceBundle.getString(REQUEST_SEARCH_ROOMS);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, hostelId);
        statement.setDate(2, requestDate);
        statement.setDate(3, requestDate);
        statement.setDate(4, requestDate);
        statement.setDate(5, endDate);
        statement.setInt(6, numberOfRooms);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.isBeforeFirst()) {
            throw new EntityNotFoundException("Cannot find rooms");
        }

        while (resultSet.next()) {
            createRoomRequest(connection, hostelId, requestDate, endDate, resultSet);
        }
    }

    /**
     * Adds request to free room in {@link Hostel}.
     *
     * @param connection current connection
     * @param hostelId hostel id
     * @param requestDate date of booking start
     * @param endDate date of booking end
     * @param resultSet result set from database
     *
     * @throws SQLException if error in database occurred
     */
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

    /**
     * Updates {@link Request} in database.
     *
     * @param connection current connection
     * @param userId user id
     * @param hostelId hostel id
     * @param numberOfRooms number of rooms to book
     * @param days number of days
     * @param cost request cost
     * @param type of request ("booking" or "payment")
     * @param requestDate date of booking start
     *
     * @throws SQLException if error in database occurred
     */
    private void updateRequests(Connection connection, int userId, int hostelId, int numberOfRooms, int days, int cost, Hostel.Booking type, Date requestDate) throws SQLException {
        String query = resourceBundle.getString(REQUEST_ADD_REQUEST);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setInt(2, hostelId);
        statement.setString(3, type.name());
        statement.setInt(4, numberOfRooms);
        statement.setInt(5, days);
        statement.setInt(6, cost);
        statement.setDate(7, requestDate);
        statement.executeUpdate();
    }

    /**
     * Calculates new {@link by.tc.hostel_system.entity.User#balance}.
     *
     * @param requestId request id
     * @param userId user id
     * @param balance user current balance
     * @param connection current connection
     *
     * @return new {@link by.tc.hostel_system.entity.User#balance}
     *
     * @throws SQLException if error in database occurred
     * @throws EntityNotFoundException if nothing found
     */
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
            int column = 1;
            int payment = resultSet.getInt(column++);
            balance = resultSet.getInt(column++);
            final String bookingType = resultSet.getString(column).toUpperCase();
            Hostel.Booking type = Hostel.Booking.valueOf(bookingType);
            if (type == Hostel.Booking.PAYMENT) {
                balance = updateBalance(balance, connection, userId, -payment);
            }
        }
        return balance;
    }

    /**
     * Updates {@link Request} status in database.
     *
     * @param requestId request id
     * @param status new request status
     * @param connection current connection
     *
     * @throws SQLException if error in database occurred
     */
    private void updateRequestStatus(int requestId, String status, Connection connection) throws SQLException {
        String query = resourceBundle.getString(REQUEST_UPDATE_STATUS);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, status);
        statement.setInt(2, requestId);
        statement.executeUpdate();
    }

    /**
     * Updates room status in database.
     *
     * @param hostelId request id
     * @param start     date of booking start
     * @param end       date of booking end
     *
     * @throws SQLException if error in database occurred
     */
    private void updateRoomsStatus(int hostelId, Date start, Date end, Connection connection, int rooms) throws SQLException {
        for (int count = 0; count < rooms; count++) {
            String query = resourceBundle.getString(REQUEST_UPDATE_ROOMS);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hostelId);
            statement.setDate(2, start);
            statement.setDate(3, end);
            statement.executeUpdate();
        }
    }
}