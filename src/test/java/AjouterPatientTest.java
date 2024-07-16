package test.java;

import org.junit.jupiter.api.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.java.AjouterPatient;
import org.bson.Document;
import org.mockito.Mockito;
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

  

}
