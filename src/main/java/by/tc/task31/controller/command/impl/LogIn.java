package by.tc.task31.controller.command.impl;

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
import static by.tc.task31.controller.constant.Message.USER_NOT_FOUND_MESSAGE;
import static by.tc.task31.controller.constant.PageUrl.*;
import static by.tc.task31.controller.constant.UserAttributes.PASSWORD;
import static by.tc.task31.controller.constant.UserAttributes.USERNAME;

public class LogIn implements Command {
    private static final Logger logger = Logger.getLogger(LogIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        HttpSession session = request.getSession();
        String sessionLang = (String) session.getAttribute(LANG_ATTRIBUTE);
        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;
        session.setAttribute(LANG_ATTRIBUTE, lang);

        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            boolean userInDB = service.userInDB(username);
            if (!userInDB){
                ControllerUtil.updateWithErrorMessage(request, response, USER_NOT_FOUND_MESSAGE, LOGIN_PAGE_URL);
            } else {
                getUser(request, response, username, password, lang, service);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
        }
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response, String username, String password, String lang, UserService service) throws ServiceException, ServletException, IOException {
        User user = service.getUserInformation(lang, username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(USER_ATTRIBUTE, user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOME_PAGE_URL);
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute(USERNAME, username);
            ControllerUtil.updateWithErrorMessage(request, response, INVALID_PASSWORD_MESSAGE, LOGIN_PAGE_URL);
        }
    }
}