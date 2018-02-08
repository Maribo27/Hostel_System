package by.tc.hostel_system.controller.command.admin_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandType;
import by.tc.hostel_system.service.ServiceException;
import by.tc.hostel_system.service.ServiceFactory;
import by.tc.hostel_system.service.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.LANG;
import static by.tc.hostel_system.controller.constant.ControlConst.NUMBER;
import static by.tc.hostel_system.controller.constant.EntityAttributes.ID;
import static by.tc.hostel_system.util.ControllerUtil.createAddressWithPaging;

public class ChangeUserDiscount implements Command {
	private static final Logger logger = Logger.getLogger(ChangeUserDiscount.class);
	private static final String SIGN = "sign";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String userId = request.getParameter(ID);
		String page = request.getParameter(NUMBER);
		String sign = request.getParameter(SIGN);
		String lang = (String) request.getSession().getAttribute(LANG);

		UserService service = ServiceFactory.getInstance().getUserService();
        try {
	        int userDiscount = service.getUserDiscount(lang, userId);
	        int id = Integer.parseInt(userId);
        	service.changeUserDiscount(id, userDiscount, sign, page);
	        String address = createAddressWithPaging(request, CommandType.SHOW_USERS.name(), page);
	        response.sendRedirect(address);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}