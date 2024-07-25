package test.java;

import main.java.AjouterSoins;
import main.java.MongoDBUtil;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AjouterSoinsTest {

    private AjouterSoins ajouterSoins;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        
        // Initialize the AjouterSoins instance
        ajouterSoins = new AjouterSoins();        
    }

    @Test
    public void testEnregistrerButtonInsertNewPatient() {
        // Configure the test data
        ajouterSoins.nomText.setText("test");
        ajouterSoins.prenomText.setText("test");
        ajouterSoins.comboBox.setSelectedItem("détartrage");

        ajouterSoins.jourComboBox.setSelectedItem(15);
        ajouterSoins.moisComboBox.setSelectedItem(8);
        ajouterSoins.anneeComboBox.setSelectedItem(2024);

        ajouterSoins.enregistrerButton.doClick();

 
    }
}