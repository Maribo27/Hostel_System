package by.tc.task31.service.validation;

import by.tc.task31.service.ServiceException;

public class NotEnoughMoneyException extends ServiceException {
	private static final long serialVersionUID = 4779414039021732866L;

	NotEnoughMoneyException(String message) {
		super(message);
	}
}
