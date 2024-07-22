package test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
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
        JPanel panel = new JPanel();;
		try {
			panel = Acceuil.createButtonPanel("text button ", "./images/icons8-unfriend-skin-type-7-48.png", mockActionListener);
		} catch (Exception e) {
			e.printStackTrace();
		}

        // Verify panel layout
        GridBagLayout layout = (GridBagLayout) panel.getLayout();
        assertNotNull(layout);

        // Verify button text
        JButton button = (JButton) panel.getComponent(1);
        assertEquals("Button Text", button.getText());

        // Verify icon
        JLabel iconLabel = (JLabel) panel.getComponent(0);
        assertTrue(iconLabel.getIcon() instanceof ImageIcon);

        // Verify action listener
        Mockito.verify(mockActionListener, Mockito.atLeastOnce()).actionPerformed(Mockito.any(ActionEvent.class));
    }
}
