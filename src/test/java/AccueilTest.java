package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.Acceuil;

public class AccueilTest {

    private JPanel panel;
    private GridBagConstraints gbc;

    @BeforeEach
    public void setUp() {
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        JPanel panel_1_1 = Acceuil.createButtonPanel("Rechercher Patient", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(panel_1_1, gbc);

        JPanel panel12 = Acceuil.createButtonPanel("Modifier Patient", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(panel12, gbc);

        JPanel panel13 = Acceuil.createButtonPanel("Ajouter RDV", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(panel13, gbc);

        JPanel panel14 = Acceuil.createButtonPanel("Modifier RDV", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(panel14, gbc);

        JPanel panel15 = Acceuil.createButtonPanel("Liste Patients", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(panel15, gbc);

        JPanel panel16 = Acceuil.createButtonPanel("Annuler RDV", "./images/addRDV.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mock implementation for test
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(panel16, gbc);

        JPanel panel17 = Acceuil.createButtonPanel("Supprimer Patient", "./images/addRDV.png", new ActionListener() {
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
        //assertNotNull(button.getIcon(), "Button should have an icon");
        //assertTrue(button.getIcon().toString().contains(expectedIconPath), "Button icon should be '" + expectedIconPath + "'");

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
}
