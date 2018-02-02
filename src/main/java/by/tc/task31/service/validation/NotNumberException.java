package by.tc.task31.service.validation;

public class NotNumberException extends InvalidParametersException {
	private static final long serialVersionUID = -6775686214466297123L;

	NotNumberException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
