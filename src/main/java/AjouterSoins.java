package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class AjouterSoins extends JFrame {
    private JPanel contentPane;
    public JTextField nomText;
    public JTextField prenomText;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> patientsCollection;

    public static void main(String[] args) {
        var frame = new AjouterSoins();
        frame.setVisible(true);
    }

    public AjouterSoins() {
        // Connect to MongoDB
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("CabinetDent");
        patientsCollection = database.getCollection("Patient");

        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ajouter Un soin réalisé");
        setBounds(100, 100, 753, 419);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        var panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        var titre = new JLabel("Ajouter Soins");
        titre.setForeground(SystemColor.windowBorder);
        titre.setBackground(SystemColor.windowBorder);
        titre.setFont(new Font("Tahoma", Font.BOLD, 25));
        panel.add(titre, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        var lblNewLabel = new JLabel("Nom :");
        panel.add(lblNewLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nomText = new JTextField(20);
        panel.add(nomText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        var lblNewLabel1 = new JLabel("Prénom : ");
        panel.add(lblNewLabel1, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        prenomText = new JTextField(20);
        panel.add(prenomText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        var lblNewLabel2 = new JLabel("Soins Réalisé :");
        panel.add(lblNewLabel2, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("**Soins**");
        comboBox.addItem("détartrage");
        comboBox.addItem("plombage");
        comboBox.addItem("dévitalisation dentaire");
        comboBox.addItem("extraction dentaire");
        comboBox.addItem("scellement des sillons");
        comboBox.addItem("Visite ");
        panel.add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        var lblNewLabel3 = new JLabel("Date soin :");
        panel.add(lblNewLabel3, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        var datePanel = new JPanel();
        datePanel.add(new JLabel("Jour"));
        JComboBox<Integer> jourComboBox = new JComboBox<>();
        for (var i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        datePanel.add(jourComboBox);

        datePanel.add(new JLabel("Mois"));
        JComboBox<Integer> moisComboBox = new JComboBox<>();
        for (var i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }
        datePanel.add(moisComboBox);

        datePanel.add(new JLabel("Année"));
        JComboBox<Integer> anneeComboBox = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 2; i <= currentYear + 5; i++) {
            anneeComboBox.addItem(i);
        }
        datePanel.add(anneeComboBox);

        panel.add(datePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        var enregistrerButton = new JButton("Enregistrer");
        enregistrerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomText.getText();
                String prenom = prenomText.getText();
                String soin = (String) comboBox.getSelectedItem();
                int jour = (int) jourComboBox.getSelectedItem();
                int mois = (int) moisComboBox.getSelectedItem();
                int annee = (int) anneeComboBox.getSelectedItem();
                String dateSoin = jour + "/" + mois + "/" + annee;

                // Création d'un document de soin
                var soinDocument = new Document("soin", soin)
                        .append("date", dateSoin);

                // Recherche du patient par nom et prénom
                Document patient = patientsCollection.find(Filters.eq("nom", nom)).first();

                if (patient == null) {
                    List<Document> soinsList = new ArrayList<>();
                    soinsList.add(soinDocument);

                    Document newPatient = new Document("nom", nom)
                            .append("prenom", prenom)
                            .append("soins", soinsList);

                    patientsCollection.insertOne(newPatient);
                } else {
                    patientsCollection.updateOne(Filters.eq("nom", nom), Updates.push("soins", soinDocument));
                }

                JOptionPane.showMessageDialog(enregistrerButton, "Soin ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(enregistrerButton, gbc);

        contentPane.add(panel, new GridBagConstraints());

        final var homeButton = new JButton("");
        homeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("./images/home.png")));
        var gbchome = new GridBagConstraints();
        gbchome.gridx = 2;
        gbchome.gridy = 0;
        gbchome.anchor = GridBagConstraints.NORTHEAST;
        contentPane.add(homeButton, gbchome);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var acc = new Acceuil();
                setVisible(false);
                acc.setVisible(true);
            }
        });

        setVisible(true);
    }
}
