package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;
import by.tc.task31.service.RequestService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.util.ControllerUtil.getEndDate;

public class AddRequest implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();

	    int userId = ((User)session.getAttribute(USER_ATTRIBUTE)).getId();
	    int balance = ((User)session.getAttribute(USER_ATTRIBUTE)).getBalance();
	    int hostelId = Integer.parseInt(request.getParameter(HOSTEL));

	    String type = request.getParameter(TYPE);

	    int rooms = Integer.parseInt(request.getParameter(ROOMS));
	    int days = Integer.parseInt(request.getParameter(DAYS));
	    int cost = Integer.parseInt(request.getParameter(COST));

	    Date date = Date.valueOf(request.getParameter(DATE));
	    Date endDate = getEndDate(days, date);

	    int discount = ((User)session.getAttribute(USER_ATTRIBUTE)).getDiscount();
	    int price = rooms * days * cost - rooms * days * cost * discount / 100;

	    RequestService service = factory.getRequestService();

	    Request userRequest = new Request();
	    userRequest.setUserId(userId);
	    userRequest.setHostelId(hostelId);
	    userRequest.setType(type);
	    userRequest.setRoom(rooms);
	    userRequest.setDays(days);
	    userRequest.setCost(price);
	    userRequest.setDate(date);
	    userRequest.setEndDate(endDate);

	    try {
	    	if (type.equals("payment") && balance < price) {
			    ControllerUtil.updateWithErrorMessage(request, response, "Not enough money", ERROR_PAGE_URL);
			    return;
		    }
		    if (type.equals("booking")) {
			    balance += price;
		    }
		    balance = service.addRequest(userRequest, balance);
	    	User user = ((User)session.getAttribute(USER_ATTRIBUTE));
	    	user.setBalance(balance);
	    	session.setAttribute(USER_ATTRIBUTE, user);

		    String address = request.getContextPath() + "/hostel_system?command=SHOW_USER_REQUESTS&number=1";
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
	    }
    }
}