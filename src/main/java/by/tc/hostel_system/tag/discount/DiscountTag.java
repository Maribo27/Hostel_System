package by.tc.hostel_system.tag.discount;

import by.tc.hostel_system.tag.TagWritingException;
import by.tc.hostel_system.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class DiscountTag extends TagSupport {
	private final static Logger logger = Logger.getLogger(DiscountTag.class);
	private static final long serialVersionUID = 1636675895743519719L;
	private static final String A_HREF = "<a href=\"";
	private static final String HOSTEL_SYSTEM = "/hostel_system?command=CHANGE_USER_DISCOUNT&number=";
	private static final String ID = "&id=";
	private static final String SIGN = "&sign=";
	private static final String HREF_END = "\">";
	private static final String A = "</a>";
	public int id;
	public int page;

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public void setPage(int page) {
		this.page = page;
	}

	int createTag(String commandSign, String tagSign) throws TagWritingException {
		if (id == 0 || page == 0) {
			return SKIP_BODY;
		}
		String context = pageContext.getServletContext().getContextPath();
		String tag = String.format("%s%s%s%d%s%d%s%s%s%s%s", A_HREF, context, HOSTEL_SYSTEM, page, ID, id, SIGN, commandSign, HREF_END, tagSign, A);
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tag, "Cannot write request status tag to page", logger);
	}
}