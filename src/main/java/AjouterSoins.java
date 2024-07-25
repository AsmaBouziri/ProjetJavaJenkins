package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class AjouterSoins extends JFrame {
    private JPanel contentPane;
    private MongoDatabase database;
    public JTextField nomText;
    public JTextField prenomText;
    public JComboBox<String> comboBox;
    public JComboBox<Integer> jourComboBox;
    public JComboBox<Integer> moisComboBox;
    public JComboBox<Integer> anneeComboBox;
    public JButton enregistrerButton;

    public static void main(String[] args) {
        AjouterSoins frame = new AjouterSoins();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public AjouterSoins() {
        try {
            this.database = MongoDBUtil.getDatabase("CabinetDent");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        MongoCollection<Document> collection = database.getCollection("Patient");

        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("Ajouter Soins");
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        var panel = new JPanel();
        panel.setBounds(101, 11, 534, 358);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel Titre = new JLabel("Ajouter Soins");
        Titre.setForeground(SystemColor.windowBorder);
        Titre.setBackground(SystemColor.windowBorder);
        Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
        Titre.setBounds(165, 11, 235, 28);
        panel.add(Titre);

        nomText = new JTextField();
        nomText.setColumns(10);
        nomText.setBounds(238, 50, 217, 28);
        panel.add(nomText);

        prenomText = new JTextField();
        prenomText.setBounds(238, 82, 217, 28);
        panel.add(prenomText);
        prenomText.setColumns(10);

        comboBox = new JComboBox<String>();
        comboBox.setBounds(238, 113, 217, 28);
        panel.add(comboBox);
        // Populate the comboBox with soins types (e.g., "détartrage", "blanchiment", etc.)
        comboBox.addItem("détartrage");
        comboBox.addItem("blanchiment");
        comboBox.addItem("consultation");

        jourComboBox = new JComboBox<Integer>();
        jourComboBox.setBounds(237, 160, 50, 22);
        panel.add(jourComboBox);
        for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }

        moisComboBox = new JComboBox<Integer>();
        moisComboBox.setBounds(286, 160, 50, 22);
        panel.add(moisComboBox);
        for (int i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }

        anneeComboBox = new JComboBox<Integer>();
        anneeComboBox.setBounds(336, 160, 100, 22);
        panel.add(anneeComboBox);
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = currentYear - 100; i <= currentYear; i++) {
            anneeComboBox.addItem(i);
        }

        var lblNewLabel8 = new JLabel("Date du Soin :");
        lblNewLabel8.setBounds(53, 157, 168, 14);
        panel.add(lblNewLabel8);

        enregistrerButton = new JButton("Enregistrer");
        enregistrerButton.setBackground(SystemColor.activeCaption);
        enregistrerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomText.getText();
                String prenom = prenomText.getText();
                String soin = (String) comboBox.getSelectedItem();
                int jour = (int) jourComboBox.getSelectedItem();
                int mois = (int) moisComboBox.getSelectedItem();
                int annee = (int) anneeComboBox.getSelectedItem();
                String dateSoin = jour + "/" + mois + "/" + annee;

                // Vérification de la validité des champs de saisie
                if (nom.isEmpty() || prenom.isEmpty() || soin == null) {
                    JOptionPane.showMessageDialog(enregistrerButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    Document document = new Document("nom", nom)
                            .append("prenom", prenom)
                            .append("soin", soin)
                            .append("date", dateSoin);

                    collection.updateOne(
                        new Document("nom", nom).append("prenom", prenom),
                        new Document("$push", new Document("soins", document))
                    );

                    // Effacement des champs de saisie
                    nomText.setText("");
                    prenomText.setText("");
                    comboBox.setSelectedIndex(0);
                    jourComboBox.setSelectedIndex(0);
                    moisComboBox.setSelectedIndex(0);
                    anneeComboBox.setSelectedIndex(0);
                }
            }
        });
        enregistrerButton.setBounds(95, 380, 130, 37);
        contentPane.add(enregistrerButton);
    }
}
