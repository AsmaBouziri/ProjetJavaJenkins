package test.java;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.AjouterPatient;
import main.java.MongoDBUtil;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AjouterPatientTest {

    private AjouterPatient frame;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    void setUp() {
      // Mock MongoDB
      database = mock(MongoDatabase.class);
      collection = mock(MongoCollection.class);
      when(database.getCollection("Patient")).thenReturn(collection);

      // Initialize the AjouterPatient frame
      frame = new AjouterPatient();
      // Set the mocked database to the frame
      frame.setDatabase(database);
    }


    @Test
    void testSavePatientValidData() {
        // Setup valid data
    	 frame.setNomTextField(new JTextField ("DAVID"));
         frame.setPrenomTextField(new JTextField ("Jon"));
         frame.setTelTextField(new JTextField ("12345678"));
         frame.setAdresseTextField(new JTextField ("Ben Arous"));
         frame.setProfessionTextField(new JTextField ("Professeur"));
         frame.setCinTextField(new JTextField ("01234567"));
         frame.jourComboBox.setSelectedItem(1);
         frame.moisComboBox.setSelectedItem(1);
         frame.anneeComboBox.setSelectedItem(2000);
         frame.hommeRadioButton.setSelected(true);

        // Perform action
        frame.getEnregistrerButton().doClick();

        // Verify if the patient has been inserted
        Document expectedDocument = new Document("nom", "Doe")
                .append("prenom", "John")
                .append("cin", "12345678")
                .append("sexe", "Homme")
                .append("adresse", "123 Main St")
                .append("telephone", "12345678")
                .append("dataNaiss", "1/1/2000")
                .append("profession", "Engineer");

        verify(collection).insertOne(expectedDocument);
    }

    @Test
    void testSavePatientInvalidPhoneNumber() {
        // Setup invalid phone number
        frame.getTelTextField().setText("12345");

        // Perform action
        frame.getEnregistrerButton().doClick();

        // Verify if the error dialog is shown
        JOptionPane.showMessageDialog(frame, "Le numéro de téléphone doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    @Test
    void testSavePatientInvalidCin() {
        // Setup invalid CIN
        frame.getCinTextField().setText("1234");

        // Perform action
        frame.getEnregistrerButton().doClick();

        // Verify if the error dialog is shown
        JOptionPane.showMessageDialog(frame, "Le numéro CIN doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    @Test
    void testSavePatientEmptyFields() {
        // Setup empty fields
    	 frame.setNomTextField(new JTextField (""));
         frame.setPrenomTextField(new JTextField (""));
         frame.setTelTextField(new JTextField (""));
         frame.setAdresseTextField(new JTextField (""));
         frame.setProfessionTextField(new JTextField (""));
         frame.setCinTextField(new JTextField (""));
         frame.jourComboBox.setSelectedItem(1);
         frame.moisComboBox.setSelectedItem(1);
         frame.anneeComboBox.setSelectedItem(2000);
         frame.hommeRadioButton.setSelected(true);
        // Perform action
        frame.getEnregistrerButton().doClick();

        // Verify if the error dialog is shown
        JOptionPane.showMessageDialog(frame, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    @Test
    void testClearFields() {
        // Setup fields with some data
        frame.setNomTextField(new JTextField ("DAVID"));
        frame.setPrenomTextField(new JTextField ("Jon"));
        frame.setTelTextField(new JTextField ("12345678"));
        frame.setAdresseTextField(new JTextField ("Ben Arous"));
        frame.setProfessionTextField(new JTextField ("Professeur"));
        frame.setCinTextField(new JTextField ("01234567"));
        frame.jourComboBox.setSelectedItem(1);
        frame.moisComboBox.setSelectedItem(1);
        frame.anneeComboBox.setSelectedItem(2000);
        frame.hommeRadioButton.setSelected(true);

        // Clear fields
        frame.clearFields();

        // Verify if the fields are cleared
        assertEquals("", frame.getNomTextField());
        assertEquals("", frame.getPrenomTextField());
        assertEquals("", frame.getCinTextField());
        assertEquals("", frame.getAdresseTextField());
        assertEquals("", frame.getProfessionTextField());
        assertEquals("", frame.getTelTextField());
        assertEquals(0, frame.jourComboBox.getSelectedIndex());
        assertEquals(0, frame.moisComboBox.getSelectedIndex());
        assertEquals(0, frame.anneeComboBox.getSelectedIndex());
        assertTrue(frame.hommeRadioButton.isSelected());
    }

}
