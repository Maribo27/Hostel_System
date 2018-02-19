package by.tc.hostel_system.service.request;

import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;

import java.sql.Date;
import java.util.List;

public interface RequestService {
	/**
	 * Returns list of all requests from DAO layer.
	 *
	 * @param lang  current language
	 * @param page  page number
	 *
	 * @return {@link List} of {@link Request}
	 *
	 * @throws ServiceException if error in DAO layer occurred
	 */
	List<Request> getRequests(String lang, String page) throws ServiceException;

	/**
	 * Returns list of concrete requests from DAO layer.
	 *
	 * @param lang  current language
	 * @param user  current user
	 * @param page  page number
	 *
	 * @return {@link List} of {@link Request}
	 *
	 * @throws ServiceException if error in DAO layer occurred
	 */
	List<Request> getRequests(String lang, Object user, String page) throws ServiceException;

	/**
	 * Returns list of upcoming requests from DAO layer.
	 *
	 * @param lang      current language
	 * @param objUser   current user
	 *
	 * @return {@link List} of {@link Request}
	 *
	 * @throws ServiceException if error in DAO layer occurred
	 */
	List<Request> getRequests(String lang, Object objUser) throws ServiceException;

	/**
	 * Send new user request to DAO layer to add it to database.
	 *
	 * @param user      current user
	 * @param hostelId  hostel id
	 * @param type      type of payment ("payment", "booking")
	 * @param rooms     number of rooms
	 * @param days      number of days
	 * @param date      date of booking start
	 * @param cost      room price
	 *
	 * @return new {@link User#balance}
	 *
	 * @throws ServiceException if error in DAO layer occurred
	 */
    int addRequest(Object user, String hostelId, String type, String rooms, String days, String date, String cost) throws ServiceException;

	/**
	 * Send new request status ("denied", "deleted") to DAO layer to change current status in database.
	 *
	 * @param requestId request id
	 * @param userId    user id
	 * @param status    new status
	 * @param user      current user
	 * @param page      page number
	 * @param start     date of booking start
	 * @param days      number of days
	 * @param rooms     number of rooms
	 * @param hostelId  hostel id
	 *
	 * @return new {@link User#balance}
	 *
	 * @throws ServiceException if error in DAO layer occurred
	 */
	int cancelRequest(String requestId, String userId, String status, Object user, String page, String start, String days, String rooms, String hostelId) throws ServiceException;

	/**
	 * Send new request status ("approved") to DAO layer to change current status in database.
	 *
	 * @param id    request id
	 * @param page  page number
	 *
	 * @throws ServiceException if error in DAO layer occurred
	 */
	void approveRequest(String id, String page) throws ServiceException;
}