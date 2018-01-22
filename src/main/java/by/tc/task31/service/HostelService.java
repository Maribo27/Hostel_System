package by.tc.task31.service;

import by.tc.task31.entity.Hostel;

import java.util.List;
import java.util.Map;

public interface HostelService {
    List<Hostel> getHostels(String lang) throws ServiceException;
    List<Hostel> getHostels(String lang, int city, int room) throws ServiceException;
    Map<Integer, String> getCities(String lang) throws ServiceException;
    void deleteHostel(int id) throws ServiceException;
}