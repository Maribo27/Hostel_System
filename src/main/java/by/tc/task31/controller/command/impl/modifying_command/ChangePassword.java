package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.PREFERENCES_URL;

public class ChangePassword implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
	    HttpSession session = request.getSession();
	    UserService service = factory.getUserService();
	    RequestDispatcher requestDispatcher;

	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
	    String password = request.getParameter(PASSWORD);
	    User newUser = (User) session.getAttribute(USER_ATTRIBUTE);

	    newUser = service.getUserInformation(lang, newUser.getUsername(), password);
	    if (newUser == null) {
		    request.setAttribute(ERROR_ATTRIBUTE, INVALID_PASSWORD_MESSAGE);
		    request.setAttribute(LANG_ATTRIBUTE, lang);
		    requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
		    requestDispatcher.forward(request, response);
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
    }
}