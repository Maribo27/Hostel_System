package by.tc.task31.dao.impl;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.HostelDAO;
import by.tc.task31.dao.connector.ConnectionPool;
import by.tc.task31.entity.Hostel;
import by.tc.task31.util.DAOUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostelDAOImpl implements HostelDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_ERROR_WHILE_SEARCHING_CITIES = "SQL error while searching cities";
    private static final String SQL_ERROR_WHILE_SEARCHING_HOSTELS = "SQL error while searching hostels";

    @Override
    public List<Hostel> getHostels(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            String query =
                    "SELECT h.id_hostel, hl.hostel_name, c.country, c.city, hl.address, " +
                    "h.booking_possibility, h.room_cost, h.hostel_email, COUNT(hr.hostel_id) " +
                    "FROM hostel AS h " +
                    "INNER JOIN hostel_local AS hl ON (h.id_hostel = hl.hostel_id) " +
                    "INNER JOIN city AS c ON (h.hostel_city = c.id_city) " +
                    "INNER JOIN hostel_room AS hr ON (h.id_hostel = hr.hostel_id) " +
                    "WHERE hl.lang_name = ? AND c.lang_name = ? " +
                    "GROUP BY hr.hostel_id";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            return DAOUtil.createHostels(resultSet, 0);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_HOSTELS);
        } finally {
                connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Hostel> getHostels(String lang, int city, int room, Date start, Date end) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();

            String query =
                    "SELECT h.id_hostel, hl.hostel_name, c.country, c.city, hl.address, h.room_cost, h.hostel_email, COUNT(id_hostel) AS cnt " +
                    "FROM hostel AS h" +
                    " INNER JOIN hostel_local AS hl ON (h.id_hostel = hl.hostel_id)" +
                    " INNER JOIN city AS c ON (h.hostel_city = c.id_city)" +
                    " RIGHT JOIN (SELECT DISTINCT r.hostel_id, CONCAT (r.hostel_id,' ',r.room) " +
                    "  FROM hostel_room AS r" +
                    "   LEFT JOIN request_room r3 ON r.hostel_id = r3.hostel AND r.room = r3.room" +
                    "  WHERE (r3.begin_date IS NULL AND r3.end_date IS NULL) OR" +
                    "        (r3.begin_date < ? AND r3.end_date < ? AND r3.end_date < ?) OR" +
                    "        (r3.begin_date > ? AND r3.end_date > ? AND r3.begin_date > ?) " +
                    "  ) AS r3 ON (r3.hostel_id = h.id_hostel) " +
                    "WHERE hl.lang_name = ? AND c.lang_name = ? AND h.hostel_city = ? " +
                    "GROUP BY id_hostel";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, start);
            statement.setDate(2, end);
            statement.setDate(3, start);
            statement.setDate(4, start);
            statement.setDate(5, end);
            statement.setDate(6, end);
            statement.setString(7, lang);
            statement.setString(8, lang);
            statement.setInt(9, city);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            return DAOUtil.createHostels(resultSet, room);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_HOSTELS);
        } finally {
            connectionPool.closeConnection(connection);
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
            DAOUtil.translateToMap(resultSet, cities);
            return cities;
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_CITIES);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}