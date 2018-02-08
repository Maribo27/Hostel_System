package by.tc.hostel_system.dao;

public class EntityExistException extends DAOException {
	private static final long serialVersionUID = 1048159226135245163L;

	public EntityExistException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
