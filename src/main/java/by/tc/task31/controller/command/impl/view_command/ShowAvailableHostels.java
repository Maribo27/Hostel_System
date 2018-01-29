package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.PaginationHelper;
import by.tc.task31.service.HostelService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static by.tc.task31.controller.ControlConst.*;
import static by.tc.task31.controller.constant.PageUrl.CREATE_REQUEST_URL;
import static by.tc.task31.controller.constant.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.constant.PageUrl.HOSTELS_INFO_PAGE_URL;
import static by.tc.task31.util.ControllerUtil.getEndDate;

public class ShowAvailableHostels implements Command {
	private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();

	    String lang = (String) session.getAttribute(LANG_ATTRIBUTE);

	    int city = Integer.parseInt(request.getParameter(CITY));
	    int rooms = Integer.parseInt(request.getParameter(ROOMS));
	    int days = Integer.parseInt(request.getParameter(DAYS));
	    int page = Integer.parseInt(request.getParameter(NUMBER));
	    String type = request.getParameter(TYPE);

	    Date date = Date.valueOf(request.getParameter(DATE));
	    Date endDate = getEndDate(days, date);

	    HostelService service = factory.getHostelService();
	    RequestDispatcher requestDispatcher;
	    try {
		    List<Hostel> hostels = service.getHostels(lang, city, rooms, date, endDate);
		    if (hostels != null){
			    PaginationHelper paginationHelper = new ControllerUtil().createPagination(request, page, hostels.size(), "SHOW_HOSTELS");
			    request.setAttribute(PAGE, paginationHelper);

			    List<Hostel> hostelsOnPage = hostels.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
			    request.setAttribute(HOSTELS_ATTRIBUTE, hostelsOnPage);
		    }

		    request.setAttribute(TYPE, type);
		    request.setAttribute(CITY, city);
		    request.setAttribute(ROOMS, rooms);
		    request.setAttribute(DAYS, days);
		    request.setAttribute(DATE, date);
		    requestDispatcher = request.getRequestDispatcher(CREATE_REQUEST_URL);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
	    }
    }
}