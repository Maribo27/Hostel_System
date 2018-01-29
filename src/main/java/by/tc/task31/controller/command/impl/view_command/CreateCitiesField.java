package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.HostelService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.CHOOSE_PARAMETERS_URL;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;

public class CreateCitiesField implements Command {
	private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
	private ServiceFactory factory = ServiceFactory.getInstance();

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);

	    HostelService service = factory.getHostelService();

	    RequestDispatcher requestDispatcher;
	    try {
		    Map<Integer, String> cities = service.getCities(lang);
		    request.setAttribute(CITIES, cities);
		    requestDispatcher = request.getRequestDispatcher(CHOOSE_PARAMETERS_URL);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
	    }
    }
}