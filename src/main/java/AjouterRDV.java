package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class AjouterRDV extends JFrame {

    private JPanel contentPane;
    private JTextField nomtextField;
    private JTextField prenomtextField;
    private MongoDatabase database;

    public static void main(String[] args) {
        AjouterRDV frame = new AjouterRDV();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public AjouterRDV() {
    	this.database = MongoDBUtil.getDatabase("CabinetDent");
    	MongoCollection<Document> collection = database.getCollection("RendezVous");
    	contentPane = new JPanel();
    	new FrameConf().configure(this, contentPane, "Ajouter Rendez-Vous");

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

        final JComboBox<Integer> jourComboBox = new JComboBox<>();
        jourComboBox.setBounds(191, 164, 50, 22);
        panel.add(jourComboBox);
        for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }

        final JComboBox<Integer> moisComboBox = new JComboBox<>();
        moisComboBox.setBounds(290, 164, 100, 22);
        panel.add(moisComboBox);
        for (int i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }

        final JComboBox<Integer> anneeComboBox = new JComboBox<>();
        anneeComboBox.setBounds(240, 164, 50, 22);
        panel.add(anneeComboBox);
        for (int i = currentYear - 2; i <= currentYear + 5; i++) {
            anneeComboBox.addItem(i);
        }

        final JComboBox<Integer> heurComboBox = new JComboBox<>();
        heurComboBox.setBounds(191, 197, 50, 22);
        panel.add(heurComboBox);
        for (int i = 9; i <= 15; i++) {
            heurComboBox.addItem(i);
        }

        final JComboBox<Integer> minComboBox = new JComboBox<>();
        minComboBox.setBounds(271, 197, 50, 22);
        panel.add(minComboBox);
        for (int i = 0; i <= 59; i = i + 15) {
            minComboBox.addItem(i);
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

        final JButton EnregistrerButton = new JButton("Enregistrer");
        EnregistrerButton.setBounds(147, 307, 139, 40);
        panel.add(EnregistrerButton);
        EnregistrerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomtextField.getText().trim();
                String prenom = prenomtextField.getText().trim();
                int jour = (int) jourComboBox.getSelectedItem();
                int mois = (int) moisComboBox.getSelectedItem();
                int annee = (int) anneeComboBox.getSelectedItem();
                int heure = (int) heurComboBox.getSelectedItem();
                int minute = (int) minComboBox.getSelectedItem();

                if (nom.isEmpty() || prenom.isEmpty()) {
                    JOptionPane.showMessageDialog(EnregistrerButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Construction du document à insérer dans la base de données MongoDB
                    Document rendezVous = new Document()
                            .append("nom", nom)
                            .append("prenom", prenom)
                            .append("date", jour + "/" + mois + "/" + annee)
                            .append("heure", heure + ":" + minute);

                    // Insertion du document dans la collection MongoDB
                    collection.insertOne(rendezVous);

                    JOptionPane.showMessageDialog(EnregistrerButton, "Le rendez-vous a été enregistré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Effacement des champs de saisie
                    nomtextField.setText("");
                    prenomtextField.setText("");
                }
            }
        });

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
