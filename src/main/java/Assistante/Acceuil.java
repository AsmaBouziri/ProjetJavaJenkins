package main.java.Assistante;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Acceuil extends JFrame {
	
    public Acceuil() { 
        setTitle("Cabinet Dentaire : Assistant");
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaption);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(37, 60, 158, 232);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
       
        JButton btnNewButton = new JButton("Ajouter Patient");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AjouterPatient ajouterFrame = new AjouterPatient();
        		ajouterFrame.setVisible(true);
        		dispose();
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnNewButton.setBounds(-20, 140, 180, 103);
        panel_1.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-add-user-group-woman-man-skin-type-7-48.png")));
        lblNewLabel.setBounds(40, 0, 108, 148);
        panel_1.add(lblNewLabel);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBounds(37, 303, 158, 232);
        panel.add(panel_1_1);
        panel_1_1.setLayout(null);
        
        JButton btnRechercherPatient = new JButton("Rechercher Patient");
        btnRechercherPatient.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RechercherPatient rechFrame = new RechercherPatient();
        		rechFrame.setVisible(true);
        		dispose();
        	}
        });
        btnRechercherPatient.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnRechercherPatient.setBounds(-12, 139, 180, 103);
        panel_1_1.add(btnRechercherPatient);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-find-user-male-skin-type-7-48.png")));
        lblNewLabel_4.setBounds(48, 0, 80, 142);
        panel_1_1.add(lblNewLabel_4);
        
        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setBounds(214, 303, 158, 232);
        panel.add(panel_1_1_1);
        panel_1_1_1.setLayout(null);
        
        JButton btnAjouterRdv = new JButton("Ajouter RDV");
        btnAjouterRdv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AjouterRDV ajouterRDVframe = new AjouterRDV();
        		ajouterRDVframe.setVisible(true);
        		dispose();
        	}
        });
        btnAjouterRdv.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnAjouterRdv.setBounds(-14, 139, 180, 103);
        panel_1_1_1.add(btnAjouterRdv);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-add-receipt-48.png")));
        lblNewLabel_5.setBounds(39, 0, 81, 141);
        panel_1_1_1.add(lblNewLabel_5);
        
        JPanel panel_1_2 = new JPanel();
        panel_1_2.setBounds(214, 60, 158, 232);
        panel.add(panel_1_2);
        panel_1_2.setLayout(null);
        
        JButton btnModifierPatient = new JButton("Modifier Patient");
        btnModifierPatient.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ModifierFichePatient modifierFrame = new ModifierFichePatient();
        		modifierFrame.setVisible(true);
        		dispose();
        	}
        });
        btnModifierPatient.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnModifierPatient.setBounds(-16, 140, 180, 102);
        panel_1_2.add(btnModifierPatient);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-edit-profile-skin-type-7-48.png")));
        lblNewLabel_1.setBounds(52, 0, 80, 141);
        panel_1_2.add(lblNewLabel_1);
        
        JPanel panel_1_1_2 = new JPanel();
        panel_1_1_2.setBounds(392, 303, 158, 232);
        panel.add(panel_1_1_2);
        panel_1_1_2.setLayout(null);
        
        JButton btnModifierRdv = new JButton("Modifier RDV");
        btnModifierRdv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 ModifierRendezVous modifierRDVFrame = new ModifierRendezVous();
        		modifierRDVFrame.setVisible(true);
        		dispose();
        	}
        });
        btnModifierRdv.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnModifierRdv.setBounds(-17, 139, 180, 103);
        panel_1_1_2.add(btnModifierRdv);
        
        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-edit-property-64.png")));
        lblNewLabel_6.setBounds(40, 0, 87, 142);
        panel_1_1_2.add(lblNewLabel_6);
        
        JPanel panel_1_3 = new JPanel();
        panel_1_3.setBounds(392, 60, 158, 232);
        panel.add(panel_1_3);
        panel_1_3.setLayout(null);
        
        JButton btnListePatients = new JButton("Liste Patients");
        btnListePatients.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 ListePatients listeFrame = new ListePatients();
        		listeFrame.setVisible(true);
        		dispose();
        	}
        });
        btnListePatients.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnListePatients.setBounds(-14, 140, 180, 104);
        panel_1_3.add(btnListePatients);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-todo-list-48.png")));
        lblNewLabel_2.setBounds(53, 0, 82, 140);
        panel_1_3.add(lblNewLabel_2);
        
        JPanel panel_1_1_2_1 = new JPanel();
        panel_1_1_2_1.setBounds(572, 303, 158, 232);
        panel.add(panel_1_1_2_1);
        panel_1_1_2_1.setLayout(null);
        
        JButton btnAnnulerRdv = new JButton("Annuler RDV");
        btnAnnulerRdv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SupprimerRdvPatient suppRDVFrame = new SupprimerRdvPatient();
        		suppRDVFrame.setVisible(true);
        		dispose();
        	}
        });
        btnAnnulerRdv.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnAnnulerRdv.setBounds(-18, 139, 180, 103);
        panel_1_1_2_1.add(btnAnnulerRdv);
        
        JLabel lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-delete-document-48.png")));
        lblNewLabel_7.setBounds(54, 0, 75, 142);
        panel_1_1_2_1.add(lblNewLabel_7);
        
        JPanel panel_1_3_1 = new JPanel();
        panel_1_3_1.setBounds(572, 60, 158, 232);
        panel.add(panel_1_3_1);
        panel_1_3_1.setLayout(null);
        
        JButton btnSupprimerPatient = new JButton("Supprimer Patient");
        btnSupprimerPatient.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SupprimerPatient suppFrame = new SupprimerPatient();
        		suppFrame.setVisible(true);
        		dispose();
        	}
        });
        btnSupprimerPatient.setFont(new Font("Tahoma", Font.ITALIC, 18));
        btnSupprimerPatient.setBounds(-16, 140, 180, 103);
        panel_1_3_1.add(btnSupprimerPatient);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(Acceuil.class.getResource("/images/icons8-unfriend-skin-type-7-48.png")));
        lblNewLabel_3.setBounds(53, 0, 82, 141);
        panel_1_3_1.add(lblNewLabel_3);
        
        JLabel lblNewLabel_8 = new JLabel("Cabinet dentaire");
        lblNewLabel_8.setForeground(SystemColor.windowBorder);
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_8.setBounds(283, 11, 291, 38);
        panel.add(lblNewLabel_8);
        setVisible(true); 
        
        
        JButton CButton = new JButton("");
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
    	Acceuil frame = new Acceuil();
    	
    }
}
