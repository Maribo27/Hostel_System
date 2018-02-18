package by.tc.hostel_system.service;

import by.tc.hostel_system.service.hostel.HostelService;
import by.tc.hostel_system.service.hostel.HostelServiceImpl;
import by.tc.hostel_system.service.request.RequestService;
import by.tc.hostel_system.service.request.RequestServiceImpl;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.service.user.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final HostelService hostelService = new HostelServiceImpl();
    private final RequestService requestService = new RequestServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {}

    /**
     * Returns Hostel Service implementations.
     *
     * @return Hostel Service
     */
    public HostelService getHostelService() {
        return hostelService;
    }

    /**
     * Returns Request Service implementations.
     *
     * @return Request Service
     */
    public RequestService getRequestService() {
        return requestService;
    }

    /**
     * Returns User Service implementations.
     *
     * @return User Service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Returns Service Factory instance.
     *
     * @return instance of ServiceFactory
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

}
