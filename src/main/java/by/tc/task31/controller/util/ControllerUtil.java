package by.tc.task31.controller.util;

import by.tc.task31.entity.PaginationHelper;

public class ControllerUtil {
	public static final int ROWS_ON_PAGE = 5;
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String PAGE = "&number=";

	public PaginationHelper createPagination(int current, int size, String command){
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

		page.setFirstPage(CONTROLLER_COMMAND + command + PAGE + first);
		page.setPrevPage(CONTROLLER_COMMAND + command + PAGE + prev);
		page.setNextPage(CONTROLLER_COMMAND + command + PAGE + next);
		page.setLastPage(CONTROLLER_COMMAND + command + PAGE + lastPage);
		return page;
	}
}