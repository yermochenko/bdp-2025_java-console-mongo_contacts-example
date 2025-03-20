package by.vsu.bdp.console.menu;

import by.vsu.bdp.console.MenuItem;
import by.vsu.bdp.domain.Phone;
import by.vsu.bdp.repository.PersonMongoDBRepository;

public class AddPhoneMenuItem extends MenuItem {
	public AddPhoneMenuItem() {
		super("Add phone");
	}

	@Override
	public boolean execute() {
		System.out.print("Enter person ID > ");
		String personId = getConsole().nextLine();
		Phone phone = new Phone();
		System.out.print("Enter phone number > ");
		String number = getConsole().nextLine();
		if(number.isBlank()) {
			System.out.println("Phone number is required");
			return true;
		}
		phone.setNumber(number);
		System.out.print("Enter phone type   > ");
		String type = getConsole().nextLine();
		if(type.isBlank()) {
			System.out.println("Phone type is required");
			return true;
		}
		phone.setType(type);
		PersonMongoDBRepository.updateByAddingPhone(personId, phone);
		return true;
	}
}
