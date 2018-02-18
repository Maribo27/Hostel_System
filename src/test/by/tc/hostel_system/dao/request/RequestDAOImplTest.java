package by.tc.hostel_system.dao.request;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.util.ServiceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class RequestDAOImplTest {
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();
	@Before
	public void setUp() {
		connectionPool.initPoolData();
	}

	@After
	public void tearDown() {
		connectionPool.dispose();
	}

	@Test
	public void addRequest() throws DAOException {
		Date date = Date.valueOf("2019-10-22");
		Request userRequest = ServiceUtil.createRequest(5, 2, "booking", 2, 3, 30, date, 20);
		int cost = userRequest.getCost();
		int expected = 2409;
		int actual = requestDAO.addRequest(userRequest, expected + cost);
		assertEquals(expected, actual);
	}

	@Test
	public void approveRequest() {
		int id = 6;
		boolean expected = true;
		boolean actual = true;
		try {
			requestDAO.approveRequest(id);
		} catch(DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void cancelRequest() throws DAOException {
		int expected = 98;
		int requestId = 6;
		int userId = 3;
		String status = "deleted";
		int balance = 98;
		int actual = requestDAO.cancelRequest(requestId, userId, status, balance);
		assertEquals(expected, actual);
	}
}