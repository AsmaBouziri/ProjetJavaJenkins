package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class ModifierFichePatient extends JFrame {
    private JPanel contentPane;
    public JTextField nomTextField ;
    public JTextField prenomTextField;
    public JTextField cinTextField;
    public JTextField adresseTextField;
    public JTextField professionTextField;
    public JTextField telTextField;
    public JRadioButton hommeRadioButton;
    public JRadioButton femmeRadioButton;
    public JButton rechercherButton;
    public JButton modifierButton;
    public JButton annulerButton;
    public JComboBox<Integer> jourComboBox;
    public JComboBox<Integer> moisComboBox;
    public JComboBox<Integer> anneeComboBox;
    public MongoDatabase database;
    public MongoCollection<Document> collection;

    public static void main(String[] args) {
        ModifierFichePatient frame = new ModifierFichePatient();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public ModifierFichePatient() {
    	try {
            this.database = MongoDBUtil.getDatabase("CabinetDent");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        MongoCollection<Document> collection = database.getCollection("Patient");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 753, 419);
        setTitle("Modifier Patient");
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(568, 358)); // Dimensions du panel
        var gbcPanel = new GridBagConstraints();
        gbcPanel.insets = new Insets(0, 0, 0, 0);
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        contentPane.add(panel, gbcPanel);
        panel.setLayout(null);

        var titre = new JLabel("Modifier Patient");
        titre.setForeground(SystemColor.windowBorder);
        titre.setBackground(SystemColor.windowBorder);
        titre.setFont(new Font("Tahoma", Font.BOLD, 25));
        titre.setBounds(165, 11, 235, 28);
        panel.add(titre);

        var lblNomPrenom = new JLabel("Nom et Prénom :");
        lblNomPrenom.setBounds(53, 50, 164, 14);
        panel.add(lblNomPrenom);

        nomTextField = new JTextField();
        nomTextField.setColumns(10);
        nomTextField.setBounds(238, 50, 217, 28);
        panel.add(nomTextField);

        prenomTextField = new JTextField();
        prenomTextField.setBounds(238, 82, 217, 28);
        panel.add(prenomTextField);
        prenomTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("CIN :");
        lblNewLabel.setBounds(53, 111, 164, 14);
        panel.add(lblNewLabel);

        cinTextField = new JTextField();
        cinTextField.setBounds(238, 113, 217, 28);
        panel.add(cinTextField);
        cinTextField.setColumns(10);

        var lblNewLabel3 = new JLabel("Sexe :");
        lblNewLabel3.setBounds(53, 182, 168, 14);
        panel.add(lblNewLabel3);

        hommeRadioButton = new JRadioButton("Homme");
        hommeRadioButton.setBounds(240, 184, 109, 28);
        panel.add(hommeRadioButton);

        femmeRadioButton = new JRadioButton("Femme");
        femmeRadioButton.setBounds(370, 184, 89, 28);
        panel.add(femmeRadioButton);

        var buttonGroupSexe = new ButtonGroup();
        buttonGroupSexe.add(hommeRadioButton);
        buttonGroupSexe.add(femmeRadioButton);

        var lblNewLabel4 = new JLabel("Adresse :");
        lblNewLabel4.setBounds(53, 217, 175, 14);
        panel.add(lblNewLabel4);

        adresseTextField = new JTextField();
        adresseTextField.setBounds(238, 219, 217, 28);
        panel.add(adresseTextField);
        adresseTextField.setColumns(10);

        var lblNewLabel5 = new JLabel("Profession :");
        lblNewLabel5.setBounds(53, 256, 175, 14);
        panel.add(lblNewLabel5);

        professionTextField = new JTextField();
        professionTextField.setBounds(238, 258, 217, 28);
        panel.add(professionTextField);
        professionTextField.setColumns(10);

        var lblNewLabel6 = new JLabel("Numéro de téléphone :");
        lblNewLabel6.setBounds(53, 293, 180, 14);
        panel.add(lblNewLabel6);

        telTextField = new JTextField();
        telTextField.setBounds(238, 297, 217, 25);
        panel.add(telTextField);
        telTextField.setColumns(10);

        var lblNewLabel8 = new JLabel("Date De Naissance :");
        lblNewLabel8.setBounds(53, 157, 168, 14);
        panel.add(lblNewLabel8);

        jourComboBox = new JComboBox<>();
        jourComboBox.setBounds(237, 160, 50, 22);
        panel.add(jourComboBox);
        for (var i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }

        moisComboBox = new JComboBox<>();
        moisComboBox.setBounds(290, 160, 50, 22);
        panel.add(moisComboBox);
        for (var i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }

        anneeComboBox = new JComboBox<>();
        anneeComboBox.setBounds(340, 160, 115, 22);
        panel.add(anneeComboBox);
        var calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = currentYear - 100; i <= currentYear; i++) {
            anneeComboBox.addItem(i);
        }

        rechercherButton = new JButton("Rechercher");
        rechercherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomTextField.getText();
                String prenom = prenomTextField.getText();

                // Recherche du patient dans la base de données
                Document query = new Document("nom", nom).append("prenom", prenom);
                Document patient = collection.find(query).first();

                if (patient != null) {
                    // Remplir les champs avec les données existantes
                    cinTextField.setText(patient.getString("cin"));
                    adresseTextField.setText(patient.getString("adresse"));
                    professionTextField.setText(patient.getString("profession"));
                    telTextField.setText(patient.getString("telephone"));

                    var sexe = patient.getString("sexe");
                    if (sexe.equals("Homme")) {
                        hommeRadioButton.setSelected(true);
                    } else {
                        femmeRadioButton.setSelected(true);
                    }

                    // Traitement de la date de naissance
                    var dateNaissance = patient.getString("dataNaiss");
                    if (dateNaissance != null && !dateNaissance.isEmpty()) {
                        String[] parts = dateNaissance.split("/");
                        if (parts.length == 3) {
                            jourComboBox.setSelectedItem(Integer.parseInt(parts[0]));
                            moisComboBox.setSelectedItem(Integer.parseInt(parts[1]));
                            anneeComboBox.setSelectedItem(Integer.parseInt(parts[2]));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rechercherButton, "Patient non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        rechercherButton.setBounds(465, 50, 100, 28);
        panel.add(rechercherButton);

        modifierButton = new JButton("Modifier");
        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

                // Vérification de la validité des champs de saisie
                if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || adresse.isEmpty() || profession.isEmpty() || telephone.isEmpty()) {
                    JOptionPane.showMessageDialog(modifierButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (telephone.length() != 8) {
                    JOptionPane.showMessageDialog(modifierButton, "Le numéro de téléphone doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (cin.length() != 8) {
                    JOptionPane.showMessageDialog(modifierButton, "Le numéro CIN doit comporter 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Mise à jour des données dans la base de données MongoDB
                    String dateNaissance = jourNaissance + "/" + moisNaissance + "/" + anneeNaissance;
                    Document query = new Document("nom", nom).append("prenom", prenom);
                    var update = new Document("$set", new Document("cin", cin)
                            .append("sexe", sexe)
                            .append("adresse", adresse)
                            .append("telephone", telephone)
                            .append("dataNaiss", dateNaissance)
                            .append("profession", profession));
                    collection.updateOne(query, update);
                    
                    // Affichage d'un message de succès
                    JOptionPane.showMessageDialog(modifierButton, "Les informations ont été modifiées avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        modifierButton.setBounds(465, 113, 100, 28);
        panel.add(modifierButton);

        annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Effacer tous les champs
                nomTextField.setText("");
                prenomTextField.setText("");
                cinTextField.setText("");
                hommeRadioButton.setSelected(true);
                adresseTextField.setText("");
                professionTextField.setText("");
                telTextField.setText("");
                jourComboBox.setSelectedIndex(0);
                moisComboBox.setSelectedIndex(0);
                anneeComboBox.setSelectedIndex(0);
            }
        });
        annulerButton.setBounds(465, 160, 100, 28);
        panel.add(annulerButton);

        var homeButton = new JButton("");
        homeButton.setIcon(new ImageIcon(ModifierFichePatient.class.getResource("./images/home.png")));
        var gbcHomeButton = new GridBagConstraints();
        gbcHomeButton.anchor = GridBagConstraints.NORTHEAST; // Ancrage en haut à droite
        gbcHomeButton.insets = new Insets(10, 10, 10, 10); // Marges autour du bouton
        gbcHomeButton.gridx = 1; // Colonne 1
        gbcHomeButton.gridy = 0; // Ligne 0
        contentPane.add(homeButton, gbcHomeButton);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Acceuil acc = new Acceuil();
                setVisible(false);
                acc.setVisible(true);
            }
        });

        pack(); // Ajuste la taille de la fenêtre en fonction du contenu
    }
}
