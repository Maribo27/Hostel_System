package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.UserService;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.task31.controller.ControlConst.ID;
import static by.tc.task31.controller.ControlConst.NUMBER;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;

public class UnlockUser implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
	private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int userId = Integer.parseInt(request.getParameter(ID));
	    int page = Integer.parseInt(request.getParameter(NUMBER));

	    UserService service = factory.getUserService();

        try {
	        service.unlockUser(userId);
	        String address = request.getContextPath() + "/hostel_system?command=SHOW_USERS&number=" + page;
	        response.sendRedirect(address);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
        }
    }
}