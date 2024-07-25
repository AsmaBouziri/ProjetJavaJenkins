package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class ModifierRendezVous extends JFrame {

    private JPanel contentPane;
    public JTextField nomtextField;
    public JTextField prenomtextField;
    public JComboBox<Integer> jourComboBox;
    public JComboBox<Integer> moisComboBox;
    public JComboBox<Integer> anneeComboBox;
    public JTextField heuretextField;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public static void main(String[] args) {
        ModifierRendezVous frame = new ModifierRendezVous();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public ModifierRendezVous() {
        try {
            this.database = MongoDBUtil.getDatabase("CabinetDent");
            this.collection = database.getCollection("Rendez-Vous");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 753, 419);
        setTitle("Modifier Rendez-Vous");
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setPreferredSize(new Dimension(431, 358)); 
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel Titre = new JLabel("Modifier RDV");
        Titre.setForeground(SystemColor.windowBorder);
        Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
        Titre.setBounds(132, 11, 319, 28);
        panel.add(Titre);

        nomtextField = new JTextField();
        nomtextField.setBounds(191, 86, 179, 28);
        panel.add(nomtextField);
        nomtextField.setColumns(10);

        prenomtextField = new JTextField();
        prenomtextField.setBounds(191, 125, 179, 28);
        panel.add(prenomtextField);
        prenomtextField.setColumns(10);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        jourComboBox = new JComboBox<>();
        jourComboBox.setBounds(191, 164, 50, 22);
        panel.add(jourComboBox);
        for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }

        moisComboBox = new JComboBox<>();
        moisComboBox.setBounds(240, 164, 50, 22);
        panel.add(moisComboBox);
        for (int i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }

        anneeComboBox = new JComboBox<>();
        anneeComboBox.setBounds(290, 164, 80, 22);
        panel.add(anneeComboBox);
        for (int i = currentYear - 2; i <= currentYear + 5; i++) {
            anneeComboBox.addItem(i);
        }

        heuretextField = new JTextField();
        heuretextField.setBounds(191, 197, 179, 28);
        panel.add(heuretextField);
        heuretextField.setColumns(10);

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

        final var rechercherButton = new JButton("Rechercher");
        rechercherButton.setBounds(201, 239, 162, 28);
        panel.add(rechercherButton);
        rechercherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomtextField.getText().trim();
                String prenom = prenomtextField.getText().trim();

                Document queryDoc = new Document()
                        .append("nom", nom)
                        .append("prenom", prenom);

                Document rendezVous = collection.find(queryDoc)
                        .sort(new Document("date", -1))
                        .first();

                if (rendezVous != null) {
                    String date = rendezVous.getString("date");
                    String heure = rendezVous.getString("heure");

                    String[] dateParts = date.split("/");
                    int jour = Integer.parseInt(dateParts[0]);
                    int mois = Integer.parseInt(dateParts[1]);
                    int annee = Integer.parseInt(dateParts[2]);

                    jourComboBox.setSelectedItem(jour);
                    moisComboBox.setSelectedItem(mois);
                    anneeComboBox.setSelectedItem(annee);

                    heuretextField.setText(heure);
                } else {
                    JOptionPane.showMessageDialog(rechercherButton, "Aucun rendez-vous trouvé pour ce patient.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    jourComboBox.setSelectedIndex(0);
                    moisComboBox.setSelectedIndex(0);
                    anneeComboBox.setSelectedIndex(0);
                    heuretextField.setText("");
                }
            }
        });

        final var enregistrerButton = new JButton("Enregistrer");
        enregistrerButton.setBounds(147, 307, 139, 40);
        panel.add(enregistrerButton);
        enregistrerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomtextField.getText().trim();
                String prenom = prenomtextField.getText().trim();
                int jour = (int) jourComboBox.getSelectedItem();
                int mois = (int) moisComboBox.getSelectedItem();
                int annee = (int) anneeComboBox.getSelectedItem();
                String heure = heuretextField.getText().trim();

                if (nom.isEmpty() || prenom.isEmpty() || heure.isEmpty()) {
                    JOptionPane.showMessageDialog(enregistrerButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    Document updateDoc = new Document("$set", new Document()
                            .append("date", jour + "/" + mois + "/" + annee)
                            .append("heure", heure));

                    Document queryDoc = new Document()
                            .append("nom", nom)
                            .append("prenom", prenom);

                    UpdateResult result = collection.updateOne(queryDoc, updateDoc);

                    if (result.getModifiedCount() > 0) {
                        JOptionPane.showMessageDialog(enregistrerButton, "Le rendez-vous a été modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(enregistrerButton, "Aucun rendez-vous trouvé pour ce patient.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    nomtextField.setText("");
                    prenomtextField.setText("");
                    jourComboBox.setSelectedIndex(0);
                    moisComboBox.setSelectedIndex(0);
                    anneeComboBox.setSelectedIndex(0);
                    heuretextField.setText("");
                }
            }
        });
    }
}
