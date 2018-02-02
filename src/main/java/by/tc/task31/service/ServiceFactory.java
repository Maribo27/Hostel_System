package by.tc.task31.service;

import by.tc.task31.service.hostel.HostelService;
import by.tc.task31.service.hostel.HostelServiceImpl;
import by.tc.task31.service.request.RequestService;
import by.tc.task31.service.request.RequestServiceImpl;
import by.tc.task31.service.user.UserService;
import by.tc.task31.service.user.UserServiceImpl;

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
