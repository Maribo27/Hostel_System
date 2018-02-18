package by.tc.hostel_system.service.validation;

public class InputException extends Exception {
	private static final long serialVersionUID = 1235667138851293559L;

	InputException(String message) {
		super(message);
	}
}