package by.tc.task31.service.impl;

import by.tc.task31.dao.DAO;
import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.DAOFactory;
import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;
import by.tc.task31.service.EntityService;
import by.tc.task31.service.ServiceException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class EntityServiceImpl implements EntityService {

    public User getUserInformation(String lang, String username, String password) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getUserInformation(lang, username, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public User addUserInformation(String username, String password, String name, String lastname, String surname, String email) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.addUser(username, password, name, lastname, surname, email);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public boolean userInDB(String username) throws ServiceException{

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.userInDB(username);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public List<Request> getRequests(String lang) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getRequests(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Request> getRequests(String lang, int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getRequests(lang, id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsers(String lang) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getUsers(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Hostel> getHostels(String lang) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getHostels(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Hostel> getHostels(String lang, int city, int room) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getHostels(lang, city, room);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, String> getReasons(String lang) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getReasons(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, String> getCities(String lang) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            return entityDAO.getCities(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addRequest(Request request) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            entityDAO.addRequest(request);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void changeRequestStatus(int id, String status) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            entityDAO.changeRequestStatus(id, status);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void blockUser(int id, Timestamp date, int reason) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            entityDAO.blockUser(id, date, reason);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void unlockUser(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            entityDAO.unlockUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User changeUserData(String lang, int id, String username, String password, String name, String lastname, String surname, String email) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            entityDAO.changeUserData(id, username, password, name, lastname, surname, email);
            return entityDAO.getUserInformation(lang, username, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            entityDAO.deleteUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteHostel(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();

        try {
            entityDAO.deleteHostel(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}