package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.EntityService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.CHOOSE_PARAMETERS_URL;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.PREFERENCES_PAGE_URL;

public class ShowPreferences implements Command {

	private ServiceFactory factory = ServiceFactory.getInstance();

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);

	    EntityService service = factory.getEntityService();

	    RequestDispatcher requestDispatcher;
		requestDispatcher = request.getRequestDispatcher(PREFERENCES_PAGE_URL);
		requestDispatcher.forward(request, response);
	}
}