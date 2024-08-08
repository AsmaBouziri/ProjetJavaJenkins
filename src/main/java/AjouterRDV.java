package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AjouterRDV extends JFrame {

    private JPanel contentPane;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    public JTextField nomtextField;
    public JTextField prenomtextField;
    public JComboBox<Integer> jourComboBox;
    public JComboBox<Integer> moisComboBox;
    public JComboBox<Integer> anneeComboBox;
    public JComboBox<Integer> heurComboBox;
    public JComboBox<Integer> minComboBox;
    public JButton enregistrerButton;
    public JButton annulerButton;
    public JButton homeButton ;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AjouterRDV frame = new AjouterRDV();
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }

    public AjouterRDV() {
        // Connexion à MongoDB
        try {
            this.database = MongoDBUtil.getDatabase("CabinetDent");
            this.collection = database.getCollection("RendezVous");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Ne pas continuer si la connexion échoue
        }

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("Ajouter Rendez-Vous");

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(135, 11, 431, 358);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel titreLabel = new JLabel("Ajouter RDV");
        titreLabel.setForeground(Color.BLACK);
        titreLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        titreLabel.setBounds(132, 11, 319, 28);
        panel.add(titreLabel);

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

        JLabel lblNom = new JLabel("Nom :");
        lblNom.setBounds(47, 93, 46, 14);
        panel.add(lblNom);

        JLabel lblPrenom = new JLabel("Prénom :");
        lblPrenom.setBounds(47, 132, 46, 14);
        panel.add(lblPrenom);

        JLabel lblDate = new JLabel("Date :");
        lblDate.setBounds(47, 168, 46, 14);
        panel.add(lblDate);

        JLabel lblHeure = new JLabel("Heure :");
        lblHeure.setBounds(47, 201, 46, 14);
        panel.add(lblHeure);

        enregistrerButton = new JButton("Enregistrer");
        enregistrerButton.setBounds(147, 307, 139, 40);
        enregistrerButton.setBackground(SystemColor.activeCaption);
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

                if (nom.isEmpty() || prenom.isEmpty()) {
                    JOptionPane.showMessageDialog(enregistrerButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    String date = jour + "/" + mois + "/" + annee;
                    String heureComplete = heure + ":" + minute;

                    Document rendezVous = new Document("nom", nom)
                            .append("prenom", prenom)
                            .append("date", date)
                            .append("heure", heureComplete);

                    collection.insertOne(rendezVous);
                    JOptionPane.showMessageDialog(enregistrerButton, "Le rendez-vous a été enregistré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

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

        annulerButton = new JButton("Annuler");
        annulerButton.setBounds(312, 307, 139, 40);
        annulerButton.setBackground(SystemColor.activeCaption);
        panel.add(annulerButton);
        annulerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nomtextField.setText("");
                prenomtextField.setText("");
                jourComboBox.setSelectedIndex(0);
                moisComboBox.setSelectedIndex(0);
                anneeComboBox.setSelectedIndex(0);
                heurComboBox.setSelectedIndex(0);
                minComboBox.setSelectedIndex(0);
            }
        });


        JPanel homePanel = new FrameConf().createHomePanel(this);
        contentPane.add(homePanel);

    }
}
