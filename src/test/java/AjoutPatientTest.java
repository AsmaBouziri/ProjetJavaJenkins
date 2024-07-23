package test.java;

import main.java.AjouterPatient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
