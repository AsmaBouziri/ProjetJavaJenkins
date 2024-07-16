package test.java;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.*;

import static org.junit.Assert.*;

public class AjouterPatientTest {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeClass
    public static void setUpClass() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("CabinetDent");
    }

    @AfterClass
    public static void tearDownClass() {
        mongoClient.close();
    }

    @Before
    public void setUp() {
        collection = database.getCollection("Patient");
        collection.drop(); 
    }

    @Test
    public void testAddPatient() {
        Document patient = new Document("name", "John Doe").append("age", 30);
        collection.insertOne(patient);

        Document found = collection.find(new Document("name", "John Doe")).first();
        assertNotNull(found);
        assertEquals("John Doe", found.getString("name"));
        assertEquals(30, found.getInteger("age").intValue());
    }

    @Test
    public void testUpdatePatient() {
        Document patient = new Document("name", "John Doe").append("age", 30);
        collection.insertOne(patient);

        // Mettre à jour l'âge du patient
        Document query = new Document("name", "John Doe");
        Document update = new Document("age", 35);
        collection.updateOne(query, new Document("$set", update));

        // Vérifier que la mise à jour a été effectuée
        Document found = collection.find(query).first();
        assertNotNull(found);
        assertEquals(35, found.getInteger("age").intValue());
    }

    @Test
    public void testRemovePatient() {
        Document patient = new Document("name", "John Doe").append("age", 30);
        collection.insertOne(patient);

        // Supprimer le patient de la base de données
        Document query = new Document("name", "John Doe");
        collection.deleteOne(query);

        // Vérifier que le patient a été supprimé
        Document found = collection.find(query).first();
        assertNull(found);
    }
}
