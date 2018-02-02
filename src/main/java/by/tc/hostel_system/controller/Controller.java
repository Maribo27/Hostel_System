package by.tc.hostel_system.controller;

import by.tc.hostel_system.controller.command.Command;
import by.tc.hostel_system.controller.command.CommandDirector;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.tc.hostel_system.controller.constant.ControlConst.COMMAND;

public class Controller extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html";

    private final CommandDirector director = CommandDirector.getInstance();
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        String commandName = request.getParameter(COMMAND);
        Command command = director.getCommand(commandName);
        command.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
