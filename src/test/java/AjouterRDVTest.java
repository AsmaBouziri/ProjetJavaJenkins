package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import main.java.AjouterRDV;
import main.java.MongoDBUtil;

class AjouterRDVTest {


    private AjouterRDV ajouterRDV;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("RendezVous");
        
        ajouterRDV = new AjouterRDV();
        ajouterRDV.setSize(800, 600);
        ajouterRDV.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
        assertNull(ajouterRDV.nomtextField);
        assertNull(ajouterRDV.prenomtextField);
        assertNull(ajouterRDV.heurComboBox);
        assertNull(ajouterRDV.minComboBox);
        assertNull(ajouterRDV.jourComboBox);
        assertNull(ajouterRDV.anneeComboBox);
        assertNull(ajouterRDV.moisComboBox);
 
    }
    

}
