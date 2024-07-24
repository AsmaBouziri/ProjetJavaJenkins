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

    private ModifierFichePatient modifierFichePatient;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        // Assuming the constructor requires no arguments
        database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        modifierFichePatient = new ModifierFichePatient();
        modifierFichePatient.setSize(800, 600);
        modifierFichePatient.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(modifierFichePatient.nomTextField);
        assertNotNull(modifierFichePatient.prenomTextField);
        assertNotNull(modifierFichePatient.cinTextField);
        assertNotNull(modifierFichePatient.adresseTextField);
        assertNotNull(modifierFichePatient.professionTextField);
        assertNotNull(modifierFichePatient.telTextField);
        assertNotNull(modifierFichePatient.rechercherButton);
        assertNotNull(modifierFichePatient.modifierButton);
        assertNotNull(modifierFichePatient.annulerButton);
        assertNotNull(modifierFichePatient.hommeRadioButton);
        assertNotNull(modifierFichePatient.femmeRadioButton);
        assertNotNull(modifierFichePatient.jourComboBox);
        assertNotNull(modifierFichePatient.moisComboBox);
        assertNotNull(modifierFichePatient.anneeComboBox);
    }
}
