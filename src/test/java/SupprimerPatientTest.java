package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.AjouterPatient;
import main.java.MongoDBUtil;
import main.java.SupprimerPatient;

class SupprimerPatientTest {


    private SupprimerPatient supprimrPatient;
    private static MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");
        
        supprimrPatient = new SupprimerPatient();
        supprimrPatient.setSize(800, 600);
        supprimrPatient.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(supprimrPatient.nomtextField);
        assertNotNull(supprimrPatient.prenomtextField);
        assertNotNull(supprimrPatient.btnNewButton);

    }
}
