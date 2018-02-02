package by.tc.task31.service.user;

import by.tc.task31.service.ServiceException;

public class UserNotFoundException extends ServiceException {
	private static final long serialVersionUID = -1779481324530814475L;

	UserNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
