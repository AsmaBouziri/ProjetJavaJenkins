package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class AjouterPatient extends JFrame {
	
	public void setDatabase(MongoDatabase database) {
		  this.database = database;
		}
    private JPanel contentPane;
    private JTextField nomTextField, prenomTextField, cinTextField, adresseTextField, professionTextField, telTextField;
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

	public JRadioButton hommeRadioButton;
	private JRadioButton femmeRadioButton;
    public JComboBox<Integer> jourComboBox;
	public JComboBox<Integer> moisComboBox;
	public JComboBox<Integer> anneeComboBox;
	public JButton enregistrerButton, annulerButton, homeButton;
    public MongoDatabase database;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AjouterPatient frame = new AjouterPatient();
            frame.setVisible(true);
        });
    }

    public AjouterPatient() {
        this.database = MongoDBUtil.getDatabase("CabinetDent");
        MongoCollection<Document> collection = database.getCollection("Patient");

        setTitle("Ajouter Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(50, 50, 700, 500);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel titreLabel = new JLabel("Ajouter Patient");
        titreLabel.setForeground(SystemColor.windowBorder);
        titreLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        titreLabel.setBounds(250, 10, 250, 30);
        panel.add(titreLabel);

        JLabel lblNom = new JLabel("Nom :");
        lblNom.setBounds(50, 50, 150, 20);
        panel.add(lblNom);

        nomTextField = new JTextField();
        nomTextField.setBounds(200, 50, 200, 25);
        panel.add(nomTextField);

        JLabel lblPrenom = new JLabel("Prénom :");
        lblPrenom.setBounds(50, 90, 150, 20);
        panel.add(lblPrenom);

        prenomTextField = new JTextField();
        prenomTextField.setBounds(200, 90, 200, 25);
        panel.add(prenomTextField);

        JLabel lblCin = new JLabel("CIN :");
        lblCin.setBounds(50, 130, 150, 20);
        panel.add(lblCin);

        cinTextField = new JTextField();
        cinTextField.setBounds(200, 130, 200, 25);
        panel.add(cinTextField);

        JLabel lblAdresse = new JLabel("Adresse :");
        lblAdresse.setBounds(50, 170, 150, 20);
        panel.add(lblAdresse);

        adresseTextField = new JTextField();
        adresseTextField.setBounds(200, 170, 200, 25);
        panel.add(adresseTextField);

        JLabel lblProfession = new JLabel("Profession :");
        lblProfession.setBounds(50, 210, 150, 20);
        panel.add(lblProfession);

        professionTextField = new JTextField();
        professionTextField.setBounds(200, 210, 200, 25);
        panel.add(professionTextField);

        JLabel lblTelephone = new JLabel("Numéro de téléphone :");
        lblTelephone.setBounds(50, 250, 200, 20);
        panel.add(lblTelephone);

        telTextField = new JTextField();
        telTextField.setBounds(200, 250, 200, 25);
        panel.add(telTextField);

        JLabel lblDateNaissance = new JLabel("Date De Naissance :");
        lblDateNaissance.setBounds(50, 290, 150, 20);
        panel.add(lblDateNaissance);

        jourComboBox = new JComboBox<>();
        jourComboBox.setBounds(200, 290, 50, 25);
        panel.add(jourComboBox);
        for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }

        moisComboBox = new JComboBox<>();
        moisComboBox.setBounds(260, 290, 50, 25);
        panel.add(moisComboBox);
        for (int i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }

        anneeComboBox = new JComboBox<>();
        anneeComboBox.setBounds(320, 290, 80, 25);
        panel.add(anneeComboBox);
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = currentYear - 100; i <= currentYear; i++) {
            anneeComboBox.addItem(i);
        }

        JLabel lblSexe = new JLabel("Sexe :");
        lblSexe.setBounds(50, 330, 150, 20);
        panel.add(lblSexe);

        hommeRadioButton = new JRadioButton("Homme");
        hommeRadioButton.setBounds(200, 330, 100, 25);
        panel.add(hommeRadioButton);

        femmeRadioButton = new JRadioButton("Femme");
        femmeRadioButton.setBounds(300, 330, 100, 25);
        panel.add(femmeRadioButton);

        ButtonGroup buttonGroupSexe = new ButtonGroup();
        buttonGroupSexe.add(hommeRadioButton);
        buttonGroupSexe.add(femmeRadioButton);

        setEnregistrerButton(new JButton("Enregistrer"));
        getEnregistrerButton().setBackground(SystemColor.activeCaption);
        getEnregistrerButton().setBounds(150, 370, 120, 30);
        getEnregistrerButton().addActionListener(e -> savePatient(collection));
        panel.add(getEnregistrerButton());

        annulerButton = new JButton("Annuler");
        annulerButton.setBackground(SystemColor.activeCaption);
        annulerButton.setBounds(300, 370, 120, 30);
        annulerButton.addActionListener(e -> clearFields());
        panel.add(annulerButton);

        homeButton = new JButton("");
        homeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("./images/home.png")));
        homeButton.setBounds(700, 10, 40, 40);
        homeButton.addActionListener(e -> goHome());
        contentPane.add(homeButton);
    }

    private void savePatient(MongoCollection<Document> collection) {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String cin = cinTextField.getText();
        int jourNaissance = (int) jourComboBox.getSelectedItem();
        int moisNaissance = (int) moisComboBox.getSelectedItem();
        int anneeNaissance = (int) anneeComboBox.getSelectedItem();
        String sexe = hommeRadioButton.isSelected() ? "Homme" : "Femme";
        String adresse = adresseTextField.getText();
        String profession = professionTextField.getText();
        String telephone = telTextField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || adresse.isEmpty() || profession.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (telephone.length() != 8) {
            JOptionPane.showMessageDialog(this, "Le numéro de téléphone doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (cin.length() != 8) {
            JOptionPane.showMessageDialog(this, "Le numéro CIN doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String dataNaiss = jourNaissance + "/" + moisNaissance + "/" + anneeNaissance;
            Document document = new Document("nom", nom)
                    .append("prenom", prenom)
                    .append("cin", cin)
                    .append("sexe", sexe)
                    .append("adresse", adresse)
                    .append("telephone", telephone)
                    .append("dataNaiss", dataNaiss)
                    .append("profession", profession);

            collection.insertOne(document);
            clearFields();
        }
    }

    public void clearFields() {
        nomTextField.setText("");
        prenomTextField.setText("");
        cinTextField.setText("");
        adresseTextField.setText("");
        professionTextField.setText("");
        telTextField.setText("");
        jourComboBox.setSelectedIndex(0);
        moisComboBox.setSelectedIndex(0);
        anneeComboBox.setSelectedIndex(0);
        hommeRadioButton.setSelected(true);
    }

    private void goHome() {
        // Implement navigation to the home screen
        // For example, creating a new instance of the home screen class
        Acceuil acc = new Acceuil();
        setVisible(false);
        acc.setVisible(true);
    }

	public JButton getEnregistrerButton() {
		return enregistrerButton;
	}

	public void setEnregistrerButton(JButton enregistrerButton) {
		this.enregistrerButton = enregistrerButton;
	}
}
