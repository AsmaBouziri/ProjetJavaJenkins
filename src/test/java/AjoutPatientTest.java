package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.AjouterPatient;
import main.java.MongoDBUtil; // Commented out for now
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

// Imports for mocking MongoDBUtil (if needed)
// import org.mockito.Mockito;
// import static org.mockito.Mockito.*;

public class AjoutPatientTest {

    private AjouterPatient ajouterPatient;
    private MongoDatabase database;

    @BeforeEach
    public void setUp() throws Exception {
    	AjouterPatient frame = new AjouterPatient();
		frame.setSize(800, 600);
		frame.setVisible(true);

    }

    @AfterEach
    public void tearDown() {
        ajouterPatient.dispose();
    }

    public static boolean waitUntilButtonsAreInitialized(AjouterPatient frame) {
    	  int maxWaitTime = 10; // Adjust timeout as needed (in seconds)
    	  int waitTime = 0;
    	  while (waitTime < maxWaitTime && (frame.enregistrerButton == null || frame.annulerButton == null)) {
    	    try {
    	      Thread.sleep(1000); // Wait for 1 second
    	      waitTime++;
    	    } catch (InterruptedException e) {
    	      e.printStackTrace();
    	      return false;
    	    }
    	  }
    	  return frame.enregistrerButton != null && frame.annulerButton != null;
    	}
    
    @Test
    public void testAjouterPatientFields() {
    	 if (!waitUntilButtonsAreInitialized(ajouterPatient)) {
    		    fail("Failed to initialize buttons within timeout");
    		  }
        // Verify that all text fields are present and initially empty
        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
        // Verify that the radio buttons are present and no selection is made initially
        assertFalse(ajouterPatient.hommeRadioButton.isSelected());
        assertFalse(ajouterPatient.femmeRadioButton.isSelected());
    }

    @Test
    public void testAjouterPatientEnregistrerButton() {
    	 if (!waitUntilButtonsAreInitialized(ajouterPatient)) {
    		    fail("Failed to initialize buttons within timeout");
    		  }
        // Set field values
        ajouterPatient.nomTextField.setText("Doe");
        ajouterPatient.prenomTextField.setText("John");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("123 Main St");
        ajouterPatient.professionTextField.setText("Dentist");
        ajouterPatient.telTextField.setText("87654321");
        ajouterPatient.hommeRadioButton.setSelected(true);

        // Simulate the click on the Enregistrer button
        ajouterPatient.enregistrerButton.doClick();

        // Verify that the document was inserted into the collection (if using a test database)
        // ... Replace with your verification logic based on your test database setup
        // For example, using Mockito:
        // verify(collection, times(1)).insertOne(any(Document.class));

        // Verify that the fields are cleared
        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
        assertFalse(ajouterPatient.hommeRadioButton.isSelected());
        assertFalse(ajouterPatient.femmeRadioButton.isSelected());
    }

    @Test
    public void testAjouterPatientAnnulerButton() {
    	 if (!waitUntilButtonsAreInitialized(ajouterPatient)) {
    		    fail("Failed to initialize buttons within timeout");
    		  }
        // Set field values
        ajouterPatient.nomTextField.setText("Doe");
        ajouterPatient.prenomTextField.setText("John");
        ajouterPatient.cinTextField.setText("12345678");
        ajouterPatient.adresseTextField.setText("123 Main St");
        ajouterPatient.professionTextField.setText("Dentist");
        ajouterPatient.telTextField.setText("87654321");
        ajouterPatient.hommeRadioButton.setSelected(true);

        // Click the Annuler button
        ajouterPatient.annulerButton.doClick();

        // Verify that the fields are cleared
        assertEquals("", ajouterPatient.nomTextField.getText());
        assertEquals("", ajouterPatient.prenomTextField.getText());
        assertEquals("", ajouterPatient.cinTextField.getText());
        assertEquals("", ajouterPatient.adresseTextField.getText());
        assertEquals("", ajouterPatient.professionTextField.getText());
        assertEquals("", ajouterPatient.telTextField.getText());
        assertFalse(ajouterPatient.hommeRadioButton.isSelected());
        assertFalse(ajouterPatient.femmeRadioButton.isSelected());
    }
}
