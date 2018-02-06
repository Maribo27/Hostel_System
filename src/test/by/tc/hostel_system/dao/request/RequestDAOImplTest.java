package by.tc.hostel_system.dao.request;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.util.ServiceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RequestDAOImplTest {
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();
	private final static String LANG_EN = "en";
	@Before
	public void setUp() {
		connectionPool.initPoolData();
	}

	@After
	public void tearDown() {
		connectionPool.dispose();
	}
	@Test
	public void getAllRequests() throws DAOException {
		List<Request> expected = createAllRequests();
		List<Request> actual = requestDAO.getRequests(LANG_EN);
		assertEquals(expected, actual);
	}

	@Test
	public void getRequests() throws DAOException {
		int userId = 5;
		List<Request> expected = createRequests();
		List<Request> actual = requestDAO.getRequests(LANG_EN, userId);
		assertEquals(expected, actual);
	}

	@Ignore
	public void addRequest() throws DAOException {
		Date date = Date.valueOf("2019-10-22");
		Request userRequest = ServiceUtil.createRequest(5, 2, "payment", 2, 3, 30, date, 20);
		int expected = 2325;
		int actual = requestDAO.addRequest(userRequest, 2409);
		assertEquals(expected, actual);
	}

	@Ignore
	public void cancelRequest() throws DAOException {
		int expected = 2409;
		int requestId = 6;
		int userId = 5;
		String status = "deleted";
		int balance = 2325;
		int actual = requestDAO.cancelRequest(requestId, userId, status, balance);
		assertEquals(expected, actual);
	}

	@Ignore
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

	private List<Request> createAllRequests() {
		List<Request> requests = new ArrayList<>();
		Request request = new Request();
		request.setId(2);
		request.setUserId(2);
		request.setHostelId(2);
		request.setHostelInfo("\"Moj kraj\", Belarus, Minsk, Minskaya st., 7");
		request.setType("payment");
		request.setRoom(1);
		request.setDays(1);
		request.setCost(20);
		request.setStatus("approved");
		request.setDate(Date.valueOf("2017-01-22"));
		requests.add(request);
		request = new Request();
		request.setId(3);
		request.setUserId(2);
		request.setHostelId(3);
		request.setHostelInfo("\"Za Kopatycha\", Belarus, Grodno, Sovetskaya st., 8");
		request.setType("booking");
		request.setRoom(1);
		request.setDays(1);
		request.setCost(25);
		request.setStatus("approved");
		request.setDate(Date.valueOf("2017-02-22"));
		requests.add(request);
		request = new Request();
		request.setId(4);
		request.setUserId(5);
		request.setHostelId(4);
		request.setHostelInfo("\"My home\", Russia, Moscow, Bombom st., 7");
		request.setType("booking");
		request.setRoom(1);
		request.setDays(2);
		request.setCost(30);
		request.setStatus("denied");
		request.setDate(Date.valueOf("2017-03-21"));
		requests.add(request);
		request = new Request();
		request.setId(5);
		request.setUserId(5);
		request.setHostelId(1);
		request.setHostelInfo("\"Oladushka\", Belarus, Minsk, Gorkogo st., 24");
		request.setType("payment");
		request.setRoom(1);
		request.setDays(5);
		request.setCost(50);
		request.setStatus("denied");
		request.setDate(Date.valueOf("2017-04-21"));
		requests.add(request);
		return requests;
	}

	private List<Request> createRequests() {
		List<Request> requests = new ArrayList<>();
		Request request = new Request();
		request.setId(4);
		request.setUserId(5);
		request.setHostelId(4);
		request.setHostelInfo("\"My home\", Russia, Moscow, Bombom st., 7");
		request.setType("booking");
		request.setRoom(1);
		request.setDays(2);
		request.setCost(30);
		request.setStatus("denied");
		request.setDate(Date.valueOf("2017-03-21"));
		requests.add(request);
		request = new Request();
		request.setId(5);
		request.setUserId(5);
		request.setHostelId(1);
		request.setHostelInfo("\"Oladushka\", Belarus, Minsk, Gorkogo st., 24");
		request.setType("payment");
		request.setRoom(1);
		request.setDays(5);
		request.setCost(50);
		request.setStatus("denied");
		request.setDate(Date.valueOf("2017-04-21"));
		requests.add(request);
		return requests;
	}
}