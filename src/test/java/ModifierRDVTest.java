package test.java;


import main.java.ModifierRendezVous;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.Document;

public class ModifierRDVTest {

    private ModifierRendezVous modifierRendezVous;
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
    

    
    
}
