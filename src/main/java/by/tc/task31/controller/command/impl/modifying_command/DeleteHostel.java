package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.HostelService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.task31.controller.command.ControlConst.ERROR_ATTRIBUTE;
import static by.tc.task31.controller.command.ControlConst.HOSTEL;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.HOSTELS_INFO_PAGE_URL;

public class DeleteHostel implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter(HOSTEL));

	    HostelService service = factory.getHostelService();

	    RequestDispatcher requestDispatcher;

	    try {
		    service.deleteHostel(id);
		    requestDispatcher = request.getRequestDispatcher(HOSTELS_INFO_PAGE_URL);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
		    RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
		    dispatcher.forward(request, response);
	    }
    }
}