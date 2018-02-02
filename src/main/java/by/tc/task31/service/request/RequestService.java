package by.tc.task31.service.request;

import by.tc.task31.entity.Request;
import by.tc.task31.service.ServiceException;

import java.util.List;

public interface RequestService {
	List<Request> getRequests(String lang, String page) throws ServiceException;
	List<Request> getRequests(String lang, Object user, String page) throws ServiceException;
    int addRequest(Object user, String hostelId, String type, String rooms, String days, String date, String cost) throws ServiceException;
	int cancelRequest(String requestId, String userId, String status, Object user, String page) throws ServiceException;
	void approveRequest(String id, String page) throws ServiceException;
}