package by.tc.hostel_system.controller.command.common_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserExistException;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.EntityAttributes.*;
import static by.tc.hostel_system.controller.constant.PageUrl.HOME_PAGE;
import static by.tc.hostel_system.controller.constant.PageUrl.INDEX_URL;

public class Registration implements Command {
    private static final Logger logger = Logger.getLogger(Registration.class);

    /**
     * The method execute the registration of the user in the system.
     * It redirects to the home page with a successful registration,
     * otherwise it redirects to the page with an error.
     * @see Command#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String lastName = request.getParameter(LAST_NAME);
        String sessionLang = (String) session.getAttribute(LANG);
        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;

        session.setAttribute(LANG, lang);

        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            service.addUserInformation(lang, username, password, name, lastName, surname, email);
            User newUser = new User();
            newUser.getPersonalInfo().setUsername(username);
            User user = service.getUserInformation(lang, newUser, password);
            session = request.getSession(true);
            session.setAttribute(USER, user);
            response.sendRedirect(HOME_PAGE);
        } catch (UserExistException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.showUserExistError(request, response, e.getMessage(), username, email, name, surname, lastName, INDEX_URL);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}