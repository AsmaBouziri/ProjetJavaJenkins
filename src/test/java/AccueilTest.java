package test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.FlowLayout;
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
		assertTrue(panel.getLayout() instanceof FlowLayout);

		// Verify button text (assuming only the button is added)
		JButton button = (JButton) panel.getComponent(0); // Access the first component (assuming it's the button)
		assertEquals("Button Text", button.getText());

		// Verify icon (if applicable)
		if (panel.getComponentCount() > 1) { // Check if there's a second component
		  JLabel iconLabel = (JLabel) panel.getComponent(1);
		  assertTrue(iconLabel.getIcon() instanceof ImageIcon);
		}


        // Verify action listener
        Mockito.verify(mockActionListener, Mockito.atLeastOnce()).actionPerformed(Mockito.any(ActionEvent.class));
    }
}
