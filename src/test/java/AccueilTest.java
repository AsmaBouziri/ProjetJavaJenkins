package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
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
        String iconPath = "./images/addRDV.png";

        // Call the method to create the panel
        JPanel panel = new JPanel();
        try {
            panel = Acceuil.createButtonPanel("Button Text", iconPath, mockActionListener);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred while creating the button panel: " + e.getMessage());
        }

        assertNotNull(panel, "Panel should not be null");

        // Verify panel layout
        assertTrue(panel.getLayout() instanceof java.awt.GridBagLayout);

        // Debugging: Display the number of components and their types
        System.out.println("Number of components: " + panel.getComponentCount());
        for (Component component : panel.getComponents()) {
            System.out.println("Component: " + component.getClass().getSimpleName());
        }

        // Ensure the panel contains exactly 2 components
        assertEquals(2, panel.getComponentCount());

        // Verify the first component is a JLabel with a non-null icon
        Component firstComponent = panel.getComponent(0);
        assertTrue(firstComponent instanceof JLabel);
        JLabel iconLabel = (JLabel) firstComponent;
        assertNotNull(iconLabel.getIcon(), "Icon should be loaded correctly");

        // Verify the second component is a JButton with correct text
        Component secondComponent = panel.getComponent(1);
        assertTrue(secondComponent instanceof JButton);
        JButton button = (JButton) secondComponent;
        assertEquals("Button Text", button.getText());

        // Verify action listener
        button.doClick();
        Mockito.verify(mockActionListener, Mockito.times(1)).actionPerformed(Mockito.any());
    }
}
