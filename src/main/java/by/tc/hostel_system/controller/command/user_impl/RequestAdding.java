package by.tc.hostel_system.controller.command.user_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.entity.PaginationHelper;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.request.RequestService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.EntityAttributes.*;

public class RequestAdding implements Command {
	private static final Logger logger = Logger.getLogger(RequestAdding.class);
	private static final String HOSTEL = "hostel";
	private static final String COST = "cost";

	/**
	 * The method adds new request.
	 * It redirects to the requests page on a successful search,
	 * otherwise it redirects to the page with an error.
	 * @see Command#execute(HttpServletRequest, HttpServletResponse)
	 */
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession session = request.getSession();

	    String type = request.getParameter(TYPE);
	    String hostelId = request.getParameter(HOSTEL);
	    String rooms = request.getParameter(ROOMS);
	    String days = request.getParameter(DAYS);
	    String cost = request.getParameter(COST);
	    String date = request.getParameter(DATE);
	    String lang = (String) session.getAttribute(LANG);

	    Object user = session.getAttribute(USER);

	    RequestService service = ServiceFactory.getInstance().getRequestService();
	    try {
		    int balance = service.addRequest(user, hostelId, type, rooms, days, date, cost);
		    User newUser = (User) user;
		    newUser.setBalance(balance);
		    session.setAttribute(USER, newUser);

		    List<Request> requests = service.getRequests(lang, user);
		    String nextCommand = CommandType.SHOW_USER_REQUESTS.name();
		    PaginationHelper paginationHelper = ControllerUtil.createPagination(request, 1, requests.size(), nextCommand);
		    List<Request> requestsOnPage = requests.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
		    session.setAttribute(REQUESTS, requestsOnPage);
		    session.setAttribute(PAGE, paginationHelper);

		    String address = ControllerUtil.createAddressWithPaging(request, CommandType.SHOW_USER_REQUESTS.name(), "1");
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
    }
}