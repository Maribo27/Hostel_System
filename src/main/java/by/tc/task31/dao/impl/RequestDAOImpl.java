package by.tc.task31.dao.impl;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.RequestDAO;
import by.tc.task31.dao.connector.ConnectorToDB;
import by.tc.task31.dao.util.DaoUtil;
import by.tc.task31.entity.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestDAOImpl implements RequestDAO {
    private static final String SQL_EXCEPTION_MESSAGE = "SQL error";

    private Connection connection;
    private PreparedStatement statement;

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

            return DaoUtil.createRequests(resultSet);
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

            return DaoUtil.createRequests(resultSet);
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
}