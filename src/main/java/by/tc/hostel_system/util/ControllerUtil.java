package by.tc.hostel_system.util;

import by.tc.hostel_system.entity.PaginationHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static by.tc.hostel_system.controller.constant.EntityAttributes.*;

public class ControllerUtil {
	private static final int ROWS_ON_PAGE = 15;
	private static final String ERROR = "error";
	private static final String CONTROLLER_COMMAND = "/hostel_system?command=";
	private static final String PAGE = "&number=";

	/**
	 * Creates pagination to current table.
	 *
	 * @param request   user request
	 * @param current   current page
	 * @param size      number of position in list
	 * @param command   next command
	 *
	 * @return  current pagination
	 */
	public static PaginationHelper createPagination(HttpServletRequest request, int current, int size, String command){
		String controllerURL = request.getContextPath() + CONTROLLER_COMMAND + command + PAGE;
		int first = 1;

		PaginationHelper page = new PaginationHelper();

		int lastPage = size / ROWS_ON_PAGE;
		if (size % ROWS_ON_PAGE != 0){
			lastPage++;
		}
		page.setLast(lastPage);

		current = lastPage < current ? lastPage : current;
		page.setCurrent(current);

		int prev = current - 1;
		page.setPrev(prev);

		int next = current + 1;
		page.setNext(next);


		int begin = ROWS_ON_PAGE * prev;
		page.setBegin(begin);

		int end = ROWS_ON_PAGE * current;
		end = end > size ? size : end;
		page.setEnd(end);

		page.setFirstPage(controllerURL + first);
		page.setPrevPage(controllerURL + prev);
		page.setNextPage(controllerURL + next);
		page.setLastPage(controllerURL + lastPage);
		return page;
	}

	/**
	 * Updates page with some message.
	 *
	 * @param request       user request
	 * @param response      user response
	 * @param message       message to show
	 * @param errorPageUrl  url to redirect
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void updateWithMessage(HttpServletRequest request, HttpServletResponse response, String message, String errorPageUrl) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		request.setAttribute(ERROR, message);
		requestDispatcher = request.getRequestDispatcher(errorPageUrl);
		requestDispatcher.forward(request, response);
	}

	/**
	 * Updates page with user exist error message.
	 *
	 * @param request   user request
	 * @param response  user response
	 * @param message   error message
	 * @param username  user username
	 * @param email     user email
	 * @param name      user name
	 * @param surname   user surname
	 * @param lastName  user last name
	 * @param url       url to redirect
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void showUserExistError(HttpServletRequest request, HttpServletResponse response, String message, String username, String email, String name, String surname, String lastName, String url) throws ServletException, IOException {
		request.setAttribute(USERNAME, username);
		request.setAttribute(NAME, name);
		request.setAttribute(SURNAME, surname);
		request.setAttribute(LAST_NAME, lastName);
		request.setAttribute(EMAIL, email);
		ControllerUtil.updateWithMessage(request, response, message, url);
	}

	/**
	 * Calculates end {@link Date} from start date and count of days
	 *
	 * @param days  number of days
	 * @param date  date of booking start
	 *
	 * @return calculated date
	 */
	public static Date getEndDate(int days, Date date) {
		long end = date.getTime() + 86400000 * days;
		return new Date(end);
	}

	/**
	 * Creates query with pagination.
	 *
	 * @param request   user request
	 * @param command   next command name
	 * @param page      current page number
	 *
	 * @return address to redirect
	 */
	public static String createAddressWithPaging(HttpServletRequest request, String command, String page){
		return request.getContextPath() + CONTROLLER_COMMAND + command + PAGE + page;
	}
}