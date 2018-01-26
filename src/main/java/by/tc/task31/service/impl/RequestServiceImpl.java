package by.tc.task31.service.impl;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.DAOFactory;
import by.tc.task31.dao.RequestDAO;
import by.tc.task31.entity.Request;
import by.tc.task31.service.RequestService;
import by.tc.task31.service.ServiceException;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    @Override
    public List<Request> getRequests(String lang) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            return requestDAO.getRequests(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Request> getRequests(String lang, int id) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            return requestDAO.getRequests(lang, id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addRequest(Request request) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            requestDAO.addRequest(request);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteRequest(int id) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            requestDAO.deleteRequest(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void changeRequestStatus(int id, String status) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            requestDAO.changeRequestStatus(id, status);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}