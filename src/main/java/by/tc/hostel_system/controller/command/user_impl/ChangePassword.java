package by.tc.hostel_system.controller.command.user_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.service.user.UserNotFoundException;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.EntityAttributes.NEW_PASSWORD;
import static by.tc.hostel_system.controller.constant.EntityAttributes.PASSWORD;
import static by.tc.hostel_system.controller.constant.PageUrl.*;

public class ChangePassword implements Command {
	private static final Logger logger = Logger.getLogger(ChangePassword.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    UserService service = factory.getUserService();
	    RequestDispatcher requestDispatcher;

	    String lang = (String) session.getAttribute(LANG);
	    String password = request.getParameter(PASSWORD);
	    String newPassword = request.getParameter(NEW_PASSWORD);
	    Object user = session.getAttribute(USER);

	    try {
		    User newUser = service.getUserInformation(lang, user, password);
		    service.changePassword(newUser.getId(), newPassword);
		    newUser.setPassword(newPassword);
		    session.setAttribute(USER, newUser);
		    requestDispatcher = request.getRequestDispatcher(PREFERENCES_PAGE);
		    requestDispatcher.forward(request, response);
	    } catch (UserNotFoundException e) {
		    logger.error(INVALID_PASSWORD_MESSAGE, e);
		    ControllerUtil.updateWithErrorMessage(request, response, INVALID_PASSWORD_MESSAGE, CHANGE_PASSWORD_PAGE);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
	    }
    }
}