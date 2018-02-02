package by.tc.task31.controller.command.common_impl;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.CommandType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static by.tc.task31.controller.constant.ControlConst.*;

public class ChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter(PAGE);
        String lang = request.getParameter(LANG);

        String params = getParams(request);
        params = params.substring(0,params.length() - 1);
        session.setAttribute(LANG, lang);
        String address = params.isEmpty() ? page : page + params;
        response.sendRedirect(address);
    }

    private String getParams(HttpServletRequest request) {
        StringBuilder query = new StringBuilder("?");
        Map<String, String[]> parameters = request.getParameterMap();
        Set<String> keys = parameters.keySet();
        for (String key : keys){
            String values[] = parameters.get(key);
            String value = values[0];
            if (key.equals(LANG) || key.equals(PAGE)){
                continue;
            }
            if (key.equals(COMMAND)){
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
        return query.toString();
    }
}