package by.tc.task31.controller.command.admin_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.CommandType;
import by.tc.task31.controller.command.user_impl.AddRequest;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.user.UserService;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.task31.controller.constant.ControlConst.NUMBER;
import static by.tc.task31.controller.constant.EntityAttributes.ID;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE;

public class UnlockUser implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
	private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String userId = request.getParameter(ID);
	    String page = request.getParameter(NUMBER);
	    UserService service = factory.getUserService();
        try {
	        service.unlockUser(userId, page);
	        String address = ControllerUtil.createAddressWithPaging(request, CommandType.SHOW_USERS.name(), page);
	        response.sendRedirect(address);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}