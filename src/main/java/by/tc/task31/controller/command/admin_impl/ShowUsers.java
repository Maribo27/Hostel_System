package by.tc.task31.controller.command.admin_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.CommandType;
import by.tc.task31.controller.command.user_impl.CreateCitiesField;
import by.tc.task31.entity.PaginationHelper;
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
import java.util.List;

import static by.tc.task31.controller.constant.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE;
import static by.tc.task31.controller.constant.PageUrl.USERS_PAGE;

public class ShowUsers implements Command {
    private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();
    private static final String USERS = "users";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG);
        UserService service = factory.getUserService();
        String page = request.getParameter(NUMBER);
        RequestDispatcher requestDispatcher;
        try {
            List<User> users = service.getUsers(lang);
            int currentPage = Integer.parseInt(page);
            String command = CommandType.SHOW_USERS.name();
            PaginationHelper paginationHelper = ControllerUtil.createPagination(request, currentPage, users.size(), command);
            request.setAttribute(PAGE, paginationHelper);
            List<User> usersOnPage = users.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
            request.setAttribute(USERS, usersOnPage);
            requestDispatcher = request.getRequestDispatcher(USERS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);
            requestDispatcher = request.getRequestDispatcher(USERS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}