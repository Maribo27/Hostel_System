package by.tc.task31.controller;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.impl.LogIn;
import by.tc.task31.controller.command.impl.Register;

import java.util.HashMap;
import java.util.Map;

class CommandDirector {
    private Map<CommandType, Command> commands = new HashMap<>();

    CommandDirector() {
        commands.put(CommandType.LOGIN, new LogIn());
        commands.put(CommandType.REGISTER, new Register());
    }

    Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        return commands.get(commandName);
    }
}
