package by.tc.task31.controller.command.common_impl;

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
import static by.tc.task31.controller.constant.EntityAttributes.PASSWORD;
import static by.tc.task31.controller.constant.EntityAttributes.USERNAME;
import static by.tc.task31.controller.constant.PageUrl.*;

public class LogIn implements Command {
    private static final Logger logger = Logger.getLogger(LogIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        HttpSession session = request.getSession();
        String sessionLang = (String) session.getAttribute(LANG);
        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;
        session.setAttribute(LANG, lang);

        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            User tempUser = new User();
            tempUser.setUsername(username);
            User user = service.getUserInformation(lang, tempUser, password);
            session = request.getSession(true);
            session.setAttribute(USER, user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOME_PAGE);
            requestDispatcher.forward(request, response);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), LOGIN_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}