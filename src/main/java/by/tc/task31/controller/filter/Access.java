package by.tc.task31.controller.filter;

import by.tc.task31.controller.CommandDirector;
import by.tc.task31.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.ControlConst.*;

public class Access implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String command = servletRequest.getParameter(COMMAND);
		if (command == null){
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		boolean isAccessGranted = checkAccess(servletRequest.getParameter(COMMAND), (HttpServletRequest) servletRequest);
		if (isAccessGranted) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public void destroy() {

	}

	private boolean checkAccess(String command, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(USER_ATTRIBUTE);
		if (user == null) {
			return false;
		}
		String status = user.getStatus();
		CommandDirector director = CommandDirector.getInstance();
		String access = director.getStatus(command);
		return status.equals(access) || access.equals(BANNED);
	}
}
