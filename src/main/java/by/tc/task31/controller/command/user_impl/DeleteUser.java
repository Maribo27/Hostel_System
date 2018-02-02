package by.tc.task31.controller.command.user_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.user.UserService;
import by.tc.task31.service.user.UserNotFoundException;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.constant.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE;
import static by.tc.task31.controller.constant.PageUrl.PREFERENCES_PAGE;

public class DeleteUser implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
    private ServiceFactory factory = ServiceFactory.getInstance();
    private static final String CONFIRM = "confirm";
	private static final String CONFIRM_PASSWORD = "confirm-password";
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    UserService service = factory.getUserService();

	    String lang = (String) session.getAttribute(LANG);
	    String password = request.getParameter(CONFIRM_PASSWORD);
	    Object user = session.getAttribute(USER);

	    if (password == null){
	    	request.setAttribute(CONFIRM, true);
		    RequestDispatcher requestDispatcher;
		    requestDispatcher = request.getRequestDispatcher(PREFERENCES_PAGE);
		    requestDispatcher.forward(request, response);
		    return;
	    }

	    try {
		    User newUser = service.getUserInformation(lang, user, password);
		    int id = newUser.getId();
		    service.deleteUser(id);
		    logger.info("User (id = )" + id + "was deleted");
		    String address = request.getContextPath() + "/hostel_system?command=LOGOUT";
		    response.sendRedirect(address);
	    } catch (UserNotFoundException e) {
		    logger.error(INVALID_PASSWORD_MESSAGE, e);
		    request.setAttribute(CONFIRM, true);
		    ControllerUtil.updateWithErrorMessage(request, response, INVALID_PASSWORD_MESSAGE, PREFERENCES_PAGE);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
	    }
    }
}