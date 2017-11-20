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

import static by.tc.task31.controller.command.Const.*;

public class LogIn implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        EntityService service = factory.getEntityService();

        RequestDispatcher requestDispatcher;

        boolean userInDB = service.userInDB(username);
        if (!userInDB){
            request.setAttribute(ERROR_ATTRIBUTE, USER_NOT_FOUND);
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
        } else {
            User user = service.getUserInformation(username, password);

            if (user != null) {
                request.setAttribute(ATTRIBUTE_NAME, user);
                requestDispatcher = request.getRequestDispatcher(USER_INFO_PAGE);
            } else {
                request.setAttribute(ERROR_ATTRIBUTE, INVALID_PASSWORD);
                request.setAttribute(USERNAME, username);
                requestDispatcher = request.getRequestDispatcher(INVALID_PASSWORD_PAGE);
            }
        }
        requestDispatcher.forward(request, response);
    }
}