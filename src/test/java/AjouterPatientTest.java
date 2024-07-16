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
        database = mongoClient.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        collection.drop();  // Nettoyer la collection avant chaque test
    }

    @Test
    public void testAddPatient() {
        Document patient = new Document("nom", "test")
                .append("prenom", "")
                .append("cin", "")
    			.append("sexe", "")
    			.append("adresse", "")
    			.append("telephone", "")
    			.append("dataNaiss", "")
    			.append("profession", "");
        collection.insertOne(patient);

        Document found = collection.find(new Document("nom", "test")).first();
        assertNotNull(found, "Patient 'test' should be found");
        assertEquals("test", found.getString("nom"));
        //assertEquals(30, found.getInteger("age"));
    }

    @Test
    public void testRemovePatient() {
        Document patient = new Document("nom", "test")
        		.append("prenom", "")
                .append("cin", "")
    			.append("sexe", "")
    			.append("adresse", "")
    			.append("telephone", "")
    			.append("dataNaiss", "")
    			.append("profession", "");
        collection.insertOne(patient);

        collection.deleteOne(patient);

        Document found = collection.find(patient).first();
        assertNull(found, "Patient 'John Doe' should be removed");
    }
}
