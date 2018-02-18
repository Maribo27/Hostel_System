package by.tc.hostel_system.service.validation;

import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.entity.User;

public class Validator {
	private static final String NUMBER = "\\d+";
	private static final String DATE = "[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])";

	enum Language {
		RU,
		EN
	}

	/**
	 * Validates input user for validity.
	 *
	 * @param objUser   user object
	 *
	 * @throws InputException if objUser is not an instance of {@link User}
	 */
	public static void isUser(Object objUser) throws InputException {
		if (objUser == null) {
			throw new InputException("User is null");
		}
		if (!(objUser instanceof User)) {
			throw new InputException("User is invalid");
		}
	}

	/**
	 * Validates language for validity.
	 *
	 * @param lang  current language
	 *
	 * @throws LangNotSupportedException if system not support input language
	 */
	public static void isLanguage(String lang) throws LangNotSupportedException {
		for (Language language : Language.values()) {
			String currentLang = language.name();
			if (lang.equalsIgnoreCase(currentLang)){
				return;
			}
		}
		throw new LangNotSupportedException(String.format("Language (%s) not supported", lang));
	}

	/**
	 * Validates input number for validity.
	 *
	 * @param number    string number
	 *
	 * @throws NotNumberException if input number is not a number or if it negative
	 */
	public static void isNumber(String number) throws NotNumberException {
		if (!number.matches(NUMBER)){
			throw new NotNumberException(String.format("%s - is not a right number", number));
		}
	}

	/**
	 * Validates input date for validity.
	 *
	 * @param date  string date
	 *
	 * @throws NotDateException if input date have invalid format
	 */
	public static void isDate(String date) throws NotDateException {
		if (!date.matches(DATE)){
			throw new NotDateException(String.format("%s - is not a right date", date));
		}
	}

	/**
	 * Validates input type for validity.
	 *
	 * @param type  string type of request
	 *
	 * @throws InputException if input type is not a {@link Hostel.Booking}
	 */
	public static void isRequestType(String type) throws InputException {
		for (Hostel.Booking currentType : Hostel.Booking.values()) {
			String typeName = currentType.name();
			if (type.equalsIgnoreCase(typeName)){
				return;
			}
		}
		throw new InputException("Invalid type");
	}
}