package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class AjouterPatient extends JFrame  {
	private JPanel contentPane;
	private JTextField nomTextField, prenomTextField, cinTextField, adresseTextField, professionTextField, telTextField;
	private JRadioButton hommeRadioButton, femmeRadioButton;;
    private JButton enregistrerButton, annulerButton;
    private JList<String> list;
    private DefaultListModel<String> listModel;
	private MongoDatabase database;
    
	public static void main(String[] args) {
		AjouterPatient frame = new AjouterPatient();
		frame.setSize(800, 600);
		frame.setVisible(true);
		
	}

	public AjouterPatient() {
		this.database = MongoDBUtil.getDatabase("CabinetDent");
		MongoCollection<Document> collection = database.getCollection("Patient");
		
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 419);
		setTitle("Ajouter Patient");
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(101, 11, 534, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		JLabel Titre = new JLabel("Ajouter Patient");
		Titre.setForeground(SystemColor.windowBorder);
		Titre.setBackground(SystemColor.windowBorder);
		Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
		Titre.setBounds(165, 11, 235, 28);
		panel.add(Titre);
		
		
		nomTextField = new JTextField();
		nomTextField.setColumns(10);
		nomTextField.setBounds(238, 50, 217, 28);
		panel.add(nomTextField);
		
		prenomTextField = new JTextField();
		prenomTextField.setBounds(238, 82, 217, 28);
		panel.add(prenomTextField);
		prenomTextField.setColumns(10);
		

		cinTextField = new JTextField();
		cinTextField.setBounds(238, 113, 217, 28);
		panel.add(cinTextField);
		cinTextField.setColumns(10);
		
 		
		final JRadioButton hommeRadioButton = new JRadioButton("Homme");
		hommeRadioButton.setBounds(227, 184, 109, 28);
		panel.add(hommeRadioButton);
		JRadioButton femmeRadioButton = new JRadioButton("Femme");
		femmeRadioButton.setBounds(370, 184, 115, 28);
		panel.add(femmeRadioButton);
		ButtonGroup buttonGroupSexe = new ButtonGroup();
        buttonGroupSexe.add(hommeRadioButton);
        buttonGroupSexe.add(femmeRadioButton);
		
		adresseTextField = new JTextField();
		adresseTextField.setBounds(238, 219, 217, 28);
		panel.add(adresseTextField);
		adresseTextField.setColumns(10);
		
		professionTextField = new JTextField();
		professionTextField.setBounds(238, 258, 217, 28);
		panel.add(professionTextField);
		professionTextField.setColumns(10);
		
		telTextField = new JTextField();
		telTextField.setBounds(238, 297, 217, 25);
		panel.add(telTextField);
		telTextField.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Prénom :");
		lblNewLabel.setBounds(53, 80, 164, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CIN :");
		lblNewLabel_1.setBounds(53, 111, 164, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Numéro de téléphone :");
		lblNewLabel_2.setBounds(53, 293, 180, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sexe :");
		lblNewLabel_3.setBounds(53, 182, 168, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Adresse :");
		lblNewLabel_4.setBounds(53, 217, 175, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Profession :");
		lblNewLabel_5.setBounds(53, 256, 175, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Nom :");
		lblNewLabel_6.setBounds(53, 48, 164, 14);
		panel.add(lblNewLabel_6);
		
		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
		final JComboBox jourComboBox = new JComboBox();
		jourComboBox.setBounds(237, 160, 50, 22);
		panel.add(jourComboBox);
		for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        
		final JComboBox moisComboBox = new JComboBox();
		moisComboBox.setBounds(336, 160, 100, 22);
		panel.add(moisComboBox);
		for (int i = currentYear - 100; i <= currentYear; i++) {
			moisComboBox.addItem(i);
        }

		

		final JComboBox annneeComboBox = new JComboBox();
		annneeComboBox.setBounds(286, 160, 50, 22);
		panel.add(annneeComboBox);
		for (int i = 1; i <= 12; i++) {
			annneeComboBox.addItem(i);
        }
		
		JLabel lblNewLabel_8 = new JLabel("Date De Naissance :");
		lblNewLabel_8.setBounds(53, 157, 168, 14);
		panel.add(lblNewLabel_8);
		
		 
        listModel = new DefaultListModel<String>();
        File folder = new File("fiches_patients/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                listModel.addElement(file.getName());
            }
        }
		
		final JButton EnregistrerBtn = new JButton("Enregistrer");
		EnregistrerBtn.setBackground(SystemColor.activeCaption);
		EnregistrerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            String nom = nomTextField.getText();
	            String prenom = prenomTextField.getText();
	            String cin = cinTextField.getText();
	            int jourNaissance = (int) jourComboBox.getSelectedItem();
	            int moisNaissance = (int) moisComboBox.getSelectedItem();
	            int anneeNaissance = (int) annneeComboBox.getSelectedItem();
	            String sexe = hommeRadioButton.isSelected() ? "Homme" : "Femme";
	            String adresse = adresseTextField.getText();
	            String profession = professionTextField.getText();
	            String telephone = telTextField.getText();

	            // Vérification de la validité des champs de saisie
	            if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || adresse.isEmpty() || profession.isEmpty() || telephone.isEmpty()) {
	                JOptionPane.showMessageDialog(EnregistrerBtn, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
	               
	            } else if (telephone.length() != 8 ) {
	                JOptionPane.showMessageDialog(EnregistrerBtn, "Le numéro de téléphone doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
	            } else if (cin.length() != 8 ) {
	                JOptionPane.showMessageDialog(EnregistrerBtn, "Le numéro CIN doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
	            } else {
	                // Enregistrement des données dans un fichier texte
	            	String DataNaiss = jourNaissance +"/" + moisNaissance  +"/"+ anneeNaissance;
	            	
	            	Document document = new Document("nom", nom)
	                        .append("prenom", prenom)
	                        .append("cin", cin)
	            			.append("sexe", sexe)
	            			.append("adresse", adresse)
	            			.append("telephone", telephone)
	            			.append("dataNaiss", DataNaiss)
	            			.append("profession", profession);

	            	collection.insertOne(document);
	                // Effacement des champs de saisie
	                nomTextField.setText("");
	                prenomTextField.setText("");
	                adresseTextField.setText("");
	                cinTextField.setText("");
	                professionTextField.setText("");
	                telTextField.setText("");
	            }
			}
		});
		EnregistrerBtn.setBounds(132, 324, 100, 23);
		panel.add(EnregistrerBtn);
		
		
		final JButton AnnulerBtn = new JButton("Annuler");
		AnnulerBtn.setBackground(SystemColor.activeCaption);
		AnnulerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomTextField.setText("");
	            prenomTextField.setText("");
	            jourComboBox.setSelectedIndex(0);
	            moisComboBox.setSelectedIndex(0);
	            annneeComboBox.setSelectedIndex(0);
	            hommeRadioButton.setSelected(true);
	            adresseTextField.setText("");
	            professionTextField.setText("");
	            telTextField.setText("");
			}
		});
		AnnulerBtn.setBounds(299, 324, 100, 23);
		panel.add(AnnulerBtn);
		
		
		
		final JButton HomeButton = new JButton("");
		HomeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("./images/home.png")));
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
