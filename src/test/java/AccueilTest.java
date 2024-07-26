package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Acceuil;
import main.java.AjouterPatient;
import main.java.AjouterRDV;
import main.java.ListePatients;
import main.java.ModifierFichePatient;
import main.java.ModifierRendezVous;
import main.java.RechercherPatient;
import main.java.SupprimerPatient;
import main.java.SupprimerRdvPatient;

import javax.swing.*;
import java.awt.*;

public class AccueilTest {

    private Acceuil frame;

    @BeforeEach
    public void setUp() {
        // Crée une instance de la classe Acceuil avant chaque test
        frame = new Acceuil();
        frame.setVisible(true);
    }

    @Test
    public void testFrameTitle() {
        // Vérifie que le titre de la fenêtre est correctement défini
        assertEquals("Cabinet Dentaire : Assistant", frame.getTitle());
    }

    @Test
    public void testButtonPresence() {
        // Vérifie que tous les boutons sont présents
        assertNotNull(findButtonByText("Ajouter Patient"));
        assertNotNull(findButtonByText("Rechercher Patient"));
        assertNotNull(findButtonByText("Modifier Patient"));
        assertNotNull(findButtonByText("Ajouter RDV"));
        assertNotNull(findButtonByText("Modifier RDV"));
        assertNotNull(findButtonByText("Liste Patients"));
        assertNotNull(findButtonByText("Annuler RDV"));
        assertNotNull(findButtonByText("Supprimer Patient"));
    }

    @Test
    public void testButtonActions() {
        // Vérifie que les boutons déclenchent les actions attendues
        JButton ajouterPatientButton = findButtonByText("Ajouter Patient");
        JButton rechercherPatientButton = findButtonByText("Rechercher Patient");
        JButton modifierPatientButton = findButtonByText("Modifier Patient");
        JButton ajouterRDVButton = findButtonByText("Ajouter RDV");
        JButton modifierRDVButton = findButtonByText("Modifier RDV");
        JButton listePatientsButton = findButtonByText("Liste Patients");
        JButton annulerRDVButton = findButtonByText("Annuler RDV");
        JButton supprimerPatientButton = findButtonByText("Supprimer Patient");

        // Simule un clic sur chaque bouton et vérifie l'action
        ajouterPatientButton.doClick();
        assertTrue(isFrameVisible(AjouterPatient.class));

        rechercherPatientButton.doClick();
        assertTrue(isFrameVisible(RechercherPatient.class));

        modifierPatientButton.doClick();
        assertTrue(isFrameVisible(ModifierFichePatient.class));

        ajouterRDVButton.doClick();
        assertTrue(isFrameVisible(AjouterRDV.class));

        modifierRDVButton.doClick();
        assertTrue(isFrameVisible(ModifierRendezVous.class));

        listePatientsButton.doClick();
        assertTrue(isFrameVisible(ListePatients.class));

        annulerRDVButton.doClick();
        assertTrue(isFrameVisible(SupprimerRdvPatient.class));

        supprimerPatientButton.doClick();
        assertTrue(isFrameVisible(SupprimerPatient.class));
    }

    private JButton findButtonByText(String text) {
        // Trouve un bouton par son texte
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                for (Component c : ((JPanel) comp).getComponents()) {
                    if (c instanceof JButton && ((JButton) c).getText().equals(text)) {
                        return (JButton) c;
                    }
                }
            }
        }
        return null;
    }

    private boolean isFrameVisible(Class<?> frameClass) {
        // Vérifie si une fenêtre de type frameClass est visible
        for (Frame frame : Frame.getFrames()) {
            if (frameClass.isInstance(frame) && frame.isVisible()) {
                return true;
            }
        }
        return false;
    }
}
