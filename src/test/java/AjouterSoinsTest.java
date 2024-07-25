package test.java;

import main.java.AjouterPatient;
import main.java.AjouterSoins;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
    
//    @Test
//    public void testEnregistrerButtonInsertsData() {
//        // Configure the test data
//        ajouterPatient.nomTextField.setText("ali");
//        ajouterPatient.prenomTextField.setText("ali");
//        ajouterPatient.cinTextField.setText("12345678");
//        ajouterPatient.adresseTextField.setText("ben arous");
//        ajouterPatient.professionTextField.setText("professeur");
//        ajouterPatient.telTextField.setText("12345678");
//
//        ajouterPatient.jourComboBox.setSelectedItem(12);
//        ajouterPatient.moisComboBox.setSelectedItem(2);
//        ajouterPatient.anneeComboBox.setSelectedItem(2150);
//        // Simulate button click
//        ajouterPatient.enregistrerButton.doClick();
//
//        Document found = collection.find(new Document("nom", "ali")).first();
//        assertNotNull(found, "Patient 'ali' should be found");
//        assertEquals("ali", found.getString("nom"));
//    }

}
