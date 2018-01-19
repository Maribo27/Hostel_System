package by.tc.task31.service;

import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface EntityService {
    User getUserInformation(String lang, String username, String password) throws ServiceException;
    User addUserInformation(String username, String password, String name, String lastname, String surname, String email) throws ServiceException;
    boolean userInDB(String username) throws ServiceException;
    
    List<Request> getRequests(String lang) throws ServiceException;
    List<Request> getRequests(String lang, int id) throws ServiceException;
    List<User> getUsers(String lang) throws ServiceException;
    List<Hostel> getHostels(String lang) throws ServiceException;
    List<Hostel> getHostels(String lang, int city, int room) throws ServiceException;
    Map<Integer, String> getReasons(String lang) throws ServiceException;
    Map<Integer, String> getCities(String lang) throws ServiceException;

    void addRequest(Request request) throws ServiceException;

    void changeRequestStatus(int id, String status) throws ServiceException;
    void blockUser(int id, Timestamp date, int reason) throws ServiceException;
    void unlockUser(int id) throws ServiceException;
    User changeUserData(String lang, int id, String username, String password, String name, String lastname, String surname, String email) throws ServiceException;

    void deleteUser(int id) throws  ServiceException;
    void deleteHostel(int id) throws ServiceException;
}