package by.tc.hostel_system.service.hostel;

import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface HostelService {
    List<Hostel> getHostels(String lang, String page) throws ServiceException;
    List<Hostel> getHostels(String lang, String city, String room, String start, String days, String page, String type) throws ServiceException;
    Map<Integer, String> getCities(String lang) throws ServiceException;
}