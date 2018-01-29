package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.UserService;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.CHANGE_PASSWORD_URL;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.constant.PageUrl.PREFERENCES_URL;
import static by.tc.task31.controller.constant.UserAttributes.NEW_PASSWORD;
import static by.tc.task31.controller.constant.UserAttributes.PASSWORD;

public class ChangePassword implements Command {
	private static final Logger logger = Logger.getLogger(ChangePassword.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    UserService service = factory.getUserService();
	    RequestDispatcher requestDispatcher;

	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
	    String password = request.getParameter(PASSWORD);
	    User newUser = (User) session.getAttribute(USER_ATTRIBUTE);

	    try {
		    newUser = service.getUserInformation(lang, newUser.getUsername(), password);
		    if (newUser == null) {
			    ControllerUtil.updateWithErrorMessage(request, response, INVALID_PASSWORD_MESSAGE, CHANGE_PASSWORD_URL);
			    return;
		    }

		    int id = ((User)session.getAttribute(USER_ATTRIBUTE)).getId();
		    String newPassword = request.getParameter(NEW_PASSWORD);

		    boolean change = service.changePassword(id, newPassword);
		    if (change) {
			    newUser.setPassword(newPassword);
			    session.setAttribute(USER_ATTRIBUTE, newUser);
		    }
		    requestDispatcher = request.getRequestDispatcher(PREFERENCES_URL);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
	    }
    }
}