package by.tc.task31.service;

import by.tc.task31.entity.Hostel;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface HostelService {
    List<Hostel> getHostels(String lang) throws ServiceException;
    List<Hostel> getHostels(String lang, int city, int room, Date start, Date end) throws ServiceException;
    Map<Integer, String> getCities(String lang) throws ServiceException;
}