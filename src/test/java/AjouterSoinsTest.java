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

   }
    
    @Test
    public void testHomeBtn() {
    	ajouterSoins.homeButton.doClick();

    }

}
