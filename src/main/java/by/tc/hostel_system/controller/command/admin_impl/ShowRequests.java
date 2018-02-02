package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.controller.command.user_impl.CreateCitiesField;
import by.tc.hostel_system.service.request.RequestNotFoundException;
import by.tc.hostel_system.util.ControllerUtil;
import by.tc.hostel_system.entity.PaginationHelper;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.service.request.RequestService;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
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
import static by.tc.hostel_system.controller.constant.PageUrl.NOTHING_FOUND_PAGE;
import static by.tc.hostel_system.controller.constant.PageUrl.REQUESTS_PAGE;

public class ShowRequests implements Command {
    private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG);
        RequestService service = factory.getRequestService();
        String page = request.getParameter(NUMBER);
        RequestDispatcher requestDispatcher;
        try {
            List<Request> requests = service.getRequests(lang, page);
            int currentPage = Integer.parseInt(page);
            String command = CommandType.SHOW_REQUESTS.name();
            PaginationHelper paginationHelper = ControllerUtil.createPagination(request, currentPage, requests.size(), command);
            request.setAttribute(PAGE, paginationHelper);
            List<Request> requestsOnPage = requests.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
            request.setAttribute(REQUESTS, requestsOnPage);
            requestDispatcher = request.getRequestDispatcher(REQUESTS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (RequestNotFoundException e) {
            logger.error(e.getMessage(), e);
            requestDispatcher = request.getRequestDispatcher(NOTHING_FOUND_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}