package by.tc.hostel_system.dao;

import by.tc.hostel_system.dao.hostel.HostelDAO;
import by.tc.hostel_system.dao.hostel.HostelDAOImpl;
import by.tc.hostel_system.dao.request.RequestDAO;
import by.tc.hostel_system.dao.request.RequestDAOImpl;
import by.tc.hostel_system.dao.user.UserDAO;
import by.tc.hostel_system.dao.user.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final HostelDAO hostelDAO = new HostelDAOImpl();
    private final RequestDAO requestDAO = new RequestDAOImpl();

    private DAOFactory() {}

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public HostelDAO getHostelDAO() {
        return hostelDAO;
    }

    public RequestDAO getRequestDAO() {
        return requestDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}