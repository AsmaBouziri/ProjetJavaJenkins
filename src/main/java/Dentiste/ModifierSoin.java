package main.java.Dentiste;

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
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.java.Assistante.Acceuil;
import main.java.Assistante.AjouterPatient;
;

public class ModifierSoin extends JFrame {

	private JPanel contentPane;
    private DefaultTableModel tableModel;
    private JTextField prenomtextField;
    private JTextField nomtextField;
	 
	public static void main(String[] args) {
		ModifierSoin frame = new ModifierSoin();
		frame.setVisible(true);

	}

	public ModifierSoin() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Modifier Soin");
		setBounds(100, 100, 753, 454);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(135, 11, 431, 385);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Titre = new JLabel("Modifier Soin");
		Titre.setForeground(SystemColor.windowBorder);
		Titre.setBackground(SystemColor.windowBorder);
		Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
		Titre.setBounds(140, 11, 185, 28);
		panel.add(Titre);

		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(68, 76, 124, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom : ");
		lblNewLabel_1.setBounds(68, 115, 124, 14);
		panel.add(lblNewLabel_1);
		
		nomtextField = new JTextField();
		nomtextField.setBounds(202, 69, 199, 28);
		panel.add(nomtextField);
		nomtextField.setColumns(10);
		
		prenomtextField = new JTextField();
		prenomtextField.setBounds(202, 108, 199, 28);
		panel.add(prenomtextField);
		prenomtextField.setColumns(10);
		

		
		JLabel lblNewLabel_4 = new JLabel("Ancien soin :");
		lblNewLabel_4.setForeground(new Color(255, 153, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(68, 147, 257, 14);
		panel.add(lblNewLabel_4);
		
	
		JLabel lblNewLabel_2 = new JLabel("Soins Réalisé :");
		lblNewLabel_2.setBounds(68, 172, 124, 14);
		panel.add(lblNewLabel_2);
		
		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
		JLabel lblNewLabel_3 = new JLabel("Date soin :");
		lblNewLabel_3.setBounds(68, 208, 133, 14);
		panel.add(lblNewLabel_3);
		
		final JComboBox jourComboBox = new JComboBox();
		jourComboBox.setBounds(202, 204, 50, 22);
		panel.add(jourComboBox);
		for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        
		final JComboBox moisComboBox = new JComboBox();
		moisComboBox.setBounds(301, 204, 100, 22);
		panel.add(moisComboBox);
		for (int i = currentYear - 2 ; i <= currentYear + 5; i++) {
			moisComboBox.addItem(i);
        }

		final JComboBox annneeComboBox = new JComboBox();
		annneeComboBox.setBounds(251, 204, 50, 22);
		panel.add(annneeComboBox);
		for (int i = 1; i <= 12; i++) {
			annneeComboBox.addItem(i);
        }
		
	
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("**Soins**");
        comboBox.addItem("détartrage");
        comboBox.addItem("plombage");
        comboBox.addItem("dévitalisation dentaire");
        comboBox.addItem("extraction dentaire");
        comboBox.addItem("scellement des sillons");
        comboBox.addItem("Visite ");
        comboBox.setBounds(202, 265, 199, 28);
		panel.add(comboBox);
		
		
		JLabel lblNewLabel_4_1 = new JLabel("Nouveau soin :");
		lblNewLabel_4_1.setForeground(new Color(255, 153, 255));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(68, 247, 234, 14);
		panel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Soins Réalisé :");
		lblNewLabel_2_1.setBounds(68, 272, 124, 14);
		panel.add(lblNewLabel_2_1);
		

		JLabel lblNewLabel_3_1 = new JLabel("Date soin :");
		lblNewLabel_3_1.setBounds(68, 308, 133, 14);
		panel.add(lblNewLabel_3_1);
		
		final JComboBox jourComboBox_1 = new JComboBox();
		jourComboBox_1.setBounds(202, 304, 50, 22);
		panel.add(jourComboBox_1);
		for (int i = 1; i <= 31; i++) {
			jourComboBox_1.addItem(i);
        }
		
		final JComboBox annneeComboBox_1 = new JComboBox();
		annneeComboBox_1.setBounds(251, 304, 50, 22);
		panel.add(annneeComboBox_1);
		for (int i = 1; i <= 12; i++) {
			annneeComboBox_1.addItem(i);
        }
		
		final JComboBox moisComboBox_1 = new JComboBox();
		moisComboBox_1.setBounds(301, 304, 100, 22);
		panel.add(moisComboBox_1);
		for (int i = currentYear - 2 ; i <= currentYear + 5; i++) {
			moisComboBox_1.addItem(i);
		}
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(202, 242, 199, 28);
		comboBox_1.addItem("**Soins**");
		comboBox_1.addItem("détartrage");
		comboBox_1.addItem("plombage");
		comboBox_1.addItem("dévitalisation dentaire");
		comboBox_1.addItem("extraction dentaire");
		comboBox_1.addItem("scellement des sillons");
		comboBox_1.addItem("Visite ");
		comboBox_1.setBounds(202, 165, 199, 28);
		panel.add(comboBox_1);
		
		
		JButton EnregistrerButton = new JButton("Modifier");
		EnregistrerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String fileName = "Soins/"+ nomtextField.getText()+"_"+prenomtextField.getText()+".txt";
				System.out.println(fileName);
				String tempFileName = "temp.txt"; // fichier temporaire pour stocker les modifications
				String Soin = (String) comboBox_1.getSelectedItem();
	            String SoinNV = (String) comboBox.getSelectedItem();
	            int jour = (int) jourComboBox.getSelectedItem();
	            int mois = (int) moisComboBox.getSelectedItem();
	            int annee= (int) annneeComboBox.getSelectedItem();
	            int jourNV = (int) jourComboBox_1.getSelectedItem();
	            int moisNV = (int) moisComboBox_1.getSelectedItem();
	            int anneeNV= (int) annneeComboBox.getSelectedItem();

	            System.out.println(jour +"/" + mois  +"/"+annee );
	            System.out.println(Soin );
				try {
				    BufferedReader reader = new BufferedReader(new FileReader(fileName));
				    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

				    String currentLine;
				    while ((currentLine = reader.readLine()) != null) {


				        if (currentLine.contains(jour +"/" + mois  +"/"+annee ) && currentLine.contains(Soin)) {
				        	currentLine = currentLine.replace("Date de Soin : "+jour +"/" + mois  +"/"+annee +" > Soin : "+ Soin , 
				        			"Date de Soin : "+jourNV +"/" + moisNV  +"/"+anneeNV +" > Soin : "+ SoinNV );
				        }


				        writer.write(currentLine + System.lineSeparator());
				    }

				    reader.close();
				    writer.close();

				   
				    File oldFile = new File(fileName);
				    oldFile.delete();

				    File newFile = new File(tempFileName);
				    newFile.renameTo(oldFile);

				    JOptionPane.showMessageDialog(EnregistrerButton, "Le fichier a été modifié avec succès!");
				    
	                nomtextField.setText("");
	                prenomtextField.setText("");
				} catch (IOException er) {
				    er.printStackTrace();
				}
				
			}
		});
		EnregistrerButton.setBounds(177, 337, 124, 37);
		panel.add(EnregistrerButton);
		
		
		JButton HomeButton = new JButton("");
		HomeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("/images/home.png")));
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
