package by.tc.task31.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
