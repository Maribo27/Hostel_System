package by.tc.hostel_system.tag;

import by.tc.hostel_system.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PriceTag extends TagSupport {
	private final static Logger logger = Logger.getLogger(PriceTag.class);
	private static final long serialVersionUID = 4639722300961774680L;
	/**
	 * Number of rooms to book.
	 */
	private int rooms;
	/**
	 * Number of days.
	 */
	private int days;
	/**
	 * Cost of request.
	 */
	private int cost;
	/**
	 * User discount.
	 */
	private int discount;

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public int doStartTag() throws JspException {
		if (rooms == 0 || days == 0 || cost == 0) {
			return SKIP_BODY;
		}

		int price = rooms * days * cost - rooms * days * cost * discount / 100;
		String tag = price + "$";
		JspWriter out = pageContext.getOut();
		pageContext.setAttribute("price", price);
		return TagUtil.writeTag(out, tag, "Cannot write user status tag to page", logger);
	}
}