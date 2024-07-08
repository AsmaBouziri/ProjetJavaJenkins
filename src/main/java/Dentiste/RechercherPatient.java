package main.java.Dentiste;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RechercherPatient extends JFrame  {

	private JPanel contentPane;
	private JTextField champNom;
	private JTextField champPrenom;

	public static void main(String[] args) {

		RechercherPatient frame = new RechercherPatient();
		frame.setVisible(true);
		frame.setSize(800, 600);

	}

	public RechercherPatient() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Rechercher Patient");
		setBounds(100, 100, 753, 419);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(133, 11, 431, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Rechercher Patient");
		lblNewLabel_7.setForeground(SystemColor.windowBorder);
		lblNewLabel_7.setBackground(SystemColor.windowBorder);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_7.setBounds(78, 11, 319, 28);
		panel.add(lblNewLabel_7);
		
		champNom = new JTextField();
		champNom.setBounds(223, 50, 185, 35);
		panel.add(champNom);
		champNom.setColumns(10);
		
		champPrenom = new JTextField();
		champPrenom.setColumns(10);
		champPrenom.setBounds(223, 96, 185, 35);
		panel.add(champPrenom);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(52, 60, 147, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom :");
		lblNewLabel_1.setBounds(52, 117, 147, 14);
		panel.add(lblNewLabel_1);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 188, 411, 140);
		panel.add(textArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.windowBorder);
		panel_1.setBounds(0, 330, 431, 28);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		final JLabel labelResultat = new JLabel("Resultat :");
		labelResultat.setBounds(10, 0, 411, 25);
		labelResultat.setForeground(SystemColor.text);
		labelResultat.setBackground(SystemColor.text);
		panel_1.add(labelResultat);
		
		final JButton ModifierButton = new JButton("Rechercher");
		ModifierButton.setBackground(SystemColor.activeCaption);
		ModifierButton.setBounds(200, 142, 147, 41);
		ModifierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String nom = champNom.getText().trim();
		            String prenom = champPrenom.getText().trim();
		            String fichier = nom + "_" + prenom + ".txt";
		            JPanel panelModifier= new JPanel(new GridLayout(3, 2));
		            
		            try {
		                BufferedReader lecteur = new BufferedReader(new FileReader("fiches_patients/" +fichier));
		                String ligne;
		                StringBuilder resultat = new StringBuilder();
		                while ((ligne = lecteur.readLine()) != null) {
		                    resultat.append(ligne).append("\n");
		                }
		                textArea.setText(resultat.toString());
		                labelResultat.setText("Résultat pour : " + nom + " " + prenom );
		                panelModifier.add(ModifierButton);
		                lecteur.close();
		            } catch (IOException ex) {
		            	textArea.setText("");
		                labelResultat.setText("Patient non trouvé");
		            }
		        }
			});
				
		panel.add(ModifierButton);
		
		
		
		JButton HomeButton = new JButton("");
		HomeButton.setIcon(new ImageIcon(Accueil.class.getResource("/images/home.png")));
		HomeButton.setBounds(679, 11, 48, 41);
		contentPane.add(HomeButton);
		HomeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Accueil acc = new Accueil();
        		setVisible(false);
        		acc.setVisible(true);
        	}
        });
		
	}
}
