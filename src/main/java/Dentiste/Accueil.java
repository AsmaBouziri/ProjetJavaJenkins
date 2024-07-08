package main.java.Dentiste;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.java.Assistante.Acceuil;
import main.java.Assistante.SeConnecter;


public class Accueil extends JFrame {
	
    public Accueil() { 
        setTitle("Cabinet Dentaire : Dentiste");
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaption);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(133, 60, 158, 232);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
       
        final JButton btnNewButton = new JButton("Planning RDV");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListeRDV listeRDVFrame = new ListeRDV();
        		listeRDVFrame.setVisible(true);
        		dispose();
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnNewButton.setBounds(-20, 121, 180, 122);
        panel_1.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 13));
        lblNewLabel.setIcon(new ImageIcon(Accueil.class.getResource("/images/icons8-calendar-48.png")));
        lblNewLabel.setBounds(52, 0, 108, 142);
        panel_1.add(lblNewLabel);
        
        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setBounds(310, 303, 158, 232);
        panel.add(panel_1_1_1);
        panel_1_1_1.setLayout(null);
        
        final JButton btnAjouterRdv = new JButton("Ajouter Soins");
        btnAjouterRdv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AjouterSoins AjouterSFrame = new AjouterSoins();
        		AjouterSFrame.setVisible(true);
        		dispose();
 
        	}
        });
        btnAjouterRdv.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnAjouterRdv.setBounds(-14, 139, 180, 103);
        panel_1_1_1.add(btnAjouterRdv);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon(Accueil.class.getResource("/images/icons8-add-receipt-48.png")));
        lblNewLabel_5.setBounds(39, 0, 81, 141);
        panel_1_1_1.add(lblNewLabel_5);
        
        JPanel panel_1_2 = new JPanel();
        panel_1_2.setBounds(310, 60, 158, 232);
        panel.add(panel_1_2);
        panel_1_2.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(53, 0, 82, 140);
        panel_1_2.add(lblNewLabel_2);
        lblNewLabel_2.setIcon(new ImageIcon(Accueil.class.getResource("/images/icons8-todo-list-48.png")));
        
        final JButton btnListePatients = new JButton("Liste Patients");
        btnListePatients.setBounds(-16, 138, 180, 104);
        panel_1_2.add(btnListePatients);
        btnListePatients.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 ListePatients listeFrame = new ListePatients();
        		 dispose();
        		listeFrame.setVisible(true);
        	}
        });
        btnListePatients.setFont(new Font("Tahoma", Font.ITALIC, 18));
        
        JPanel panel_1_3 = new JPanel();
        panel_1_3.setBounds(488, 60, 158, 232);
        panel.add(panel_1_3);
        panel_1_3.setLayout(null);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setBounds(49, 0, 80, 142);
        panel_1_3.add(lblNewLabel_4);
        lblNewLabel_4.setIcon(new ImageIcon(Accueil.class.getResource("/images/icons8-find-user-male-skin-type-7-48.png")));
        
        final JButton btnRechercherPatient = new JButton("Rechercher Patient");
        btnRechercherPatient.setBounds(-11, 139, 180, 103);
        panel_1_3.add(btnRechercherPatient);
        btnRechercherPatient.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RechercherPatient rechFrame = new RechercherPatient();
        		dispose();
        		rechFrame.setVisible(true);
        	}
        });
        btnRechercherPatient.setFont(new Font("Tahoma", Font.ITALIC, 18));
        
        JLabel lblNewLabel_8 = new JLabel("Cabinet dentaire");
        lblNewLabel_8.setForeground(SystemColor.windowBorder);
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_8.setBounds(283, 11, 291, 38);
        panel.add(lblNewLabel_8);
        
        
        final JButton CButton = new JButton("");
        CButton.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-logout-25.png")));
        CButton.setBounds(679, 11, 48, 41);
		panel.add(CButton);
		CButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SeConnecter connect = new SeConnecter();
        		setVisible(false);
        		connect.setVisible(true);
        	}
        });
        
        
    }

    public static void main(String[] args) { 
    	Accueil frame = new Accueil();
    	
    }
}
