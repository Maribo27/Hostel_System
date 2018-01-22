package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.entity.User;
import by.tc.task31.service.ServiceException;
import by.tc.task31.service.ServiceFactory;
import by.tc.task31.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tc.task31.controller.command.ControlConst.*;
import static by.tc.task31.controller.command.PageUrl.ERROR_PAGE_URL;
import static by.tc.task31.controller.command.PageUrl.USERS_INFO_PAGE_URL;

public class ShowUsers implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
        UserService service = factory.getUserService();
        int page = Integer.parseInt(request.getParameter(NUMBER)) - 1;
        int next = page + 2;

        RequestDispatcher requestDispatcher;

        try {
            List<User> users = service.getUsers(lang);
            request.setAttribute(FIRST_ATTRIBUTE, 0);
            request.setAttribute(PREV_ATTRIBUTE, page - 1);
            request.setAttribute(NEXT_ATTRIBUTE, next - 1);

            int lastPage = users.size() / ROWS_ON_PAGE - 1;
            if (users.size() / ROWS_ON_PAGE != 0){
                lastPage++;
            }
            request.setAttribute(LAST_ATTRIBUTE, lastPage);

            request.setAttribute(SIZE_ATTRIBUTE, users.size());

            request.setAttribute(BEGIN_ATTRIBUTE, page * ROWS_ON_PAGE);
            int end = page * ROWS_ON_PAGE + ROWS_ON_PAGE - 1;
            if (page == lastPage) {
                end = users.size() - 1;
            }
            request.setAttribute(END_ATTRIBUTE, end);

            request.setAttribute(GO_TO_FIRST, "Controller?command=SHOW_USERS&number=1");
            request.setAttribute(GO_TO_LAST, "Controller?command=SHOW_USERS&number=" + lastPage);
            request.setAttribute(GO_TO_NEXT, "Controller?command=SHOW_USERS&number=" + next);
            request.setAttribute(GO_TO_PREV, "Controller?command=SHOW_USERS&number=" + page);
            request.setAttribute(USERS_ATTRIBUTE, users);
            requestDispatcher = request.getRequestDispatcher(USERS_INFO_PAGE_URL);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
            dispatcher.forward(request, response);
        }
    }
}