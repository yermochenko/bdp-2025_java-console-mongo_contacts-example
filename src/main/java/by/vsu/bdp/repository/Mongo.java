package by.vsu.bdp.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Mongo implements AutoCloseable {
	public static final String URI = "mongodb://localhost:27017";
	public static final String DATABASE = "contacts";

	private MongoClient client;

	public Mongo() {
		client = MongoClients.create(URI);
	}

	public MongoCollection<Document> collection(String name) {
		return client.getDatabase(DATABASE).getCollection(name);
	}

	@Override
	public void close() {
		if(client != null) {
			client.close();
			client = null;
		}
	}
}
