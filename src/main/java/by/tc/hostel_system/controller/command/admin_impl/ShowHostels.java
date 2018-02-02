package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.controller.command.user_impl.CreateCitiesField;
import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.entity.PaginationHelper;
import by.tc.hostel_system.service.hostel.HostelService;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.hostel.HostelNotFoundException;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.PageUrl.*;

public class ShowHostels implements Command {
	private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    HostelService service = factory.getHostelService();
	    String lang = (String) session.getAttribute(LANG);
	    String page = request.getParameter(NUMBER);
	    RequestDispatcher requestDispatcher;
        try {
	        List<Hostel> hostels = service.getHostels(lang, page);
	        int currentPage = Integer.parseInt(page);
	        String nextCommand = CommandType.SHOW_HOSTELS.name();
	        PaginationHelper paginationHelper = ControllerUtil.createPagination(request, currentPage, hostels.size(), nextCommand);
	        request.setAttribute(PAGE, paginationHelper);
	        List<Hostel> hostelsOnPage = hostels.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
	        request.setAttribute(HOSTELS, hostelsOnPage);
	        requestDispatcher = request.getRequestDispatcher(HOSTELS_PAGE);
	        requestDispatcher.forward(request, response);
        } catch (HostelNotFoundException e){
	        logger.error(e.getMessage(), e);
	        requestDispatcher = request.getRequestDispatcher(NOTHING_FOUND_PAGE);
	        requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}