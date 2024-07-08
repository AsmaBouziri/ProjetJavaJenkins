package main.java.Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;

public class SupprimerRdvPatient extends JFrame  {
	private JPanel contentPane;
	private JTextField nomtextField;
	private JTextField prenomtextField;

	public static void main(String[] args) {
		SupprimerRdvPatient frame = new SupprimerRdvPatient();
		frame.setVisible(true);

	}

	public SupprimerRdvPatient() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 419);
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
		
		JLabel Titre = new JLabel("Annuler RDV");
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
		for (int i = currentYear - 100; i <= currentYear; i++) {
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
		

		JButton EnregistrerButton = new JButton("Annuler");
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
		        String fileName ="RDVS.txt";
		        int jour = (int) jourComboBox.getSelectedItem();
	            int mois = (int) moisComboBox.getSelectedItem();
	            int annee= (int) annneeComboBox.getSelectedItem();
	            int heur = (int) HeurComboBox.getSelectedItem();
	            int min= (int) MinComboBox.getSelectedItem();
			
				try {
				    FileReader reader = new FileReader(fileName);
				    FileWriter writer = new FileWriter("temp.txt");
				    BufferedReader bufferedReader = new BufferedReader(reader);
				    BufferedWriter bufferedWriter = new BufferedWriter(writer);
				//&& line.contains("Date RDV : "+ jour +"/" + annee  +"/"+mois + " , " + "Heur : "+ heur + "h" + min)
				    String line;
				    while ((line = bufferedReader.readLine()) != null) {
				    	System.out.println("Date RDV : "+ annee +"/" + jour  +"/"+mois + " , " + "Heur : "+ heur + "h" + min);
				    	System.out.println("annee :"+annee);
				    	System.out.println("jour :"+jour);
				    	System.out.println("mois :"+mois);
				        if (line.contains(nom) && line.contains(prenom)&& line.contains("Date RDV : "+ jour +"/" + mois  +"/"+ annee+ " , " + "Heur : "+ heur + "h" + min) )
				        {
				            continue;
				        }
				        bufferedWriter.write(line);
				        bufferedWriter.newLine();
				    }
				    bufferedReader.close();
				    bufferedWriter.close();
				    
				    File oldFile = new File(fileName);
				    oldFile.delete();

				    File newFile = new File("temp.txt");
				    newFile.renameTo(oldFile);
				

				    // Affichez un message de confirmation de la suppression de la ligne contenant "asma"
				    JOptionPane.showMessageDialog(EnregistrerButton, "supprimée avec succès!");
				} catch (IOException er) {
				    // Gérer les erreurs de lecture/écriture ici
				    er.printStackTrace();
				}
				
				
			}});
	}
}
