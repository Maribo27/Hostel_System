package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
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
import java.sql.Date;

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.USER_INFO_PAGE_URL;

public class AddRequest implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
	    HttpSession session = request.getSession();
	    int userId = ((User)session.getAttribute(USER_ATTRIBUTE)).getId();
	    int hostelId = Integer.parseInt(request.getParameter(HOSTEL));
	    String type = request.getParameter(TYPE);
	    int rooms = Integer.parseInt(request.getParameter(ROOMS));
	    int days = Integer.parseInt(request.getParameter(DAYS));
	    int cost = Integer.parseInt(request.getParameter(COST));
	    Date date = Date.valueOf(request.getParameter(DATE));
	    int discount = ((User)session.getAttribute(USER_ATTRIBUTE)).getDiscount();
	    int price = rooms * days * cost * discount / 100;

	    RequestService service = factory.getRequestService();

	    RequestDispatcher requestDispatcher;

	    Request userRequest = new Request();
	    userRequest.setUserId(userId);
	    userRequest.setHostelId(hostelId);
	    userRequest.setType(type);
	    userRequest.setRoom(rooms);
	    userRequest.setDays(days);
	    userRequest.setCost(price);
	    userRequest.setDate(date);

	    service.addRequest(userRequest);

	    requestDispatcher = request.getRequestDispatcher(USER_INFO_PAGE_URL);
	    requestDispatcher.forward(request, response);
    }
}