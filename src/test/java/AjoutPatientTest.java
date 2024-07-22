package test.java;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.AjouterPatient;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AjouterPatientTest {

    private AjouterPatient frame;  
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @Test
    public void testAjouterPatientCreation() {
        // Crée une instance de AjouterPatient
        AjouterPatient frame = new AjouterPatient();
        
        // Vérifiez que l'instance est correctement créée
        assertNotNull(frame);
        assertTrue(frame instanceof AjouterPatient);
        
        // Testez d'autres fonctionnalités si nécessaire
        // Par exemple, vous pouvez tester si la fenêtre est visible
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            assertTrue(frame.isVisible());
        });
    }
    
    @Test
    void testSavePatientValidData() {
        // Setup valid data
        frame.setNomTextField(new JTextField("DAVID"));
        frame.setPrenomTextField(new JTextField("Jon"));
        frame.setTelTextField(new JTextField("12345678"));
        frame.setAdresseTextField(new JTextField("Ben Arous"));
        frame.setProfessionTextField(new JTextField("Professeur"));
        frame.setCinTextField(new JTextField("01234567"));
        frame.jourComboBox.setSelectedItem(1);
        frame.moisComboBox.setSelectedItem(1);
        frame.anneeComboBox.setSelectedItem(2000);
        frame.hommeRadioButton.setSelected(true);

        // Perform action
        frame.getEnregistrerButton().doClick();

        // Verify if the patient has been inserted
        Document expectedDocument = new Document("nom", "DAVID")
                .append("prenom", "Jon")
                .append("cin", "01234567")
                .append("sexe", "Homme")
                .append("adresse", "Ben Arous")
                .append("telephone", "12345678")
                .append("dataNaiss", "1/1/2000")
                .append("profession", "Professeur");

        verify(collection).insertOne(expectedDocument);
    }

    @Test
    void testSavePatientInvalidPhoneNumber() {
        // Setup invalid phone number
        frame.setTelTextField(new JTextField("12345"));

        // Capture and verify dialog
        JOptionPane.showMessageDialog(frame, "Le numéro de téléphone doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);

        // Perform action
        frame.getEnregistrerButton().doClick();

        // Check if error dialog was shown
        // This may require an alternative way to verify dialog appearance, such as a mock or assert the dialog is called
    }

    @Test
    void testSavePatientInvalidCin() {
        // Setup invalid CIN
        frame.setCinTextField(new JTextField("1234"));

        // Perform action
        frame.getEnregistrerButton().doClick();

        // Verify if the error dialog is shown
        JOptionPane.showMessageDialog(frame, "Le numéro CIN doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    @Test
    void testSavePatientEmptyFields() {
        // Setup empty fields
        frame.setNomTextField(new JTextField(""));
        frame.setPrenomTextField(new JTextField(""));
        frame.setTelTextField(new JTextField(""));
        frame.setAdresseTextField(new JTextField(""));
        frame.setProfessionTextField(new JTextField(""));
        frame.setCinTextField(new JTextField(""));
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
        frame.setNomTextField(new JTextField("DAVID"));
        frame.setPrenomTextField(new JTextField("Jon"));
        frame.setTelTextField(new JTextField("12345678"));
        frame.setAdresseTextField(new JTextField("Ben Arous"));
        frame.setProfessionTextField(new JTextField("Professeur"));
        frame.setCinTextField(new JTextField("01234567"));
        frame.jourComboBox.setSelectedItem(1);
        frame.moisComboBox.setSelectedItem(1);
        frame.anneeComboBox.setSelectedItem(2000);
        frame.hommeRadioButton.setSelected(true);

        // Clear fields
        frame.clearFields();

        // Verify if the fields are cleared
        assertEquals("", frame.getNomTextField().getText());
        assertEquals("", frame.getPrenomTextField().getText());
        assertEquals("", frame.getCinTextField().getText());
        assertEquals("", frame.getAdresseTextField().getText());
        assertEquals("", frame.getProfessionTextField().getText());
        assertEquals("", frame.getTelTextField().getText());
        assertEquals(0, frame.jourComboBox.getSelectedIndex());
        assertEquals(0, frame.moisComboBox.getSelectedIndex());
        assertEquals(0, frame.anneeComboBox.getSelectedIndex());
        assertTrue(frame.hommeRadioButton.isSelected());
    }
}
