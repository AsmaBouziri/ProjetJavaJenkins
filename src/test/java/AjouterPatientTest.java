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

import javax.swing.JTextField;

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

        frame.setDatabase(database);

        ActionListener mockActionListener = Mockito.mock(ActionListener.class);
        Assertions.assertNotNull(frame.getEnregistrerButton(), "Le bouton enregistrer n'est pas initialisé.");
        frame.getEnregistrerButton().addActionListener(mockActionListener);
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(patientsCollection);
    }
    
    
    @Test
    public void testAddPatientWithValidData() {
        // Assuming AjouterPatient has public methods to access components
        frame.setNomTextField(new JTextField("Dupont"));
        frame.setPrenomTextField(new JTextField("Jean"));
        frame.setCinTextField(new JTextField ("123456789"));
        frame.setAdresseTextField(new JTextField ("1 Rue du Soleil"));
        frame.setProfessionTextField(new JTextField("Ingénieur"));
        frame.setTelTextField(new JTextField("0123456789"));


        // Trigger the "Enregistrer" button action
        ActionEvent event = new ActionEvent(frame.getEnregistrerButton(), ActionEvent.ACTION_PERFORMED, "");
        frame.getEnregistrerButton().dispatchEvent(event);

        // Create expected patient document with correct date format
        Document expectedPatientDocument = new Document()
                .append("nom", "Dupont")
                .append("prenom", "Jean")
                .append("cin", "123456789")
                .append("adresse", "1 Rue du Soleil")
                .append("profession", "Ingénieur")
                .append("tel", "0123456789")
                .append("sexe", "Homme")
                .append("dateNaissance", new Document()
                        .append("jour", 15)
                        .append("mois", 7)
                        .append("annee", 2023));

        // Verify that the patient was added to the database
        Mockito.verify(patientsCollection).insertOne(expectedPatientDocument);
    }



}
