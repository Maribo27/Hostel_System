package by.tc.hostel_system.tag;

class EntityTagException extends RuntimeException {
	private static final long serialVersionUID = 6226872062306087935L;

	EntityTagException(String message, Throwable e) {
		super(message, e);
	}
}