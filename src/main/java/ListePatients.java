package main.java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import com.mongodb.client.*;
import java.awt.*;

public class ListePatients extends JFrame {
    public JTable table;
    private DefaultTableModel model;
    private MongoDatabase database;
    public JButton homeButton;

    public static void main(String[] args) {
        ListePatients frame = new ListePatients();
        frame.setSize(800, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Plein écran
        frame.setLocationRelativeTo(null); // Centre la fenêtre à l'écran
        frame.setVisible(true);
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


        JPanel homePanel = new FrameConf().createHomePanel(this);
        contentPane.add(homePanel, BorderLayout.NORTH); 

    }
}
