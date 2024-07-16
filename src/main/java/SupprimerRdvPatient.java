package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class SupprimerRdvPatient extends JFrame {

    private JPanel contentPane;
    private JTextField nomtextField;
    private JTextField prenomtextField;
    private MongoDatabase database;

    public static void main(String[] args) {
        SupprimerRdvPatient frame = new SupprimerRdvPatient();
        frame.setVisible(true);
        frame.setSize(800, 600);

    }

    public SupprimerRdvPatient() {
        this.database = MongoDBUtil.getDatabase("CabinetDent");
        MongoCollection<Document> collection = database.getCollection("RendezVous");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 753, 419);
        setTitle("Annuler Rendez-Vous");
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

        JLabel Titre = new JLabel("Annuler RDV");
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
        for (int i = currentYear - 100; i <= currentYear; i++) {
            moisComboBox.addItem(i);
        }

        final JComboBox<Integer> anneeComboBox = new JComboBox<>();
        anneeComboBox.setBounds(240, 164, 50, 22);
        panel.add(anneeComboBox);
        for (int i = 1; i <= 12; i++) {
            anneeComboBox.addItem(i);
        }

        final JComboBox<Integer> HeurComboBox = new JComboBox<>();
        HeurComboBox.setBounds(191, 197, 50, 22);
        panel.add(HeurComboBox);
        for (int i = 9; i <= 15; i++) {
            HeurComboBox.addItem(i);
        }

        final JComboBox<Integer> MinComboBox = new JComboBox<>();
        MinComboBox.setBounds(271, 197, 50, 22);
        panel.add(MinComboBox);
        for (int i = 0; i <= 59; i = i + 15) {
            MinComboBox.addItem(i);
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

        final JButton EnregistrerButton = new JButton("Annuler");
        EnregistrerButton.setBounds(147, 307, 139, 40);
        panel.add(EnregistrerButton);
        EnregistrerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomtextField.getText().trim();
                String prenom = prenomtextField.getText().trim();
                int jour = (int) jourComboBox.getSelectedItem();
                int mois = (int) moisComboBox.getSelectedItem();
                int annee = (int) anneeComboBox.getSelectedItem();
                int heure = (int) HeurComboBox.getSelectedItem();
                int minute = (int) MinComboBox.getSelectedItem();

                if (nom.isEmpty() || prenom.isEmpty()) {
                    JOptionPane.showMessageDialog(EnregistrerButton, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Construction du filtre pour la suppression
                    Document filter = new Document()
                            .append("nom", nom)
                            .append("prenom", prenom)
                            .append("date", jour + "/" + mois + "/" + annee)
                            .append("heure", heure + ":" + minute);

                    // Suppression du document correspondant dans la collection MongoDB
                    DeleteResult deleteResult = collection.deleteOne(filter);

                    if (deleteResult.getDeletedCount() > 0) {
                        JOptionPane.showMessageDialog(EnregistrerButton, "Le rendez-vous a été annulé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(EnregistrerButton, "Aucun rendez-vous correspondant trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

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