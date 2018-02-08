package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.NUMBER;
import static by.tc.hostel_system.controller.constant.EntityAttributes.ID;

public class UnlockUser implements Command {
	private static final Logger logger = Logger.getLogger(UnlockUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String userId = request.getParameter(ID);
	    String page = request.getParameter(NUMBER);

	    UserService service = ServiceFactory.getInstance().getUserService();
        try {
	        service.unlockUser(userId, page);
	        String address = ControllerUtil.createAddressWithPaging(request, CommandType.SHOW_USERS.name(), page);
	        response.sendRedirect(address);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}