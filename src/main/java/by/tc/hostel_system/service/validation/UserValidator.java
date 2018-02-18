package by.tc.hostel_system.service.validation;

import by.tc.hostel_system.entity.User;

import static by.tc.hostel_system.controller.constant.EntityAttributes.*;
import static by.tc.hostel_system.service.validation.Validator.isNumber;

public class UserValidator {
	private static final String SIGN = "plus|minus";
	private static final String USERNAME_REGEX = "^[\\w][\\w._\\d]{4,20}$";
	private static final String PASSWORD_REGEX = "^[\\w\\d._]{5,12}$";
	private static final String NAME_REGEX = "^[A-ZА-Я][a-zа-я]{1,50}$";
	private static final String EMAIL_REGEX = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";
	private static final String INCORRECT_USERNAME = "Incorrect username";
	private static final String INCORRECT_PASSWORD = "Incorrect password";

	/**
	 * Validates login data for validity.
	 *
	 * @param user      current user
	 * @param password  current password
	 *
	 * @throws InputException if login data is incorrect
	 */
	public static void isLoginDataValid(Object user, String password) throws InputException {
		Validator.isUser(user);
		User currentUser = (User) user;
		String username = currentUser.getPersonalInfo().getUsername();
		isUsername(username);
		isPassword(password);
	}

	/**
	 * Validates input data for validity.
	 *
	 * @param username  user username
	 * @param password  user password
	 * @param name      user name
	 * @param lastName  user last name
	 * @param surname   user surname
	 * @param email     user email
	 *
	 * @throws InputException if input data is incorrect
	 */
	public static void isInputDataValid(String username, String password, String name, String lastName, String surname, String email)
			throws InputException {
		isUsername(username);
		isPassword(password);
		isName(name, NAME);
		if (!lastName.isEmpty()) {
			isName(lastName, LAST_NAME);
		}
		isName(surname, SURNAME);
		isEmail(email);
	}

	/**
	 * Validates blocked user data for validity.
	 *
	 * @param id        user id
	 * @param reason    blocking reason
	 * @param page      page number
	 *
	 * @throws NotNumberException if blocked user data is incorrect
	 */
	public static void isBlockUserDataValid(String id, String reason, String page) throws NotNumberException {
		Validator.isNumber(id);
		Validator.isNumber(reason);
		Validator.isNumber(page);
	}

	/**
	 * Validates data to change for validity.
	 *
	 * @param user      current user
	 * @param username  new username
	 * @param email     new email
	 * @param name      new name
	 * @param surname   new surname
	 * @param lastName  new last name
	 *
	 * @throws InputException if data to change is incorrect
	 */
	public static void isChangeDataValid(User user, String username, String email, String name, String surname, String lastName)
			throws InputException {
		if (user == null) {
			throw new InputException("User is null");
		}
		if (user.getId() == 0) {
			throw new InputException("User ID is null");
		}
		isUsername(username);
		isName(name, NAME);
		if (!lastName.isEmpty()) {
			isName(lastName, LAST_NAME);
		}
		isName(surname, SURNAME);
		isEmail(email);
	}

	/**
	 * Validates input password for validity.
	 *
	 * @param id        user id
	 * @param password  new password
	 *
	 * @throws InputException if input password is incorrect
	 */
	public static void isChangePasswordDataValid(int id, String password) throws InputException {
		if (id == 0) {
			throw new InputException("User ID is null");
		}
		isPassword(password);
	}

	/**
	 * Validates user data and page for validity.
	 *
	 * @param user  current user
	 * @param page  page number
	 *
	 * @throws InputException if user data or page is incorrect
	 */
	public static void isUserRequestData(Object user, String page) throws InputException {
		Validator.isUser(user);
		User currentUser = (User) user;
		int id = currentUser.getId();
		checkNumber(id);
		isNumber(page);
	}

	/**
	 * Check input data for changing {@link User#discount} for validity.
	 *
	 * @param userId        user id
	 * @param userDiscount  discount
	 * @param sign          sign of coefficient ("+" for increasing, "-" - otherwise)
	 * @param page          page number
	 *
	 * @throws NotNumberException if input data is incorrect
	 */
	public static void checkDiscount(int userId, int userDiscount, String sign, String page) throws InputException {
		checkPositiveNumber(userId);
		checkNumber(userDiscount);
		isSign(sign);
		isNumber(page);
	}

	/**
	 * Validates id for validity.
	 *
	 * @param id input id
	 *
	 * @throws NotNumberException if input id is not a positive number
	 */
	public static void checkPositiveNumber(int id) throws NotNumberException {
		if (id <= 0) {
			throw new NotNumberException(String.format("%d - is not a right number", id));
		}
	}

	/**
	 * Validates input number for validity.
	 *
	 * @param id    input id
	 *
	 * @throws NotNumberException if input id is a negative number
	 */
	public static void checkNumber(int id) throws NotNumberException {
		if (id < 0) {
			throw new NotNumberException(String.format("%d - is not a right number", id));
		}
	}

	/**
	 * Validates input sign for validity.
	 *
	 * @param sign  sign name
	 *
	 * @throws InputException if input sign is not a sign
	 */
	public static void isSign(String sign) throws InputException {
		if (!sign.matches(SIGN)){
			throw new InputException(String.format("%s - is not a sign", sign));
		}
	}

	/**
	 * Validates input password for validity.
	 *
	 * @param password  input password
	 *
	 * @throws InputException if input password is incorrect
	 */
	public static void isPassword(String password) throws InputException {
		boolean validPassword = password == null || !password.matches(PASSWORD_REGEX);
		if (validPassword) {
			throw new InputException(INCORRECT_PASSWORD);
		}
	}

	/**
	 * Validates input email for validity.
	 *
	 * @param email input email
	 *
	 * @throws InputException if input email is incorrect
	 */
	static void isEmail(String email) throws InputException {
		boolean incorrectEmail = email == null || !email.matches(EMAIL_REGEX);
		if (incorrectEmail) {
			throw new InputException("Incorrect email");
		}
	}

	/**
	 * Validates input name for validity.
	 *
	 * @param name  input name
	 * @param type  type of name ("name", "surname", "last name")
	 *
	 * @throws InputException if input name is incorrect
	 */
	static void isName(String name, String type) throws InputException {
		boolean incorrectName = name == null || !name.matches(NAME_REGEX);
		if (incorrectName) {
			throw new InputException("Incorrect" + type);
		}
	}

	/**
	 * Validates input username for validity.
	 *
	 * @param username  inpur username
	 *
	 * @throws InputException if input username is incorrect
	 */
	static void isUsername(String username) throws InputException {
		boolean incorrectUsername = username == null || !username.matches(USERNAME_REGEX);
		if (incorrectUsername) {
			throw new InputException(INCORRECT_USERNAME);
		}
	}
}