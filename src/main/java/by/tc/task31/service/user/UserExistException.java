package by.tc.task31.service.user;

import by.tc.task31.service.ServiceException;

public class UserExistException extends ServiceException {
	private static final long serialVersionUID = -1779481324530814475L;

	public UserExistException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
