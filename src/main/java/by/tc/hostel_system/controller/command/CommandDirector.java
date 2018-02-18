package by.tc.hostel_system.controller.command;

import by.tc.hostel_system.controller.command.admin_impl.*;
import by.tc.hostel_system.controller.command.common_impl.LocaleChanging;
import by.tc.hostel_system.controller.command.common_impl.Authorization;
import by.tc.hostel_system.controller.command.common_impl.LogOff;
import by.tc.hostel_system.controller.command.common_impl.*;
import by.tc.hostel_system.controller.command.user_impl.*;
import by.tc.hostel_system.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDirector {
    private Map<CommandType, CommandRights> commands = new HashMap<>();
    private Map<CommandType, Command> authorizationCommands = new HashMap<>();
    private static CommandDirector instance = new CommandDirector();

    private CommandDirector() {
        List<User.Status> rights = new ArrayList<>();
        rights.add(User.Status.ADMIN);
        commands.put(CommandType.SHOW_USERS, new CommandRights(rights, new AllUsers()));
        commands.put(CommandType.SHOW_REQUESTS, new CommandRights(rights, new AllRequests()));
        commands.put(CommandType.SHOW_HOSTELS, new CommandRights(rights, new AllHostels()));
        commands.put(CommandType.CHANGE_USER_DISCOUNT, new CommandRights(rights, new DiscountChanging()));
        commands.put(CommandType.APPROVE_REQUEST, new CommandRights(rights, new RequestApproving()));
        commands.put(CommandType.UNLOCK, new CommandRights(rights, new UserUnlocking()));
        commands.put(CommandType.BLOCK, new CommandRights(rights, new UserBlocking()));

        rights = new ArrayList<>();
        rights.add(User.Status.ADMIN);
        rights.add(User.Status.USER);
        rights.add(User.Status.BANNED);
        commands.put(CommandType.CANCEL_REQUEST, new CommandRights(rights, new RequestCanceling()));
        commands.put(CommandType.LOGOUT, new CommandRights(rights, new LogOff()));
        commands.put(CommandType.CHANGE_LOCALE, new CommandRights(rights, new LocaleChanging()));
        commands.put(CommandType.SHOW_NEW_REQUESTS, new CommandRights(rights, new UpcomingRequests()));

        rights = new ArrayList<>();
        rights.add(User.Status.USER);
        rights.add(User.Status.BANNED);
        commands.put(CommandType.SHOW_USER_REQUESTS, new CommandRights(rights, new UserRequests()));
        commands.put(CommandType.CHANGE_USER_DATA, new CommandRights(rights, new UserDataChanging()));
        commands.put(CommandType.CHANGE_PASSWORD, new CommandRights(rights, new ChangingPassword()));
        commands.put(CommandType.DELETE_USER, new CommandRights(rights, new UserDeleting()));

        rights = new ArrayList<>();
        rights.add(User.Status.USER);
        commands.put(CommandType.ADD_REQUEST, new CommandRights(rights, new RequestAdding()));
        commands.put(CommandType.SHOW_AVAILABLE_HOSTELS, new CommandRights(rights, new AvailableHostels()));
        commands.put(CommandType.GET_CITIES, new CommandRights(rights, new CitiesFieldCreating()));

        authorizationCommands.put(CommandType.LOGIN, new Authorization());
        authorizationCommands.put(CommandType.REGISTER, new Registration());

    }

    /**
     * Returns command implementation by command name.
     *
     * @param name command name
     *
     * @return command implementation
     */
    public Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        if (isCommon(name)) {
            return authorizationCommands.get(commandName);
        } else {
            return commands.get(commandName).getCommand();
        }
    }

    /**
     * Verifies user access rights.
     *
     * @param commandName
     * {@link Command} name
     * @param user
     * current {@link User}
     *
     * @throws AccessIsNotAllowedException
     * if current {@link User} does not have access rights
     */
    public void checkAccess(String commandName, User user) throws AccessIsNotAllowedException {
        final boolean changeLocale = commandName.equalsIgnoreCase(CommandType.CHANGE_LOCALE.name());
        if (changeLocale){
            return;
        }
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

    /**
     * Checks current {@link CommandType} status.
     *
     * @param name
     * current {@link Command} name
     *
     * @return true if this command is authorization command; false - otherwise
     */
    private boolean isCommon(String name){
        CommandType commandName = CommandType.valueOf(name);
        return authorizationCommands.containsKey(commandName);
    }

    /**
     * Returns {@link CommandDirector} instance.
     *
     * @return instance of command director
     */
    public static CommandDirector getInstance() {
        return instance;
    }
}