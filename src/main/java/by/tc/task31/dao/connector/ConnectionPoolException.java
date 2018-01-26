package by.tc.task31.dao.connector;

public class ConnectionPoolException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConnectionPoolException(String message, Exception e){
		super(message, e);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}
}
