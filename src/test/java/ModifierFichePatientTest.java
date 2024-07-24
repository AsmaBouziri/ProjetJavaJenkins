package test.java;

import main.java.AjouterPatient;
import main.java.ModifierFichePatient;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.Document;

public class ModifierFichePatientTest {

    private ModifierFichePatient modiferPatient;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        
        modiferPatient = new ModifierFichePatient();
        modiferPatient.setSize(800, 600);
        modiferPatient.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(modiferPatient.nomTextField);
        assertNotNull(modiferPatient.prenomTextField);
        assertNotNull(modiferPatient.cinTextField);
        assertNotNull(modiferPatient.adresseTextField);
        assertNotNull(modiferPatient.professionTextField);
        assertNotNull(modiferPatient.telTextField);
        assertNotNull(modiferPatient.hommeRadioButton);
        assertNotNull(modiferPatient.femmeRadioButton);
        assertNotNull(modiferPatient.jourComboBox);
        assertNotNull(modiferPatient.anneeComboBox);
        assertNotNull(modiferPatient.moisComboBox);
    }
    
    
    @Test
    public void testRechercherButton() {
        // Configure the test data
    	modiferPatient.nomTextField.setText("test");
    	modiferPatient.prenomTextField.setText("test");

        // Simulate button click
        modiferPatient.rechercherButton.doClick();

     // Verify that the fields are populated correctly
        assertEquals("123456", modiferPatient.cinTextField.getText());
        assertEquals("123 Test St", modiferPatient.adresseTextField.getText());
        assertEquals("Engineer", modiferPatient.professionTextField.getText());
        assertEquals("123-456-7890", modiferPatient.telTextField.getText());
        assertTrue(modiferPatient.hommeRadioButton.isSelected());
        assertEquals(1, modiferPatient.jourComboBox.getSelectedItem());
        assertEquals(1, modiferPatient.moisComboBox.getSelectedItem());
        assertEquals(2000, modiferPatient.anneeComboBox.getSelectedItem());
    }
    
    

}
