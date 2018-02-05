package by.tc.hostel_system.dao.user;

import by.tc.hostel_system.dao.CurrentEntityExist;
import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.service.user.UserExistException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static by.tc.hostel_system.dao.SQLQuery.USER_SEARCH_USERNAME;
import static org.junit.Assert.assertEquals;

public class UserDAOImplTest {

	private final static UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();

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
	public void getUserDiscount() {

	}

	@Test
	public void changeUserDiscount() {
	}

	@Test
	public void getUserInformation() {
	}

	@Test
	public void addUser() {
	}

	@Test
	public void getUsers() {
	}

	@Test
	public void getReasons() {
	}

	@Test
	public void blockUser() {
	}

	@Test
	public void unlockUser() {
	}

	@Test
	public void changeUserData() {
	}

	@Test
	public void changePassword() {
	}

	@Test
	public void deleteUser() {
	}
}