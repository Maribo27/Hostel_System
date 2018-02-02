package by.tc.task31.dao.request;

import by.tc.task31.dao.DAOException;
import by.tc.task31.entity.Request;

import java.util.List;

public interface RequestDAO {
    List<Request> getRequests(String lang) throws DAOException;
    List<Request> getRequests(String lang, int id) throws DAOException;
    int addRequest(Request request, int balance) throws DAOException;

	int cancelRequest(int requestId, int userId, String status, int balance) throws DAOException;

	void approveRequest(int id) throws DAOException;
}