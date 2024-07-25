package test.java;

import main.java.AjouterSoins;
import main.java.MongoDBUtil;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AjouterSoinsTest {

    private AjouterSoins ajouterSoins;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        // Initialize the AjouterSoins instance
        ajouterSoins = new AjouterSoins();
        database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");

        // Set up a test database
        database = mongoClient.getDatabase("TestDatabase");
        collection = database.getCollection("Patient");
    }

    @Test
    public void testEnregistrerButtonInsertNewPatient() {
       // Configure the test data
        ajouterSoins.nomText.setText("test");
        ajouterSoins.prenomText.setText("test");
        ajouterSoins.comboBox.setSelectedItem("détartrage");

        ajouterSoins.jourComboBox.setSelectedItem(15);
        ajouterSoins.moisComboBox.setSelectedItem(8);
        ajouterSoins.anneeComboBox.setSelectedItem(2024);

        
        ajouterSoins.enregistrerButton.doClick();

        // Verify the interaction with MongoDB
        Document found = collection.find(new Document("nom", "test")).first();
        assertNotNull(found, "Patient 'test' should be found");
        assertEquals("test", found.getString("prenom"));
        
        List<Document> soinsList = (List<Document>) found.get("soins");
        assertNotNull(soinsList);
        assertEquals(1, soinsList.size());

        Document soinDocument = soinsList.get(0);
        assertEquals("détartrage", soinDocument.getString("soin"));
        assertEquals("15/8/2024", soinDocument.getString("date"));
    }

}
