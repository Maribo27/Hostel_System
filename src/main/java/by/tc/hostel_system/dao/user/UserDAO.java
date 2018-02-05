package by.tc.hostel_system.dao.user;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.user.UserExistException;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserDAO {
    User getUserInformation(String lang, String username, String password) throws DAOException;
    void addUser(String username, String password, String name, String lastname, String surname, String email) throws DAOException, UserExistException;
    List<User> getUsers(String lang) throws DAOException;
    Map<Integer, String> getReasons(String lang) throws DAOException;
    void blockUser(int id, Date date, int reason) throws DAOException;
    void unlockUser(int id) throws DAOException;
    void changeUserData(int id, List<String> dataToUpdate) throws DAOException;
    void changePassword(int id, String password) throws DAOException;
    void deleteUser(int id) throws  DAOException;
    boolean checkUser(String data, String bundle) throws DAOException, UserExistException;
    void changeUserDiscount(int userId, int userDiscount) throws DAOException;

	int getUserDiscount(int id) throws DAOException;
}