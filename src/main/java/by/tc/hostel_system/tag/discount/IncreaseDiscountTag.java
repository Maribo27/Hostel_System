package by.tc.hostel_system.tag.discount;

import javax.servlet.jsp.JspException;

public class IncreaseDiscountTag extends DiscountTag {
	private static final long serialVersionUID = 9137788262382091178L;
	private static final String CURRENT_SIGN = "plus";
	private static final String PLUS = "+";

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public int doStartTag() throws JspException {
		return createTag(CURRENT_SIGN, PLUS);
	}
}