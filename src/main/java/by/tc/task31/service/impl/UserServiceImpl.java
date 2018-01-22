package by.tc.task31.service.impl;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.DAOFactory;
import by.tc.task31.dao.UserDAO;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.UserService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserInformation(String lang, String username, String password) throws ServiceException {

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            return userDAO.getUserInformation(lang, username, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User addUserInformation(String username, String password, String name, String lastname, String surname, String email) throws ServiceException {

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            return userDAO.addUser(username, password, name, lastname, surname, email);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public boolean userInDB(String username) throws ServiceException{

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            return userDAO.userInDB(username);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public List<User> getUsers(String lang) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            return userDAO.getUsers(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, String> getReasons(String lang) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            return userDAO.getReasons(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void blockUser(int id, Date date, int reason) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            userDAO.blockUser(id, date, reason);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void unlockUser(int id) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            userDAO.unlockUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User changeUserData(String lang, int id, String username, String password, String name, String lastname, String surname, String email) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            userDAO.changeUserData(id, username, password, name, lastname, surname, email);
            return userDAO.getUserInformation(lang, username, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            userDAO.deleteUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}