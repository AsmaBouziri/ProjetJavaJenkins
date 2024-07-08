package Assistante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class AjouterRDV extends JFrame  {

	private JPanel contentPane;
	private JTextField nomtextField;
	private JTextField prenomtextField;

	public static void main(String[] args) {
		AjouterRDV frame = new AjouterRDV();
		frame.setVisible(true);
		frame.setSize(800, 600);

	}

	public AjouterRDV() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 419);
		setTitle("Ajouter Rendez-Vous");
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(135, 11, 431, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Titre = new JLabel("Ajouter RDV");
		Titre.setForeground(SystemColor.windowBorder);
		Titre.setBackground(SystemColor.windowBorder);
		Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
		Titre.setBounds(132, 11, 319, 28);
		panel.add(Titre);
		
	
		nomtextField = new JTextField();
		nomtextField.setBounds(191, 86, 162, 28);
		panel.add(nomtextField);
		nomtextField.setColumns(10);
		
		prenomtextField = new JTextField();
		prenomtextField.setBounds(191, 125, 162, 28);
		panel.add(prenomtextField);
		prenomtextField.setColumns(10);

		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
		JComboBox jourComboBox = new JComboBox();
		jourComboBox.setBounds(191, 164, 50, 22);
		panel.add(jourComboBox);
		for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        
		JComboBox moisComboBox = new JComboBox();
		moisComboBox.setBounds(290, 164, 100, 22);
		panel.add(moisComboBox);
		for (int i = currentYear - 2 ; i <= currentYear + 5; i++) {
			moisComboBox.addItem(i);
        }

		

		JComboBox annneeComboBox = new JComboBox();
		annneeComboBox.setBounds(240, 164, 50, 22);
		panel.add(annneeComboBox);
		for (int i = 1; i <= 12; i++) {
			annneeComboBox.addItem(i);
        }
		
		JComboBox 	HeurComboBox = new JComboBox();
		HeurComboBox.setBounds(191, 197, 50, 22);
		panel.add(HeurComboBox);
		for (int i = 9; i <= 15; i++) {
			HeurComboBox.addItem(i);
        }
		
		JComboBox MinComboBox = new JComboBox();
		MinComboBox.setBounds(271, 197, 50, 22);
		panel.add(MinComboBox);
		for (int i = 0; i <= 59; i=i+15) {
			MinComboBox.addItem(i);
        }
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(47, 93, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom");
		lblNewLabel_1.setBounds(47, 132, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setBounds(47, 168, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Heure");
		lblNewLabel_3.setBounds(47, 201, 46, 14);
		panel.add(lblNewLabel_3);
		

		JButton EnregistrerButton = new JButton("Enregistrer");
		EnregistrerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		EnregistrerButton.setBounds(147, 307, 139, 40);
		panel.add(EnregistrerButton);
		EnregistrerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = nomtextField.getText();
	            String prenom = prenomtextField.getText();
	            int jour = (int) jourComboBox.getSelectedItem();
	            int mois = (int) moisComboBox.getSelectedItem();
	            int annee= (int) annneeComboBox.getSelectedItem();
	            int heur = (int) HeurComboBox.getSelectedItem();
	            int min= (int) MinComboBox.getSelectedItem();
	            String nomFichier = nom + "_" + prenom + ".txt";
	            
	            if (nom.isEmpty() || prenom.isEmpty()) {
	                JOptionPane.showMessageDialog(EnregistrerButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
	             
	            } else {
	                try (FileWriter writer = new FileWriter("RDVS.txt", true) ) {
	                    writer.write("Nom : " +nom + " , " + "Prénom : "+ prenom +" , " + "Date RDV : "+ jour +"/" + annee  +"/"+ mois
	                            + " , " + "Heur : "+ heur + "h" + min + "\n" );
	                    try (FileWriter fw = new FileWriter("fiches_patients/"+nomFichier, true))
	                    {
	                    	fw.write("Date RDV : "+ jour +"/" + annee  +"/"+ mois + " , " + "Heur : "+ heur + "h" + min + "\n" );
		                    JOptionPane.showMessageDialog(EnregistrerButton, "La fiche a été enregistrée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	                    } catch (IOException ex) {
		                    JOptionPane.showMessageDialog(EnregistrerButton, "Impossible d'enregistrer la fiche.", "Erreur", JOptionPane.ERROR_MESSAGE);
		                }

	                    
	                } catch (IOException ex) {
	                    JOptionPane.showMessageDialog(EnregistrerButton, "Impossible d'enregistrer la fiche.", "Erreur", JOptionPane.ERROR_MESSAGE);
	                }

	                // Effacement des champs de saisie
	                nomtextField.setText("");
	                prenomtextField.setText("");
	
	            }
	            
			}});
		
		JButton HomeButton = new JButton("");
		HomeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("/images/home.png")));
		HomeButton.setBounds(679, 11, 48, 41);
		contentPane.add(HomeButton);
		HomeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Acceuil acc = new Acceuil();
        		setVisible(false);
        		acc.setVisible(true);
        	}
        });
	}
}
