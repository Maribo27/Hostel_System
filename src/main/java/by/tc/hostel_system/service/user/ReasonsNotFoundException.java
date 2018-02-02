package by.tc.hostel_system.service.user;

import by.tc.hostel_system.service.ServiceException;

class ReasonsNotFoundException extends ServiceException {
	private static final long serialVersionUID = 1385334929007576138L;

	ReasonsNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
