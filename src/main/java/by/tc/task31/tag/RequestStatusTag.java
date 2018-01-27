package by.tc.task31.tag;

import by.tc.task31.controller.constant.StatusLocale;
import by.tc.task31.entity.Request;
import by.tc.task31.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;
import java.util.ResourceBundle;

import static by.tc.task31.controller.ControlConst.BASE_NAME;
import static by.tc.task31.controller.ControlConst.LANG_ATTRIBUTE;
import static by.tc.task31.exception.constant.Message.REQUEST_STATUS_TAG_ERROR;

public class RequestStatusTag extends TagSupport {
	private final static Logger logger = Logger.getLogger(RequestStatusTag.class);
	private static final long serialVersionUID = 6259324892174710379L;
	private Request.Status status;

	public void setUserStatus(Request.Status status) {
		this.status = status;
	}

	@Override
	public int doStartTag() throws JspException {
		if (status == null) {
			return SKIP_BODY;
		}

		String lang = (String) pageContext.getSession().getAttribute(LANG_ATTRIBUTE);
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(lang));
		String property = StatusLocale.getRequestStatus(status);
		String tag = resourceBundle.getString(property);
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tag, REQUEST_STATUS_TAG_ERROR, logger);
	}
}