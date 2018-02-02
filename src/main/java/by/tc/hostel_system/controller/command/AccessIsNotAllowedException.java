package by.tc.hostel_system.controller.command;

public class AccessIsNotAllowedException extends Exception {
	private static final long serialVersionUID = -2105804762294629669L;

	AccessIsNotAllowedException(String message) {
		super(message);
	}
}
