package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.util.ControllerUtil;
import by.tc.task31.entity.PaginationHelper;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;
import by.tc.task31.service.RequestService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.REQUESTS_INFO_PAGE_URL;

public class ShowUserRequests implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
        int userId = ((User)session.getAttribute(USER_ATTRIBUTE)).getId();
        RequestService service = factory.getRequestService();
        int page = Integer.parseInt(request.getParameter(NUMBER));

        RequestDispatcher requestDispatcher;

        try {
            List<Request> requests = service.getRequests(lang, userId);
            PaginationHelper paginationHelper = new ControllerUtil().createPagination(page, requests.size(), "SHOW_USER_REQUESTS");
            request.setAttribute(PAGE, paginationHelper);

            List<Request> requestsOnPage = requests.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
            request.setAttribute(REQUESTS_ATTRIBUTE, requestsOnPage);
            requestDispatcher = request.getRequestDispatcher(REQUESTS_INFO_PAGE_URL);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
            dispatcher.forward(request, response);
        }
    }
}