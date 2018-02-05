package by.tc.hostel_system.service.user;

import by.tc.hostel_system.dao.CurrentEntityExist;
import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.user.UserDAO;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.validation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.tc.hostel_system.controller.constant.EntityAttributes.*;
import static by.tc.hostel_system.dao.SQLQuery.USER_SEARCH_EMAIL;
import static by.tc.hostel_system.dao.SQLQuery.USER_SEARCH_USERNAME;

public class UserServiceImpl implements UserService {
    private static final int DISCOUNT = 5;

    @Override
    public User getUserInformation(String lang, Object user, String password) throws ServiceException {

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            boolean langValid = Validator.isLanguage(lang);
            boolean inputDataValid = UserValidator.isLoginDataValid(user, password);
            User newUser = (User) user;
            return userDAO.getUserInformation(lang, newUser.getUsername(), password);
        } catch (LangNotSupportedException | UserValidator.InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void addUserInformation(String username, String password, String name, String lastname, String surname, String email) throws ServiceException {

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            boolean inputDataValid = UserValidator.isInputDataValid(username, password, name, lastname, surname, email);
            userDAO.addUser(username, password, name, lastname, surname, email);
        } catch (CurrentEntityExist e){
            throw new UserExistException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (UserValidator.InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsers(String lang) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean langValid = Validator.isLanguage(lang);
            return userDAO.getUsers(lang);
        } catch (LangNotSupportedException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, String> getReasons(String lang) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean langValid = Validator.isLanguage(lang);
            return userDAO.getReasons(lang);
        } catch (LangNotSupportedException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new ReasonsNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void blockUser(String id, String date, String reason, String page) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean inputDataValid = UserValidator.isBlockUserDataValid(id, date, reason, page);
            int idNumber = Integer.parseInt(id);
            Date beginDate = Date.valueOf(date);
            int reasonNumber = Integer.parseInt(reason);
            userDAO.blockUser(idNumber, beginDate, reasonNumber);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void unlockUser(String id, String page) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean inputDataValid = Validator.isNumber(id);
            int idNumber = Integer.parseInt(id);
            userDAO.unlockUser(idNumber);
        } catch (NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void changeUserData(User user, String username, String email, String name, String surname, String lastname) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean inputDataValid = UserValidator.isChangeDataValid(user, username, email, name, surname, lastname);
            List<String> dataToUpdate = getDataToChange(user, username, email, name, surname, lastname);
            userDAO.checkUser(username, USER_SEARCH_USERNAME);
	        userDAO.checkUser(email, USER_SEARCH_EMAIL);
            userDAO.changeUserData(user.getId(), dataToUpdate);
        } catch (CurrentEntityExist e) {
        	throw new UserExistException(e.getMessage());
        } catch (UserValidator.InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void changePassword(int id, String password) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean inputDataValid = UserValidator.isChangePasswordDataValid(id, password);
            userDAO.changePassword(id, password);
        } catch (UserValidator.InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean inputDataValid = Validator.checkPositiveNumber(id);
            userDAO.deleteUser(id);
        } catch (NotNumberException e) {
	        throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getUserDiscount(String userId) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean inputDataValid = Validator.isNumber(userId);
            int id = Integer.parseInt(userId);
            int discount = userDAO.getUserDiscount(id);
            return discount;
        } catch (NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void changeUserDiscount(int userId, int userDiscount, String sign, String page) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            boolean inputDataValid = UserValidator.checkDiscount(userId, userDiscount, sign, page);
            sign = sign.replace("plus", "");
            sign = sign.replace("minus", "-");
            int newDiscount = Integer.parseInt(sign + DISCOUNT);
            int discount = newDiscount + userDiscount;
            if (discount > 50 || discount < 0) {
                throw new InvalidParametersException("Cannot change discount");
            }
            userDAO.changeUserDiscount(userId, discount);
        } catch (NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private List<String> getDataToChange(User newUser, String username, String email, String name, String surname, String lastname) {
        List<String> data = new ArrayList<>();

        if (!username.equalsIgnoreCase(newUser.getUsername())){
            data.add(USERNAME + "=" + username);
            newUser.setUsername(username);
        }

        if (!email.equalsIgnoreCase(newUser.getEmail())){
            data.add(EMAIL + "=" + email);
            newUser.setEmail(email);
        }

        if (!name.equalsIgnoreCase(newUser.getName())){
            data.add(NAME + "=" + name);
            newUser.setName(name);
        }

        if (!surname.equalsIgnoreCase(newUser.getSurname())){
            data.add(SURNAME + "=" + surname);
            newUser.setSurname(surname);
        }

        if (!lastname.equalsIgnoreCase(newUser.getLastname())){
            data.add(LASTNAME + "=" + lastname);
            newUser.setLastname(lastname);
        }
        return data;
    }
}