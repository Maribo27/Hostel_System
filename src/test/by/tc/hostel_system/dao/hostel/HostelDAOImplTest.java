package by.tc.hostel_system.dao.hostel;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.Hostel;
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
		List<Hostel> actual = hostelDAO.getHostels(LANG_EN, city, room, start, end);
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
		Hostel hostel = new Hostel();
		hostel.setName("Oladushka");
		hostel.setCity("Minsk");
		hostel.setCountry("Belarus");
		hostel.setAddress("Gorkogo st., 24");
		hostel.setBooking("booking");
		hostel.setEmail("oladushka@gmail.com");
		hostel.setId(1);
		hostel.setCost(10);
		hostel.setRoom(5);
		hostels.add(hostel);
		hostel = new Hostel();
		hostel.setName("Moj kraj");
		hostel.setCity("Minsk");
		hostel.setCountry("Belarus");
		hostel.setAddress("Minskaya st., 7");
		hostel.setBooking("payment");
		hostel.setEmail("moj_kraj@gmail.com");
		hostel.setId(2);
		hostel.setCost(20);
		hostel.setRoom(5);
		hostels.add(hostel);
		hostel = new Hostel();
		hostel.setName("Za Kopatycha");
		hostel.setCity("Grodno");
		hostel.setCountry("Belarus");
		hostel.setAddress("Sovetskaya st., 8");
		hostel.setBooking("booking");
		hostel.setEmail("zakopatycha@gmail.com");
		hostel.setId(3);
		hostel.setCost(25);
		hostel.setRoom(5);
		hostels.add(hostel);
		hostel = new Hostel();
		hostel.setName("My home");
		hostel.setCity("Moscow");
		hostel.setCountry("Russia");
		hostel.setAddress("Bombom st., 7");
		hostel.setBooking("booking");
		hostel.setEmail("my_home@gmail.com");
		hostel.setId(4);
		hostel.setCost(15);
		hostel.setRoom(5);
		hostels.add(hostel);
		hostel = new Hostel();
		hostel.setName("Sweet Day");
		hostel.setCity("St. Petersburg");
		hostel.setCountry("Russia");
		hostel.setAddress("Armax st., 9");
		hostel.setBooking("booking");
		hostel.setEmail("sweet_day@gmail.com");
		hostel.setId(5);
		hostel.setCost(16);
		hostel.setRoom(5);
		hostels.add(hostel);
		return hostels;
	}

	private List<Hostel> createHostelForRequestList() {
		List<Hostel> hostels = new ArrayList<>();
		Hostel hostel = new Hostel();
		hostel.setName("Oladushka");
		hostel.setCity("Minsk");
		hostel.setCountry("Belarus");
		hostel.setAddress("Gorkogo st., 24");
		hostel.setEmail("oladushka@gmail.com");
		hostel.setId(1);
		hostel.setCost(10);
		hostel.setRoom(5);
		hostels.add(hostel);
		hostel = new Hostel();
		hostel.setName("Moj kraj");
		hostel.setCity("Minsk");
		hostel.setCountry("Belarus");
		hostel.setAddress("Minskaya st., 7");
		hostel.setEmail("moj_kraj@gmail.com");
		hostel.setId(2);
		hostel.setCost(20);
		hostel.setRoom(5);
		hostels.add(hostel);
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