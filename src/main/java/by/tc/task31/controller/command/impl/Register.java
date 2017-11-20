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

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.*;

public class Register implements Command {

    private static final String USER_EXCEPTION_MESSAGE = "This user exist";
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
            requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE_URL);
        } else {
            User user = service.addUserInformation(username, password, name, email);
            request.setAttribute(USER_ATTRIBUTE, user);
            requestDispatcher = request.getRequestDispatcher(USER_INFO_PAGE_URL);
        }
        requestDispatcher.forward(request, response);
    }
}