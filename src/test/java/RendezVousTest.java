package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

class RendezVousTest {
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
        collection = database.getCollection("RendezVous");
        collection.drop();  
    }

    @Test
    public void testAddRDV() {
    	Document RDV = new Document("nom", "Mouhamed")
                .append("prenom", "Ali")
                .append("date", "17/07/2024" )
                .append("heure", "9:0 ");
        collection.insertOne(RDV);

        Document found = collection.find(new Document("nom", "Mouhamed")).first();
        assertNotNull(found, "Recheche RDV de Mouhamed ...");
        assertEquals("nom", found.getString("Mouhamed"));
    }

}
