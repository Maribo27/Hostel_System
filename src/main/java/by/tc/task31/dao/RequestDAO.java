package by.tc.task31.dao;

import by.tc.task31.entity.Request;

import java.util.List;

public interface RequestDAO {
    List<Request> getRequests(String lang) throws DAOException;
    List<Request> getRequests(String lang, int id) throws DAOException;
    void addRequest(Request request) throws DAOException;

	void deleteRequest(int id) throws DAOException;

	void changeRequestStatus(int id, String status) throws DAOException;
}