package by.tc.hostel_system.dao.hostel;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.entity.builder.HostelBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HostelDAOImplTest {
	private static final HostelDAO hostelDAO = DAOFactory.getInstance().getHostelDAO();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
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
	public void getHostels() throws DAOException{
		List<Hostel> expected = createHostelList();
		List<Hostel> actual = hostelDAO.getHostels(LANG_EN);
		assertEquals(expected, actual);
	}

	@Test
	public void getHostelsForRequest() throws DAOException{
		List<Hostel> expected = createHostelForRequestList();
		int city = 1;
		int room = 1;
		Date start = Date.valueOf("2018-07-09");
		Date end = Date.valueOf("2018-07-19");
		Hostel.Booking type = Hostel.Booking.valueOf("BOOKING");
		List<Hostel> actual = hostelDAO.getHostels(LANG_EN, type, city, room, start, end);
		assertEquals(expected, actual);
	}

	@Test
	public void getCities() throws DAOException {
		Map<Integer, String> expected = createCitiesMap();
		Map<Integer, String> actual = hostelDAO.getCities(LANG_EN);
		assertEquals(expected, actual);
	}

	private List<Hostel> createHostelList() {
		List<Hostel> hostels = new ArrayList<>();
		HostelBuilder builder = new HostelBuilder();
		builder.addName("Oladushka");
		builder.addAddress("Minsk", "Belarus", "Gorkogo st., 24");
		builder.addBooking("booking");
		builder.addEmail("oladushka@gmail.com");
		builder.addId(1);
		builder.addCost(10);
		builder.addRoom(5);
		hostels.add(builder.buildHostel());
		builder = new HostelBuilder();
		builder.addName("Moj kraj");
		builder.addAddress("Minsk", "Belarus", "Minskaya st., 7");
		builder.addBooking("payment");
		builder.addEmail("moj_kraj@gmail.com");
		builder.addId(2);
		builder.addCost(20);
		builder.addRoom(5);
		hostels.add(builder.buildHostel());
		builder = new HostelBuilder();
		builder.addName("Za Kopatycha");
		builder.addAddress("Grodno", "Belarus", "Sovetskaya st., 8");
		builder.addBooking("booking");
		builder.addEmail("zakopatycha@gmail.com");
		builder.addId(3);
		builder.addCost(25);
		builder.addRoom(5);
		hostels.add(builder.buildHostel());
		builder = new HostelBuilder();
		builder.addName("My home");
		builder.addAddress("Moscow", "Russia", "Bombom st., 7");
		builder.addBooking("booking");
		builder.addEmail("my_home@gmail.com");
		builder.addId(4);
		builder.addCost(15);
		builder.addRoom(5);
		hostels.add(builder.buildHostel());
		builder = new HostelBuilder();
		builder.addName("Sweet Day");
		builder.addAddress("St. Petersburg", "Russia", "Armax st., 9");
		builder.addBooking("booking");
		builder.addEmail("sweet_day@gmail.com");
		builder.addId(5);
		builder.addCost(16);
		builder.addRoom(5);
		hostels.add(builder.buildHostel());
		return hostels;
	}

	private List<Hostel> createHostelForRequestList() {
		List<Hostel> hostels = new ArrayList<>();
		HostelBuilder builder = new HostelBuilder();
		builder.addName("Oladushka");
		builder.addAddress("Minsk", "Belarus", "Gorkogo st., 24");
		builder.addEmail("oladushka@gmail.com");
		builder.addBooking("booking");
		builder.addId(1);
		builder.addCost(10);
		builder.addRoom(5);
		hostels.add(builder.buildHostel());
		return hostels;
	}

	private Map<Integer, String> createCitiesMap() {
		Map<Integer, String> cities = new HashMap<>();
		cities.put(1, "Minsk");
		cities.put(2, "Grodno");
		cities.put(3, "Moscow");
		cities.put(4, "St. Petersburg");
		cities.put(5, "Kiev");
		return cities;
	}
}