package by.tc.hostel_system.tag;

import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;
import java.util.ResourceBundle;

import static by.tc.hostel_system.controller.constant.ControlConst.BASE_NAME;
import static by.tc.hostel_system.controller.constant.ControlConst.LANG;

public class HostelBookingTag extends TagSupport {
	private final static Logger logger = Logger.getLogger(HostelBookingTag.class);
	private static final long serialVersionUID = 6259324892174710379L;
	/**
	 * Type of payment in hostel ("booking" or "payment").
	 */
	private Hostel.Booking bookingType;

	public void setBookingType(Hostel.Booking bookingType) {
		this.bookingType = bookingType;
	}

	@Override
	public int doStartTag() throws JspException {
		if (bookingType == null) {
			return SKIP_BODY;
		}

		String lang = (String) pageContext.getSession().getAttribute(LANG);
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(lang));
		String property = StatusLocale.getBookingType(bookingType);
		String tag = resourceBundle.getString(property);
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tag, "Cannot write booking type tag to page", logger);
	}
}