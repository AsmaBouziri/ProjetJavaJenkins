package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.Acceuil;
import main.java.AjouterPatient;

public class AccueilTest {

    private JPanel panel;
    private GridBagConstraints gbc;
    private Acceuil accueil;

    @BeforeEach
    public void setUp() {
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        JPanel panel_1_1 = accueil.createButtonPanel("Rechercher Patient", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(panel_1_1, gbc);

        JPanel panel12 = accueil.createButtonPanel("Modifier Patient", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(panel12, gbc);

        JPanel panel13 = accueil.createButtonPanel("Ajouter RDV", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(panel13, gbc);

        JPanel panel14 = accueil.createButtonPanel("Modifier RDV", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(panel14, gbc);

        JPanel panel15 = accueil.createButtonPanel("Liste Patients", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(panel15, gbc);

        JPanel panel16 = accueil.createButtonPanel("Annuler RDV", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(panel16, gbc);

        JPanel panel17 = accueil.createButtonPanel("Supprimer Patient", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(panel17, gbc);
        panel.setVisible(true);
    }

    @Test
    public void testPanelConfigurations() {
        assertEquals(7, panel.getComponentCount(), "Panel should have 7 components");

        // Test each panel component
        testButtonPanel((JPanel) panel.getComponent(0), "Rechercher Patient", "./images/addRDV.png", 1, 1);
        testButtonPanel((JPanel) panel.getComponent(1), "Modifier Patient", "./images/addRDV.png", 2, 1);
        testButtonPanel((JPanel) panel.getComponent(2), "Ajouter RDV", "./images/addRDV.png", 3, 1);
        testButtonPanel((JPanel) panel.getComponent(3), "Modifier RDV", "./images/addRDV.png", 0, 2);
        testButtonPanel((JPanel) panel.getComponent(4), "Liste Patients", "./images/addRDV.png", 1, 2);
        testButtonPanel((JPanel) panel.getComponent(5), "Annuler RDV", "./images/addRDV.png", 2, 2);
        testButtonPanel((JPanel) panel.getComponent(6), "Supprimer Patient", "./images/addRDV.png", 3, 2);
    }

    private void testButtonPanel(JPanel buttonPanel, String expectedText, String expectedIconPath, int expectedGridx, int expectedGridy) {
        assertEquals(2, buttonPanel.getComponentCount(), "Button panel should have two components");

        JButton button = (JButton) buttonPanel.getComponent(1);
        assertEquals(expectedText, button.getText(), "Button text should be '" + expectedText + "'");

        GridBagConstraints gbc = getGridBagConstraints(panel, buttonPanel);
        assertNotNull(gbc, "GridBagConstraints should not be null");
        assertEquals(expectedGridx, gbc.gridx, "GridBagConstraints.gridx should be " + expectedGridx);
        assertEquals(expectedGridy, gbc.gridy, "GridBagConstraints.gridy should be " + expectedGridy);
    }

    private GridBagConstraints getGridBagConstraints(JPanel panel, JPanel buttonPanel) {
        for (java.awt.Component comp : panel.getComponents()) {
            if (comp == buttonPanel) {
                return ((GridBagLayout) panel.getLayout()).getConstraints(comp);
            }
        }
        return null;
    }
    
    @Test
    public void testComponentVisibility() {
        JPanel panel = (JPanel) this.panel.getComponent(0);
        for (int i = 0; i < panel.getComponentCount(); i++) {
            Component component = panel.getComponent(i);

            // Check if the component is a JPanel before casting
            if (component instanceof JPanel) {
                JPanel buttonPanel = (JPanel) component;
                assertTrue(buttonPanel.isVisible(), "Button panel " + i + " should be visible");

                // Now access components within the button panel
                JButton button = (JButton) buttonPanel.getComponent(1);
                assertTrue(button.isVisible(), "Button in panel " + i + " should be visible");
            } 
        }
    }


}
