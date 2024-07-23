package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import main.java.AjouterPatient;

public class AjoutPatientTest {

    private AjouterPatient ajouterPatient;

    @BeforeEach
    public void setUp() throws Exception {
        ajouterPatient = new AjouterPatient();
        ajouterPatient.setSize(800, 600);
        ajouterPatient.setVisible(true);
        
        // Ensure that the GUI components are initialized properly
        ajouterPatient.nomTextField = new JTextField();
        ajouterPatient.prenomTextField = new JTextField();
        ajouterPatient.cinTextField = new JTextField();
        ajouterPatient.adresseTextField = new JTextField();
        ajouterPatient.professionTextField = new JTextField();
        ajouterPatient.telTextField = new JTextField();

        // Reinitialize buttons if necessary
        ajouterPatient.enregistrerButton = new JButton();
        ajouterPatient.annulerButton = new JButton();
    }

    @AfterEach
    public void tearDown() {
        ajouterPatient.dispose();
    }

    @Test
    public void testAjouterPatientFields() {
        // Verify that all text fields are present and initially empty
        assertNotNull(ajouterPatient.nomTextField);
        assertNotNull(ajouterPatient.prenomTextField);
        assertNotNull(ajouterPatient.cinTextField);
        assertNotNull(ajouterPatient.adresseTextField);
        assertNotNull(ajouterPatient.professionTextField);
        assertNotNull(ajouterPatient.telTextField);

        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
    }

    @Test
    public void testAjouterPatientEnregistrerButton() {
        // Set field values
        ajouterPatient.nomTextField.setText("Doe");
        ajouterPatient.prenomTextField.setText("John");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("123 Main St");
        ajouterPatient.professionTextField.setText("Dentist");
        ajouterPatient.telTextField.setText("87654321");

        // Simulate the click on the Enregistrer button
        ajouterPatient.enregistrerButton.doClick();

        // Verify that the fields are cleared
        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
    }

    @Test
    public void testAjouterPatientAnnulerButton() {
        // Set field values
        ajouterPatient.nomTextField.setText("Doe");
        ajouterPatient.prenomTextField.setText("John");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("123 Main St");
        ajouterPatient.professionTextField.setText("Dentist");
        ajouterPatient.telTextField.setText("87654321");

        // Click the Annuler button
        ajouterPatient.annulerButton.doClick();

        // Verify that the fields are cleared
        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
    }
}
