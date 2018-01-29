package by.tc.task31.controller.command.impl.modifying_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.UserService;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.constant.PageUrl.PREFERENCES_URL;

public class DeleteUser implements Command {
	private static final Logger logger = Logger.getLogger(AddRequest.class);
    private ServiceFactory factory = ServiceFactory.getInstance();
    private static final String CONFIRM = "confirm";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    UserService service = factory.getUserService();

	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
	    String password = request.getParameter(CONFIRM_PASSWORD);
	    User newUser = (User) session.getAttribute(USER_ATTRIBUTE);

	    if (password == null){
	    	request.setAttribute(CONFIRM, true);
		    RequestDispatcher requestDispatcher;
		    requestDispatcher = request.getRequestDispatcher(PREFERENCES_URL);
		    requestDispatcher.forward(request, response);
		    return;
	    }

	    try {
		    newUser = service.getUserInformation(lang, newUser.getUsername(), password);
		    if (newUser == null) {
			    request.setAttribute(CONFIRM, true);
			    ControllerUtil.updateWithErrorMessage(request, response, INVALID_PASSWORD_MESSAGE, PREFERENCES_URL);
			    return;
		    }

		    int id = ((User)session.getAttribute(USER_ATTRIBUTE)).getId();
		    service.deleteUser(id);
		    logger.info("User (id = )" + id + "was deleted");
		    String address = request.getContextPath() + "/hostel_system?command=LOGOUT";
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
	    }
    }
}