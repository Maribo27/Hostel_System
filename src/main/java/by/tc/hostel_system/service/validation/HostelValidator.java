package by.tc.hostel_system.service.validation;

import static by.tc.hostel_system.service.validation.Validator.isRequestType;

public class HostelValidator {
	public static boolean isSearchDataValid(String city, String room, String start, String days, String page, String type) throws NotNumberException, NotDateException, UserValidator.InputException {
		boolean validCity = Validator.isNumber(city);
		boolean validRoom = Validator.isNumber(room);
		boolean validType = isRequestType(type);
		boolean validPage = Validator.isNumber(page);
		boolean validNumber = Validator.isNumber(days);
		boolean validDate = Validator.isDate(start);
		return validCity && validRoom && validDate && validPage && validNumber && validType;
	}
}