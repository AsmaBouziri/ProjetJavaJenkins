package main.java.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
importmain.java. Project.RechercherPatient;

public class ListePatients extends JFrame implements ActionListener {
    
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JButton afficherButton;
    
    public ListePatients() {
        super("Liste des patients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        
        listModel = new DefaultListModel<String>();
        File folder = new File("fiches_patients/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                listModel.addElement(file.getName());
            }
        }
        
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(list);
        mainPanel.add(listScrollPane, BorderLayout.CENTER);
        
        afficherButton = new JButton("Afficher le fichier");
        afficherButton.addActionListener(this);
        mainPanel.add(afficherButton, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == afficherButton) {
            String selectedFile = list.getSelectedValue();
            if (selectedFile != null) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("fiches_patients/" + selectedFile));
                    String line;
                    String content = "";
                    while ((line = reader.readLine()) != null) {
                        content += line + "\n";
                    }
                    JOptionPane.showMessageDialog(this, content, "Contenu du fichier " + selectedFile, JOptionPane.PLAIN_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erreur lors de la lecture du fichier " + selectedFile, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucun fichier sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        new ListePatients();
    }
}
