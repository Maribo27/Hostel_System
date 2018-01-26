package by.tc.task31.service;

import by.tc.task31.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> getRequests(String lang) throws ServiceException;
    List<Request> getRequests(String lang, int id) throws ServiceException;
    void addRequest(Request request) throws ServiceException;

	void deleteRequest(int id) throws ServiceException;

	void changeRequestStatus(int id, String status) throws ServiceException;
}