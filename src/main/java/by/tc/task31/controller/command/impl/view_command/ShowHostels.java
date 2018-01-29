package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.util.ControllerUtil;
import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.PaginationHelper;
import by.tc.task31.service.HostelService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.constant.PageUrl.HOSTELS_INFO_PAGE_URL;
import static by.tc.task31.controller.constant.PageUrl.REQUESTS_INFO_PAGE_URL;

public class ShowHostels implements Command {
	private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
	    HostelService service = factory.getHostelService();
	    int page = Integer.parseInt(request.getParameter(NUMBER));

	    RequestDispatcher requestDispatcher;

        try {
	        List<Hostel> hostels = service.getHostels(lang);
	        if (hostels == null){
		        requestDispatcher = request.getRequestDispatcher(HOSTELS_INFO_PAGE_URL);
		        requestDispatcher.forward(request, response);
		        return;
	        }
	        PaginationHelper paginationHelper = new ControllerUtil().createPagination(request, page, hostels.size(), "SHOW_HOSTELS");
	        request.setAttribute(PAGE, paginationHelper);

	        List<Hostel> hostelsOnPage = hostels.subList(paginationHelper.getBegin(), paginationHelper.getEnd());

	        request.setAttribute(HOSTELS_ATTRIBUTE, hostelsOnPage);
	        requestDispatcher = request.getRequestDispatcher(HOSTELS_INFO_PAGE_URL);
	        requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
        }
    }
}