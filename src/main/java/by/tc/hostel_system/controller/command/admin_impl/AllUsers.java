package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.entity.PaginationHelper;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserNotFoundException;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.PageUrl.NOTHING_FOUND_PAGE;
import static by.tc.hostel_system.controller.constant.PageUrl.USERS_PAGE;
import static sun.security.x509.IssuingDistributionPointExtension.REASONS;

public class AllUsers implements Command {
    private static final Logger logger = Logger.getLogger(AllUsers.class);
    private static final String USERS = "users";

    /**
     * The method searches for all users.
     * It redirects to a page with users on a successful search,
     * otherwise it redirects to the page with an error.
     * @see Command#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String lang = (String) session.getAttribute(LANG);
        String page = request.getParameter(NUMBER);

        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            List<User> users = service.getUsers(lang);
            int currentPage = Integer.parseInt(page);

            String command = CommandType.SHOW_USERS.name();
            PaginationHelper paginationHelper = ControllerUtil.createPagination(request, currentPage, users.size(), command);
            request.setAttribute(PAGE, paginationHelper);

            List<User> usersOnPage = users.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
            request.setAttribute(USERS, usersOnPage);

            Map<Integer, String> reasons = service.getReasons(lang);
            request.setAttribute(REASONS, reasons);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(USERS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithMessage(request, response, e.getMessage(), NOTHING_FOUND_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}