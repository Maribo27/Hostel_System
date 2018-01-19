package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.service.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.command.ControlConst.LANG_ATTRIBUTE;
import static by.tc.task31.controller.command.ControlConst.PAGE;
import static by.tc.task31.controller.command.PageUrl.LOGIN_PAGE_URL;

public class ChangeLocale implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = request.getParameter(LANG_ATTRIBUTE);
        String page = request.getParameter(PAGE);
        RequestDispatcher requestDispatcher;

        session.setAttribute(LANG_ATTRIBUTE, lang);
        requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}