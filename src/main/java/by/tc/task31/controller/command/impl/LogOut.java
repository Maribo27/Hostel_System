package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tc.task31.controller.command.PageUrl.LOGIN_PAGE_URL;

public class LogOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(LOGIN_PAGE_URL);
    }
}