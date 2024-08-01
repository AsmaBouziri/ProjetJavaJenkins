package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.Acceuil;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SeConnecter extends JFrame {
    private JPanel contentPane;
    public JTextField txtEmail;
    public JTextField textField;
    public  JButton btnSeConnecter;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public static void main(String[] args) {
       
            try {
                SeConnecter frame = new SeConnecter();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    public SeConnecter() {
        initializeDatabase();
        initializeUI();
    }

    private void initializeDatabase() {
        try {
            this.database = MongoDBUtil.getDatabase("CabinetDent");
            this.collection = database.getCollection("LogIn");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeUI() {
        setTitle("Se Connecter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 753, 419);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(89, 5, 597, 370);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(153, 153, 153));
        panel_1.setBounds(0, 0, 282, 370);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(SeConnecter.class.getResource("/main/resources/loginimg.png")));
        lblNewLabel_3.setBounds(40, 26, 272, 277);
        panel_1.add(lblNewLabel_3);

        JLabel lblNewLabel_2 = new JLabel("Se Connecter");
        lblNewLabel_2.setForeground(SystemColor.activeCaption);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblNewLabel_2.setBounds(331, 22, 256, 55);
        panel.add(lblNewLabel_2);

        txtEmail = new JTextField();
        txtEmail.setBounds(376, 113, 183, 29);
        panel.add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblUtilisateur = new JLabel("Utilisateur");
        lblUtilisateur.setBounds(376, 88, 183, 14);
        panel.add(lblUtilisateur);

        JLabel lblMotDePasse = new JLabel("Mot De Passe");
        lblMotDePasse.setBounds(376, 155, 86, 14);
        panel.add(lblMotDePasse);

        textField = new JPasswordField();
        textField.setBounds(376, 180, 183, 29);
        panel.add(textField);
        textField.setColumns(10);

        btnSeConnecter = new JButton("Se Connecter");
        btnSeConnecter.setBackground(SystemColor.activeCaption);
        btnSeConnecter.setBounds(412, 232, 119, 38);
        btnSeConnecter.addActionListener(this::handleConnectAction);
        panel.add(btnSeConnecter);
    }

    private void handleConnectAction(ActionEvent e) {
        String email = txtEmail.getText();
        String mdp = textField.getText();

        Document filter = new Document("user", email).append("pwd", mdp);
        Document found = collection.find(filter).first();

        if (found != null) {
            JOptionPane.showMessageDialog(btnSeConnecter, "Connexion réussie!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the current login window
            Acceuil acc = new Acceuil();
            acc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(btnSeConnecter, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
