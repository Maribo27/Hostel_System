package by.tc.task31.service;

import by.tc.task31.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserInformation(String lang, String username, String password) throws ServiceException;
    User addUserInformation(String username, String password, String name, String lastname, String surname, String email) throws ServiceException;
    boolean userInDB(String username) throws ServiceException;
    List<User> getUsers(String lang) throws ServiceException;
    Map<Integer, String> getReasons(String lang) throws ServiceException;
    void blockUser(int id, Date date, int reason) throws ServiceException;
    void unlockUser(int id) throws ServiceException;
    User changeUserData(String lang, int id, String username, String password, String name, String lastname, String surname, String email) throws ServiceException;
    void deleteUser(int id) throws  ServiceException;
}