package test.java;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AjouterPatientTest {

    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeAll
    public static void setUpClass() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
    }

    @AfterAll
    public static void tearDownClass() {
        mongoClient.close();
    }

    @BeforeEach
    public void setUp() {
        database = mongoClient.getDatabase("testdb");
        collection = database.getCollection("patients");
        collection.drop();  // Nettoyer la collection avant chaque test
    }

    @Test
    public void testAddPatient() {
        Document patient = new Document("name", "John Doe").append("age", 30);
        collection.insertOne(patient);

        Document found = collection.find(new Document("name", "John Doe")).first();
        assertNotNull(found, "Patient 'John Doe' should be found");
        assertEquals("John Doe", found.getString("name"));
        assertEquals(30, found.getInteger("age"));
    }

    @Test
    public void testRemovePatient() {
        Document patient = new Document("name", "John Doe").append("age", 30);
        collection.insertOne(patient);

        collection.deleteOne(patient);

        Document found = collection.find(patient).first();
        assertNull(found, "Patient 'John Doe' should be removed");
    }
}
