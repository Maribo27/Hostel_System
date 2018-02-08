package by.tc.hostel_system.controller.command.common_impl;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.util.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.LANG;
import static by.tc.hostel_system.controller.constant.ControlConst.PAGE;

public class ChangeLocale implements Command {
    private static final String PARAM = "param";
    private static final String SERVLET = "/hostel_system";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter(PAGE);
        String lang = request.getParameter(LANG);
        String parameters = request.getParameter(PARAM);
        session.setAttribute(LANG, lang);
        String address = parameters.isEmpty() ? page : SERVLET + "?" + parameters;
        response.sendRedirect(address);
    }
}