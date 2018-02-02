package by.tc.task31.controller.command.user_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.user.UserService;
import by.tc.task31.service.user.UserExistException;
import by.tc.task31.service.user.UserNotFoundException;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.constant.ControlConst.LANG;
import static by.tc.task31.controller.constant.ControlConst.USER;
import static by.tc.task31.controller.constant.EntityAttributes.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE;
import static by.tc.task31.controller.constant.PageUrl.PREFERENCES_PAGE;

public class ChangeUserData implements Command {
	private static final Logger logger = Logger.getLogger(ChangeUserData.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    UserService service = factory.getUserService();

	    String lang = (String) session.getAttribute(LANG);
	    String password = request.getParameter(PASSWORD);
	    String username = request.getParameter(USERNAME);
	    String email = request.getParameter(EMAIL);
	    String name = request.getParameter(NAME);
	    String surname = request.getParameter(SURNAME);
	    String lastname = request.getParameter(LASTNAME);

	    Object user = session.getAttribute(USER);

	    try {
		    User newUser = service.getUserInformation(lang, user, password);
		    service.changeUserData(newUser, username, email, name, surname, lastname);
		    session.setAttribute(USER, newUser);
		    response.sendRedirect(PREFERENCES_PAGE);
	    } catch (UserExistException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.showUserExistError(request, response, username, email, name, surname, lastname, PREFERENCES_PAGE);
	    } catch (UserNotFoundException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), PREFERENCES_PAGE);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
	    }
    }

}