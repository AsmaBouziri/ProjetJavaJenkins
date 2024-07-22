package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import main.java.MongoDBUtil;


public class AjouterPatient extends JFrame  {
	private JPanel contentPane;
	private MongoDatabase database;
    
	public static void main(String[] args) {
		AjouterPatient frame = new AjouterPatient();
		frame.setSize(800, 600);
		frame.setVisible(true);
		
	}

	public AjouterPatient() {
		try {
		    this.database = MongoDBUtil.getDatabase("CabinetDent");
		} catch (Exception e) {
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
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
		var panel = new JPanel();
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
		
 		
		final var hommeRB = new JRadioButton("Homme");
		hommeRB.setBounds(227, 184, 109, 28);
		panel.add(hommeRB);
		var femmeRadioButton = new JRadioButton("Femme");
		femmeRadioButton.setBounds(370, 184, 115, 28);
		panel.add(femmeRadioButton);
		ButtonGroup buttonGroupSexe = new ButtonGroup();
        buttonGroupSexe.add(hommeRB);
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
		
		
		var lblNewLabel = new JLabel("Prénom :");
		lblNewLabel.setBounds(53, 80, 164, 14);
		panel.add(lblNewLabel);
		
		var lblNewLabel1 = new JLabel("CIN :");
		lblNewLabel1.setBounds(53, 111, 164, 14);
		panel.add(lblNewLabel1);
		
		var lblNewLabel2 = new JLabel("Numéro de téléphone :");
		lblNewLabel2.setBounds(53, 293, 180, 14);
		panel.add(lblNewLabel2);
		
		var lblNewLabel3 = new JLabel("Sexe :");
		lblNewLabel3.setBounds(53, 182, 168, 14);
		panel.add(lblNewLabel3);
		
		var lblNewLabel4 = new JLabel("Adresse :");
		lblNewLabel4.setBounds(53, 217, 175, 14);
		panel.add(lblNewLabel4);
		
		var lblNewLabel5 = new JLabel("Profession :");
		lblNewLabel5.setBounds(53, 256, 175, 14);
		panel.add(lblNewLabel5);
		
		var lblNewLabel6 = new JLabel("Nom :");
		lblNewLabel6.setBounds(53, 48, 164, 14);
		panel.add(lblNewLabel6);
		
		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        
		final var jourComboBox = new JComboBox();
		jourComboBox.setBounds(237, 160, 50, 22);
		panel.add(jourComboBox);
		for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        
		final var moisComboBox = new JComboBox();
		moisComboBox.setBounds(336, 160, 100, 22);
		panel.add(moisComboBox);
		for (int i = currentYear - 100; i <= currentYear; i++) {
			moisComboBox.addItem(i);
        }

		

		final var annneeComboBox = new JComboBox();
		annneeComboBox.setBounds(286, 160, 50, 22);
		panel.add(annneeComboBox);
		for (int i = 1; i <= 12; i++) {
			annneeComboBox.addItem(i);
        }
		
		var lblNewLabel8 = new JLabel("Date De Naissance :");
		lblNewLabel8.setBounds(53, 157, 168, 14);
		panel.add(lblNewLabel8);
		
    
		final var enregistrerBtn = new JButton("Enregistrer");
		enregistrerBtn.setBackground(SystemColor.activeCaption);
		enregistrerBtn.addActionListener(new ActionListener() {
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
	                JOptionPane.showMessageDialog(enregistrerBtn, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
	               
	            } else if (telephone.length() != 8 ) {
	                JOptionPane.showMessageDialog(enregistrerBtn, "Le numéro de téléphone doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
	            } else if (cin.length() != 8 ) {
	                JOptionPane.showMessageDialog(enregistrerBtn, "Le numéro CIN doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
	            } else {
	                // Enregistrement des données dans un fichier texte
	            	String dataNaiss = jourNaissance +"/" + moisNaissance  +"/"+ anneeNaissance;
	            	
	            	Document document = new Document("nom", nom)
	                        .append("prenom", prenom)
	                        .append("cin", cin)
	            			.append("sexe", sexe)
	            			.append("adresse", adresse)
	            			.append("telephone", telephone)
	            			.append("dataNaiss", dataNaiss)
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
		enregistrerBtn.setBounds(132, 324, 100, 23);
		panel.add(enregistrerBtn);
		
		
		final var annulerBtn = new JButton("Annuler");
		annulerBtn.setBackground(SystemColor.activeCaption);
		annulerBtn.addActionListener(new ActionListener() {
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
		annulerBtn.setBounds(299, 324, 100, 23);
		panel.add(annulerBtn);
		
		
		
		final var homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("./images/home.png")));
		homeButton.setBounds(679, 11, 48, 41);
		contentPane.add(homeButton);
		homeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Acceuil acc = new Acceuil();
        		setVisible(false);
        		acc.setVisible(true);
        	}
        });
		

	}
	
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField cinTextField;
	private JTextField adresseTextField;
	private JTextField professionTextField;
	private JTextField telTextField;
	

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getNomTextField() {
		return nomTextField;
	}

	public void setNomTextField(JTextField nomTextField) {
		this.nomTextField = nomTextField;
	}

	public JTextField getPrenomTextField() {
		return prenomTextField;
	}

	public void setPrenomTextField(JTextField prenomTextField) {
		this.prenomTextField = prenomTextField;
	}

	public JTextField getCinTextField() {
		return cinTextField;
	}

	public void setCinTextField(JTextField cinTextField) {
		this.cinTextField = cinTextField;
	}

	public JTextField getAdresseTextField() {
		return adresseTextField;
	}

	public void setAdresseTextField(JTextField adresseTextField) {
		this.adresseTextField = adresseTextField;
	}

	public JTextField getProfessionTextField() {
		return professionTextField;
	}

	public void setProfessionTextField(JTextField professionTextField) {
		this.professionTextField = professionTextField;
	}

	public JTextField getTelTextField() {
		return telTextField;
	}

	public void setTelTextField(JTextField telTextField) {
		this.telTextField = telTextField;
	}

	public JRadioButton getHommeRadioButton() {
		return hommeRadioButton;
	}

	public void setHommeRadioButton(JRadioButton hommeRadioButton) {
		this.hommeRadioButton = hommeRadioButton;
	}

	public JRadioButton getFemmeRadioButton() {
		return femmeRadioButton;
	}

	public void setFemmeRadioButton(JRadioButton femmeRadioButton) {
		this.femmeRadioButton = femmeRadioButton;
	}

	public JButton getEnregistrerButton() {
		return enregistrerButton;
	}

	public void setEnregistrerButton(JButton enregistrerButton) {
		this.enregistrerButton = enregistrerButton;
	}

	public JButton getAnnulerButton() {
		return annulerButton;
	}

	public void setAnnulerButton(JButton annulerButton) {
		this.annulerButton = annulerButton;
	}

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	private JRadioButton hommeRadioButton;
	private JRadioButton femmeRadioButton;
    private JButton annulerButton;
    private JButton enregistrerButton;
    private JList<String> list;
    private DefaultListModel<String> listModel;
}