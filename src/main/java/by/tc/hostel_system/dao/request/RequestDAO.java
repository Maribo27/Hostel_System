package by.tc.hostel_system.dao.request;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.entity.Request;

import java.sql.Date;
import java.util.List;

public interface RequestDAO {
	/**
	 * Returns {@link List} of {@link Request} from database.
	 *
	 * @param lang
	 * current language
	 * @param bundle
	 * query bundle
	 *
	 * @return {@link List} of {@link Request}
	 *
	 * @throws DAOException if database error occurred
	 */
	List<Request> getRequests(String lang, String bundle) throws DAOException;

	/**
	 * Returns {@link List} of {@link by.tc.hostel_system.entity.User}{@link Request} from database.
	 *
	 * @param lang
	 * current language
	 * @param id
	 * current user id
	 * @param bundle
	 * query bundle
	 *
	 * @return {@link List} of {@link Request}
	 *
	 * @throws DAOException if database error occurred
	 */
    List<Request> getRequests(String lang, int id, String bundle) throws DAOException;

	/**
	 * Adds {@link Request} to database.
	 *
	 * @param request
	 * user request
	 * @param balance
	 * current user balance
	 *
	 * @return new {@link by.tc.hostel_system.entity.User#balance}
	 *
	 * @throws DAOException if database error occurred
	 */
    int addRequest(Request request, int balance) throws DAOException;

	/**
	 * Changes {@link Request} status to "denied" or "deleted" in database.
	 *
	 * @param requestId request id
	 * @param userId    user id
	 * @param status    new request status
	 * @param balance   current user balance
	 * @param start     date of booking start
	 * @param end       date of booking end
	 * @param rooms     number of rooms
	 * @param hostelId  hostelId
	 *
	 * @return new {@link by.tc.hostel_system.entity.User#balance}
	 *
	 * @throws DAOException if database error occurred
	 */
	int cancelRequest(int requestId, int userId, String status, int balance, Date start, Date end, int rooms, int hostelId) throws DAOException;

	/**
	 * Changes {@link Request} status to "approved" in database.
	 *
	 * @param id request id
	 * @throws DAOException if database error occurred
	 */
	void approveRequest(int id) throws DAOException;
}