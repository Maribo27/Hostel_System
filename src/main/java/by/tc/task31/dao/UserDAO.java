package by.tc.task31.dao;

import by.tc.task31.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserDAO {
    User getUserInformation(String lang, String username, String password) throws DAOException;
    boolean userInDB(String username) throws DAOException;
    void addUser(String username, String password, String name, String lastname, String surname, String email) throws DAOException;
    List<User> getUsers(String lang) throws DAOException;
    Map<Integer, String> getReasons(String lang) throws DAOException;
    void blockUser(int id, Date date, int reason) throws DAOException;
    void unlockUser(int id) throws DAOException;
    void changeUserData(int id, List<String> dataToUpdate) throws DAOException;
    void changePassword(int id, String password) throws DAOException;
    void deleteUser(int id) throws  DAOException;
}