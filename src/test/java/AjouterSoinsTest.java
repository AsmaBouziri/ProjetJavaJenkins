package test.java;

import main.java.AjouterSoins;
import main.java.MongoDBUtil;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AjouterSoinsTest {

    private AjouterSoins ajouterSoins;
    private MongoDatabase database;
    private MongoCollection<Document> patientsCollection;

    @BeforeEach
    public void setUp() {

        // Initialize the AjouterSoins instance without showing the GUI
        ajouterSoins = new AjouterSoins();
        ajouterSoins.setSize(800, 600);
        ajouterSoins.setVisible(false);
    }
    
    @Test
    public void testComponentsInitialization() {
        assertNotNull(ajouterSoins.nomText);
        assertNotNull(ajouterSoins.prenomText);

    }

    
}
