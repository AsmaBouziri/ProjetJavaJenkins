package test.java;

import org.junit.jupiter.api.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.AjouterPatient;

import org.bson.Document;

import org.mockito.Mockito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterPatientTest {

    private AjouterPatient frame;
    private MongoDatabase database;
    private MongoCollection<Document> patientsCollection;

    @BeforeEach
    public void setUp() {
        frame = new AjouterPatient();
        Assertions.assertNotNull(frame, "Le frame AjouterPatient n'est pas initialisé.");

        MongoClient mockMongoClient = Mockito.mock(MongoClient.class);
        database = Mockito.mock(MongoDatabase.class);
        patientsCollection = Mockito.mock(MongoCollection.class);
        Mockito.when(mockMongoClient.getDatabase("CabinetDent")).thenReturn(database);
        Mockito.when(database.getCollection("Patient")).thenReturn(patientsCollection);

        ActionListener mockActionListener = Mockito.mock(ActionListener.class);
        Assertions.assertNotNull(frame.enregistrerButton, "Le bouton enregistrer n'est pas initialisé.");
        frame.enregistrerButton.addActionListener(mockActionListener);
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(patientsCollection);
    }

    @Test
    public void testAddPatientWithValidData() {
        frame.getNomTextField().setText("Dupont");
        frame.getPrenomTextField().setText("Jean");
        frame.getCinTextField().setText("123456789");
        frame.getAdresseTextField().setText("1 Rue du Soleil");
        frame.getProfessionTextField().setText("Ingénieur");
        frame.getTelTextField().setText("0123456789");
        frame.getHommeRadioButton().setSelected(true);

        ActionEvent event = new ActionEvent(frame.enregistrerButton, ActionEvent.ACTION_PERFORMED, "");
        frame.enregistrerButton.dispatchEvent(event);

        Document expectedPatientDocument = new Document()
                .append("nom", "Dupont")
                .append("prenom", "Jean")
                .append("cin", "123456789")
                .append("adresse", "1 Rue du Soleil")
                .append("profession", "Ingénieur")
                .append("tel", "0123456789")
                .append("sexe", "Homme")
                .append("dateNaissance", "");
        Mockito.verify(patientsCollection).insertOne(expectedPatientDocument);
    }

    @Test
    public void testAddPatientWithInvalidData() {
        frame.getNomTextField().setText("");
        frame.getPrenomTextField().setText("Jean");
        frame.getCinTextField().setText("123456789");
        frame.getAdresseTextField().setText("1 Rue du Soleil");
        frame.getProfessionTextField().setText("Ingénieur");
        frame.getTelTextField().setText("0123456789");
        frame.getHommeRadioButton().setSelected(true);

        ActionEvent event = new ActionEvent(frame.enregistrerButton, ActionEvent.ACTION_PERFORMED, "");
        frame.enregistrerButton.dispatchEvent(event);

        Mockito.verifyNoInteractions(patientsCollection);
    }

    @Test
    public void testCancelAddPatient() {
        ActionEvent event = new ActionEvent(frame.getAnnulerButton(), ActionEvent.ACTION_PERFORMED, "");
        frame.getAnnulerButton().dispatchEvent(event);

        Mockito.verifyNoInteractions(patientsCollection);
    }
}
