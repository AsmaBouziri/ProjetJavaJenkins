package test.java;

import main.java.ListePatients;
import main.java.MongoDBUtil;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListePatientTest {
    private ListePatients listePatients;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("Patient");

        
        // Ajouter des données de test
        Document patient1 = new Document("nom", "Dupont")
                .append("prenom", "Jean")
                .append("cin", "AB123456")
                .append("adresse", "123 Rue de Paris")
                .append("profession", "Ingénieur")
                .append("tel", "0102030405");
        Document patient2 = new Document("nom", "Martin")
                .append("prenom", "Claire")
                .append("cin", "CD789012")
                .append("adresse", "456 Avenue des Champs")
                .append("profession", "Médecin")
                .append("tel", "0607080901");
        collection.insertMany(List.of(patient1, patient2));

        // Créer une instance de ListePatients
        listePatients = new ListePatients();
        listePatients.setVisible(true);
    }

    @Test
    public void testFrameVisibility() {
        assertTrue(listePatients.isVisible(), "La fenêtre ListePatients devrait être visible.");
    }

    @Test
    public void testHomeBtn() {
        listePatients.homeButton.doClick();

    }


     @AfterEach
    public void cleanUp() {
        collection.deleteOne(new Document("nom", "Dupont"));
        collection.deleteOne(new Document("nom", "Martin"));
    }


}
