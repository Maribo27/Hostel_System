package by.tc.task31.controller.command.user_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.request.RequestNotFoundException;
import by.tc.task31.service.user.UserNotFoundException;
import by.tc.task31.util.ControllerUtil;
import by.tc.task31.entity.PaginationHelper;
import by.tc.task31.entity.Request;
import by.tc.task31.service.request.RequestService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
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
import static by.tc.task31.controller.constant.PageUrl.NOTHING_FOUND_PAGE;
import static by.tc.task31.controller.constant.PageUrl.REQUESTS_PAGE;

public class ShowUserRequests implements Command {
    private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestService service = factory.getRequestService();

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG);
        String page = request.getParameter(NUMBER);
        Object user = session.getAttribute(USER);

        RequestDispatcher requestDispatcher;

        try {
            List<Request> requests = service.getRequests(lang, user, page);
            int currentPage = Integer.parseInt(page);
            PaginationHelper paginationHelper = ControllerUtil.createPagination(request, currentPage, requests.size(), "SHOW_USER_REQUESTS");
            request.setAttribute(PAGE, paginationHelper);

            List<Request> requestsOnPage = requests.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
            request.setAttribute(REQUESTS, requestsOnPage);
            requestDispatcher = request.getRequestDispatcher(REQUESTS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (RequestNotFoundException e) {
            requestDispatcher = request.getRequestDispatcher(NOTHING_FOUND_PAGE);
            requestDispatcher.forward(request, response);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);
            requestDispatcher = request.getRequestDispatcher(REQUESTS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}