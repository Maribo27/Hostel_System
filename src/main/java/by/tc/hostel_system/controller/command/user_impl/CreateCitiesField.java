package by.tc.hostel_system.controller.command.user_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.service.hostel.HostelService;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.tc.hostel_system.controller.constant.ControlConst.LANG;
import static by.tc.hostel_system.controller.constant.PageUrl.CHOOSE_PARAMETERS_PAGE;
import static by.tc.hostel_system.controller.constant.PageUrl.ERROR_PAGE;

public class CreateCitiesField implements Command {
	private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
	private ServiceFactory factory = ServiceFactory.getInstance();
	private static final String CITIES = "cities";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG);

	    HostelService service = factory.getHostelService();

	    RequestDispatcher requestDispatcher;
	    try {
		    Map<Integer, String> cities = service.getCities(lang);
		    request.setAttribute(CITIES, cities);
		    requestDispatcher = request.getRequestDispatcher(CHOOSE_PARAMETERS_PAGE);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
	    }
    }
}