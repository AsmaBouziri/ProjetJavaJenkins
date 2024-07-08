package main.java.Dentiste;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
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


public class AjouterSoins extends JFrame {
	private JPanel contentPane;
    private JTextField NomText;
    private JTextField PrenomText;
	 
	public static void main(String[] args) {
		AjouterSoins frame = new AjouterSoins();
		frame.setVisible(true);

	}

	public AjouterSoins() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ajouter Un soin réalisé  ");
		setBounds(100, 100, 753, 419);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(135, 11, 431, 321);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Titre = new JLabel("Ajouter Soins");
		Titre.setForeground(SystemColor.windowBorder);
		Titre.setBackground(SystemColor.windowBorder);
		Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
		Titre.setBounds(140, 11, 185, 28);
		panel.add(Titre);

		
		NomText = new JTextField();
		NomText.setBounds(202, 69, 199, 28);
		panel.add(NomText);
		NomText.setColumns(10);
		
		PrenomText = new JTextField();
		PrenomText.setBounds(202, 108, 199, 28);
		panel.add(PrenomText);
		PrenomText.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(68, 76, 124, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom : ");
		lblNewLabel_1.setBounds(68, 115, 124, 14);
		panel.add(lblNewLabel_1);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("**Soins**");
        comboBox.addItem("détartrage");
        comboBox.addItem("plombage");
        comboBox.addItem("dévitalisation dentaire");
        comboBox.addItem("extraction dentaire");
        comboBox.addItem("scellement des sillons");
        comboBox.addItem("Visite ");
        comboBox.setBounds(202, 147, 199, 28);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Soins Réalisé :");
		lblNewLabel_2.setBounds(68, 154, 124, 14);
		panel.add(lblNewLabel_2);
		
		
		
		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
		final JComboBox jourComboBox = new JComboBox();
		jourComboBox.setBounds(202, 186, 50, 22);
		panel.add(jourComboBox);
		for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        
		final JComboBox moisComboBox = new JComboBox();
		moisComboBox.setBounds(301, 186, 100, 22);
		panel.add(moisComboBox);
		for (int i = currentYear - 2 ; i <= currentYear + 5; i++) {
			moisComboBox.addItem(i);
        }

		

		final JComboBox annneeComboBox = new JComboBox();
		annneeComboBox.setBounds(251, 186, 50, 22);
		panel.add(annneeComboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Date soin :");
		lblNewLabel_3.setBounds(68, 190, 133, 14);
		panel.add(lblNewLabel_3);
		for (int i = 1; i <= 12; i++) {
			annneeComboBox.addItem(i);
        }

 
        JButton EnregistrerButton = new JButton("Enregistrer");
		EnregistrerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String nom = NomText.getText();
		         String prenom = PrenomText.getText();
		         String Soin = (String)comboBox.getSelectedItem();
		         int jour = (int) jourComboBox.getSelectedItem();
		         int mois = (int) moisComboBox.getSelectedItem();
		         int annee = (int) annneeComboBox.getSelectedItem();

				String filename = nom + "_" + prenom + ".txt";
                try (FileWriter writer = new FileWriter("Soins/" +filename, true)) {
                    writer.write( "Date de Soin : "+ jour +"/" + mois  +"/"+ annee +  " > Soin : "+Soin + "\n");
                    JOptionPane.showMessageDialog(EnregistrerButton, "Soin ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(EnregistrerButton, "Impossible d'enregistrer!!! ", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
				
			 
			}
		});
		EnregistrerButton.setBounds(154, 251, 139, 40);
		panel.add(EnregistrerButton);
		
		JButton HomeButton = new JButton("");
		HomeButton.setIcon(new ImageIcon(Accueil.class.getResource("/images/home.png")));
		HomeButton.setBounds(679, 11, 48, 41);
		contentPane.add(HomeButton);
		
		JButton 	RetourBtn = new JButton("");
		RetourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListeRDV LRDV = new ListeRDV();
        		setVisible(false);
        		LRDV.setVisible(true);
			}
		});
		RetourBtn.setIcon(new ImageIcon(AjouterSoins.class.getResource("/images/retr.png")));
		RetourBtn.setBounds(679, 63, 48, 41);
		contentPane.add(RetourBtn);
		
		HomeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Accueil acc = new Accueil();
        		setVisible(false);
        		acc.setVisible(true);
        	}
        });
	    setVisible(true);
	}
	
	

}
