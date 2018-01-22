package by.tc.task31.service;

import by.tc.task31.service.impl.HostelServiceImpl;
import by.tc.task31.service.impl.RequestServiceImpl;
import by.tc.task31.service.impl.UserServiceImpl;

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
