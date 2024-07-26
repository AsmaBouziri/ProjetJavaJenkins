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
        assertNotNull(getComponentByName(contentPane, "nomtextField"), "Le champ 'Nom' devrait être présent.");
        assertNotNull(getComponentByName(contentPane, "prenomtextField"), "Le champ 'Prénom' devrait être présent.");

        // Vérifie la présence des JComboBox
        assertNotNull(getComponentByName(contentPane, "jourComboBox"), "Le JComboBox 'Jour' devrait être présent.");
        assertNotNull(getComponentByName(contentPane, "moisComboBox"), "Le JComboBox 'Mois' devrait être présent.");
        assertNotNull(getComponentByName(contentPane, "anneeComboBox"), "Le JComboBox 'Année' devrait être présent.");
        assertNotNull(getComponentByName(contentPane, "HeurComboBox"), "Le JComboBox 'Heure' devrait être présent.");
        assertNotNull(getComponentByName(contentPane, "MinComboBox"), "Le JComboBox 'Minute' devrait être présent.");
        
        // Vérifie la présence du bouton 'Annuler'
        JButton enregistrerButton = (JButton) getComponentByName(contentPane, "EnregistrerButton");
        assertNotNull(enregistrerButton, "Le bouton 'Annuler' devrait être présent.");
        
        // Vérifie la présence du bouton 'Home'
        JButton homeButton = (JButton) getComponentByName(contentPane, "HomeButton");
        assertNotNull(homeButton, "Le bouton 'Home' devrait être présent.");
    }

    @Test
    public void testAnnulerRdv() {
        JTextField nomtextField = (JTextField) getComponentByName(supprimerRdvPatient.getContentPane(), "nomtextField");
        JTextField prenomtextField = (JTextField) getComponentByName(supprimerRdvPatient.getContentPane(), "prenomtextField");
        JComboBox<Integer> jourComboBox = (JComboBox<Integer>) getComponentByName(supprimerRdvPatient.getContentPane(), "jourComboBox");
        JComboBox<Integer> moisComboBox = (JComboBox<Integer>) getComponentByName(supprimerRdvPatient.getContentPane(), "moisComboBox");
        JComboBox<Integer> anneeComboBox = (JComboBox<Integer>) getComponentByName(supprimerRdvPatient.getContentPane(), "anneeComboBox");
        JComboBox<Integer> heurComboBox = (JComboBox<Integer>) getComponentByName(supprimerRdvPatient.getContentPane(), "HeurComboBox");
        JComboBox<Integer> minComboBox = (JComboBox<Integer>) getComponentByName(supprimerRdvPatient.getContentPane(), "MinComboBox");

        // Remplir les champs avec des données
        nomtextField.setText("Dupont");
        prenomtextField.setText("Jean");
        jourComboBox.setSelectedItem(15);
        moisComboBox.setSelectedItem(7);
        anneeComboBox.setSelectedItem(2024);
        heurComboBox.setSelectedItem(10);
        minComboBox.setSelectedItem(30);

        JButton enregistrerButton = (JButton) getComponentByName(supprimerRdvPatient.getContentPane(), "EnregistrerButton");
        enregistrerButton.doClick();

        // Vérifie que le rendez-vous a été supprimé
        Document filter = new Document()
                .append("nom", "Dupont")
                .append("prenom", "Jean")
                .append("date", "15/7/2024")
                .append("heure", "10:30");
        
        long count = collection.countDocuments(filter);
        assertEquals(0, count, "Le rendez-vous devrait être supprimé.");
    }

    @AfterEach
    public void tearDown() {
        // Fermer la connexion à MongoDB
        mongoClient.close();
        // Fermer la fenêtre de test
        supprimerRdvPatient.dispose();
    }

    private Component getComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (component instanceof JComponent) {
                JComponent jComponent = (JComponent) component;
                if (name.equals(jComponent.getName())) {
                    return jComponent;
                }
            }
            if (component instanceof Container) {
                Component found = getComponentByName((Container) component, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
