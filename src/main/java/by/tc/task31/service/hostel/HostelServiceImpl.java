package by.tc.task31.service.hostel;

import by.tc.task31.dao.DAOException;
import by.tc.task31.dao.DAOFactory;
import by.tc.task31.dao.EntityNotFoundException;
import by.tc.task31.dao.hostel.HostelDAO;
import by.tc.task31.entity.Hostel;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.validation.*;
import by.tc.task31.util.ControllerUtil;

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
            Date endDate = ControllerUtil.getEndDate(daysNumber, startDate);
            return hostelDAO.getHostels(lang, cityId, roomId, startDate, endDate);
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