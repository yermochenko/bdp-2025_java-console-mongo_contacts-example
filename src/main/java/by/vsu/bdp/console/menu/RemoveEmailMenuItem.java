package by.vsu.bdp.console.menu;

import by.vsu.bdp.console.MenuItem;
import by.vsu.bdp.repository.PersonMongoDBRepository;

public class RemoveEmailMenuItem extends MenuItem {
	public RemoveEmailMenuItem() {
		super("Remove email");
	}

	@Override
	public boolean execute() {
		System.out.print("Enter person ID > ");
		String personId = getConsole().nextLine();
		System.out.print("Enter email > ");
		String email = getConsole().nextLine();
		if(email.isBlank()) {
			System.out.println("Email is required");
			return true;
		}
		PersonMongoDBRepository.updateByRemovingEmail(personId, email);
		return true;
	}
}
