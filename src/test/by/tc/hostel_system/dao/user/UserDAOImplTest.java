package by.tc.hostel_system.dao.user;

import by.tc.hostel_system.dao.CurrentEntityExist;
import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.user.UserExistException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.tc.hostel_system.dao.SQLQuery.USER_SEARCH_USERNAME;
import static org.junit.Assert.assertEquals;

public class UserDAOImplTest {

	private final static UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final String LANG_EN = "en";

	@Before
	public void setUp() {
		connectionPool.initPoolData();
	}

	@After
	public void tearDown() {
		connectionPool.dispose();
	}

	@Test
	public void checkUserTrue() throws UserExistException, DAOException {
		boolean expected = true;
		boolean actual = false;
		try {
			userDAO.checkUser("admin", USER_SEARCH_USERNAME);
		} catch (CurrentEntityExist e) {
			actual = true;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void checkUserFalse() throws UserExistException, DAOException {
		boolean expected = false;
		boolean actual = false;
		try {
			userDAO.checkUser("anastas", USER_SEARCH_USERNAME);
		} catch (CurrentEntityExist e) {
			actual = true;
		}
		assertEquals(expected, actual);
	}


	@Test
	public void getUserDiscount() throws DAOException {
		int id = 3;
		int expected = 5;
		int actual = userDAO.getUserDiscount(id);
		assertEquals(expected, actual);
	}

	@Test
	public void changeUserDiscount() {
		int id = 3;
		int discount = 5;
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.changeUserDiscount(id, discount);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void getUserInformation() throws DAOException {
		User expected = createUser();
		final String username = "admin";
		final String password = "admin";
		User actual = userDAO.getUserInformation(LANG_EN, username, password);
		assertEquals(expected, actual);
	}

	@Test
	public void addUser() throws Exception {
		String username = "checking";
		String email = "check@mhu.tut";
		String password = "checking";
		String name = "Check";
		String surname = "Test";
		String lastname = null;
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.addUser(username, password, name, lastname, surname, email);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void getUsers() throws DAOException {
		List<User> expected = createUsers();
		List<User> actual = userDAO.getUsers(LANG_EN);
		assertEquals(expected, actual);
	}


	@Test
	public void getReasons() throws DAOException {
		Map<Integer, String> expected = createReasons();
		Map<Integer, String> actual = userDAO.getReasons(LANG_EN);
		assertEquals(expected, actual);
	}

	@Test
	public void blockUser() {
		int idNumber = 8;
		Date beginDate = Date.valueOf("2018-09-09");
		int reasonNumber = 2;
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.blockUser(idNumber, beginDate, reasonNumber);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void unlockUser() {
		int idNumber = 6;
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.unlockUser(idNumber);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void changeUserData() {
		String email = "email='burunduk@email.tut'";
		String name = "name='Burund'";
		int id = 7;
		List<String> dataToUpdate = new ArrayList<>();
		dataToUpdate.add(email);
		dataToUpdate.add(name);
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.changeUserData(id, dataToUpdate);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void changePassword() {
		int id = 6;
		String password = "tututu";
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.changePassword(id, password);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser() {
		int id = 8;
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.deleteUser(id);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	private User createUser() {
		User user = new User();
		user.setId(1);
		user.setUsername("admin");
		user.setEmail("admin@tut.by");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		user.setSurname("Иванов");
		user.setName("Иван");
		user.setLastname("Иванович");
		user.setDiscount(50);
		user.setBalance(7);
		user.setAccount("c4ca4238a0b923820dcc509a6f75849b");
		user.setStatus("admin");
		return user;
	}

	private List<User> createUsers() {
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setId(1);
		user.setUsername("admin");
		user.setEmail("admin@tut.by");
		user.setSurname("Иванов");
		user.setName("Иван");
		user.setLastname("Иванович");
		user.setDiscount(50);
		user.setBalance(7);
		user.setStatus("admin");
		users.add(user);
		user = new User();
		user.setId(2);
		user.setUsername("alexx");
		user.setEmail("alexander@mail.ru");
		user.setSurname("Doe");
		user.setName("Alex");
		user.setDiscount(10);
		user.setBalance(200);
		user.setStatus("banned");
		user.setBlockDate(Date.valueOf("2018-02-01"));
		user.setUnlockDate(Date.valueOf("2018-02-04"));
		user.setBlockReason("Insult");
		user.setRequests(2);
		users.add(user);
		user = new User();
		user.setId(3);
		user.setUsername("ashotik");
		user.setEmail("ashort@gogo.kz");
		user.setSurname("Рахмед");
		user.setName("Ашот");
		user.setLastname("Мухаммедыч");
		user.setDiscount(5);
		user.setBalance(98);
		user.setStatus("user");
		users.add(user);
		user = new User();
		user.setId(4);
		user.setUsername("kukareku");
		user.setEmail("petuh@gmail.com");
		user.setSurname("Синий");
		user.setName("Павел");
		user.setLastname("Михайлович");
		user.setDiscount(25);
		user.setBalance(254);
		user.setStatus("banned");
		user.setBlockDate(Date.valueOf("2017-02-08"));
		user.setUnlockDate(Date.valueOf("2018-02-08"));
		user.setBlockReason("Residence rules violation");
		users.add(user);
		user = new User();
		user.setId(5);
		user.setUsername("pierce.k");
		user.setEmail("petrova.k@gmail.com");
		user.setSurname("Петрова");
		user.setName("Катерина");
		user.setDiscount(30);
		user.setBalance(2409);
		user.setStatus("user");
		user.setRequests(3);
		users.add(user);
		return users;
	}

	private Map<Integer, String> createReasons() {
		Map<Integer, String> reasons = new HashMap<>();
		reasons.put(1, "Cheating");
		reasons.put(2, "Insult");
		reasons.put(3, "Residence rules violation");
		reasons.put(4, "Information theft");
		return reasons;
	}
}