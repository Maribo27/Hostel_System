package by.tc.hostel_system.dao.user;

import by.tc.hostel_system.dao.CurrentEntityExist;
import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.entity.builder.UserBuilder;
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
		String lastname = "";
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
	public void userBlocking() {
		int idNumber = 3;
		int reasonNumber = 2;
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.blockUser(idNumber, reasonNumber);
		} catch (DAOException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void userUnlocking() {
		int idNumber = 3;
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
		int id = 17;
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
		UserBuilder builder = new UserBuilder();
		builder.addId(1);
		final String username = "admin";
		final String email = "admin@tut.by";
		final String pass = "21232f297a57a5a743894a0e4a801fc3";
		final String surname = "Иванов";
		final String name = "Иван";
		final String lastname = "Иванович";
		builder.addPersonalInfo(username, email, pass, name, surname, lastname);
		builder.addDiscount(50);
		builder.addBalance(7);
		builder.addAccount("c4ca4238a0b923820dcc509a6f75849b");
		builder.addStatus("admin");
		return builder.buildUser();
	}

	private List<User> createUsers() {
		List<User> users = new ArrayList<>();
		UserBuilder builder = new UserBuilder();
		builder = new UserBuilder();
		builder.addId(2);
		builder.addPersonalInfo("alexx", "alexander@mail.ru", "", "Alex", "Doe", "");
		builder.addDiscount(10);
		builder.addBalance(200);
		builder.addStatus("banned");
		builder.addBlockInfo("Insult", Date.valueOf("2018-02-01"));
		builder.addRequests(2);
		users.add(builder.buildUser());
		builder = new UserBuilder();
		builder.addId(3);
		builder.addPersonalInfo("ashotik", "ashort@gogo.kz", "", "Ашот", "Рахмед", "Мухаммедыч");
		builder.addDiscount(5);
		builder.addBalance(98);
		builder.addStatus("user");
		users.add(builder.buildUser());
		builder = new UserBuilder();
		builder.addId(4);
		builder.addPersonalInfo("kukareku", "petuh@gmail.com", "", "Павел", "Синий", "Михайлович");
		builder.addDiscount(25);
		builder.addBalance(254);
		builder.addStatus("banned");
		builder.addBlockInfo("Residence rules violation", Date.valueOf("2017-02-08"));
		users.add(builder.buildUser());
		builder = new UserBuilder();
		builder.addId(5);
		builder.addPersonalInfo("pierce.k", "petrova.k@gmail.com", "", "Катерина", "Петрова", "");
		builder.addDiscount(30);
		builder.addBalance(2409);
		builder.addStatus("user");
		builder.addRequests(3);
		users.add(builder.buildUser());
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