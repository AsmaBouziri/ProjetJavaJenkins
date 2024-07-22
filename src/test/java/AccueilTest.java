package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mockito.Mockito;
import main.java.Acceuil;

public class AccueilTest {

    @Test
    public void testCreateButtonPanel() {
        // Create mock objects for dependencies
        ActionListener mockActionListener = Mockito.mock(ActionListener.class);

        // Call the method to create the panel
        JPanel panel = new JPanel();
        try {
            panel = Acceuil.createButtonPanel("text button", "./images/addRDV.png", mockActionListener);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred while creating the button panel: " + e.getMessage());
        }

        // Verify panel layout
        assertTrue(panel.getLayout() instanceof GridBagLayout, "Panel layout should be GridBagLayout");

        // Verify components in the panel
        assertEquals(2, panel.getComponentCount(), "Panel should have two components");

        // Verify button text and action listener
        JButton button = (JButton) panel.getComponent(1);
        assertEquals("text button", button.getText(), "Button text should be 'text button'");
        button.doClick();
        Mockito.verify(mockActionListener, Mockito.times(1)).actionPerformed(Mockito.any());
    }
}
