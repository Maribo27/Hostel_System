package by.tc.hostel_system.dao.hostel;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.util.DAOUtil;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static by.tc.hostel_system.dao.EntityMessageLocale.Entity.HOSTELS;
import static by.tc.hostel_system.dao.SQLQuery.*;

public class HostelDAOImpl implements HostelDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("query.hostel");
    private static final String SQL_ERROR_WHILE_SEARCHING_HOSTELS = "SQL error while searching hostels";

    @Override
    public List<Hostel> getHostels(String lang) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String query = resourceBundle.getString(HOSTEL_ALL_HOSTELS);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            statement.setString(2, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException(HOSTELS.getMessage(lang));
            }

            return DAOUtil.createHostels(resultSet, Hostel.Booking.PAYMENT, 0);
        } catch (SQLException e) {
            throw new DAOException(SQL_ERROR_WHILE_SEARCHING_HOSTELS);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Hostel> getHostels(String lang, Hostel.Booking type, int city, int room, Date start, Date end) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(HOSTEL_CONCRETE_HOSTELS);
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
                throw new EntityNotFoundException(HOSTELS.getMessage(lang));
            }

            final List<Hostel> hostels = DAOUtil.createHostels(resultSet, type, room);
            if (hostels.size() == 0) {
                throw new EntityNotFoundException(HOSTELS.getMessage(lang));
            }
            return hostels;
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
            connection = connectionPool.getConnection();

            String query = resourceBundle.getString(HOSTEL_CITIES);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lang);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new EntityNotFoundException("Cities not found");
            }

            Map<Integer, String> cities = DAOUtil.translateToMap(resultSet);
            return cities;
        } catch (SQLException e) {
            throw new DAOException("SQL error while searching cities");
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}