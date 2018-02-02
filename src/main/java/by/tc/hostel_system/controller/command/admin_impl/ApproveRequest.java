package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.user_impl.AddRequest;
import by.tc.hostel_system.service.request.RequestService;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.*;
import static by.tc.hostel_system.controller.constant.PageUrl.ERROR_PAGE;

public class ApproveRequest implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
	private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter(REQUEST);
	    String page = request.getParameter(NUMBER);
	    String command = request.getParameter(NEXT_COMMAND);
	    RequestService service = factory.getRequestService();

        try {
	        service.approveRequest(id, page);
	        String address = ControllerUtil.createAddressWithPaging(request, command, page);
	        response.sendRedirect(address);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}