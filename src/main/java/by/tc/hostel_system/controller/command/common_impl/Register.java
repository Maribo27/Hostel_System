package by.tc.hostel_system.controller.command.common_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserExistException;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.EntityAttributes.*;
import static by.tc.hostel_system.controller.constant.PageUrl.*;

public class Register implements Command {
    private static final Logger logger = Logger.getLogger(Register.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String lastname = request.getParameter(LASTNAME);
        String sessionLang = (String) session.getAttribute(LANG);
        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;

        session.setAttribute(LANG, lang);

        UserService service = factory.getUserService();

        try {
            service.addUserInformation(username, password, name, lastname, surname, email);
            User newUser = new User();
            newUser.getPersonalInfo().setUsername(username);
            User user = service.getUserInformation(lang, newUser, password);
            session = request.getSession(true);
            session.setAttribute(USER, user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOME_PAGE);
            requestDispatcher.forward(request, response);
        } catch (UserExistException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.showUserExistError(request, response, username, email, name, surname, lastname, INDEX_URL);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}