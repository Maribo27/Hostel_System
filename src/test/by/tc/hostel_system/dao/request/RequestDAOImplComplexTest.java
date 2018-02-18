package by.tc.hostel_system.dao.request;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.builder.RequestBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static by.tc.hostel_system.dao.SQLQuery.REQUEST_SEARCH_ALL_REQUESTS;
import static by.tc.hostel_system.dao.SQLQuery.REQUEST_SEARCH_USER_REQUESTS;
import static org.junit.Assert.assertEquals;

public class RequestDAOImplComplexTest {
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
		List<Request> actual = requestDAO.getRequests(LANG_EN, REQUEST_SEARCH_ALL_REQUESTS);
		assertEquals(expected, actual);
	}

	@Test
	public void getRequests() throws DAOException {
		int userId = 5;
		List<Request> expected = createRequests();
		List<Request> actual = requestDAO.getRequests(LANG_EN, userId, REQUEST_SEARCH_USER_REQUESTS);
		assertEquals(expected, actual);
	}

	private List<Request> createAllRequests() {
		List<Request> requests = new ArrayList<>();
		RequestBuilder builder = new RequestBuilder();
		builder.addId(2);
		builder.addUserId(2);
		builder.addHostelId(2);
		builder.addHostelInfo("\"Moj kraj\", Belarus, Minsk, Minskaya st., 7");
		builder.addType("payment");
		builder.addRoom(1);
		builder.addDays(1);
		builder.addCost(20);
		builder.addStatus("approved");
		builder.addDate(Date.valueOf("2017-01-22"));
		requests.add(builder.buildRequest());
		builder = new RequestBuilder();
		builder.addId(3);
		builder.addUserId(2);
		builder.addHostelId(3);
		builder.addHostelInfo("\"Za Kopatycha\", Belarus, Grodno, Sovetskaya st., 8");
		builder.addType("booking");
		builder.addRoom(1);
		builder.addDays(1);
		builder.addCost(25);
		builder.addStatus("approved");
		builder.addDate(Date.valueOf("2017-02-22"));
		requests.add(builder.buildRequest());
		builder = new RequestBuilder();
		builder.addId(4);
		builder.addUserId(5);
		builder.addHostelId(4);
		builder.addHostelInfo("\"My home\", Russia, Moscow, Bombom st., 7");
		builder.addType("booking");
		builder.addRoom(1);
		builder.addDays(2);
		builder.addCost(30);
		builder.addStatus("denied");
		builder.addDate(Date.valueOf("2017-03-21"));
		requests.add(builder.buildRequest());
		builder = new RequestBuilder();
		builder.addId(5);
		builder.addUserId(5);
		builder.addHostelId(1);
		builder.addHostelInfo("\"Oladushka\", Belarus, Minsk, Gorkogo st., 24");
		builder.addType("payment");
		builder.addRoom(1);
		builder.addDays(5);
		builder.addCost(50);
		builder.addStatus("denied");
		builder.addDate(Date.valueOf("2017-04-21"));
		requests.add(builder.buildRequest());
		builder = new RequestBuilder();
		builder.addId(6);
		builder.addUserId(3);
		builder.addHostelId(3);
		builder.addHostelInfo("\"Za Kopatycha\", Belarus, Grodno, Sovetskaya st., 8");
		builder.addType("booking");
		builder.addRoom(1);
		builder.addDays(1);
		builder.addCost(20);
		builder.addStatus("processing");
		builder.addDate(Date.valueOf("2018-02-25"));
		requests.add(builder.buildRequest());
		return requests;
	}

	private List<Request> createRequests() {
		List<Request> requests = new ArrayList<>();
		RequestBuilder builder = new RequestBuilder();
		builder.addId(4);
		builder.addUserId(5);
		builder.addHostelId(4);
		builder.addHostelInfo("\"My home\", Russia, Moscow, Bombom st., 7");
		builder.addType("booking");
		builder.addRoom(1);
		builder.addDays(2);
		builder.addCost(30);
		builder.addStatus("denied");
		builder.addDate(Date.valueOf("2017-03-21"));
		requests.add(builder.buildRequest());
		builder = new RequestBuilder();
		builder.addId(5);
		builder.addUserId(5);
		builder.addHostelId(1);
		builder.addHostelInfo("\"Oladushka\", Belarus, Minsk, Gorkogo st., 24");
		builder.addType("payment");
		builder.addRoom(1);
		builder.addDays(5);
		builder.addCost(50);
		builder.addStatus("denied");
		builder.addDate(Date.valueOf("2017-04-21"));
		requests.add(builder.buildRequest());
		return requests;
	}
}