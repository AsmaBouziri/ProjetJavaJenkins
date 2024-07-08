package main.java.Assistante;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ModifierRendezVous extends JFrame {

	private JPanel contentPane;
	private JTextField nomtextField;
	private JTextField prénomtextField;

	public static void main(String[] args) {
		ModifierRendezVous frame = new ModifierRendezVous();
		frame.setVisible(true);
		frame.setSize(800, 600);
		
	}

	public ModifierRendezVous() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 419);
		contentPane = new JPanel();
		setTitle("Modifier Rendez-vous");
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(133, 11, 431, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Titre = new JLabel("Modifier RDV");
		Titre.setForeground(SystemColor.windowBorder);
		Titre.setBackground(SystemColor.windowBorder);
		Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
		Titre.setBounds(112, 11, 319, 28);
		panel.add(Titre);
		
		
		nomtextField = new JTextField();
		nomtextField.setBounds(191, 86, 162, 28);
		panel.add(nomtextField);
		nomtextField.setColumns(10);
		
		prénomtextField = new JTextField();
		prénomtextField.setBounds(191, 125, 162, 28);
		panel.add(prénomtextField);
		prénomtextField.setColumns(10);
		
		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
		JComboBox jourAncComboBox = new JComboBox();
		jourAncComboBox.setBounds(47, 217, 50, 22);
		panel.add(jourAncComboBox);
		for (int i = 1; i <= 31; i++) {
			jourAncComboBox.addItem(i);
        }
        
		JComboBox moisAncComboBox = new JComboBox();
		moisAncComboBox.setBounds(142, 217, 67, 22);
		panel.add(moisAncComboBox);
		for (int i = currentYear - 2; i <= currentYear + 10; i++) {
			moisAncComboBox.addItem(i);
        }

		
		JComboBox annneeAncComboBox = new JComboBox();
		annneeAncComboBox.setBounds(93, 217, 50, 22);;
		panel.add(annneeAncComboBox);
		for (int i = 1; i <= 12; i++) {
			annneeAncComboBox.addItem(i);
        }
		
		JComboBox 	HeurAncComboBox = new JComboBox();
		HeurAncComboBox.setBounds(47, 250, 50, 22);
		panel.add(HeurAncComboBox);
		for (int i = 9; i <= 15; i++) {
			HeurAncComboBox.addItem(i);
        }
		
		JComboBox MinAncComboBox = new JComboBox();
		MinAncComboBox.setBounds(127, 250, 50, 22);
		panel.add(MinAncComboBox);
		for (int i = 0; i <= 59; i=i+15) {
			MinAncComboBox.addItem(i);
        }
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(47, 93, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom");
		lblNewLabel_1.setBounds(47, 132, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setBounds(10, 217, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Heure");
		lblNewLabel_3.setBounds(10, 250, 46, 14);
		panel.add(lblNewLabel_3);

        
		JComboBox jourComboBox = new JComboBox();
		jourComboBox.setBounds(242, 217, 50, 22);
		panel.add(jourComboBox);
		for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        
		JComboBox moisComboBox = new JComboBox();
		moisComboBox.setBounds(340, 217, 67, 22);
		panel.add(moisComboBox);
		for (int i = currentYear - 2; i <= currentYear+10; i++) {
			moisComboBox.addItem(i);
        }

		

		JComboBox annneeComboBox = new JComboBox();
		annneeComboBox.setBounds(292, 217, 50, 22);
		panel.add(annneeComboBox);
		for (int i = 1; i <= 12; i++) {
			annneeComboBox.addItem(i);
        }
		
		JComboBox 	HeurComboBox = new JComboBox();
		HeurComboBox.setBounds(242, 250, 50, 22);
		panel.add(HeurComboBox);
		for (int i = 9; i <= 15; i++) {
			HeurComboBox.addItem(i);
        }
		
		JComboBox MinComboBox = new JComboBox();
		MinComboBox.setBounds(322, 250, 50, 22);
		panel.add(MinComboBox);
		for (int i = 0; i <= 59; i=i+15) {
			MinComboBox.addItem(i);
        }
		
		JLabel lblNewLabel_4 = new JLabel("Ancien RDV");
		lblNewLabel_4.setForeground(new Color(255, 153, 153));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(70, 176, 139, 28);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Nouveau RDV");
		lblNewLabel_4_1.setForeground(new Color(255, 153, 153));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4_1.setBounds(259, 176, 139, 28);
		panel.add(lblNewLabel_4_1);
		

		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBounds(147, 307, 139, 40);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = "RDVS.txt";
				String tempFileName = "temp.txt"; // fichier temporaire pour stocker les modifications
				String nom = nomtextField.getText();
	            String prenom = prénomtextField.getText();
	            int jour = (int) jourComboBox.getSelectedItem();
	            int mois = (int) moisComboBox.getSelectedItem();
	            int annee= (int) annneeComboBox.getSelectedItem();
	            int heur = (int) HeurComboBox.getSelectedItem();
	            int min= (int) MinComboBox.getSelectedItem();
	            int jourAnc = (int) jourAncComboBox.getSelectedItem();
	            int moisAnc = (int) moisAncComboBox.getSelectedItem();
	            int anneeAnc= (int) annneeAncComboBox.getSelectedItem();
	            int heurAnc = (int) HeurAncComboBox.getSelectedItem();
	            int minAnc= (int) MinAncComboBox.getSelectedItem();

				try {
				    BufferedReader reader = new BufferedReader(new FileReader(fileName));
				    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

				    String currentLine;
				    while ((currentLine = reader.readLine()) != null) {


				        if (currentLine.contains(nom) && currentLine.contains(prenom)) {
				            currentLine = currentLine.replace("Date RDV : "+ jourAnc +"/" + anneeAnc  +"/"+ moisAnc
		                            + " , " + "Heur : "+ heurAnc + "h" + minAnc , "Date RDV : "+ jour +"/" + annee  +"/"+ mois
		                            + " , " + "Heur : "+ heur + "h" + min );
				        }


				        writer.write(currentLine + System.lineSeparator());
				    }

				    reader.close();
				    writer.close();

				   
				    File oldFile = new File(fileName);
				    oldFile.delete();

				    File newFile = new File(tempFileName);
				    newFile.renameTo(oldFile);

				    JOptionPane.showMessageDialog(btnNewButton, "Le fichier a été modifié avec succès!");
				    
	                nomtextField.setText("");
	                prénomtextField.setText("");
				} catch (IOException er) {
				    er.printStackTrace();
				}
				
			}
		});
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
