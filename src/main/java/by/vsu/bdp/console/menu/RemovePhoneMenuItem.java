package by.vsu.bdp.console.menu;

import by.vsu.bdp.console.MenuItem;
import by.vsu.bdp.repository.PersonMongoDBRepository;

public class RemovePhoneMenuItem extends MenuItem {
	public RemovePhoneMenuItem() {
		super("Remove phone");
	}

	@Override
	public boolean execute() {
		System.out.print("Enter person ID > ");
		String personId = getConsole().nextLine();
		System.out.print("Enter phone number > ");
		String number = getConsole().nextLine();
		if(number.isBlank()) {
			System.out.println("Phone number is required");
			return true;
		}
		PersonMongoDBRepository.updateByRemovingPhone(personId, number);
		return true;
	}
}
