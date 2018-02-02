package by.tc.task31.util;

import by.tc.task31.tag.TagWritingException;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

public class TagUtil {
	public static int writeTag(JspWriter out, String tag, String message, Logger logger) throws TagWritingException {
		try {
			out.write(tag);
			return SKIP_BODY;
		} catch (IOException e) {
			logger.error(message, e);
			throw new TagWritingException(message, e);
		}
	}
}