package by.tc.task31.controller.command.common_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.user_impl.AddRequest;
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

import static by.tc.task31.controller.constant.ControlConst.*;
import static by.tc.task31.controller.constant.EntityAttributes.ID;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE;

public class CancelRequest implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
	private ServiceFactory factory = ServiceFactory.getInstance();
	private static final String STATUS = "status";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
    	String requestId = request.getParameter(REQUEST);
	    String userId = request.getParameter(ID);
	    String page = request.getParameter(NUMBER);
	    String nextCommand = request.getParameter(NEXT_COMMAND);
	    String status = request.getParameter(STATUS);
	    Object user = session.getAttribute(USER);
	    RequestService requestService = factory.getRequestService();
	    try {
		    int balance = requestService.cancelRequest(requestId, userId, status, user, page);
		    User newUser = (User) user;
		    newUser.setBalance(balance);
		    session.setAttribute(USER, newUser);
		    String address = ControllerUtil.createAddressWithPaging(request, nextCommand, page);
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
	    }
    }
}