package test.java;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.SeConnecter;

class LoginTest {
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
        collection = database.getCollection("LogIn");
        collection.drop();  
    }

    @Test
    public void connectDentiste() {
        Document found = collection.find(new Document("user", "dentiste")).first();
        assertNotNull(found, "Recheche Dentiste ...");
        assertEquals("dentiste", found.getString("user"));
        assertEquals("987", found.getString("mdp"));
    }
}
