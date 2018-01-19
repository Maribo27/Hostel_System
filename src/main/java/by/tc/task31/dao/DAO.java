package by.tc.task31.dao;

import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface DAO {
    User getUserInformation(String lang, String username, String password) throws DAOException;
    boolean userInDB(String username) throws DAOException;
    User addUser(String username, String password, String name, String lastname, String surname, String email) throws DAOException;

    List<Request> getRequests(String lang) throws DAOException;
    List<Request> getRequests(String lang, int id) throws DAOException;
    List<User> getUsers(String lang) throws DAOException;
    List<Hostel> getHostels(String lang) throws DAOException;
    List<Hostel> getHostels(String lang, int city, int room) throws DAOException;
    Map<Integer, String> getReasons(String lang) throws DAOException;
    Map<Integer, String> getCities(String lang) throws DAOException;

    void addRequest(Request request) throws DAOException;

    void changeRequestStatus(int id, String status) throws DAOException;
    void blockUser(int id, Timestamp date, int reason) throws DAOException;
    void unlockUser(int id) throws DAOException;
    void changeUserData(int id, String username, String password, String name, String lastname, String surname, String email) throws DAOException;

    void deleteUser(int id) throws  DAOException;
    void deleteHostel(int id) throws DAOException;
}