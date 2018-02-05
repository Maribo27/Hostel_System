package by.tc.hostel_system.service.user;

import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserInformation(String lang, Object user, String password) throws ServiceException;
    void addUserInformation(String username, String password, String name, String lastname, String surname, String email) throws ServiceException;
    List<User> getUsers(String lang) throws ServiceException;
    Map<Integer, String> getReasons(String lang) throws ServiceException;
    void blockUser(String id, String date, String reason, String page) throws ServiceException;
    void unlockUser(String id, String page) throws ServiceException;
    void changeUserData(User user, String username, String email, String name, String surname, String lastname) throws ServiceException;
    void changePassword(int id, String password) throws ServiceException;
    void deleteUser(int id) throws  ServiceException;

	void changeUserDiscount(int userId, int userDiscount, String sign, String page) throws ServiceException;

	int getUserDiscount(String userId) throws ServiceException;
}