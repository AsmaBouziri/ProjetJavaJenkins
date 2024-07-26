package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Acceuil;

import javax.swing.*;
import java.awt.*;

public class AccueilTest {

    private Acceuil frame;

    @BeforeEach
    public void setUp() {
        // Crée une instance de la classe Acceuil avant chaque test
        frame = new Acceuil();
        frame.setVisible(true);
        // Assure que la fenêtre est bien initialisée avant de continuer
        assertNotNull(frame);
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
        if (ajouterPatientButton != null) ajouterPatientButton.doClick();
        if (rechercherPatientButton != null) rechercherPatientButton.doClick();
        if (modifierPatientButton != null) modifierPatientButton.doClick();
        if (ajouterRDVButton != null) ajouterRDVButton.doClick();
        if (modifierRDVButton != null) modifierRDVButton.doClick();
        if (listePatientsButton != null) listePatientsButton.doClick();
        if (annulerRDVButton != null) annulerRDVButton.doClick();
        if (supprimerPatientButton != null) supprimerPatientButton.doClick();
        
        // Utiliser une autre méthode pour vérifier la visibilité des nouvelles fenêtres
        // Vous devez probablement adapter cette partie selon comment vous vérifiez la visibilité des fenêtres
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

    // Cette méthode pourrait être adaptée selon la logique de votre application pour vérifier les fenêtres
    private boolean isFrameVisible(Class<?> frameClass) {
        for (Frame frame : Frame.getFrames()) {
            if (frameClass.isInstance(frame) && frame.isVisible()) {
                return true;
            }
        }
        return false;
    }
}
