package by.tc.task31.dao;

import by.tc.task31.entity.Hostel;

import java.util.List;
import java.util.Map;

public interface HostelDAO {
    List<Hostel> getHostels(String lang) throws DAOException;
    List<Hostel> getHostels(String lang, int city, int room) throws DAOException;
    Map<Integer, String> getCities(String lang) throws DAOException;
    void deleteHostel(int id) throws DAOException;
}