package by.tc.task31.service.validation;

import by.tc.task31.service.ServiceException;

public class InvalidParametersException extends ServiceException {
	private static final long serialVersionUID = 4117036608318016722L;

	public InvalidParametersException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
