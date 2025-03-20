package by.vsu.bdp.console;

import java.util.Scanner;

abstract public class MenuItem {
	private final Scanner console = new Scanner(System.in);
	private final String name;

	public MenuItem(String name) {
		this.name = name;
	}

	/**
	 * Perform an action for menu item
	 * @return <code>false</code> if after this action program should be stopped
	 */
	abstract public boolean execute();

	public String getName() {
		return name;
	}

	protected Scanner getConsole() {
		return console;
	}
}
