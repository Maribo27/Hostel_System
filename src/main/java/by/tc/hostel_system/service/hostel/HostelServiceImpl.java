package by.tc.hostel_system.service.hostel;

import by.tc.hostel_system.dao.DAOException;
import by.tc.hostel_system.dao.DAOFactory;
import by.tc.hostel_system.dao.EntityNotFoundException;
import by.tc.hostel_system.dao.hostel.HostelDAO;
import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.validation.*;
import by.tc.hostel_system.util.ControllerUtil;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class HostelServiceImpl implements HostelService {

    @Override
    public List<Hostel> getHostels(String lang, String page) throws ServiceException {
        HostelDAO hostelDAO = DAOFactory.getInstance().getHostelDAO();
        try {
            boolean langValid = Validator.isLanguage(lang);
            boolean pageValid = Validator.isNumber(page);
            return hostelDAO.getHostels(lang);
        } catch (LangNotSupportedException | NotNumberException e){
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new HostelNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Hostel> getHostels(String lang, String city, String room, String start, String days, String page, String type) throws ServiceException {
        HostelDAO hostelDAO = DAOFactory.getInstance().getHostelDAO();

        try {
            boolean langValid = Validator.isLanguage(lang);
            boolean inputDataValid = HostelValidator.isSearchDataValid(city, room, start, days, page, type);
            int cityId = Integer.parseInt(city);
            int roomId = Integer.parseInt(room);
            int daysNumber = Integer.parseInt(days);
            Date startDate = Date.valueOf(start);
            Hostel.Booking bookingType = Hostel.Booking.valueOf(type.toUpperCase());
            Date endDate = ControllerUtil.getEndDate(daysNumber, startDate);
            return hostelDAO.getHostels(lang, bookingType, cityId, roomId, startDate, endDate);
        } catch (LangNotSupportedException | NotDateException | NotNumberException | UserValidator.InputException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new HostelNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, String> getCities(String lang) throws ServiceException {
        HostelDAO hostelDAO = DAOFactory.getInstance().getHostelDAO();

        try {
            boolean langValid = Validator.isLanguage(lang);
            return hostelDAO.getCities(lang);
        } catch (LangNotSupportedException e) {
            throw new InvalidParametersException(e.getMessage());
        } catch (EntityNotFoundException e){
            throw new HostelNotFoundException(e.getMessage());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}