package test.java;

import main.java.AjouterPatient;
import main.java.AjouterSoins;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.Document;

public class AjouterSoinsTest {

    private AjouterSoins ajouterSoins;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        
        ajouterSoins = new AjouterSoins();
        ajouterSoins.setSize(800, 600);
        ajouterSoins.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
    	 assertNotNull(ajouterSoins.nomText);
         assertNotNull(ajouterSoins.prenomText);
         assertNotNull(ajouterSoins.comboBox);
         assertNotNull(ajouterSoins.jourComboBox);
         assertNotNull(ajouterSoins.moisComboBox);
         assertNotNull(ajouterSoins.anneeComboBox);
         assertNotNull(ajouterSoins.enregistrerButton);
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

        // Check if the data was added to MongoDB
        Document patient = collection.find(Filters.eq("nom", "John")).first();
        assertNotNull(patient);
        assertTrue(patient.getList("soins", Document.class).stream()
            .anyMatch(doc -> doc.getString("soin").equals("détartrage") &&
                            doc.getString("date").equals("15/7/2024")));
    }

}
