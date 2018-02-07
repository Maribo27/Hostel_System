package by.tc.hostel_system.service.validation;

import by.tc.hostel_system.entity.User;

import static by.tc.hostel_system.controller.constant.EntityAttributes.*;
import static by.tc.hostel_system.service.validation.Validator.*;

public class UserValidator {

	public static boolean isLoginDataValid(Object user, String password) throws InputException {
		if (user == null) {
			throw new InputException("User is null");
		}
		if (!(user instanceof User)) {
			throw new InputException("User is invalid");
		}
		User currentUser = (User) user;
		String username = currentUser.getPersonalInfo().getUsername();
		boolean validUsername = isUsername(username);
		boolean validPassword = isPassword(password);
		return validUsername && validPassword;
	}

	public static boolean isInputDataValid(String username, String password, String name, String lastname, String surname, String email)
			throws InputException {
		boolean validUsername = isUsername(username);
		boolean validPassword = isPassword(password);
		boolean validName = isName(name, NAME);
		boolean validLastname = lastname.isEmpty() || isName(lastname, LAST_NAME);
		boolean validSurname = isName(surname, SURNAME);
		boolean validEmail = isEmail(email);
		return validUsername && validPassword && validName && validLastname && validSurname && validEmail;
	}

	public static boolean isBlockUserDataValid(String id, String reason, String page) throws NotNumberException {
		boolean validId = Validator.isNumber(id);
		boolean validReason = Validator.isNumber(reason);
		boolean validPage = Validator.isNumber(page);
		return validId && validReason && validPage;
	}

	public static boolean isChangeDataValid(User user, String username, String email, String name, String surname, String lastname)
			throws InputException {
		if (user == null) {
			throw new InputException("User is null");
		}
		if (user.getId() == 0) {
			throw new InputException("User ID is null");
		}
		boolean validUsername = isUsername(username);
		boolean validName = isName(name, NAME);
		boolean validLastname = lastname.isEmpty() || isName(lastname, LAST_NAME);
		boolean validSurname = isName(surname, SURNAME);
		boolean validEmail = isEmail(email);
		return validEmail && validLastname && validName && validSurname && validUsername;
	}

	public static boolean isChangePasswordDataValid(int id, String password) throws InputException {
		if (id == 0) {
			throw new InputException("User ID is null");
		}
		return isPassword(password);
	}

	public static boolean isUserRequestData(Object user, String page) throws InputException, NotNumberException {
		if (user == null) {
			throw new InputException("User is null");
		}
		if (!(user instanceof User)) {
			throw new InputException("User is invalid");
		}
		User currentUser = (User) user;
		int id = currentUser.getId();
		boolean validId = checkNumber(id);
		boolean validPage = isNumber(page);
		return validId && validPage;
	}

	public static class InputException extends Exception {
		private static final long serialVersionUID = 1235667138851293559L;

		InputException(String message) {
			super(message);
		}
	}

	public static boolean checkDiscount(int userId, int userDiscount, String sign, String page) throws NotNumberException {
		boolean validId = checkPositiveNumber(userId);
		boolean validDiscount = checkNumber(userDiscount);
		boolean validSign = isSign(sign);
		boolean validPage = isNumber(page);
		return validId && validDiscount && validPage && validSign;
	}
}