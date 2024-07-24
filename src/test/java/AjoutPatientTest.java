package test.java;

import main.java.AjouterPatient;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.Document;

public class AjoutPatientTest {

    private AjouterPatient ajouterPatient;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        
        ajouterPatient = new AjouterPatient();
        ajouterPatient.setSize(800, 600);
        ajouterPatient.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(ajouterPatient.nomTextField);
        assertNotNull(ajouterPatient.prenomTextField);
        assertNotNull(ajouterPatient.cinTextField);
        assertNotNull(ajouterPatient.adresseTextField);
        assertNotNull(ajouterPatient.professionTextField);
        assertNotNull(ajouterPatient.telTextField);
        assertNotNull(ajouterPatient.enregistrerButton);
        assertNotNull(ajouterPatient.hommeRadioButton);
        assertNotNull(ajouterPatient.femmeRadioButton);
        assertNotNull(ajouterPatient.jourComboBox);
        assertNotNull(ajouterPatient.anneeComboBox);
        assertNotNull(ajouterPatient.moisComboBox);
    }
    
    @Test
    public void testEnregistrerButtonInsertsData() {
        // Configure the test data
        ajouterPatient.nomTextField.setText("ali");
        ajouterPatient.prenomTextField.setText("ali");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("ben arous");
        ajouterPatient.professionTextField.setText("professeur");
        ajouterPatient.telTextField.setText("12345678");

        ajouterPatient.jourComboBox.setSelectedItem(12);
        ajouterPatient.moisComboBox.setSelectedItem(2);
        ajouterPatient.anneeComboBox.setSelectedItem(2150);
        // Simulate button click
        ajouterPatient.enregistrerButton.doClick();

        Document found = collection.find(new Document("nom", "ali")).first();
        assertNotNull(found, "Patient 'ali' should be found");
        assertEquals("ali", found.getString("nom"));
    }

}
