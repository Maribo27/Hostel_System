package by.tc.hostel_system.dao.hostel;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.entity.Hostel;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface HostelDAO {
    /**
     * Returns {@link List} of {@link Hostel} on concrete language.
     *
     * @param lang
     * current language
     *
     * @return {@link List} of {@link Hostel}
     *
     * @throws DAOException if database error occurred
     */
    List<Hostel> getHostels(String lang) throws DAOException;

    /**
     * Returns {@link List} of {@link Hostel} with some parameters.
     *
     * @param lang
     * current language
     * @param type
     * booking or payment possibility
     * @param city
     * hostel city and country id
     * @param room
     * number of rooms to request
     * @param start
     * start of booking date
     * @param end
     * end of booking date
     *
     * @return {@link List} of {@link Hostel}
     *
     * @throws DAOException if database error occurred
     */
    List<Hostel> getHostels(String lang, Hostel.Booking type, int city, int room, Date start, Date end) throws DAOException;

    /**
     * Returns {@link Map} of cities, that contains city id and name.
     *
     * @param lang
     * current language
     *
     * @return {@link Map} of cities
     *
     * @throws DAOException if database error occurred
     */
    Map<Integer, String> getCities(String lang) throws DAOException;
}