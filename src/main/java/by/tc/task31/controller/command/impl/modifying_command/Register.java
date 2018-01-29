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
import static by.tc.task31.controller.constant.PageUrl.*;
import static by.tc.task31.controller.constant.UserAttributes.*;

public class Register implements Command {
    private static final Logger logger = Logger.getLogger(Register.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String lastname = request.getParameter(LASTNAME);
        HttpSession session = request.getSession();
        String sessionLang = (String) session.getAttribute(LANG_ATTRIBUTE);
        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;
        session.setAttribute(LANG_ATTRIBUTE, lang);

        UserService service = factory.getUserService();

        try {
            boolean userInDB = service.userInDB(username);
            if (userInDB){
                ControllerUtil.showUserExistError(request, response, username, email, name, surname, lastname, INDEX_URL);
            } else {
                service.addUserInformation(username, password, name, lastname, surname, email);
                User user = service.getUserInformation(lang, username, password);
                session = request.getSession(true);
                session.setAttribute(USER_ATTRIBUTE, user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOME_PAGE_URL);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
        }
    }
}