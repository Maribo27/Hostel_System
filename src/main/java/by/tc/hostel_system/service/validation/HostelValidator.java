package by.tc.hostel_system.service.validation;

public class HostelValidator {
	/**
	 * Validates input data for validity.
	 *
	 * @param city  city id
	 * @param room  room number
	 * @param start date of booking start
	 * @param days  number of days
	 * @param page  page number
	 * @param type  request type
	 *
	 * @throws InputException if input data is incorrect
	 */
	public static void isSearchDataValid(String city, String room, String start, String days, String page, String type) throws InputException {
		Validator.isNumber(city);
		Validator.isNumber(room);
		Validator.isRequestType(type);
		Validator.isNumber(page);
		Validator.isNumber(days);
		Validator.isDate(start);
	}
}