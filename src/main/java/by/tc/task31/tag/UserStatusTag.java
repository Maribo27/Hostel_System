package by.tc.task31.tag;

import by.tc.task31.controller.constant.StatusLocale;
import by.tc.task31.entity.User;
import by.tc.task31.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;
import java.util.ResourceBundle;

import static by.tc.task31.controller.ControlConst.BASE_NAME;
import static by.tc.task31.controller.ControlConst.LANG_ATTRIBUTE;
import static by.tc.task31.exception.constant.Message.USER_STATUS_TAG_ERROR;

public class UserStatusTag extends TagSupport {
	private final static Logger logger = Logger.getLogger(UserStatusTag.class);
	private static final long serialVersionUID = 2697396836332703037L;
	private User.Status status;

	public void setUserStatus(User.Status status) {
		this.status = status;
	}

	@Override
	public int doStartTag() throws JspException {
		if (status == null) {
			return SKIP_BODY;
		}

		String lang = (String) pageContext.getSession().getAttribute(LANG_ATTRIBUTE);
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(lang));
		String property = StatusLocale.getUserStatus(status);
		String tag = resourceBundle.getString(property);
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tag, USER_STATUS_TAG_ERROR, logger);
	}
}