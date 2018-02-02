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

    public HostelService getHostelService() {
        return hostelService;
    }

    public RequestService getRequestService() {
        return requestService;
    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
