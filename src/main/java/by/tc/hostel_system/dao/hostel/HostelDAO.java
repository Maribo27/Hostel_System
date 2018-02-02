package by.tc.hostel_system.dao.hostel;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.entity.Hostel;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface HostelDAO {
    List<Hostel> getHostels(String lang) throws DAOException;
    List<Hostel> getHostels(String lang, int city, int room, Date start, Date end) throws DAOException;
    Map<Integer, String> getCities(String lang) throws DAOException;
}