package by.tc.hostel_system.service.user;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.EntityExistException;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.user.UserDAO;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.validation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.tc.hostel_system.controller.constant.EntityAttributes.*;
import static by.tc.hostel_system.dao.SQLQuery.USER_SEARCH_EMAIL;
import static by.tc.hostel_system.dao.SQLQuery.USER_SEARCH_USERNAME;

public class UserServiceImpl implements UserService {
    private static final int DISCOUNT = 5;

    /**
     * @see UserService#getUserInformation(String, Object, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public User getUserInformation(String lang, Object user, String password) throws ServiceException {

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            Validator.isLanguage(lang);
            UserValidator.isLoginDataValid(user, password);
            User newUser = (User) user;
            return userDAO.getUserInformation(lang, newUser.getPersonalInfo().getUsername(), password);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#addUserInformation(String, String, String, String, String, String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}), nothing found ({@link EntityNotFoundException}),
     * current entity exist ({@link EntityExistException}) or catch {@link DAOException}
     */
    @Override
    public void addUserInformation(String lang, String username, String password, String name, String lastName, String surname, String email) throws ServiceException {

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            Validator.isLanguage(lang);
            UserValidator.isInputDataValid(username, password, name, lastName, surname, email);
            userDAO.addUser(lang, username, password, name, lastName, surname, email);
        } catch (EntityExistException e){
            throw new UserExistException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    /**
     * @see UserService#getUsers(String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link LangNotSupportedException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public List<User> getUsers(String lang) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            Validator.isLanguage(lang);
            return userDAO.getUsers(lang);
        } catch (LangNotSupportedException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#getReasons(String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link LangNotSupportedException}) or catch {@link DAOException}
     */
    @Override
    public Map<Integer, String> getReasons(String lang) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            Validator.isLanguage(lang);
            return userDAO.getReasons(lang);
        } catch (LangNotSupportedException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#blockUser(String, String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link NotNumberException}) or catch {@link DAOException}
     */
    @Override
    public void blockUser(String id, String reason, String page) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            UserValidator.isBlockUserDataValid(id, reason, page);
            int idNumber = Integer.parseInt(id);
            int reasonNumber = Integer.parseInt(reason);
            userDAO.blockUser(idNumber, reasonNumber);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } catch (NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        }
    }

    /**
     * @see UserService#unlockUser(String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link NotNumberException}) or catch {@link DAOException}
     */
    @Override
    public void unlockUser(String id, String page) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            Validator.isNumber(id);
            int idNumber = Integer.parseInt(id);
            userDAO.unlockUser(idNumber);
        } catch (NotNumberException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#changeUserData(String, User, String, String, String, String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}),
     * nothing found ({@link EntityNotFoundException}) or catch {@link DAOException}
     */
    @Override
    public void changeUserData(String lang, User user, String username, String email, String name, String surname, String lastName) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            Validator.isLanguage(lang);
            UserValidator.isChangeDataValid(user, username, email, name, surname, lastName);
            List<String> dataToUpdate = getDataToChange(user.getPersonalInfo(), username, email, name, surname, lastName);
            final boolean usernameChanged = !user.getPersonalInfo().getUsername().equalsIgnoreCase(username);
            if (usernameChanged) {
                userDAO.checkUser(lang, username, USER_SEARCH_USERNAME);
            }
            final boolean emailChanged = !user.getPersonalInfo().getEmail().equalsIgnoreCase(email);
            if (emailChanged) {
                userDAO.checkUser(lang, email, USER_SEARCH_EMAIL);
            }
            userDAO.changeUserData(user.getId(), dataToUpdate);
        } catch (EntityExistException e) {
        	throw new UserExistException(e.getMessage());
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#changePassword(int, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}) or catch {@link DAOException}
     */
    @Override
    public void changePassword(int id, String password) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            UserValidator.isChangePasswordDataValid(id, password);
            userDAO.changePassword(id, password);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#deleteUser(int)
     *
     * @throws ServiceException if input data is incorrect (catch {@link NotNumberException}) or catch {@link DAOException}
     */
    @Override
    public void deleteUser(int id) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            UserValidator.checkPositiveNumber(id);
            userDAO.deleteUser(id);
        } catch (NotNumberException e) {
	        throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#getUserDiscount(String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link InputException}) or catch {@link DAOException}
     */
    @Override
    public int getUserDiscount(String lang, String userId) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            Validator.isLanguage(lang);
            Validator.isNumber(userId);
            int id = Integer.parseInt(userId);
            return userDAO.getUserDiscount(lang, id);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @see UserService#changeUserDiscount(int, int, String, String)
     *
     * @throws ServiceException if input data is incorrect (catch {@link NotNumberException}) or catch {@link DAOException}
     */
    @Override
    public void changeUserDiscount(int userId, int userDiscount, String sign, String page) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            UserValidator.checkDiscount(userId, userDiscount, sign, page);
            sign = sign.replace("plus", "");
            sign = sign.replace("minus", "-");
            int newDiscount = Integer.parseInt(sign + DISCOUNT);
            int discount = newDiscount + userDiscount;
            final int maxDiscount = 50;
            final int minDiscount = 0;
            if (discount > maxDiscount || discount < minDiscount) {
                throw new InvalidParametersException("Cannot change discount");
            }
            userDAO.changeUserDiscount(userId, discount);
        } catch (InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Creates list of user data to change.
     *
     * @param person    user personal information
     * @param username  user username
     * @param email     user email
     * @param name      user name
     * @param surname   user surname
     * @param lastName  user last name
     *
     * @return {@link List} of data to change
     */
    private List<String> getDataToChange(User.PersonalInfo person, String username, String email, String name, String surname, String lastName) {
        List<String> data = new ArrayList<>();

        if (!username.equalsIgnoreCase(person.getUsername())){
            data.add(USERNAME + "='" + username + "'");
            person.setUsername(username);
        }

        if (!email.equalsIgnoreCase(person.getEmail())){
            data.add(EMAIL + "='" + email + "'");
            person.setEmail(email);
        }

        if (!name.equalsIgnoreCase(person.getName())){
            data.add(NAME + "='" + name + "'");
            person.setName(name);
        }

        if (!surname.equalsIgnoreCase(person.getSurname())){
            data.add(SURNAME + "='" + surname + "'");
            person.setSurname(surname);
        }

        if (!lastName.equalsIgnoreCase(person.getLastName())){
            data.add("lastname='" + lastName + "'");
            person.setLastName(lastName);
        }
        return data;
    }
}