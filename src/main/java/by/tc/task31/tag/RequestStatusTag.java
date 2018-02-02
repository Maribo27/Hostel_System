package by.tc.task31.tag;

import by.tc.task31.entity.Request;
import by.tc.task31.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;
import java.util.ResourceBundle;

import static by.tc.task31.controller.constant.ControlConst.BASE_NAME;
import static by.tc.task31.controller.constant.ControlConst.LANG;

public class RequestStatusTag extends TagSupport {
	private final static Logger logger = Logger.getLogger(RequestStatusTag.class);
	private static final long serialVersionUID = 6259324892174710379L;
	private Request.Status requestStatus;

	public void setRequestStatus(Request.Status requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public int doStartTag() throws JspException {
		if (requestStatus == null) {
			return SKIP_BODY;
		}

		String lang = (String) pageContext.getSession().getAttribute(LANG);
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(lang));
		String property = StatusLocale.getRequestStatus(requestStatus);
		String tag = resourceBundle.getString(property);
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tag, "Cannot write request status tag to page", logger);
	}
}