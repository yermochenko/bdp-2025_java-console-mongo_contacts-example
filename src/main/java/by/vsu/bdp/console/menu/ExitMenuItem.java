package by.vsu.bdp.console.menu;

import by.vsu.bdp.console.MenuItem;

public class ExitMenuItem extends MenuItem {
	public ExitMenuItem() {
		super("Exit");
	}

	@Override
	public boolean execute() {
		System.out.print("Are you sure you want to exit? (yes/no): ");
		return !"yes".equalsIgnoreCase(getConsole().nextLine());
	}
}
