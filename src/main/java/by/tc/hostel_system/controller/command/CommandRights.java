package by.tc.hostel_system.controller.command;

import by.tc.hostel_system.entity.User;

import java.util.List;

public class CommandRights {
	private Command command;
	private List<User.Status> rights;

	CommandRights(List<User.Status> rights, Command command) {
		this.command = command;
		this.rights = rights;
	}

	/**
	 * Returns current {@link Command}.
	 *
	 * @return command
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * Change current {@link Command}.
	 *
	 * @param command
	 * new command
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * Returns {@link User} rights.
	 *
	 * @return users rights {@link List}
	 */
	public List<User.Status> getRights() {
		return rights;
	}
}