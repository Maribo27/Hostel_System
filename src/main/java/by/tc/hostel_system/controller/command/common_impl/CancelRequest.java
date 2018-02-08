package by.tc.hostel_system.controller.command.common_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.request.RequestService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.EntityAttributes.ID;

public class CancelRequest implements Command {
	private static final Logger logger = Logger.getLogger(CancelRequest.class);
	private static final String STATUS = "status";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession session = request.getSession();
		Object user = session.getAttribute(USER);

		String requestId = request.getParameter(REQUEST);
	    String userId = request.getParameter(ID);
	    String page = request.getParameter(NUMBER);
	    String nextCommand = request.getParameter(NEXT_COMMAND);
	    String status = request.getParameter(STATUS);

	    RequestService service = ServiceFactory.getInstance().getRequestService();
	    try {
		    int balance = service.cancelRequest(requestId, userId, status, user, page);
		    User newUser = (User) user;
		    if (newUser.getStatus() != User.Status.ADMIN) {
		    	newUser.setBalance(balance);
			    session.setAttribute(USER, newUser);
		    }
		    String address = ControllerUtil.createAddressWithPaging(request, nextCommand, page);
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
    }
}