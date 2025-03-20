package by.vsu.bdp.console;

import by.vsu.bdp.console.menu.*;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<MenuItem> menu = List.of(
			new ShowAllMenuItem(),
			new ShowPersonByIdMenuItem(),
			new AddPersonMenuItem(),
			new AddPhoneMenuItem(),
			new RemovePhoneMenuItem(),
			new AddEmailMenuItem(),
			new RemoveEmailMenuItem(),
			new ExitMenuItem()
		);
		boolean work = true;
		while(work) {
			System.out.println("MENU:");
			int n = 1;
			for(MenuItem menuItem : menu) {
				System.out.printf("%d) %s\n", n++, menuItem.getName());
			}
			System.out.println();
			System.out.print("Enter menu item number > ");
			try {
				MenuItem menuItem = menu.get(Integer.parseInt(scanner.nextLine()) - 1);
				System.out.println();
				work = menuItem.execute();
				System.out.println();
			} catch(NumberFormatException | IndexOutOfBoundsException e) {
				System.out.println("Invalid menu item number");
			}
		}
		System.out.println("Goodbye");
	}
}
