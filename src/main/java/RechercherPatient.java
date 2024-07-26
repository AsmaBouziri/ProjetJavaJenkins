package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import org.bson.Document;
import com.mongodb.client.*;

public class RechercherPatient extends JFrame {

    private JPanel contentPane;
    public JTextField champNom;
    public JTextField champPrenom;
    public JTextArea textArea;
    public JLabel labelResultat;
    public JButton modifierButton;
    private MongoDatabase database;

    public static void main(String[] args) {
        RechercherPatient frame = new RechercherPatient();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public RechercherPatient() {
        this.database = MongoDBUtil.getDatabase("CabinetDent");
        MongoCollection<Document> collection = database.getCollection("Patient");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rechercher Patient");
        setBounds(100, 100, 753, 553);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setBounds(110, 11, 558, 492);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_7 = new JLabel("Rechercher Patient");
        lblNewLabel_7.setForeground(SystemColor.windowBorder);
        lblNewLabel_7.setBackground(SystemColor.windowBorder);
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel_7.setBounds(78, 11, 319, 28);
        panel.add(lblNewLabel_7);

        champNom = new JTextField();
        champNom.setBounds(223, 50, 268, 35);
        panel.add(champNom);
        champNom.setColumns(10);

        champPrenom = new JTextField();
        champPrenom.setColumns(10);
        champPrenom.setBounds(223, 96, 268, 35);
        panel.add(champPrenom);

        JLabel lblNewLabel = new JLabel("Nom :");
        lblNewLabel.setBounds(52, 60, 147, 14);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Prénom :");
        lblNewLabel_1.setBounds(52, 117, 147, 14);
        panel.add(lblNewLabel_1);

        textArea = new JTextArea();
        textArea.setBounds(10, 213, 538, 240);
        panel.add(textArea);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.windowBorder);
        panel_1.setBounds(0, 457, 558, 35);
        panel.add(panel_1);
        panel_1.setLayout(null);

        labelResultat = new JLabel("Resultat :");
        labelResultat.setBounds(10, 0, 411, 25);
        labelResultat.setForeground(SystemColor.text);
        labelResultat.setBackground(SystemColor.text);
        panel_1.add(labelResultat);

        modifierButton = new JButton("Rechercher");
        modifierButton.setBackground(SystemColor.activeCaption);
        modifierButton.setBounds(250, 142, 147, 41);
        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = champNom.getText().trim();
                String prenom = champPrenom.getText().trim();

                // Requête pour rechercher le patient dans la collection MongoDB
                Document query = new Document("nom", nom).append("prenom", prenom);
                Document patient = collection.find(query).first();

                if (patient != null) {
                    var adresse = patient.getString("adresse");
                    var profession = patient.getString("profession");
                    var tel = patient.getString("telephone");
                    var sexe = patient.getString("sexe");
                    var dateNaiss = patient.getString("dateNaiss");

                    // Affichage des informations dans le JTextArea
                    textArea.setText("Nom : " + nom + "\n"
                            + "Prénom : " + prenom + "\n"
                            + "Adresse : " + adresse + "\n"
                            + "Profession : " + profession + "\n"
                            + "Téléphone : " + tel +"\n"
                            + "Date de Naissance : " + dateNaiss +"\n"
                    		+"Sexe : " + sexe);
                    labelResultat.setText("Résultat pour : " + nom + " " + prenom);
                } else {
                    textArea.setText("");
                    labelResultat.setText("Patient non trouvé");
                }
            }
        });

        panel.add(modifierButton);

//        var homeButton = new JButton("");
//        homeButton.setIcon(new ImageIcon(RechercherPatient.class.getResource("./images/home.png")));
//        homeButton.setBounds(679, 11, 48, 41);
//        contentPane.add(homeButton);
//        homeButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Acceuil acc = new Acceuil();
//                setVisible(false);
//                acc.setVisible(true);
//            }
//        });
   }
}
