package by.tc.hostel_system.controller.command.user_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserNotFoundException;
import by.tc.hostel_system.service.user.UserService;
import by.tc.hostel_system.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.LANG;
import static by.tc.hostel_system.controller.constant.ControlConst.Message.PASSWORD_INCORRECT;
import static by.tc.hostel_system.controller.constant.ControlConst.USER;
import static by.tc.hostel_system.controller.constant.PageUrl.PREFERENCES_PAGE;

public class DeleteUser implements Command {
	private static final Logger logger = Logger.getLogger(DeleteUser.class);
    private static final String DELETE = "delete";
	private static final String CONFIRM_PASSWORD = "delete-confirm-password";
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    UserService service = ServiceFactory.getInstance().getUserService();

	    String lang = (String) session.getAttribute(LANG);
	    String password = request.getParameter(CONFIRM_PASSWORD);
	    Object user = session.getAttribute(USER);

		if (password == null){
			ControllerUtil.showHiddenBlock(request, response, DELETE);
			return;
		}

	    try {
		    User newUser = service.getUserInformation(lang, user, password);
		    int id = newUser.getId();
		    service.deleteUser(id);
		    logger.info("User (id = )" + id + "was deleted");
		    String address = request.getContextPath() + "/hostel_system?command=LOGOUT";
		    response.sendRedirect(address);
	    } catch (UserNotFoundException e) {
		    logger.error(e.getMessage(), e);
		    request.setAttribute(DELETE, true);
		    ControllerUtil.updateWithMessage(request, response, PASSWORD_INCORRECT.getMessage(lang), PREFERENCES_PAGE);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
    }
}