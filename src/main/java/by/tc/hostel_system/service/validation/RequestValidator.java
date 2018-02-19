package by.tc.hostel_system.service.validation;

import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.User;

public class RequestValidator {
	/**
	 * Validates input request data for validity.
	 *
	 * @param user      current user
	 * @param hostelId  hostel id
	 * @param type      request type
	 * @param rooms     number of rooms
	 * @param days      number of days
	 * @param start     date of booking start
	 * @param cost      room cost
	 *
	 * @throws InputException if input data is incorrect
	 */
	public static void isRequestDataValid(Object user, String hostelId, String type, String rooms, String days, String start, String cost)
			throws InputException {
		Validator.isUser(user);
		User currentUser = (User) user;
		if (currentUser.getId() == 0) {
			throw new InputException("User ID is null");
		}
		Validator.isNumber(hostelId);
		Validator.isNumber(rooms);
		Validator.isNumber(days);
		Validator.isDate(start);
		Validator.isNumber(cost);
		Validator.isRequestType(type);
	}

	/**
	 * Validates input data for request canceling for validity.
	 *
	 * @param requestId request id
	 * @param userId    user id
	 * @param status    new request status
	 * @param user      current user
	 * @param page      page number
	 * @param date      booking date
	 * @param days      number of days
	 *
	 * @throws InputException if input data is incorrect
	 */
	public static void isCancelDataValid(String requestId, String userId, String status, Object user, String page, String date, String days, String rooms, String hostelId) throws InputException {
		Validator.isUser(user);
		User currentUser = (User) user;
		if (currentUser.getBalance() < 0) {
			throw new InputException("User balance is invalid");
		}
		Validator.isDate(date);
		Validator.isNumber(rooms);
		Validator.isNumber(hostelId);
		Validator.isNumber(days);
		Validator.isNumber(userId);
		Validator.isNumber(requestId);
		Validator.isNumber(page);
		isRequestStatus(status);
	}

	/**
	 * Compares current {@link User#balance} and the full cost from request.
	 *
	 * @param type      request type
	 * @param balance   user balance
	 * @param cost      request cost
	 *
	 * @throws NotEnoughMoneyException if user has not enough money
	 */
	public static void isMoneyEnough(String type, int balance, int cost) throws NotEnoughMoneyException {
		if (type.equals("payment") && balance < cost) {
			throw new NotEnoughMoneyException("Not enough money");
		}
	}

	/**
	 * Validates input status for validity.
	 *
	 * @param status    request type
	 *
	 * @throws InputException if input status is not valid status
	 */
	static void isRequestStatus(String status) throws InputException {
		for (Request.Status currentStatus : Request.Status.values()) {
			String statusName = currentStatus.name();
			if (status.equalsIgnoreCase(statusName)){
				return;
			}
		}
		throw new InputException("Invalid status");
	}
}
