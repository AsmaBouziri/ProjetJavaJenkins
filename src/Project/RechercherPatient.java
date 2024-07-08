package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import Project.ListePatients;

public class RechercherPatient extends JFrame implements ActionListener {

    private JLabel labelNom, labelPrenom, labelResultat;
    private JTextField champNom, champPrenom;
    private JButton boutonRechercher, boutonModifier;
    private JTextArea zoneResultat;
    private DefaultListModel<String> listModel;
    private JButton afficherButton;

    public RechercherPatient() {
        super("Rechercher un patient");

        // Création des éléments de l'interface
        labelNom = new JLabel("Nom:");
        labelPrenom = new JLabel("Prénom:");
        labelResultat = new JLabel("Résultat:");
        champNom = new JTextField(20);
        champPrenom = new JTextField(20);
        boutonRechercher = new JButton("Rechercher");
        zoneResultat = new JTextArea(10, 20);
        

        // Ajout des éléments au conteneur principal
        Container conteneur = getContentPane();
        conteneur.setLayout(new BorderLayout());
        JPanel panelFormulaire = new JPanel(new GridLayout(3, 2));
        panelFormulaire.add(labelNom);
        panelFormulaire.add(champNom);
        panelFormulaire.add(labelPrenom);
        panelFormulaire.add(champPrenom);
        panelFormulaire.add(new JLabel(""));
        panelFormulaire.add(boutonRechercher);
        conteneur.add(panelFormulaire, BorderLayout.NORTH);
        conteneur.add(new JScrollPane(zoneResultat), BorderLayout.CENTER);
        conteneur.add(labelResultat, BorderLayout.SOUTH);

        // Ajout des écouteurs d'événements
        boutonRechercher.addActionListener(this);

        // Configuration de la fenêtre
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        listModel = new DefaultListModel<String>();
        File folder = new File("fiches_patients/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                listModel.addElement(file.getName());
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonRechercher) {
            // Lecture du fichier et recherche du patient
            String nom = champNom.getText().trim();
            String prenom = champPrenom.getText().trim();
            String fichier = nom + "_" + prenom + ".txt";
            JPanel panelModifier= new JPanel(new GridLayout(3, 2));
            
            try {
                BufferedReader lecteur = new BufferedReader(new FileReader("fiches_patients/" +fichier));
                String ligne;
                StringBuilder resultat = new StringBuilder();
                while ((ligne = lecteur.readLine()) != null) {
                    resultat.append(ligne).append("\n");
                }
                zoneResultat.setText(resultat.toString());
                labelResultat.setText("Résultat pour " + nom + " " + prenom + ":");
                panelModifier.add(boutonModifier);
                lecteur.close();
            } catch (IOException ex) {
                zoneResultat.setText("");
                labelResultat.setText("Patient non trouvé");
            }
        }
    }

    public static void main(String[] args) {
        new RechercherPatient();
    }
}
