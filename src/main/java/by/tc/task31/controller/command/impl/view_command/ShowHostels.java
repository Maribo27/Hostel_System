package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.util.ControllerUtil;
import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.PaginationHelper;
import by.tc.task31.service.HostelService;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.HOSTELS_INFO_PAGE_URL;
import static by.tc.task31.controller.util.ControllerUtil.ROWS_ON_PAGE;

public class ShowHostels implements Command {

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
	        PaginationHelper paginationHelper = new ControllerUtil().createPagination(page, hostels.size(), "SHOW_HOSTELS");
	        request.setAttribute(PAGE, paginationHelper);

	        List<Hostel> hostelsOnPage = hostels.subList(paginationHelper.getBegin(), paginationHelper.getEnd());

	        request.setAttribute(HOSTELS_ATTRIBUTE, hostelsOnPage);
	        requestDispatcher = request.getRequestDispatcher(HOSTELS_INFO_PAGE_URL);
	        requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
            dispatcher.forward(request, response);
        }
    }
}