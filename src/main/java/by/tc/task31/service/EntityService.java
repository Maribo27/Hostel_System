package by.tc.task31.service;

import by.tc.task31.entity.User;

import java.util.List;

public interface EntityService {
    User getUserInformation(String username, String password) throws ServiceException;
    User addUserInformation(String username, String password, String name, String email) throws ServiceException;
    boolean userInDB(String username) throws ServiceException;
}