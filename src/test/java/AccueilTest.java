package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

        // Path to the icon
        String iconPath = "./images/icons8-unfriend-skin-type-7-48.png";

        // Load the icon to avoid NullPointerException
        java.net.URL imgURL = getClass().getResource(iconPath);
        assertNotNull(imgURL, "Image resource should be available");

        // Call the method to create the panel
        JPanel panel = null;
        try {
            panel = Acceuil.createButtonPanel("Button Text", iconPath, mockActionListener);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred while creating the button panel: " + e.getMessage());
        }

        assertNotNull(panel, "Panel should not be null");

        // Verify panel layout
        assertTrue(panel.getLayout() instanceof java.awt.GridBagLayout);

        // Verify button text (assuming the button is the second component)
        JButton button = (JButton) panel.getComponent(1);
        assertEquals("Button Text", button.getText());

        // Verify icon (if applicable)
        JLabel iconLabel = (JLabel) panel.getComponent(0);
        assertTrue(iconLabel.getIcon() instanceof ImageIcon);

        // Verify action listener
        button.doClick();
        Mockito.verify(mockActionListener, Mockito.times(1)).actionPerformed(Mockito.any());
    }
}
