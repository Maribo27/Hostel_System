package by.tc.hostel_system.service.validation;

import static by.tc.hostel_system.service.validation.Validator.isRequestType;

public class HostelValidator {
	public static boolean isSearchDataValid(String city, String room, String start, String days, String page, String type) throws NotNumberException, NotDateException, UserValidator.InputException {
		boolean cityIsNumber = Validator.isNumber(city);
		boolean roomIsNumber = Validator.isNumber(room);
		boolean validType = isRequestType(type);
		boolean pageIsNumber = Validator.isNumber(page);
		boolean daysIsNumber = Validator.isNumber(days);
		boolean startIsDate = Validator.isDate(start);
		return cityIsNumber && roomIsNumber && startIsDate && pageIsNumber && daysIsNumber && validType;
	}
}