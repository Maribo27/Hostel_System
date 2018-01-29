package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.RequestService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.UserService;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;

public class CancelRequest implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
	private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
    	int requestId = Integer.parseInt(request.getParameter(REQUEST));
	    int userId = Integer.parseInt(request.getParameter(ID));
	    int page = Integer.parseInt(request.getParameter(NUMBER));
	    String pageToGo = request.getParameter("next-command");
	    String status = request.getParameter(STATUS);

	    RequestService requestService = factory.getRequestService();

	    try {
		    int balance = requestService.cancelRequest(requestId, userId, status);
		    User user = ((User)session.getAttribute(USER_ATTRIBUTE));
		    user.setBalance(balance);
		    session.setAttribute(USER_ATTRIBUTE, user);
		    String address = request.getContextPath() + "/hostel_system?command=" + pageToGo + "&number=" + page;
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
	    }
    }
}