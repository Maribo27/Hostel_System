package by.tc.hostel_system.service.hostel;

import by.tc.hostel_system.service.ServiceException;

public class HostelNotFoundException extends ServiceException {
	private static final long serialVersionUID = 4117036608318016722L;

	HostelNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
