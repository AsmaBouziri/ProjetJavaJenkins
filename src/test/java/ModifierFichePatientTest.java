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

    private ModifierFichePatient ajouterPatient;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        
        ajouterPatient = new ModifierFichePatient();
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
        assertNotNull(ajouterPatient.hommeRadioButton);
        assertNotNull(ajouterPatient.femmeRadioButton);
        assertNotNull(ajouterPatient.jourComboBox);
        assertNotNull(ajouterPatient.anneeComboBox);
        assertNotNull(ajouterPatient.moisComboBox);
    }
    

}
