package by.tc.task31.service;

import by.tc.task31.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> getRequests(String lang) throws ServiceException;
    List<Request> getRequests(String lang, int id) throws ServiceException;
    int addRequest(Request request, int balance) throws ServiceException;

	int cancelRequest(int requestId, int userId, String status) throws ServiceException;

	void approveRequest(int id) throws ServiceException;
}