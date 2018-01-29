package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.util.ControllerUtil;
import by.tc.task31.entity.PaginationHelper;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.UserService;
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
import static by.tc.task31.controller.constant.PageUrl.USERS_INFO_PAGE_URL;

public class ShowUsers implements Command {
    private static final Logger logger = Logger.getLogger(CreateCitiesField.class);
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
        UserService service = factory.getUserService();
        int page = Integer.parseInt(request.getParameter(NUMBER));

        RequestDispatcher requestDispatcher;

        try {
            List<User> users = service.getUsers(lang);
            if (users == null){
                requestDispatcher = request.getRequestDispatcher(USERS_INFO_PAGE_URL);
                requestDispatcher.forward(request, response);
                return;
            }
            PaginationHelper paginationHelper = new ControllerUtil().createPagination(request, page, users.size(), "SHOW_USERS");
            request.setAttribute(PAGE, paginationHelper);

            List<User> usersOnPage = users.subList(paginationHelper.getBegin(), paginationHelper.getEnd());
            request.setAttribute(USERS_ATTRIBUTE, usersOnPage);
            requestDispatcher = request.getRequestDispatcher(USERS_INFO_PAGE_URL);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            ControllerUtil.updateWithErrorMessage(request, response, e.getMessage(), ERROR_PAGE_URL);
        }
    }
}