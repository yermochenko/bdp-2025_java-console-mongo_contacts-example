package by.vsu.bdp.console.menu;

import by.vsu.bdp.console.MenuItem;
import by.vsu.bdp.domain.Person;
import by.vsu.bdp.repository.PersonMongoDBRepository;

import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShowPersonByIdMenuItem extends MenuItem {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public ShowPersonByIdMenuItem() {
		super("Show person by ID");
	}

	@Override
	public boolean execute() {
		System.out.print("Enter person ID: ");
		String id = getConsole().nextLine();
		Optional<Person> person = PersonMongoDBRepository.findById(id);
		if(person.isPresent()) {
			System.out.printf("Person with ID %s:\n", person.get().getId());
			System.out.printf("First name....: %s\n", person.get().getFirstName());
			System.out.printf("Middle name...: %s\n", person.get().getMiddleName().orElse(""));
			System.out.printf("Last name.....: %s\n", person.get().getLastName());
			System.out.printf("Birthday......: %s\n", person.get().getBirthday().stream()
			                                                                    .map(DATE_FORMAT::format)
			                                                                    .findFirst()
			                                                                    .orElse(""));
			System.out.printf("Phones........: %s\n", person.get().getPhones().stream()
			                                         .map(phone -> phone.getNumber() + " (" + phone.getType() + ")")
			                                         .collect(Collectors.joining("\n" + " ".repeat(16))));
			System.out.printf("E-mails.......: %s\n", person.get().getEmails().stream()
			                                         .collect(Collectors.joining("\n" + " ".repeat(16))));
		} else {
			System.out.printf("There are no person with ID %s\n", id);
		}
		return true;
	}
}
