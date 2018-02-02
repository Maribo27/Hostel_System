package by.tc.hostel_system.service.validation;

import by.tc.hostel_system.entity.User;

public class RequestValidator {
	public static boolean isRequestDataValid(Object user, String hostelId, String type, String rooms, String days, String start, String cost)
			throws NotNumberException, NotDateException, UserValidator.InputException {
		if (user == null) {
			throw new UserValidator.InputException("User is null");
		}
		if (!(user instanceof User)) {
			throw new UserValidator.InputException("User is invalid");
		}
		User currentUser = (User) user;
		if (currentUser.getId() == 0) {
			throw new UserValidator.InputException("User ID is null");
		}
		boolean hostelIdIsNumber = Validator.isNumber(hostelId);
		boolean roomsIsNumber = Validator.isNumber(rooms);
		boolean daysIsNumber = Validator.isNumber(days);
		boolean startIsDate = Validator.isDate(start);
		boolean costIsNumber = Validator.isNumber(cost);
		boolean validType = Validator.isRequestType(type);
		return hostelIdIsNumber && roomsIsNumber && daysIsNumber && startIsDate && costIsNumber && validType;
	}

	public static boolean isCancelDataValid(String requestId, String userId, String status, Object user, String page) throws NotNumberException, UserValidator.InputException {
		if (user == null) {
			throw new UserValidator.InputException("User is null");
		}
		if (!(user instanceof User)) {
			throw new UserValidator.InputException("User is invalid");
		}
		User currentUser = (User) user;
		if (currentUser.getBalance() < 0) {
			throw new UserValidator.InputException("User balance is invalid");
		}
		boolean userIdIsNumber = Validator.isNumber(userId);
		boolean requestIdIsNumber = Validator.isNumber(requestId);
		boolean validStatus = Validator.isRequestStatus(status);
		boolean pageIsNumber = Validator.isNumber(page);
		return userIdIsNumber && requestIdIsNumber && validStatus && pageIsNumber;
	}

	public static boolean isMoneyEnough(String type, int balance, int cost) throws NotEnoughMoneyException {
		if (type.equals("payment") && balance < cost) {
			throw new NotEnoughMoneyException("Not enough money");
		}
		return true;
	}
}
