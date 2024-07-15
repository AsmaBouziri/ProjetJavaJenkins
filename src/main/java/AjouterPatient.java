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
    private JPanel contentPane;
    private JTextField nomTextField, prenomTextField, cinTextField, adresseTextField, professionTextField, telTextField;
    private JRadioButton hommeRadioButton, femmeRadioButton;;
    private JButton enregistrerButton, annulerButton;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private MongoDatabase database;

    public static void main(String[] args) {
        AjouterPatient frame = new AjouterPatient();
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

    public AjouterPatient() {
        this.database = MongoDBUtil.getDatabase("CabinetDent");
        MongoCollection<Document> collection = database.getCollection("Patient");

        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 753, 419);
        setTitle("Ajouter Patient");
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout ici

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Marges entre les composants

        JLabel Titre = new JLabel("Ajouter Patient");
        Titre.setForeground(SystemColor.windowBorder);
        Titre.setBackground(SystemColor.windowBorder);
        Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(Titre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 1;
        panel.add(nomTextField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Prénom :"), gbc);
        gbc.gridx = 1;
        panel.add(prenomTextField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("CIN :"), gbc);
        gbc.gridx = 1;
        panel.add(cinTextField = new JTextField(20), gbc);

        // Radio buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Sexe :"), gbc);

        JPanel radioPanel = new JPanel();
        ButtonGroup buttonGroupSexe = new ButtonGroup();
        hommeRadioButton = new JRadioButton("Homme");
        femmeRadioButton = new JRadioButton("Femme");
        buttonGroupSexe.add(hommeRadioButton);
        buttonGroupSexe.add(femmeRadioButton);
        radioPanel.add(hommeRadioButton);
        radioPanel.add(femmeRadioButton);
        gbc.gridx = 1;
        panel.add(radioPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Adresse :"), gbc);
        gbc.gridx = 1;
        panel.add(adresseTextField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Profession :"), gbc);
        gbc.gridx = 1;
        panel.add(professionTextField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Numéro de téléphone :"), gbc);
        gbc.gridx = 1;
        panel.add(telTextField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Date de Naissance :"), gbc);

        JPanel datePanel = new JPanel();
        JComboBox<Integer> jourComboBox = new JComboBox<>();
        JComboBox<Integer> moisComboBox = new JComboBox<>();
        JComboBox<Integer> anneeComboBox = new JComboBox<>();
        datePanel.add(jourComboBox);
        datePanel.add(new JLabel("/"));
        datePanel.add(moisComboBox);
        datePanel.add(new JLabel("/"));
        datePanel.add(anneeComboBox);
        for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = currentYear - 100; i <= currentYear; i++) {
            anneeComboBox.addItem(i);
        }
        gbc.gridx = 1;
        panel.add(datePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JButton("Enregistrer"), gbc);

        gbc.gridy = 10;
        panel.add(new JButton("Annuler"), gbc);

        contentPane.add(panel, new GridBagConstraints());

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
