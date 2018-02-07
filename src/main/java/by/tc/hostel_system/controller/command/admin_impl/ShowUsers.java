package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.controller.command.user_impl.CreateCitiesField;
import by.tc.hostel_system.entity.PaginationHelper;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.service.user.UserNotFoundException;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.PageUrl.ERROR_PAGE;
import static by.tc.hostel_system.controller.constant.PageUrl.USERS_PAGE;

public class ShowUsers implements Command {
    private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private static final String USERS = "users";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG);
        UserService service = ServiceFactory.getInstance().getUserService();
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