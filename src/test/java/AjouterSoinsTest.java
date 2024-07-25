package test.java;

import main.java.AjouterSoins;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.bson.Document;

public class AjouterSoinsTest {

    private AjouterSoins ajouterSoins;
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
        ajouterSoins.nomText.setText("test");
        ajouterSoins.prenomText.setText("test");
        ajouterSoins.comboBox.setSelectedItem("détartrage");
        ajouterSoins.jourComboBox.setSelectedItem(15);
        ajouterSoins.moisComboBox.setSelectedItem(7);
        ajouterSoins.anneeComboBox.setSelectedItem(2024);

        // Simulate the button click to add a soin
        ajouterSoins.enregistrerButton.doClick();

        // Check if the data was added to MongoDB
        Document patient = collection.find(Filters.eq("nom", "test")).first();
        assertNotNull(patient);
        List<Document> soins = patient.getList("soins", Document.class);

        // Find the matching soin
        Document foundSoin = soins.stream()
            .filter(doc -> doc.getString("soin").equals("détartrage") &&
                           doc.getString("date").equals("15/7/2024"))
            .findFirst()
            .orElse(null);

        // Check if the found soin is not null (i.e., it exists)
        assertEquals("détartrage", foundSoin != null ? foundSoin.getString("soin") : null);
        assertEquals("15/7/2024", foundSoin != null ? foundSoin.getString("date") : null);
    }

}
