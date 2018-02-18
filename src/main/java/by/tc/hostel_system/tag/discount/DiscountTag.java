package by.tc.hostel_system.tag.discount;

import by.tc.hostel_system.tag.TagWritingException;
import by.tc.hostel_system.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DiscountTag extends TagSupport {
	private final static Logger logger = Logger.getLogger(DiscountTag.class);
	private static final long serialVersionUID = 1636675895743519719L;
	private static final String A_HREF = "<a href=\"";
	private static final String HOSTEL_SYSTEM = "/hostel_system?command=CHANGE_USER_DISCOUNT&number=";
	private static final String ID = "&id=";
	private static final String SIGN = "&sign=";
	private static final String HREF_END = "\">";
	private static final String A = "</a>";
	/**
	 * Current user id.
	 */
	public int id;
	/**
	 * current page number.
	 */
	public int page;

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * Creates discount tag.
	 *
	 * @param commandSign   sign of coefficient ("plus" if increasing, "minus" - otherwise) for command
	 * @param tagSign       sign of coefficient ("+" if increasing, "-" - otherwise) for showing on page
	 *
	 * @return tag body result
	 *
	 * @throws TagWritingException if error occurred while writing tag
	 */
	int createTag(String commandSign, String tagSign) throws TagWritingException {
		if (id == 0 || page == 0) {
			return SKIP_BODY;
		}
		String context = pageContext.getServletContext().getContextPath();
		StringBuilder tagBuilder = new StringBuilder();
		tagBuilder.append(A_HREF);
		tagBuilder.append(context);
		tagBuilder.append(HOSTEL_SYSTEM);
		tagBuilder.append(page);
		tagBuilder.append(ID);
		tagBuilder.append(id);
		tagBuilder.append(SIGN);
		tagBuilder.append(commandSign);
		tagBuilder.append(HREF_END);
		tagBuilder.append(tagSign);
		tagBuilder.append(A);
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tagBuilder.toString(), "Cannot write request status tag to page", logger);
	}
}