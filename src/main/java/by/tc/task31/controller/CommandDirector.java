package by.tc.task31.controller;

import by.tc.task31.controller.command.Command;
import by.tc.task31.controller.command.impl.BlockPage;
import by.tc.task31.controller.command.impl.ChangeLocale;
import by.tc.task31.controller.command.impl.LogIn;
import by.tc.task31.controller.command.impl.LogOut;
import by.tc.task31.controller.command.impl.modifying_command.*;
import by.tc.task31.controller.command.impl.view_command.*;

import java.util.HashMap;
import java.util.Map;

import static by.tc.task31.controller.ControlConst.*;

public class CommandDirector {
    private Map<CommandType, Command> commands = new HashMap<>();
    private Map<CommandType, Command> userCommands = new HashMap<>();
    private Map<CommandType, Command> adminCommands = new HashMap<>();
    private static CommandDirector instance = new CommandDirector();

    CommandDirector() {
        createCommonCommands();
        createAdminCommands();
        createUserCommands();
    }

    private void createAdminCommands() {
        adminCommands.put(CommandType.SHOW_USERS, new ShowUsers());
        adminCommands.put(CommandType.SHOW_REQUESTS, new ShowRequests());
        adminCommands.put(CommandType.SHOW_HOSTELS, new ShowHostels());
        adminCommands.put(CommandType.DELETE_HOSTEL, new DeleteHostel());
        adminCommands.put(CommandType.CHANGE_REQUEST_STATUS, new ChangeRequestStatus());
        adminCommands.put(CommandType.UNLOCK, new UnlockUser());
        adminCommands.put(CommandType.BLOCK, new BlockUser());
        adminCommands.put(CommandType.OPEN_BLOCK_PAGE, new BlockPage());
    }

    private void createCommonCommands() {
        commands.put(CommandType.LOGIN, new LogIn());
        commands.put(CommandType.LOGOUT, new LogOut());
        commands.put(CommandType.REGISTER, new Register());
        commands.put(CommandType.SHOW_USER_REQUESTS, new ShowUserRequests());
        commands.put(CommandType.DELETE_USER, new DeleteUser());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocale());
    }

    private void createUserCommands() {
        userCommands.put(CommandType.CHANGE_USER_DATA, new ChangeUserData());
        userCommands.put(CommandType.CHANGE_PASSWORD, new ChangePassword());
        userCommands.put(CommandType.DELETE_REQUEST, new DeleteRequest());
        userCommands.put(CommandType.ADD_REQUEST, new AddRequest());
        userCommands.put(CommandType.CREATE_REQUEST, new CreateRequest());
        userCommands.put(CommandType.SHOW_CREATING_FORM, new ShowCreatingForm());
    }

    Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        if (isAdminCommand(name)) {
            return adminCommands.get(commandName);
        } else return isUserCommand(name) ? userCommands.get(commandName) : commands.get(commandName);
    }

    public String getStatus(String name) {
        if (isAdminCommand(name)) {
            return ADMIN;
        } else return isUserCommand(name) ? USER : BANNED;
    }

    private boolean isAdminCommand(String name){
        CommandType commandName = CommandType.valueOf(name);
        return adminCommands.containsKey(commandName);
    }

    private boolean isUserCommand(String name){
        CommandType commandName = CommandType.valueOf(name);
        return userCommands.containsKey(commandName);
    }

    public static CommandDirector getInstance() {
        return instance;
    }
}