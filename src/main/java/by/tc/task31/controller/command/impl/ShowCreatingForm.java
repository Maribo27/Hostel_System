package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;
import by.tc.task31.service.EntityService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.CHOOSE_PARAMETERS_URL;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.USER_INFO_PAGE_URL;

public class ShowCreatingForm implements Command {

	private ServiceFactory factory = ServiceFactory.getInstance();

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);

	    EntityService service = factory.getEntityService();

	    RequestDispatcher requestDispatcher;
	    try {
		    Map<Integer, String> cities = service.getCities(lang);
		    request.setAttribute(CITIES, cities);
		    requestDispatcher = request.getRequestDispatcher(CHOOSE_PARAMETERS_URL);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
		    RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
		    dispatcher.forward(request, response);
	    }
    }
}