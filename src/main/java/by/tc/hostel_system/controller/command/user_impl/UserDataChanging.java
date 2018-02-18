package by.tc.hostel_system.controller.command.user_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserExistException;
import by.tc.hostel_system.service.user.UserNotFoundException;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.LANG;
import static by.tc.hostel_system.controller.constant.ControlConst.Message.DATA_CHANGED;
import static by.tc.hostel_system.controller.constant.ControlConst.USER;
import static by.tc.hostel_system.controller.constant.EntityAttributes.*;
import static by.tc.hostel_system.controller.constant.PageUrl.PREFERENCES_PAGE;

public class UserDataChanging implements Command {
	private static final Logger logger = Logger.getLogger(UserDataChanging.class);

	/**
	 * The method changes current user data.
	 * It redirects to the preferences page on a successful change,
	 * otherwise it redirects to the page with an error.
	 * @see Command#execute(HttpServletRequest, HttpServletResponse)
	 */
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    UserService service = ServiceFactory.getInstance().getUserService();

	    String lang = (String) session.getAttribute(LANG);
	    String password = request.getParameter(PASSWORD);
	    String username = request.getParameter(USERNAME);
	    String email = request.getParameter(EMAIL);
	    String name = request.getParameter(NAME);
	    String surname = request.getParameter(SURNAME);
	    String lastName = request.getParameter(LAST_NAME);

	    Object user = session.getAttribute(USER);

	    try {
		    User newUser = service.getUserInformation(lang, user, password);
		    service.changeUserData(lang, newUser, username, email, name, surname, lastName);
		    session.setAttribute(USER, newUser);
		    ControllerUtil.updateWithMessage(request, response, DATA_CHANGED.getMessage(lang), PREFERENCES_PAGE);
	    } catch (UserExistException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.showUserExistError(request, response, e.getMessage(), username, email, name, surname, lastName, PREFERENCES_PAGE);
	    } catch (UserNotFoundException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithMessage(request, response, e.getMessage(), PREFERENCES_PAGE);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
    }

}