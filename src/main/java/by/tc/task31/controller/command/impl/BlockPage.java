package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.impl.view_command.CreateCitiesField;
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
import java.util.Map;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.BLOCK_PAGE_URL;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;

public class BlockPage implements Command {
	private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
	    int userId = Integer.parseInt(request.getParameter(ID));
	    int page = Integer.parseInt(request.getParameter(NUMBER));

	    UserService service = factory.getUserService();

        RequestDispatcher requestDispatcher;
        try {
	        Map<Integer, String> reasons = service.getReasons(lang);
	        request.setAttribute(ID, userId);
	        request.setAttribute(REASONS, reasons);
	        request.setAttribute(NUMBER, page);
	        requestDispatcher = request.getRequestDispatcher(BLOCK_PAGE_URL);
	        requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
        }
    }
}