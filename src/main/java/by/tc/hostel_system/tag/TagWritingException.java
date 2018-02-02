package by.tc.hostel_system.tag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class TagWritingException extends JspException {
	private static final long serialVersionUID = -1427859375759651282L;

	public TagWritingException(String message, IOException e) {
	}
}
