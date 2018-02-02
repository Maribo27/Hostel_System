package by.tc.task31.service.request;

import by.tc.task31.service.ServiceException;

public class RequestNotFoundException extends ServiceException {
	private static final long serialVersionUID = -2684212233856343071L;

	RequestNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
