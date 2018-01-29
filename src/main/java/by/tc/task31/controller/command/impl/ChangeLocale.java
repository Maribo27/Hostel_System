package by.tc.task31.controller.command.impl;

import by.tc.task31.controller.CommandType;
import by.tc.task31.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static by.tc.task31.controller.ControlConst.*;

public class ChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter(PAGE);
        String lang = request.getParameter(LANG_ATTRIBUTE);

        StringBuilder query = new StringBuilder("?");
        Map<String, String[]> parameters = request.getParameterMap();
        Set<String> keys = parameters.keySet();
        for (String key : keys){
            String values[] = parameters.get(key);
            String value = values[0];
            if (key.equals(LANG_ATTRIBUTE) || key.equals(PAGE)){
                continue;
            }
            if (key.equals(COMMAND_ATTRIBUTE)){
                String currentCommand = CommandType.CHANGE_LOCALE.toString();
                if (values.length == 1){
                    continue;
                }
                if (value.equals(currentCommand)){
                    value = values[1];
                }
            }
            query.append(key);
            query.append("=");
            query.append(value);
            query.append("&");
        }
        String params = query.toString();
        params = params.substring(0,params.length() - 1);
        session.setAttribute(LANG_ATTRIBUTE, lang);
        String address = params.isEmpty() ? page : page + params;
        response.sendRedirect(address);
    }
}