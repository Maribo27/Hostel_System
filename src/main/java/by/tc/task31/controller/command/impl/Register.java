package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.EntityService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register implements Command {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String ATTRIBUTE_NAME = "user";
    private static final String USER_INFO_PAGE = "WEB-INF/jsp/successLogination.jsp";
    private static final String REGISTER_PAGE = "WEB-INF/jsp/reRegister.jsp";
    private static final String USER_EXCEPTION_MESSAGE = "This user exist";
    private static final String ERROR_ATTRIBUTE = "error";

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String email = request.getParameter(EMAIL);

        EntityService service = factory.getEntityService();

        RequestDispatcher requestDispatcher;

        boolean userInDB = service.userInDB(username);
        if (userInDB){
            request.setAttribute(ERROR_ATTRIBUTE, USER_EXCEPTION_MESSAGE);
            request.setAttribute(USERNAME, username);
            request.setAttribute(PASSWORD, password);
            request.setAttribute(NAME, name);
            request.setAttribute(EMAIL, email);
            requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE);
        } else {
            User user = service.addUserInformation(username, password, name, email);
            request.setAttribute(ATTRIBUTE_NAME, user);
            requestDispatcher = request.getRequestDispatcher(USER_INFO_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
