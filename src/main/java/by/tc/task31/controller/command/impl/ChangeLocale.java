package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.task31.controller.command.ControlConst.CURRENT_PAGE;
import static by.tc.task31.controller.command.ControlConst.LANG_ATTRIBUTE;
import static by.tc.task31.controller.command.ControlConst.PAGE;

public class ChangeLocale implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StringBuffer requestURL = request.getRequestURL();
        String lang = request.getParameter(LANG_ATTRIBUTE);
        String page = (String) session.getAttribute(CURRENT_PAGE);
        RequestDispatcher requestDispatcher;

        session.setAttribute(LANG_ATTRIBUTE, lang);
        requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}