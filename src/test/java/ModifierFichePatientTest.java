package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import main.java.ModifierFichePatient;
import main.java.MongoDBUtil;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ModifierFichePatientTest {

    private ModifierFichePatient modifierFichePatient;
    private static MongoClient mockMongoClient;
    private MongoDatabase mockDatabase;
    private MongoCollection<Document> mockCollection;

    @BeforeEach
    public void setUp() {
        mockMongoClient = Mockito.mock(MongoClient.class);
        mockDatabase = Mockito.mock(MongoDatabase.class);
        mockCollection = Mockito.mock(MongoCollection.class);

        // Assuming MongoDBUtil uses the mocked MongoClient
        Mockito.when(MongoDBUtil.getDatabase("CabinetDent")).thenReturn(mockDatabase);
        Mockito.when(mockDatabase.getCollection("Patient")).thenReturn(mockCollection);

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
        assertNotNull(modifierFichePatient.hommeRadioButton);
        assertNotNull(modifierFichePatient.femmeRadioButton);
        assertNotNull(modifierFichePatient.rechercherButton);
        assertNotNull(modifierFichePatient.modifierButton);
        assertNotNull(modifierFichePatient.annulerButton);
        assertNotNull(modifierFichePatient.jourComboBox);
        assertNotNull(modifierFichePatient.moisComboBox);
        assertNotNull(modifierFichePatient.anneeComboBox);
    }

    // You can add more tests here to test specific functionalities of ModifierFichePatient,
    // such as searching for a patient, modifying patient information, etc.
    // These tests might involve interacting with the mocked MongoClient objects.

}
