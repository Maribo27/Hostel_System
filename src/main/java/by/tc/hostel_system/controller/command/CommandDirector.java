package by.tc.hostel_system.controller.command;

import by.tc.hostel_system.controller.command.admin_impl.*;
import by.tc.hostel_system.controller.command.common_impl.ChangeLocale;
import by.tc.hostel_system.controller.command.common_impl.LogIn;
import by.tc.hostel_system.controller.command.common_impl.LogOut;
import by.tc.hostel_system.controller.command.common_impl.*;
import by.tc.hostel_system.controller.command.user_impl.*;
import by.tc.hostel_system.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDirector {
    private Map<CommandType, CommandRights> commands = new HashMap<>();
    private Map<CommandType, Command> commonCommands = new HashMap<>();
    private static CommandDirector instance = new CommandDirector();

    private CommandDirector() {
        List<User.Status> rights = new ArrayList<>();
        rights.add(User.Status.ADMIN);
        commands.put(CommandType.SHOW_USERS, new CommandRights(rights, new ShowUsers()));
        commands.put(CommandType.SHOW_REQUESTS, new CommandRights(rights, new ShowRequests()));
        commands.put(CommandType.SHOW_HOSTELS, new CommandRights(rights, new ShowHostels()));
        commands.put(CommandType.CHANGE_USER_DISCOUNT, new CommandRights(rights, new ChangeUserDiscount()));
        commands.put(CommandType.APPROVE_REQUEST, new CommandRights(rights, new ApproveRequest()));
        commands.put(CommandType.UNLOCK, new CommandRights(rights, new UnlockUser()));
        commands.put(CommandType.BLOCK, new CommandRights(rights, new BlockUser()));
        commands.put(CommandType.GET_REASONS, new CommandRights(rights, new CreateBlockReasons()));

        rights = new ArrayList<>();
        rights.add(User.Status.ADMIN);
        rights.add(User.Status.USER);
        rights.add(User.Status.BANNED);
        commands.put(CommandType.CANCEL_REQUEST, new CommandRights(rights, new CancelRequest()));
        commands.put(CommandType.LOGOUT, new CommandRights(rights, new LogOut()));
        commands.put(CommandType.CHANGE_LOCALE, new CommandRights(rights, new ChangeLocale()));

        rights = new ArrayList<>();
        rights.add(User.Status.USER);
        rights.add(User.Status.BANNED);
        commands.put(CommandType.SHOW_USER_REQUESTS, new CommandRights(rights, new ShowUserRequests()));
        commands.put(CommandType.CHANGE_USER_DATA, new CommandRights(rights, new ChangeUserData()));
        commands.put(CommandType.CHANGE_PASSWORD, new CommandRights(rights, new ChangePassword()));
        commands.put(CommandType.DELETE_USER, new CommandRights(rights, new DeleteUser()));

        rights = new ArrayList<>();
        rights.add(User.Status.USER);
        commands.put(CommandType.ADD_REQUEST, new CommandRights(rights, new AddRequest()));
        commands.put(CommandType.SHOW_AVAILABLE_HOSTELS, new CommandRights(rights, new ShowAvailableHostels()));
        commands.put(CommandType.GET_CITIES, new CommandRights(rights, new CreateCitiesField()));

        commonCommands.put(CommandType.LOGIN, new LogIn());
        commonCommands.put(CommandType.REGISTER, new Register());
    }

    public Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        if (isCommon(name)) {
            return commonCommands.get(commandName);
        } else {
            return commands.get(commandName).getCommand();
        }
    }

    public void checkAccess(String commandName, User user) throws AccessIsNotAllowedException {
        if (isCommon(commandName) && user == null) {
            return;
        }
        if (!isCommon(commandName) && user != null){
            CommandType type = CommandType.valueOf(commandName);
            CommandRights commandRights = commands.get(type);
            List<User.Status> rights = commandRights.getRights();
            boolean accessGranted = rights.contains(user.getStatus());
            if (accessGranted) {
                return;
            }
        }
        throw new AccessIsNotAllowedException("Access is not allowed");
    }

    private boolean isCommon(String name){
        CommandType commandName = CommandType.valueOf(name);
        return commonCommands.containsKey(commandName);
    }

    public static CommandDirector getInstance() {
        return instance;
    }
}