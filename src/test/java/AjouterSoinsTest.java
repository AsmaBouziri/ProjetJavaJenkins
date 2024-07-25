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
    	
        database = MongoDBUtil.getDatabase("CabinetDent");
        patientsCollection = database.getCollection("Patient");
        // Initialize the AjouterSoins instance without showing the GUI
        ajouterSoins = new AjouterSoins();
        ajouterSoins.setSize(800, 600);
        ajouterSoins.setVisible(false);
    }
    
    @Test
    public void testComponentsInitialization() {
        assertNull(ajouterSoins.nomText);
        assertNull(ajouterSoins.prenomText);

    }
    
    @Test
    public void testAddSoin() {
        // Set up the form data
        ajouterSoins.nomText.setText("John");
        ajouterSoins.prenomText.setText("Doe");
        ajouterSoins.comboBox.setSelectedItem("détartrage");
        ajouterSoins.jourComboBox.setSelectedItem(15);
        ajouterSoins.moisComboBox.setSelectedItem(7);
        ajouterSoins.anneeComboBox.setSelectedItem(2024);

        // Simulate the button click to add a soin
        ajouterSoins.enregistrerButton.doClick();

        // Check that the soin was added to the collection
        Document query = new Document("nom", "John").append("prenom", "Doe");
        List<Document> results = patientsCollection.find(query).into(new ArrayList<>());

        assertEquals(1, results.size());
        Document addedPatient = results.get(0);
        List<Document> soinsList = (List<Document>) addedPatient.get("soins");
        assertNotNull(soinsList);
        assertEquals(1, soinsList.size());
        Document addedSoin = soinsList.get(0);
        assertEquals("détartrage", addedSoin.getString("soin"));
        assertEquals("15/7/2024", addedSoin.getString("date"));
    }

    
}
