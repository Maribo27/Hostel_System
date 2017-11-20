package by.tc.task31.dao;

import by.tc.task31.entity.User;

public interface DAO {
    User getUserInformation(String username, String password) throws DAOException;
    boolean userInDB(String username) throws DAOException;
    User addUserInformation(String username, String password, String name, String email) throws DAOException;
}