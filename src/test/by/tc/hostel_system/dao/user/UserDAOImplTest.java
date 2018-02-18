package by.tc.hostel_system.dao.user;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
	public void addUser() throws Exception {
		String username = "checking";
		String email = "check@mhu.tut";
		String password = "checking";
		String name = "Check";
		String surname = "Test";
		String lastName = "";
		boolean expected = true;
		boolean actual = true;
		try {
			userDAO.addUser(LANG_EN, username, password, name, lastName, surname, email);
		} catch (DAOException e) {
			actual = false;
		}
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
		int id = 8;
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
		int id = 8;
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
}