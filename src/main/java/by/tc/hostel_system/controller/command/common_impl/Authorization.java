package by.tc.hostel_system.controller.command.common_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.entity.PaginationHelper;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.request.RequestNotFoundException;
import by.tc.hostel_system.service.request.RequestService;
import by.tc.hostel_system.service.user.UserNotFoundException;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.EntityAttributes.PASSWORD;
import static by.tc.hostel_system.controller.constant.EntityAttributes.USERNAME;
import static by.tc.hostel_system.controller.constant.PageUrl.HOME_PAGE;
import static by.tc.hostel_system.controller.constant.PageUrl.LOGIN_PAGE;

public class Authorization implements Command {
    private static final Logger logger = Logger.getLogger(Authorization.class);

    /**
     * The method logs the user into the system.
     * It redirects to the home page with a successful search,
     * otherwise it redirects to the page with an error.
     * @see Command#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String sessionLang = (String) session.getAttribute(LANG);

        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;
        session.setAttribute(LANG, lang);

        UserService userService = ServiceFactory.getInstance().getUserService();
        RequestService requestService = ServiceFactory.getInstance().getRequestService();
        try {
            User tempUser = new User();
            tempUser.getPersonalInfo().setUsername(username);

            User user = userService.getUserInformation(lang, tempUser, password);
            session = request.getSession(true);
            session.setAttribute(USER, user);

            List<Request> requests = requestService.getRequests(lang, user);
            final String command = CommandType.SHOW_NEW_REQUESTS.name();
            PaginationHelper paginationHelper = ControllerUtil.createPagination(request, 1, requests.size(), command);
            List<Request> requestsOnPage = requests.subList(paginationHelper.getBegin(), paginationHelper.getEnd());

            session.setAttribute(REQUESTS, requestsOnPage);
            session.setAttribute(PAGE, paginationHelper);
            response.sendRedirect(HOME_PAGE);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithMessage(request, response, e.getMessage(), LOGIN_PAGE);
        } catch (RequestNotFoundException e) {
            response.sendRedirect(HOME_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}