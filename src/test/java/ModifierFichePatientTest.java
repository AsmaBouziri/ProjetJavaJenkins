package test.java;

import main.java.ModifierFichePatient;
import main.java.MongoDBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.Document;

public class ModifierFichePatientTest {

    private ModifierFichePatient modifierFichePatient;
    private MongoCollection<Document> mockCollection;

    @BeforeEach
    public void setUp() {
        mockCollection = Mockito.mock(MongoCollection.class);
        modifierFichePatient = new ModifierFichePatient();
        modifierFichePatient.database = MongoDBUtil.getDatabase("CabinetDent");
        modifierFichePatient.collection = mockCollection;
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
    
    @Test
    public void testModifierButtonUpdatesData() {
        // Configure the test data
        modifierFichePatient.nomTextField.setText("ali");
        modifierFichePatient.prenomTextField.setText("ali");
        modifierFichePatient.cinTextField.setText("12345678");
        modifierFichePatient.adresseTextField.setText("ben arous");
        modifierFichePatient.professionTextField.setText("professeur");
        modifierFichePatient.telTextField.setText("12345678");

        modifierFichePatient.jourComboBox.setSelectedItem(12);
        modifierFichePatient.moisComboBox.setSelectedItem(2);
        modifierFichePatient.anneeComboBox.setSelectedItem(2150);
        modifierFichePatient.hommeRadioButton.setSelected(true);

        // Simulate button click
        modifierFichePatient.modifierButton.doClick();

        // Expected Document
        Document expectedDocument = new Document("nom", "ali")
            .append("prenom", "ali")
            .append("cin", "12345678")
            .append("sexe", "Homme") 
            .append("adresse", "ben arous")
            .append("telephone", "12345678")
            .append("dataNaiss", "12/02/2150")
            .append("profession", "professeur");

        // Verify that updateOne was called with the expected document
        Document query = new Document("nom", "ali").append("prenom", "ali");
        Document update = new Document("$set", expectedDocument);
        verify(mockCollection).updateOne(query, update);
    }
}
