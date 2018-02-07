package by.tc.hostel_system.service.validation;

import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.entity.Request;

public class Validator {
	private static final String NUMBER = "\\d+";
	private static final String SIGN = "plus|minus";
	private static final String DATE = "[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])";
	private static final String USERNAME_REGEX = "^[\\w][\\w._\\d]{4,20}$";
	private static final String PASSWORD_REGEX = "^[\\w\\d._]{5,12}$";
	private static final String NAME_REGEX = "^[A-ZА-Я][a-zа-я]{1,50}$";
	private static final String EMAIL_REGEX = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";
	private static final String INCORRECT_USERNAME = "Incorrect username";
	private static final String INCORRECT_PASSWORD = "Incorrect password";


	public static boolean checkPositiveNumber(int id) throws NotNumberException {
		if (id <= 0) {
			throw new NotNumberException(String.format("%d - is not a right number", id));
		}
		return true;
	}

	static boolean checkNumber(int id) throws NotNumberException {
		if (id < 0) {
			throw new NotNumberException(String.format("%d - is not a right number", id));
		}
		return true;
	}

	enum Language {
		RU,
		EN
	}

	public static boolean isLanguage(String lang) throws LangNotSupportedException {
		for (Language language : Language.values()) {
			String currentLang = language.name();
			if (lang.equalsIgnoreCase(currentLang)){
				return true;
			}
		}
		throw new LangNotSupportedException(String.format("Language (%s) not supported", lang));
	}

	public static boolean isNumber(String number) throws NotNumberException {
		if (!number.matches(NUMBER)){
			throw new NotNumberException(String.format("%s - is not a right number", number));
		}
		return true;
	}

	static boolean isSign(String sign) throws NotNumberException {
		if (!sign.matches(SIGN)){
			throw new NotNumberException(String.format("%s - is not a sign", sign));
		}
		return true;
	}

	public static boolean isDate(String date) throws NotDateException {
		if (!date.matches(DATE)){
			throw new NotDateException(String.format("%s - is not a right date", date));
		}
		return true;
	}

	static boolean isPassword(String password) throws UserValidator.InputException {
		boolean validPassword = password == null || !password.matches(PASSWORD_REGEX);
		if (validPassword) {
			throw new UserValidator.InputException(INCORRECT_PASSWORD);
		}
		return true;
	}

	static boolean isEmail(String email) throws UserValidator.InputException {
		boolean incorrectEmail = email == null || !email.matches(EMAIL_REGEX);
		if (incorrectEmail) {
			throw new UserValidator.InputException("Incorrect email");
		}
		return true;
	}

	static boolean isName(String name, String type) throws UserValidator.InputException {
		boolean incorrectName = name == null || !name.matches(NAME_REGEX);
		if (incorrectName) {
			throw new UserValidator.InputException("Incorrect" + type);
		}
		return true;
	}

	static boolean isUsername(String username) throws UserValidator.InputException {
		boolean incorrectUsername = username == null || !username.matches(USERNAME_REGEX);
		if (incorrectUsername) {
			throw new UserValidator.InputException(INCORRECT_USERNAME);
		}
		return true;
	}

	public static boolean isRequestType(String type) throws UserValidator.InputException {
		for (Hostel.Booking currentType : Hostel.Booking.values()) {
			String typeName = currentType.name();
			if (type.equalsIgnoreCase(typeName)){
				return true;
			}
		}
		throw new UserValidator.InputException("Invalid type");
	}

	public static boolean isRequestStatus(String status) throws UserValidator.InputException {
		for (Request.Status currentStatus : Request.Status.values()) {
			String statusName = currentStatus.name();
			if (status.equalsIgnoreCase(statusName)){
				return true;
			}
		}
		throw new UserValidator.InputException("Invalid status");
	}
}