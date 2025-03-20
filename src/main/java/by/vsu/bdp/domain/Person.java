package by.vsu.bdp.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Person {
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthday;
	private List<Phone> phones;
	private List<String> emails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Optional<String> getMiddleName() {
		return Optional.ofNullable(middleName);
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Optional<Date> getBirthday() {
		return Optional.ofNullable(birthday);
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		return "Person{" +
				"id='" + getId() + '\'' +
				", firstName='" + getFirstName() + '\'' +
				", middleName=" + (getMiddleName().isPresent() ? "'" + getMiddleName().get() + "'" : "null") +
				", lastName='" + getLastName() + '\'' +
				", birthday=" + (getBirthday().isPresent() ? "'" + formatter.format(getBirthday().get()) + "'" : "null") +
				", phones=" + getPhones() +
				", emails=" + getEmails() +
				'}';
	}
}
