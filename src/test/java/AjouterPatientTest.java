package test.java;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.*;
import static org.junit.Assert.*;

public class AjouterPatientTest {

    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeClass
    public static void setUpClass() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
    }

    @AfterClass
    public static void tearDownClass() {
        mongoClient.close();
    }

    @Before
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
        assertNotNull("Patient 'John Doe' should be found", found);
        assertEquals("John Doe", found.getString("name"));
        assertEquals(30, found.getInteger("age").intValue());
    }

    @Test
    public void testRemovePatient() {
        Document patient = new Document("name", "John Doe").append("age", 30);
        collection.insertOne(patient);

        collection.deleteOne(patient);

        Document found = collection.find(patient).first();
        assertNull("Patient 'John Doe' should be removed", found);
    }
}
