package main.java;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrameConf {
	public void configure(JFrame frame, JPanel contentPane, String title) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 753, 419);
        frame.setTitle(title);
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
    }
	
	public JPanel createHomePanel(JFrame frame) {
	    JPanel homePanel = new JPanel();
	    homePanel.setBounds(650, 10, 100, 50); 
	    homePanel.setBackground(SystemColor.activeCaption);

	    var homeButton = new JButton("");
	    homeButton.setIcon(new ImageIcon(ListePatients.class.getResource("/images/home.png")));
	    homePanel.add(homeButton);  

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                Acceuil acc = new Acceuil();
                acc.setVisible(true);
            }
        });

	    return homePanel;
	}

}
