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
        ajouterSoins.nomText.setText("Dupont");
        ajouterSoins.prenomText.setText("Jean");

        JComboBox<String> comboBox = (JComboBox<String>) getPrivateField(ajouterSoins, "comboBox");
        comboBox.setSelectedItem("détartrage");

        JComboBox<Integer> jourComboBox = (JComboBox<Integer>) getPrivateField(ajouterSoins, "jourComboBox");
        jourComboBox.setSelectedItem(15);

        JComboBox<Integer> moisComboBox = (JComboBox<Integer>) getPrivateField(ajouterSoins, "moisComboBox");
        moisComboBox.setSelectedItem(8);

        JComboBox<Integer> anneeComboBox = (JComboBox<Integer>) getPrivateField(ajouterSoins, "anneeComboBox");
        anneeComboBox.setSelectedItem(2024);

        JButton enregistrerButton = (JButton) getPrivateField(ajouterSoins, "enregistrerButton");
        enregistrerButton.doClick();

        // Verify the interaction with MongoDB
        Document found = collection.find(new Document("nom", "Dupont")).first();
        assertNotNull(found, "Patient 'Dupont' should be found");
        assertEquals("Jean", found.getString("prenom"));
        
        List<Document> soinsList = (List<Document>) found.get("soins");
        assertNotNull(soinsList);
        assertEquals(1, soinsList.size());

        Document soinDocument = soinsList.get(0);
        assertEquals("détartrage", soinDocument.getString("soin"));
        assertEquals("15/8/2024", soinDocument.getString("date"));
    }

    @Test
    public void testEnregistrerButtonUpdateExistingPatient() {
        // Setup existing patient
        Document existingPatient = new Document("nom", "Dupont")
                .append("prenom", "Jean")
                .append("soins", List.of());
        collection.insertOne(existingPatient);

        // Configure the test data
        ajouterSoins.nomText.setText("Dupont");
        ajouterSoins.prenomText.setText("Jean");

        JComboBox<String> comboBox = (JComboBox<String>) getPrivateField(ajouterSoins, "comboBox");
        comboBox.setSelectedItem("plombage");

        JComboBox<Integer> jourComboBox = (JComboBox<Integer>) getPrivateField(ajouterSoins, "jourComboBox");
        jourComboBox.setSelectedItem(20);

        JComboBox<Integer> moisComboBox = (JComboBox<Integer>) getPrivateField(ajouterSoins, "moisComboBox");
        moisComboBox.setSelectedItem(7);

        JComboBox<Integer> anneeComboBox = (JComboBox<Integer>) getPrivateField(ajouterSoins, "anneeComboBox");
        anneeComboBox.setSelectedItem(2024);

        JButton enregistrerButton = (JButton) getPrivateField(ajouterSoins, "enregistrerButton");
        enregistrerButton.doClick();

        // Verify the update in MongoDB
        Document found = collection.find(new Document("nom", "Dupont")).first();
        assertNotNull(found, "Patient 'Dupont' should be found");

        List<Document> soinsList = (List<Document>) found.get("soins");
        assertNotNull(soinsList);
        assertEquals(1, soinsList.size());

        Document soinDocument = soinsList.get(0);
        assertEquals("plombage", soinDocument.getString("soin"));
        assertEquals("20/7/2024", soinDocument.getString("date"));
    }

    private Object getPrivateField(Object object, String fieldName) {
        try {
            var field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
