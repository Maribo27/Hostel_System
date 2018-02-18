package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.entity.PaginationHelper;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.request.RequestNotFoundException;
import by.tc.hostel_system.service.request.RequestService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.PageUrl.HOME_PAGE;

public class RequestApproving implements Command {
	private static final Logger logger = Logger.getLogger(RequestApproving.class);

	/**
	 * The method changes the status of the request of the selected user to "approved".
	 * It redirects to a page with changes on a successful change,
	 * otherwise it redirects to the page with an error.
	 * @see Command#execute(HttpServletRequest, HttpServletResponse)
	 */
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG);
    	String id = request.getParameter(REQUEST);
	    String page = request.getParameter(NUMBER);
	    String command = request.getParameter(NEXT_COMMAND);
	    Object user = session.getAttribute(USER);

	    RequestService service = ServiceFactory.getInstance().getRequestService();
        try {
	        service.approveRequest(id, page);
	        String address = ControllerUtil.createAddressWithPaging(request, command, page);

	        List<Request> requests = service.getRequests(lang, user);
	        PaginationHelper paginationHelper = ControllerUtil.createPagination(request, 1, requests.size(), command);
	        List<Request> requestsOnPage = requests.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
	        session.setAttribute(REQUESTS, requestsOnPage);
	        session.setAttribute(PAGE, paginationHelper);

	        response.sendRedirect(address);
        } catch (RequestNotFoundException e) {
        	session.removeAttribute(REQUESTS);
	        session.removeAttribute(PAGE);
	        response.sendRedirect(HOME_PAGE);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}