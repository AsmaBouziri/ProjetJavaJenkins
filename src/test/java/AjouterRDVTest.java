package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.AjouterRDV;
import main.java.MongoDBUtil;

class AjouterRDVTest {

    private AjouterRDV ajouterRDV;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("RendezVous");

        ajouterRDV = new AjouterRDV();
        ajouterRDV.setSize(800, 600);
        ajouterRDV.setVisible(true);
        
        ajouterRDV.pack();
    }

    @Test
    public void testComponentsInitialization() {
        // Vérifiez que les composants ne sont pas null
        assertNotNull(ajouterRDV.nomtextField, "Nom text field should be initialized");
        assertNotNull(ajouterRDV.prenomtextField, "Prénom text field should be initialized");
        assertNotNull(ajouterRDV.heurComboBox, "Heure combo box should be initialized");
        assertNotNull(ajouterRDV.minComboBox, "Minute combo box should be initialized");
        assertNotNull(ajouterRDV.jourComboBox, "Jour combo box should be initialized");
        assertNotNull(ajouterRDV.anneeComboBox, "Année combo box should be initialized");
        assertNotNull(ajouterRDV.moisComboBox, "Mois combo box should be initialized");
    }
    
    @Test
    public void testHomeBtn() {
    	ajouterRDV.homeButton.doClick();

    }
}
