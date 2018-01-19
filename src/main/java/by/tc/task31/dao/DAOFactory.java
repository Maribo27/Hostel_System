package by.tc.task31.dao;

import by.tc.task31.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DAO entityDAO = new UserDAOImpl();

    private DAOFactory() {}

    public DAO getEntityDAO() {
        return entityDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}