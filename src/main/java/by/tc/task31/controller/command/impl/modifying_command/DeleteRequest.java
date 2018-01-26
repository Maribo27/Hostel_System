package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.RequestService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.REQUESTS_INFO_PAGE_URL;

public class DeleteRequest implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter(REQUEST));

	    RequestService service = factory.getRequestService();

	    RequestDispatcher requestDispatcher;

	    try {
		    service.deleteRequest(id);
		    requestDispatcher = request.getRequestDispatcher(REQUESTS_INFO_PAGE_URL);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
		    RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
		    dispatcher.forward(request, response);
	    }
    }
}