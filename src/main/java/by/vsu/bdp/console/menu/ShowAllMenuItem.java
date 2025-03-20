package by.vsu.bdp.console.menu;

import by.vsu.bdp.console.MenuItem;
import by.vsu.bdp.domain.Person;
import by.vsu.bdp.repository.PersonMongoDBRepository;

import java.util.List;

public class ShowAllMenuItem extends MenuItem {
	public ShowAllMenuItem() {
		super("Show all persons");
	}

	@Override
	public boolean execute() {
		List<Person> people = PersonMongoDBRepository.findAll();
		if(!people.isEmpty()) {
			System.out.println("Persons:");
			for(Person person : people) {
				System.out.printf(
					"[ID=%s] %s (%d phones, %d emails)\n",
					person.getId(),
					fullName(person),
					person.getPhones().size(),
					person.getEmails().size()
				);
			}
		} else {
			System.out.println("There are no any persons");
		}
		return true;
	}

	private String fullName(Person person) {
		if(person.getMiddleName().isPresent()) {
			return person.getLastName() + " "
			     + person.getFirstName().charAt(0) + ". "
			     + person.getMiddleName().get().charAt(0) + ".";
		} else {
			return person.getLastName() + " "
			     + person.getFirstName().charAt(0) + ".";
		}
	}
}
