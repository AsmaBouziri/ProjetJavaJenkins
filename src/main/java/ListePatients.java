package main.java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import com.mongodb.client.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListePatients extends JFrame {
    public  JTable table;
    private DefaultTableModel model;
    private MongoDatabase database;


    public static void main(String[] args) {
        ListePatients frame = new ListePatients();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public ListePatients() {
        this.database = MongoDBUtil.getDatabase("CabinetDent");
        MongoCollection<Document> collection = database.getCollection("Patient");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Liste des Patients");

        var contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("CIN");
        model.addColumn("Adresse");
        model.addColumn("Profession");
        model.addColumn("Téléphone");

        FindIterable<Document> patients = collection.find();
        for (Document patient : patients) {
            var nom = patient.getString("nom");
            var prenom = patient.getString("prenom");
            var cin = patient.getString("cin");
            var adresse = patient.getString("adresse");
            var profession = patient.getString("profession");
            var tel = patient.getString("tel");

            model.addRow(new Object[]{nom, prenom, cin, adresse, profession, tel});
        }

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        var scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Création d'un JPanel pour contenir le bouton Home avec un FlowLayout aligné à droite
        var panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(SystemColor.activeCaption);
        contentPane.add(panelButtons, BorderLayout.NORTH);
       var homeButton = new JButton("");
        homeButton.setIcon(new ImageIcon(ListePatients.class.getResource("/images/home.png")));
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
