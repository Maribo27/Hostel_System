package by.tc.task31.service.user;

import by.tc.task31.service.ServiceException;

class ReasonsNotFoundException extends ServiceException {
	private static final long serialVersionUID = 1385334929007576138L;

	ReasonsNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
