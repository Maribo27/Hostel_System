package by.tc.hostel_system.service;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 2361632240740168011L;

    public ServiceException(String exceptionMessage){
        super(exceptionMessage);
    }
}
