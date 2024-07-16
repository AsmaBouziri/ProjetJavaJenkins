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
        frame.getNomTextField().setText("Dupont");
        frame.getPrenomTextField().setText("Jean");
        frame.getCinTextField().setText("123456789");
        frame.getAdresseTextField().setText("1 Rue du Soleil");
        frame.getProfessionTextField().setText("Ingénieur");
        frame.getTelTextField().setText("0123456789");
        frame.getHommeRadioButton().setSelected(true);

        ActionEvent event = new ActionEvent(frame.getEnregistrerButton(), ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : frame.getEnregistrerButton().getActionListeners()) {
            listener.actionPerformed(event);
        }

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

}
