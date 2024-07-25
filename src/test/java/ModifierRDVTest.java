package test.java;

import main.java.AjouterPatient;
import main.java.ModifierRendezVous;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.Document;

public class ModifierRDVTest {

    private ModifierRendezVous modifierRendezVous;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("RendezVous");
        
        modifierRendezVous = new ModifierRendezVous();
        modifierRendezVous.setSize(800, 600);
        modifierRendezVous.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(modifierRendezVous.nomtextField);
        assertNotNull(modifierRendezVous.prenomtextField);
        assertNotNull(modifierRendezVous.heuretextField);
        assertNotNull(modifierRendezVous.anneeComboBox);
        assertNotNull(modifierRendezVous.jourComboBox);
        assertNotNull(modifierRendezVous.moisComboBox);

    }
    
    
    @Test
    public void testEnregistrerRendezVous() {

        modifierRendezVous.nomtextField.setText("Dupont");
        modifierRendezVous.prenomtextField.setText("Jean");
        modifierRendezVous.jourComboBox.setSelectedItem(15);
        modifierRendezVous.moisComboBox.setSelectedItem(9);
        modifierRendezVous.anneeComboBox.setSelectedItem(2024);
        modifierRendezVous.heuretextField.setText("15:00");

        modifierRendezVous.enregistrerButton.doClick();

        Document updatedRendezVous = collection.find(new Document("nom", "Dupont")
                .append("prenom", "Jean")).first();
        assertNotNull(updatedRendezVous);
        assertEquals("15/08/2024", updatedRendezVous.getString("date"));
        assertEquals("15:00", updatedRendezVous.getString("heure"));
    }

    
    
}
