package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.Hostel;
import by.tc.task31.service.HostelService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.CREATE_REQUEST_URL;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;

public class CreateRequest implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);

	    String type = request.getParameter(TYPE);
	    int city = Integer.parseInt(request.getParameter(CITY));
	    int rooms = Integer.parseInt(request.getParameter(ROOMS));
	    int days = Integer.parseInt(request.getParameter(DAYS));
	    Date date = Date.valueOf(request.getParameter(DATE));

	    HostelService service = factory.getHostelService();

	    RequestDispatcher requestDispatcher;

	    try {
		    List<Hostel> hostels = service.getHostels(lang, city, rooms);
		    request.setAttribute(HOSTELS_ATTRIBUTE, hostels);
		    request.setAttribute(TYPE, type);
		    request.setAttribute(CITY, city);
		    request.setAttribute(ROOMS, rooms);
		    request.setAttribute(DAYS, days);
		    request.setAttribute(DATE, date);
		    requestDispatcher = request.getRequestDispatcher(CREATE_REQUEST_URL);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
		    RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
		    dispatcher.forward(request, response);
	    }
    }
}