package by.vsu.bdp.repository;

import by.vsu.bdp.domain.Person;
import by.vsu.bdp.domain.Phone;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonMongoDBRepository {
	public final static String COLLECTION = "person";

	public static List<Person> findAll() {
		try(Mongo db = new Mongo()) {
			MongoCollection<Document> collection = db.collection(COLLECTION);
			List<Person> persons = new ArrayList<>();
			for(Document doc : collection.find()) {
				persons.add(convert(doc));
			}
			return persons;
		}
	}

	public static Optional<Person> findById(String id) {
		try(Mongo db = new Mongo()) {
			MongoCollection<Document> collection = db.collection(COLLECTION);
			Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
			return Optional.ofNullable(doc != null ? convert(doc) : null);
		}
	}

	public static void insert(Person person) {
		try(Mongo db = new Mongo()) {
			MongoCollection<Document> collection = db.collection(COLLECTION);
			Document doc = new Document();
			doc.put("first_name", person.getFirstName());
			if(person.getMiddleName().isPresent()) {
				doc.put("middle_name", person.getMiddleName().get());
			}
			doc.put("last_name", person.getLastName());
			if(person.getBirthday().isPresent()) {
				doc.put("birthday", person.getBirthday().get());
			}
			doc.put("phones", person.getPhones().stream().map(PersonMongoDBRepository::convert).toList());
			doc.put("emails", person.getEmails());
			collection.insertOne(doc);
		}
	}

	public static void updateByAddingPhone(String personId, Phone phone) {
		try(Mongo db = new Mongo()) {
			MongoCollection<Document> collection = db.collection(COLLECTION);
			collection.updateOne(
				Filters.eq("_id", new ObjectId(personId)),
				Updates.push("phones", convert(phone))
			);
		}
	}

	public static void updateByRemovingPhone(String personId, String phoneNumber) {
		try(Mongo db = new Mongo()) {
			MongoCollection<Document> collection = db.collection(COLLECTION);
			Document doc = new Document();
			doc.put("number", phoneNumber);
			collection.updateOne(
				Filters.eq("_id", new ObjectId(personId)),
				Updates.pull("phones", doc)
			);
		}
	}

	public static void updateByAddingEmail(String personId, String email) {
		try(Mongo db = new Mongo()) {
			MongoCollection<Document> collection = db.collection(COLLECTION);
			collection.updateOne(
				Filters.eq("_id", new ObjectId(personId)),
				Updates.push("emails", email)
			);
		}
	}

	public static void updateByRemovingEmail(String personId, String email) {
		try(Mongo db = new Mongo()) {
			MongoCollection<Document> collection = db.collection(COLLECTION);
			collection.updateOne(
				Filters.eq("_id", new ObjectId(personId)),
				Updates.pull("emails", email)
			);
		}
	}

	@SuppressWarnings("unchecked")
	private static Person convert(Document doc) {
		Person person = new Person();
		person.setId(doc.getObjectId("_id").toHexString());
		person.setFirstName(doc.getString("first_name"));
		if(doc.containsKey("middle_name")) {
			person.setMiddleName(doc.getString("middle_name"));
		}
		person.setLastName(doc.getString("last_name"));
		if(doc.containsKey("birthday")) {
			person.setBirthday(doc.getDate("birthday"));
		}
		List<Document> phones = (List<Document>) doc.get("phones");
		person.setPhones(new ArrayList<>(phones.size()));
		for(Document phoneDoc : phones) {
			Phone phone = new Phone();
			phone.setNumber(phoneDoc.getString("number"));
			phone.setType(phoneDoc.getString("type"));
			person.getPhones().add(phone);
		}
		person.setEmails((List<String>) doc.get("emails"));
		return person;
	}

	private static Document convert(Phone phone) {
		Document phoneDoc = new Document();
		phoneDoc.put("number", phone.getNumber());
		phoneDoc.put("type", phone.getType());
		return phoneDoc;
	}
}
