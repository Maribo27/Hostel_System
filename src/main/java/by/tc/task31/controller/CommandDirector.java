package by.tc.task31.controller;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

class CommandDirector {
    private Map<CommandType, Command> commands = new HashMap<>();

    CommandDirector() {
        commands.put(CommandType.LOGIN, new LogIn());
        commands.put(CommandType.LOGOUT, new LogOut());
        commands.put(CommandType.REGISTER, new Register());

        commands.put(CommandType.SHOW_USERS, new ShowUsers());
        commands.put(CommandType.SHOW_REQUESTS, new ShowRequests());
        commands.put(CommandType.SHOW_USER_REQUESTS, new ShowUserRequests());
        commands.put(CommandType.SHOW_HOSTELS, new ShowHostels());
        commands.put(CommandType.SHOW_PREFERENCES, new ShowPreferences());

        commands.put(CommandType.DELETE_HOSTEL, new DeleteHostel());
        commands.put(CommandType.DELETE_USER, new DeleteUser());

        commands.put(CommandType.ADD_REQUEST, new AddRequest());

        commands.put(CommandType.CHANGE_REQUEST_STATUS, new ChangeRequestStatus());
        commands.put(CommandType.CHANGE_USER_DATA, new ChangeUserData());
        commands.put(CommandType.UNLOCK, new UnlockUser());
        commands.put(CommandType.BLOCK, new BlockUser());
        commands.put(CommandType.OPEN_BLOCK_PAGE, new BlockPage());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocale());
        commands.put(CommandType.CREATE_REQUEST, new CreateRequest());
        commands.put(CommandType.SHOW_CREATING_FORM, new ShowCreatingForm());
    }

    Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        return commands.get(commandName);
    }
}