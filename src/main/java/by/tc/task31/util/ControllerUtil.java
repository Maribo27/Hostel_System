package by.tc.task31.util;

import by.tc.task31.entity.PaginationHelper;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {
	private static final int ROWS_ON_PAGE = 5;
	private static final String CONTROLLER_COMMAND = "/hostel_system?command=";
	private static final String PAGE = "&number=";

	public PaginationHelper createPagination(HttpServletRequest request, int current, int size, String command){
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
}