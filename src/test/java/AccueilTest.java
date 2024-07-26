package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mockito.Mockito;
import main.java.Acceuil;
import main.java.SupprimerRdvPatient;

public class AccueilTest {

    @Test
    public void testCreateButtonPanel() {
        // Create mock objects for dependencies
        ActionListener mockActionListener = Mockito.mock(ActionListener.class);

     // Call the method to create the panel
        JPanel panel = Acceuil.createButtonPanel("Annuler RDV", "./images/addRDV.png", mockActionListener);

        // Verify panel layout
        assertTrue(panel.getLayout() instanceof GridBagLayout, "Panel layout should be GridBagLayout");

        // Verify components in the panel
        assertEquals(2, panel.getComponentCount(), "Panel should have two components");

        // Verify button text and action listener
        JButton button = (JButton) panel.getComponent(1);
        assertEquals("Annuler RDV", button.getText(), "Button text should be 'text button'");
        button.doClick();
        Mockito.verify(mockActionListener, Mockito.times(1)).actionPerformed(Mockito.any());
    }
    
   @Test 
    private GridBagConstraints getGridBagConstraints(JPanel panel, JLabel label) {
        for (java.awt.Component comp : panel.getComponents()) {
            if (comp instanceof JLabel && comp == label) {
                return ((GridBagConstraints) ((GridBagLayout) panel.getLayout()).getConstraints(comp));
            }
        }
        return null;
    }
}
