package test.java;


import main.java.ModifierFichePatient;
import main.java.MongoDBUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.bson.Document;

public class ModifierFichePatientTest {

    private ModifierFichePatient modiferPatient;
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
    public void testPatientIntrouvable() {
        // Simuler l'entrée de l'utilisateur
    	modiferPatient.nomTextField.setText("Inconnu");
    	modiferPatient.prenomTextField.setText("Inconnu");

        // Simuler le clic sur le bouton rechercher
        modiferPatient.rechercherButton.doClick();

    }
}
    

    
