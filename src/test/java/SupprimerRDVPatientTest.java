package test.java;

import main.java.SupprimerRdvPatient;
import main.java.MongoDBUtil;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.DeleteResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SupprimerRDVPatientTest {
    private SupprimerRdvPatient supprimerRdvPatient;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("CabinetDent");
        collection = database.getCollection("RendezVous");

        // Ajouter des données de test
        Document rdv1 = new Document("nom", "Dupont")
                .append("prenom", "Jean")
                .append("date", "15/7/2024")
                .append("heure", "10:30");
        collection.insertOne(rdv1);

        // Créer une instance de SupprimerRdvPatient
        supprimerRdvPatient = new SupprimerRdvPatient();
        supprimerRdvPatient.setVisible(true);
    }

    @Test
    public void testFrameVisibility() {
        assertTrue(supprimerRdvPatient.isVisible(), "La fenêtre SupprimerRdvPatient devrait être visible.");
    }

    @Test
    public void testComponentsPresence() {
        JPanel contentPane = (JPanel) supprimerRdvPatient.getContentPane();
        
        // Vérifie la présence des champs de texte
	   	 assertNotNull(supprimerRdvPatient.nomtextField);
	     assertNotNull(supprimerRdvPatient.prenomtextField);
	     assertNotNull(supprimerRdvPatient.HeurComboBox);
	     assertNotNull(supprimerRdvPatient.MinComboBox);
	     assertNotNull(supprimerRdvPatient.jourComboBox);
	     assertNotNull(supprimerRdvPatient.moisComboBox);
	     assertNotNull(supprimerRdvPatient.anneeComboBox);
	     assertNotNull(supprimerRdvPatient.EnregistrerButton);
	        

        assertNotNull(supprimerRdvPatient.EnregistrerButton, "Le bouton 'Annuler' devrait être présent.");
        
    }


}
