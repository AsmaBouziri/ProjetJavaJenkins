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
    public void testEnregistrerButtonInsertsData() {
        // Configure the test data
    	modiferPatient.nomTextField.setText("ali");
    	modiferPatient.prenomTextField.setText("ali");
    	modiferPatient.cinTextField.setText("12345678");
    	modiferPatient.adresseTextField.setText("ben arous");
    	modiferPatient.professionTextField.setText("professeur");
        modiferPatient.telTextField.setText("12345678");

        modiferPatient.jourComboBox.setSelectedItem(12);
        modiferPatient.moisComboBox.setSelectedItem(2);
        modiferPatient.anneeComboBox.setSelectedItem(2150);
        // Simulate button click
        modiferPatient.modifierButton.doClick();

        Document found = collection.find(new Document("nom", "ali")).first();
        assertNotNull(found, "Patient 'ali' should be found");
        assertEquals("ali", found.getString("nom"));
    }

}
