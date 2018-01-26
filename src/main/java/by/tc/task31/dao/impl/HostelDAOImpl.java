package by.tc.task31.dao.impl;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.HostelDAO;
import by.tc.task31.dao.connector.ConnectionPool;
import by.tc.task31.entity.Hostel;
import by.tc.task31.util.DaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostelDAOImpl implements HostelDAO {
    private static final String SQL_EXCEPTION_MESSAGE = "SQL error";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Hostel> getHostels(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

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

            return DaoUtil.createHostels(resultSet);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                connectionPool.closeConnection(connection);
            }
        }
    }

    @Override
    public List<Hostel> getHostels(String lang, int city, int room) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

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

            return DaoUtil.createHostels(resultSet);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                connectionPool.closeConnection(connection);
            }
        }
    }

    @Override
    public Map<Integer, String> getCities(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            String query = "SELECT c.id_city, c.city " +
                    "FROM city AS c WHERE c.lang_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            Map<Integer, String> cities = new HashMap<>();
            DaoUtil.createMapFromDB(resultSet, cities);
            return cities;
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                connectionPool.closeConnection(connection);
            }
        }
    }

    @Override
    public void deleteHostel(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            String query = "DELETE FROM hostel WHERE id_hostel=?";
            PreparedStatement statement = connection.prepareStatement(query);
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
                connectionPool.closeConnection(connection);
            }
        }
    }

}