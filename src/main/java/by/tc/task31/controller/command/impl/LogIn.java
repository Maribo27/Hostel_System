package by.tc.task31.controller.command.impl;

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
import static by.tc.task31.controller.command.PageUrl.*;

public class LogIn implements Command {
    private static final String USER_NOT_FOUND_MESSAGE = "User not found";
    private static final String INVALID_PASSWORD_MESSAGE = "Invalid password";
    private static final String DEFAULT_LANG = "ru";

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String sessionLang = (String) request.getSession().getAttribute(LANG_ATTRIBUTE);
        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;
        UserService service = factory.getUserService();

        RequestDispatcher requestDispatcher;

        boolean userInDB = service.userInDB(username);
        if (!userInDB){
            request.setAttribute(ERROR_ATTRIBUTE, USER_NOT_FOUND_MESSAGE);
            request.setAttribute(LANG_ATTRIBUTE, lang);
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
            requestDispatcher.forward(request, response);
        } else {
            User user = service.getUserInformation(lang, username, password);

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(USER_ATTRIBUTE, user);
                session.setAttribute(LANG_ATTRIBUTE, lang);
                //response.sendRedirect(USER_INFO_PAGE_URL);
                requestDispatcher = request.getRequestDispatcher(USER_INFO_PAGE_URL);
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(ERROR_ATTRIBUTE, INVALID_PASSWORD_MESSAGE);
                request.setAttribute(USERNAME, username);
                request.setAttribute(LANG_ATTRIBUTE, lang);
                requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE_URL);
                requestDispatcher.forward(request, response);
            }
        }

    }
}