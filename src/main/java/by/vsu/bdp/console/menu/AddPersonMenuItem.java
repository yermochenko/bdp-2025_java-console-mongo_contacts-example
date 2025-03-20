package by.vsu.bdp.console.menu;

import by.vsu.bdp.console.MenuItem;
import by.vsu.bdp.domain.Person;
import by.vsu.bdp.domain.Phone;
import by.vsu.bdp.repository.PersonMongoDBRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddPersonMenuItem extends MenuItem {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public AddPersonMenuItem() {
		super("Add person");
	}

	@Override
	public boolean execute() {
		Person person = new Person();
		System.out.println("Enter information about the person:");
		// First name
		System.out.print("First name > ");
		String firstName = getConsole().nextLine();
		if(firstName.isBlank()) {
			System.out.println("First name is required");
			return true;
		}
		person.setFirstName(firstName);
		// Middle name
		System.out.print("Middle name (press 'Enter' to leave blank) > ");
		String middleName = getConsole().nextLine();
		if(!middleName.isBlank()) {
			person.setMiddleName(middleName);
		}
		// Last name
		System.out.print("Last name > ");
		String lastName = getConsole().nextLine();
		if(lastName.isBlank()) {
			System.out.println("Last name is required");
			return true;
		}
		person.setLastName(lastName);
		// Birthday
		System.out.printf("Birthday (in format %s or press 'Enter' to leave blank) > ", DATE_FORMAT.toPattern());
		String birthday = getConsole().nextLine();
		if(!birthday.isBlank()) {
			try {
				person.setBirthday(DATE_FORMAT.parse(birthday));
			} catch(ParseException e) {
				System.out.println("Birthday has incorrect format");
				return true;
			}
		}
		boolean continueEnter = true;
		// Phones
		person.setPhones(new ArrayList<>());
		System.out.println("List of phones (press 'Enter' to end list)");
		while(continueEnter) {
			System.out.print("number > ");
			String number = getConsole().nextLine();
			if(!number.isBlank()) {
				System.out.print("type   > ");
				String type = getConsole().nextLine();
				if(type.isBlank()) {
					System.out.println("Type of phone is required");
					return true;
				}
				Phone phone = new Phone();
				phone.setNumber(number);
				phone.setType(type);
				person.getPhones().add(phone);
			} else {
				continueEnter = false;
			}
		}
		// E-mails
		person.setEmails(new ArrayList<>());
		System.out.println("List of e-mails (press 'Enter' to end list)");
		continueEnter = true;
		while(continueEnter) {
			System.out.print("> ");
			String email = getConsole().nextLine();
			if(!email.isBlank()) {
				person.getEmails().add(email);
			} else {
				continueEnter = false;
			}
		}
		PersonMongoDBRepository.insert(person);
		return true;
	}
}
