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

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.INDEX_URL;
import static by.tc.task31.controller.command.PageUrl.HOME_PAGE_URL;

public class Register implements Command {

    private static final String USER_EXCEPTION_MESSAGE = "This user exist";
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String lastname = request.getParameter(LASTNAME);
        String lang = request.getParameter(LANG_ATTRIBUTE);

        UserService service = factory.getUserService();

        RequestDispatcher requestDispatcher;

        boolean userInDB = service.userInDB(username);
        if (userInDB){
            request.setAttribute(ERROR_ATTRIBUTE, USER_EXCEPTION_MESSAGE);
            request.setAttribute(USERNAME, username);
            request.setAttribute(PASSWORD, password);
            request.setAttribute(NAME, name);
            request.setAttribute(SURNAME, surname);
            request.setAttribute(LASTNAME, lastname);
            request.setAttribute(EMAIL, email);
            request.setAttribute(LANG_ATTRIBUTE, lang);
            requestDispatcher = request.getRequestDispatcher(INDEX_URL);
        } else {
            User user = service.addUserInformation(username, password, name, lastname, surname, email);
            HttpSession session = request.getSession(true);

            session.setAttribute(USER_ATTRIBUTE, user);
            session.setAttribute(LANG_ATTRIBUTE, lang);
            requestDispatcher = request.getRequestDispatcher(HOME_PAGE_URL);
        }
        requestDispatcher.forward(request, response);
    }
}