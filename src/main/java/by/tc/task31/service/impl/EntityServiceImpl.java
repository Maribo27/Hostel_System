package by.tc.task31.service.impl;

import by.tc.task31.dao.DAO;
import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.DAOFactory;
import by.tc.task31.entity.User;
import by.tc.task31.service.EntityService;
import by.tc.task31.service.ServiceException;

public class EntityServiceImpl implements EntityService {

    public User getUserInformation(String username, String password) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            User user = entityDAO.getUserInformation(username, password);
            return user;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public User addUserInformation(String username, String password, String name, String email) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            User user = entityDAO.addUserInformation(username, password, name, email);
            return user;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public boolean userInDB(String username) throws ServiceException{

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            boolean userExist = entityDAO.userInDB(username);
            return userExist;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }
}