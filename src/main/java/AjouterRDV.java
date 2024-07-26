package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class AjouterRDV extends JFrame {

    private JPanel contentPane;
    public JTextField nomtextField;
    public JTextField prenomtextField;
    private MongoDatabase database;
    private JComboBox<Integer> jourComboBox;
    private JComboBox<Integer> moisComboBox;
    private JComboBox<Integer> anneeComboBox;
    private JComboBox<Integer> heurComboBox;
    private JComboBox<Integer> minComboBox;

    public static void main(String[] args) {
        AjouterRDV frame = new AjouterRDV();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public AjouterRDV() {
        try {
            this.database = MongoDBUtil.getDatabase("CabinetDent");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        MongoCollection<Document> collection = database.getCollection("RendezVous");

        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("Ajouter Rendez-Vous");
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

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

        jourComboBox = new JComboBox<>();
        jourComboBox.setBounds(191, 164, 50, 22);
        panel.add(jourComboBox);
        for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }

        moisComboBox = new JComboBox<>();
        moisComboBox.setBounds(290, 164, 100, 22);
        panel.add(moisComboBox);
        for (int i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }

        anneeComboBox = new JComboBox<>();
        anneeComboBox.setBounds(240, 164, 50, 22);
        panel.add(anneeComboBox);
        for (int i = currentYear - 2; i <= currentYear + 5; i++) {
            anneeComboBox.addItem(i);
        }

        heurComboBox = new JComboBox<>();
        heurComboBox.setBounds(191, 197, 50, 22);
        panel.add(heurComboBox);
        for (int i = 9; i <= 15; i++) {
            heurComboBox.addItem(i);
        }

        minComboBox = new JComboBox<>();
        minComboBox.setBounds(271, 197, 50, 22);
        panel.add(minComboBox);
        for (int i = 0; i <= 59; i += 15) {
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

        JButton enregistrerButton = new JButton("Enregistrer");
        enregistrerButton.setBounds(147, 307, 139, 40);
        panel.add(enregistrerButton);
        enregistrerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomtextField.getText().trim();
                String prenom = prenomtextField.getText().trim();
                int jour = (int) jourComboBox.getSelectedItem();
                int mois = (int) moisComboBox.getSelectedItem();
                int annee = (int) anneeComboBox.getSelectedItem();
                int heure = (int) heurComboBox.getSelectedItem();
                int minute = (int) minComboBox.getSelectedItem();

                // Vérification de la validité des champs de saisie
                if (nom.isEmpty() || prenom.isEmpty()) {
                    JOptionPane.showMessageDialog(enregistrerButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Construction du document à insérer dans la base de données MongoDB
                    String date = jour + "/" + mois + "/" + annee;
                    String heureComplete = heure + ":" + minute;

                    Document rendezVous = new Document("nom", nom)
                            .append("prenom", prenom)
                            .append("date", date)
                            .append("heure", heureComplete);

                    // Insertion du document dans la collection MongoDB
                    collection.insertOne(rendezVous);
                    JOptionPane.showMessageDialog(enregistrerButton, "Le rendez-vous a été enregistré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Effacement des champs de saisie
                    nomtextField.setText("");
                    prenomtextField.setText("");
                    jourComboBox.setSelectedIndex(0);
                    moisComboBox.setSelectedIndex(0);
                    anneeComboBox.setSelectedIndex(0);
                    heurComboBox.setSelectedIndex(0);
                    minComboBox.setSelectedIndex(0);
                }
            }
        });

        JButton homeButton = new JButton("");
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
}
