package by.tc.task31.dao;

import by.tc.task31.dao.impl.HostelDAOImpl;
import by.tc.task31.dao.impl.RequestDAOImpl;
import by.tc.task31.dao.impl.UserDAOImpl;

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