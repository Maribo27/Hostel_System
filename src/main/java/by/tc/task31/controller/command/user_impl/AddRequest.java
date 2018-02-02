package by.tc.task31.controller.command.user_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.CommandType;
import by.tc.task31.entity.User;
import by.tc.task31.service.request.RequestService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.constant.ControlConst.USER;
import static by.tc.task31.controller.constant.EntityAttributes.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE;

public class AddRequest implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
	private ServiceFactory factory = ServiceFactory.getInstance();
	private static final String HOSTEL = "hostel";
	private static final String COST = "cost";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();

	    String type = request.getParameter(TYPE);
	    String hostelId = request.getParameter(HOSTEL);
	    String rooms = request.getParameter(ROOMS);
	    String days = request.getParameter(DAYS);
	    String cost = request.getParameter(COST);
	    String date = request.getParameter(DATE);

	    Object user = session.getAttribute(USER);

	    RequestService service = factory.getRequestService();
	    try {
	    	int balance = service.addRequest(user, hostelId, type, rooms, days, date, cost);
	    	User newUser = (User) user;
	    	newUser.setBalance(balance);
	    	session.setAttribute(USER, newUser);

		    String address = ControllerUtil.createAddressWithPaging(request, CommandType.SHOW_USER_REQUESTS.name(), "1");
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
	    }
    }
}