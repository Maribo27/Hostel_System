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

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.HOME_PAGE_URL;

public class ChangeUserData implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);

	    int id = ((User)session.getAttribute(USER_ATTRIBUTE)).getId();
	    String username = request.getParameter(USERNAME);
	    String email = request.getParameter(EMAIL);
	    String password = request.getParameter(PASSWORD);
	    String name = request.getParameter(NAME);
	    String surname = request.getParameter(SURNAME);
	    String lastname = request.getParameter(LASTNAME);

	    UserService service = factory.getUserService();

        RequestDispatcher requestDispatcher;

	    User user = service.changeUserData(lang, id, username, password, name, lastname, surname, email);
	    session.setAttribute(USER_ATTRIBUTE, user);
	    requestDispatcher = request.getRequestDispatcher(HOME_PAGE_URL);
	    requestDispatcher.forward(request, response);
    }
}