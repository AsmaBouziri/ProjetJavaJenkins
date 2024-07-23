package test.java;

import main.java.AjouterPatient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.Document;

public class AjoutPatientTest {

    private AjouterPatient ajouterPatient;

    @BeforeEach
    public void setUp() {
        ajouterPatient = new AjouterPatient();
        ajouterPatient.setSize(800, 600);
        ajouterPatient.setVisible(true);
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(ajouterPatient.nomTextField);
        assertNotNull(ajouterPatient.prenomTextField);
        assertNotNull(ajouterPatient.cinTextField);
        assertNotNull(ajouterPatient.adresseTextField);
        assertNotNull(ajouterPatient.professionTextField);
        assertNotNull(ajouterPatient.telTextField);
        assertNotNull(ajouterPatient.enregistrerButton);
        assertNotNull(ajouterPatient.annulerButton);
        assertNotNull(ajouterPatient.hommeRadioButton);
        assertNotNull(ajouterPatient.femmeRadioButton);
    }
    
    @Test
    public void testEnregistrerButtonInsertsData() {
        // Configure the test data
        ajouterPatient.nomTextField.setText("ali");
        ajouterPatient.prenomTextField.setText("ali");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("ben arous");
        ajouterPatient.professionTextField.setText("professeur");
        ajouterPatient.telTextField.setText("12345678");
        // Simulate button click
        ajouterPatient.enregistrerButton.doClick();

        // Expected Document
        Document expectedDocument = new Document("nom", "ali")
            .append("prenom", "ali")
            .append("cin", "12345678")
            .append("sexe", "f") // Assure-toi que c'est 'f' ou adapte selon le champ de sexe
            .append("adresse", "ben arous")
            .append("telephone", "12345678")
            .append("dataNaiss", "12/02/2150")
            .append("profession", "professeur");

        // Verify that insertOne was called with the expected document
        verify(mockCollection).insertOne(expectedDocument);
    }

}
