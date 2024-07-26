package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import javax.swing.*;

import main.java.Acceuil;

public class AccueilTest {

    private Acceuil acceuil;

    @BeforeEach
    public void setUp() {
        acceuil = new Acceuil();
        // Ensure the frame is visible for testing
        acceuil.setVisible(true);
    }

    @Test
    public void testLabelAndButtonInitialization() {
        // Wait for the frame to be fully rendered
        SwingUtilities.invokeLater(() -> {
            // Check that the JLabel with text "Cabinet dentaire" is added
            boolean labelFound = false;
            boolean buttonFound = false;

            Component[] components = acceuil.getContentPane().getComponents();
            for (Component comp : components) {
                if (comp instanceof JPanel) {
                    JPanel panel = (JPanel) comp;
                    Component[] panelComponents = panel.getComponents();
                    for (Component c : panelComponents) {
                        if (c instanceof JLabel) {
                            JLabel label = (JLabel) c;
                            if ("Cabinet dentaire".equals(label.getText())) {
                                labelFound = true;
                                assertEquals(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22), label.getFont());
                                assertEquals(SystemColor.windowBorder, label.getForeground());
                            }
                        }
                        if (c instanceof JButton) {
                            JButton button = (JButton) c;
                            if (button.getIcon() != null) {
                                buttonFound = true;
                                assertNotNull(button.getIcon());
                                assertEquals(new ImageIcon(Acceuil.class.getResource("./images/icons8-logout-25.png")), button.getIcon());
                            }
                        }
                    }
                }
            }

            assertTrue(labelFound, "The 'Cabinet dentaire' label was not found.");
            assertTrue(buttonFound, "The logout button with icon was not found.");
        });
    }

    @Test
    public void testButtonPanelConfigurations() {
        JPanel panel = (JPanel) acceuil.getContentPane().getComponent(0); // Assuming the first component is the panel
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

        GridBagConstraints gbc = getGridBagConstraints(acceuil, buttonPanel);
        assertNotNull(gbc, "GridBagConstraints should not be null");
        assertEquals(expectedGridx, gbc.gridx, "GridBagConstraints.gridx should be " + expectedGridx);
        assertEquals(expectedGridy, gbc.gridy, "GridBagConstraints.gridy should be " + expectedGridy);
    }

    private GridBagConstraints getGridBagConstraints(Acceuil frame, JPanel buttonPanel) {
        JPanel panel = (JPanel) frame.getContentPane().getComponent(0); // Assuming the first component is the panel
        for (Component comp : panel.getComponents()) {
            if (comp == buttonPanel) {
                return ((GridBagLayout) panel.getLayout()).getConstraints(comp);
            }
        }
        return null;
    }

    @Test
    public void testComponentVisibility() {
        JPanel panel = (JPanel) acceuil.getContentPane().getComponent(0);
        for (int i = 0; i < panel.getComponentCount(); i++) {
            Component component = panel.getComponent(i);

            if (component instanceof JPanel) {
                JPanel buttonPanel = (JPanel) component;
                assertTrue(buttonPanel.isVisible(), "Button panel " + i + " should be visible");

                JButton button = (JButton) buttonPanel.getComponent(1);
                assertTrue(button.isVisible(), "Button in panel " + i + " should be visible");
            }
        }
    }
}
