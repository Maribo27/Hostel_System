package by.tc.hostel_system.service.validation;

public class LangNotSupportedException extends InputException {
	private static final long serialVersionUID = -4665782529370786267L;

	LangNotSupportedException(String message) {
		super(message);
	}
}
