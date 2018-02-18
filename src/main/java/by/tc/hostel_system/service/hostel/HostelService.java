package by.tc.hostel_system.service.hostel;

import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface HostelService {
    /**
     * Returns list of all hostels from DAO layer.
     *
     * @param lang current lang
     * @param page current page number
     *
     * @return {@link List} of {@link Hostel}
     *
     * @throws ServiceException if error on DAO layer occurred
     */
    List<Hostel> getHostels(String lang, String page) throws ServiceException;

    /**
     * Returns list of concrete hostels from DAO layer.
     *
     * @param lang  current language
     * @param city  hostel city
     * @param room  number of free rooms
     * @param start date of booking start
     * @param days  count of days
     * @param page  page number
     * @param type  type of payment
     *
     * @return {@link List} of concrete {@link Hostel}
     *
     * @throws ServiceException if error on DAO layer occurred
     */
    List<Hostel> getHostels(String lang, String city, String room, String start, String days, String page, String type) throws ServiceException;

    /**
     * Returns map of cities from DAO layer.
     *
     * @param lang  current language
     *
     * @return {@link Map} of cities
     *
     * @throws ServiceException if error on DAO layer occurred
     */
    Map<Integer, String> getCities(String lang) throws ServiceException;
}