package by.tc.hostel_system.service.request;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.request.RequestDAO;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.validation.*;
import by.tc.hostel_system.util.ControllerUtil;
import by.tc.hostel_system.util.ServiceUtil;

import java.sql.Date;
import java.util.List;

import static by.tc.hostel_system.dao.SQLQuery.*;

public class RequestServiceImpl implements RequestService {

    /**
     * @see RequestService#getRequests(String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public List<Request> getRequests(String lang, String page) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            Validator.isLanguage(lang);
            Validator.isNumber(page);
            return requestDAO.getRequests(lang, REQUEST_SEARCH_ALL_REQUESTS);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see RequestService#getRequests(String, Object, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public List<Request> getRequests(String lang, Object user, String page) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            Validator.isLanguage(lang);
            UserValidator.isUserRequestData(user, page);
            User newUser = (User) user;
            return requestDAO.getRequests(lang, newUser.getId(), REQUEST_SEARCH_USER_REQUESTS);
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see RequestService#getRequests(String, Object)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public List<Request> getRequests(String lang, Object objUser) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            Validator.isLanguage(lang);
            Validator.isUser(objUser);
            User user = (User) objUser;
            if (user.getStatus() == User.Status.ADMIN) {
                return requestDAO.getRequests(lang, REQUEST_SEARCH_NEW_REQUESTS);
            } else {
                return requestDAO.getRequests(lang, user.getId(), REQUEST_SEARCH_USER_FUTURE_REQUESTS);
            }
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see RequestService#addRequest(Object, String, String, String, String, String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public int addRequest(Object user, String hostelId, String type, String rooms, String days, String date, String cost) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            RequestValidator.isRequestDataValid(user, hostelId, type, rooms, days, date, cost);
            User newUser = (User) user;
            int hostelIdNumber = Integer.parseInt(hostelId);
            int roomsCount = Integer.parseInt(rooms);
            int daysCount = Integer.parseInt(days);
            int costNumber = Integer.parseInt(cost);
            Date beginDate = Date.valueOf(date);
            Request userRequest = ServiceUtil.createRequest(newUser.getId(), hostelIdNumber, type, roomsCount, daysCount, newUser.getDiscount(), beginDate, costNumber);
            int balance = newUser.getBalance();
            RequestValidator.isMoneyEnough(type, balance, userRequest.getCost());
            if (type.equals("booking")) {
                balance += userRequest.getCost();
            }
            return requestDAO.addRequest(userRequest, balance);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (DAOException | NotEnoughMoneyException e) {
            throw new ServiceException(e.getMessage());
        }
    }

	/**
     * @see RequestService#cancelRequest(String, String, String, Object, String, String, String, String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public int cancelRequest(String requestId, String userId, String status, Object user, String page, String start, String days, String rooms, String hostel) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            RequestValidator.isCancelDataValid(requestId, userId, status, user, page, start, days, rooms, hostel);
            User newUser = (User) user;
            Date startDate = Date.valueOf(start);
            int numberOfDays = Integer.parseInt(days);
            int hostelId = Integer.parseInt(hostel);
            Date end = ControllerUtil.getEndDate(numberOfDays, startDate);
            int requestIdNumber = Integer.parseInt(requestId);
            int userIdNumber = Integer.parseInt(userId);
            int roomsNumber = Integer.parseInt(rooms);
            return requestDAO.cancelRequest(requestIdNumber, userIdNumber, status, newUser.getBalance(), startDate, end, roomsNumber, hostelId);
        } catch (EntityNotFoundException e){
            throw new RequestNotFoundException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see RequestService#approveRequest(String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link NotNumberException}) or catch {@link DAOException}
     */
    @Override
    public void approveRequest(String id, String page) throws ServiceException {
        RequestDAO requestDAO = DAOFactory.getInstance().getRequestDAO();

        try {
            Validator.isNumber(id);
            Validator.isNumber(page);
            int idNumber = Integer.parseInt(id);
            requestDAO.approveRequest(idNumber);
        } catch (NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}