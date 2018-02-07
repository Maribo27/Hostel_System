package by.tc.hostel_system.filter;

import by.tc.hostel_system.controller.command.AccessIsNotAllowedException;
import by.tc.hostel_system.controller.command.CommandDirector;
import by.tc.hostel_system.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.COMMAND;
import static by.tc.hostel_system.controller.constant.ControlConst.USER;

public class Access implements Filter {
	private static final Logger logger = Logger.getLogger(Access.class);
	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String command = servletRequest.getParameter(COMMAND);
		if (command == null){
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		HttpSession session = ((HttpServletRequest)servletRequest).getSession();
		User user = (User) session.getAttribute(USER);
		CommandDirector director = CommandDirector.getInstance();
		try {
			director.checkAccess(command, user);
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (AccessIsNotAllowedException e) {
			logger.error(e.getMessage(), e);
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public void destroy() {

	}
}
