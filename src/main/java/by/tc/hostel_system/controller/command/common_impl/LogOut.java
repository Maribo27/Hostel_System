package by.tc.hostel_system.controller.command.common_impl;

import by.tc.hostel_system.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.PageUrl.LOGIN_PAGE;

public class LogOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(LOGIN_PAGE);
    }
}