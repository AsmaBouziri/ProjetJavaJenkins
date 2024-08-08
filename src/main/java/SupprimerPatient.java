package main.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;

public class SupprimerPatient extends JFrame {
    private JPanel contentPane;
    public JTextField nomtextField;
    public JTextField prenomtextField;
    public final JButton btnNewButton ;
    private MongoDatabase database;

    public static void main(String[] args) {
        SupprimerPatient frame = new SupprimerPatient();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public SupprimerPatient() {
        this.database = MongoDBUtil.getDatabase("CabinetDent");
        MongoCollection<Document> collection = database.getCollection("Patient");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 753, 419);
        setTitle("Supprimer un patient");
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(133, 11, 431, 358);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_7 = new JLabel("Supprimer Patient");
        lblNewLabel_7.setForeground(SystemColor.windowBorder);
        lblNewLabel_7.setBackground(SystemColor.windowBorder);
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel_7.setBounds(112, 11, 235, 28);
        panel.add(lblNewLabel_7);

        nomtextField = new JTextField();
        nomtextField.setBounds(191, 102, 185, 35);
        panel.add(nomtextField);
        nomtextField.setColumns(10);

        prenomtextField = new JTextField();
        prenomtextField.setColumns(10);
        prenomtextField.setBounds(191, 182, 185, 35);
        panel.add(prenomtextField);

        btnNewButton = new JButton("Supprimer");
        btnNewButton.setBackground(SystemColor.activeCaption);
        btnNewButton.setBounds(162, 272, 147, 41);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomtextField.getText().trim();
                String prenom = prenomtextField.getText().trim();

                // Construire la requête pour trouver et supprimer le patient
                Document query = new Document("nom", nom).append("prenom", prenom);
                DeleteResult result = collection.deleteOne(query);

                if (result.getDeletedCount() > 0) {
                    JOptionPane.showMessageDialog(null, "Le patient a été supprimé de la base de données.");
                    nomtextField.setText("");
                    prenomtextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Impossible de trouver ou supprimer le patient.");
                }
            }
        });
        panel.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Nom :");
        lblNewLabel.setBounds(49, 112, 70, 14);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Prénom :");
        lblNewLabel_1.setBounds(49, 192, 70, 14);
        panel.add(lblNewLabel_1);


        JPanel homePanel = new FrameConf().createHomePanel(this);
        contentPane.add(homePanel);

        
    }
}
