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
	private static final int ROWS_ON_PAGE = 5;
	private static final String ERROR = "error";
	private static final String CONTROLLER_COMMAND = "/hostel_system?command=";
	private static final String PAGE = "&number=";

	public static PaginationHelper createPagination(HttpServletRequest request, int current, int size, String command){
		String controllerURL = request.getContextPath() + CONTROLLER_COMMAND + command + PAGE;
		int first = 1;

		PaginationHelper page = new PaginationHelper();

		page.setCurrent(current);

		int prev = current - 1;
		page.setPrev(prev);

		int next = current + 1;
		page.setNext(next);

		int lastPage = size / ROWS_ON_PAGE;
		if (size % ROWS_ON_PAGE != 0){
			lastPage++;
		}
		page.setLast(lastPage);

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

	public static void updateWithErrorMessage(HttpServletRequest request, HttpServletResponse response, String message, String errorPageUrl) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		request.setAttribute(ERROR, message);
		requestDispatcher = request.getRequestDispatcher(errorPageUrl);
		requestDispatcher.forward(request, response);
	}

	public static void showUserExistError(HttpServletRequest request, HttpServletResponse response, String username, String email, String name, String surname, String lastname, String url) throws ServletException, IOException {
		request.setAttribute(USERNAME, username);
		request.setAttribute(NAME, name);
		request.setAttribute(SURNAME, surname);
		request.setAttribute(LASTNAME, lastname);
		request.setAttribute(EMAIL, email);
		ControllerUtil.updateWithErrorMessage(request, response, "This user exist", url);
	}

	public static Date getEndDate(int days, Date date) {
		long end = date.getTime() + 86400000 * days;
		return new Date(end);
	}

	public static String createAddressWithPaging(HttpServletRequest request, String command, String page){
		return request.getContextPath() + CONTROLLER_COMMAND + command + PAGE + page;
	}
}