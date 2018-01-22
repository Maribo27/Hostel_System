package by.tc.task31.controller.command.impl.view_command;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.task31.controller.command.PageUrl.PREFERENCES_PAGE_URL;

public class ShowPreferences implements Command {
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher requestDispatcher;
		requestDispatcher = request.getRequestDispatcher(PREFERENCES_PAGE_URL);
		requestDispatcher.forward(request, response);
	}
}