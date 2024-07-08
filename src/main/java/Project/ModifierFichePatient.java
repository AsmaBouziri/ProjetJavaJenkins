package main.java.Project;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ModifierFichePatient extends JFrame implements ActionListener {

    private JLabel labelNom;
    private JTextField fieldNom;
    private JLabel labelPrenom;
    private JTextField fieldPrenom;
    private JTextArea textArea;
    private JButton buttonOuvrir;
    private JButton buttonEnregistrer;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public ModifierFichePatient() {
        super("Modifier Fichier Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel pour les champs nom et prénom
        JPanel panelChamps = new JPanel(new GridLayout(2, 2));
        labelNom = new JLabel("Nom : ");
        fieldNom = new JTextField();
        labelPrenom = new JLabel("Prénom : ");
        fieldPrenom = new JTextField();
        panelChamps.add(labelNom);
        panelChamps.add(fieldNom);
        panelChamps.add(labelPrenom);
        panelChamps.add(fieldPrenom);
        add(panelChamps, BorderLayout.NORTH);

        // TextArea pour l'affichage du contenu du fichier
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel panelBoutons = new JPanel();
        buttonOuvrir = new JButton("Ouvrir");
        buttonOuvrir.addActionListener(this);
        panelBoutons.add(buttonOuvrir);
        buttonEnregistrer = new JButton("Enregistrer");
        buttonEnregistrer.addActionListener(this);
        panelBoutons.add(buttonEnregistrer);
        add(panelBoutons, BorderLayout.SOUTH);

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
        if (e.getSource() == buttonOuvrir) {
            // Ouvrir le fichier et afficher son contenu dans la TextArea
            String nom = fieldNom.getText();
            String prenom = fieldPrenom.getText();
            String nomFichier = nom + "_" + prenom + ".txt";
            try {
                BufferedReader reader = new BufferedReader(new FileReader("fiches_patients/"+nomFichier));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == buttonEnregistrer) {
            // Enregistrer le contenu de la TextArea dans le fichier
            String nom = fieldNom.getText();
            String prenom = fieldPrenom.getText();
            String nomFichier = nom + "_" + prenom + ".txt";
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("fiches_patients/"+nomFichier));
                textArea.write(writer);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ModifierFichePatient();
    }

}

