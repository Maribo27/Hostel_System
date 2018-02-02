package by.tc.task31.service.request;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.DAOFactory;
import by.tc.task31.dao.EntityNotFoundException;
import by.tc.task31.dao.request.RequestDAO;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.validation.*;
import by.tc.task31.util.ServiceUtil;

import java.sql.Date;
import java.util.List;

public class RequestServiceImpl implements RequestService {

    @Override
    public List<Request> getRequests(String lang, String page) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            boolean langValid = Validator.isLanguage(lang);
            boolean pageValid = Validator.isNumber(page);
            return requestDAO.getRequests(lang);
        } catch (LangNotSupportedException | NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Request> getRequests(String lang, Object user, String id) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            boolean langValid = Validator.isLanguage(lang);
            boolean inputDataValid = Validator.isNumber(id);
            int requestId = Integer.parseInt(id);
            return requestDAO.getRequests(lang, requestId);
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (LangNotSupportedException | NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int addRequest(Object user, String hostelId, String type, String rooms, String days, String date, String cost) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            boolean inputDataValid = RequestValidator.isRequestDataValid(user, hostelId, type, rooms, days, date, cost);
            User newUser = (User) user;
            int hostelIdNumber = Integer.parseInt(hostelId);
            int roomsCount = Integer.parseInt(rooms);
            int daysCount = Integer.parseInt(days);
            int costNumber = Integer.parseInt(cost);
            Date beginDate = Date.valueOf(date);
            Request userRequest = ServiceUtil.createRequest(newUser.getId(), hostelIdNumber, type, roomsCount, daysCount, newUser.getDiscount(), beginDate, costNumber);
            int balance = newUser.getBalance();
            boolean moneyEnough = RequestValidator.isMoneyEnough(type, balance, userRequest.getCost());
            if (type.equals("booking")) {
                balance += userRequest.getCost();
            }
            return requestDAO.addRequest(userRequest, balance);
        } catch (NotNumberException | NotDateException | UserValidator.InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (DAOException | NotEnoughMoneyException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int cancelRequest(String requestId, String userId, String status, Object user, String page) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            boolean inputDataValid = RequestValidator.isCancelDataValid(requestId, userId, status, user, page);
            User newUser = (User) user;
            int requestIdNumber = Integer.parseInt(requestId);
            int userIdNumber = Integer.parseInt(userId);
            return requestDAO.cancelRequest(requestIdNumber, userIdNumber, status, newUser.getBalance());
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (NotNumberException | UserValidator.InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void approveRequest(String id, String page) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            boolean inputDataValid = Validator.isNumber(id);
            boolean pageValid = Validator.isNumber(page);
            int idNumber = Integer.parseInt(id);
            requestDAO.approveRequest(idNumber);
        } catch (NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}