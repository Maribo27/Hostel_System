package by.tc.task31.controller.command.admin_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.user_impl.CreateCitiesField;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.user.UserService;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.tc.task31.controller.constant.ControlConst.LANG;
import static by.tc.task31.controller.constant.ControlConst.NUMBER;
import static by.tc.task31.controller.constant.EntityAttributes.ID;
import static by.tc.task31.controller.constant.PageUrl.BLOCK_PAGE;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE;

public class CreateBlockReasons implements Command {
	private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();
	private static final String REASONS = "reasons";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG);
	    String userId = request.getParameter(ID);
	    String page = request.getParameter(NUMBER);

	    UserService service = factory.getUserService();

        RequestDispatcher requestDispatcher;
        try {
	        Map<Integer, String> reasons = service.getReasons(lang);
	        request.setAttribute(ID, userId);
	        request.setAttribute(REASONS, reasons);
	        request.setAttribute(NUMBER, page);
	        requestDispatcher = request.getRequestDispatcher(BLOCK_PAGE);
	        requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE);
        }
    }
}