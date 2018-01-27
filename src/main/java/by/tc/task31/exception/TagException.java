package by.tc.task31.exception;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class TagException extends JspException {
	private static final long serialVersionUID = -1427859375759651282L;

	public TagException(String message, IOException e) {
	}
}
