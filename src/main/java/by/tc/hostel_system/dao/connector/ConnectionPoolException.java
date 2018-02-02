package by.tc.hostel_system.dao.connector;

public class ConnectionPoolException extends RuntimeException {
	private static final long serialVersionUID = 1565264625000631981L;

	public ConnectionPoolException(String message, Exception e){
		super(message, e);
	}

	ConnectionPoolException(String message) {
		super(message);
	}
}