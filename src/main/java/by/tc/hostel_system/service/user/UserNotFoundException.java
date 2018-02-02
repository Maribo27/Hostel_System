package by.tc.hostel_system.service.user;

import by.tc.hostel_system.service.ServiceException;

public class UserNotFoundException extends ServiceException {
	private static final long serialVersionUID = -1779481324530814475L;

	UserNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
