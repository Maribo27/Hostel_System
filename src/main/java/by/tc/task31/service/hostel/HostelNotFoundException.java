package by.tc.task31.service.hostel;

import by.tc.task31.service.ServiceException;

public class HostelNotFoundException extends ServiceException {
	private static final long serialVersionUID = 4117036608318016722L;

	HostelNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
