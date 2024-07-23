package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.AjouterPatient;
import main.java.MongoDBUtil;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AjoutPatientTest {
    private AjouterPatient ajouterPatient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        // Initialize the AjouterPatient frame
        ajouterPatient = new AjouterPatient();
        ajouterPatient.setSize(800, 600);
        ajouterPatient.setVisible(true);

        // Set up the database connection

    }

    @AfterEach
    public void tearDown() {
        // Clear the collection after each test
        collection.drop();

        // Close the AjouterPatient frame
        ajouterPatient.dispose();
    }

    @Test
    public void testAjouterPatientFields() {
        // Verify that all text fields are present and initially empty
        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
        // Verify that the radio buttons are present and no selection is made initially
        assertFalse(ajouterPatient.hommeRadioButton.isSelected());
        assertFalse(ajouterPatient.femmeRadioButton.isSelected());
    }

    @Test
    public void testAjouterPatientEnregistrerButton() {
        // Set field values
        ajouterPatient.nomTextField.setText("Doe");
        ajouterPatient.prenomTextField.setText("John");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("123 Main St");
        ajouterPatient.professionTextField.setText("Dentist");
        ajouterPatient.telTextField.setText("87654321");
        ajouterPatient.hommeRadioButton.setSelected(true);

        // Click the Enregistrer button
        ajouterPatient.enregistrerButton.doClick();

        // Verify that the document was inserted into the collection
        Document doc = collection.find(new Document("cin", "12345678")).first();
        assertNotNull(doc);
        assertEquals("Doe", doc.getString("nom"));
        assertEquals("John", doc.getString("prenom"));
        assertEquals("12345678", doc.getString("cin"));
        assertEquals("Homme", doc.getString("sexe"));
        assertEquals("123 Main St", doc.getString("adresse"));
        assertEquals("Dentist", doc.getString("profession"));
        assertEquals("87654321", doc.getString("telephone"));
        assertEquals("15/6/1985", doc.getString("dataNaiss"));
    }

    @Test
    public void testAjouterPatientAnnulerButton() {
        // Set field values
        ajouterPatient.nomTextField.setText("Doe");
        ajouterPatient.prenomTextField.setText("John");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("123 Main St");
        ajouterPatient.professionTextField.setText("Dentist");
        ajouterPatient.telTextField.setText("87654321");
        ajouterPatient.hommeRadioButton.setSelected(true);

        // Click the Annuler button
        ajouterPatient.annulerButton.doClick();

        // Verify that the fields are cleared
        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
        assertFalse(ajouterPatient.hommeRadioButton.isSelected());
        assertFalse(ajouterPatient.femmeRadioButton.isSelected());
    }
}
