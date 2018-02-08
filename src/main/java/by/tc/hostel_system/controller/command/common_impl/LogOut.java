package by.tc.hostel_system.controller.command.common_impl;

import by.tc.hostel_system.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.LANG;
import static by.tc.hostel_system.controller.constant.PageUrl.LOGIN_PAGE;

public class LogOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG);
        session.invalidate();
        request.getSession().setAttribute(LANG, lang);
        response.sendRedirect(LOGIN_PAGE);
    }
}