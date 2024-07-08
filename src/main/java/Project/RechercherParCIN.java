package main.java.Project;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RechercherParCIN extends JFrame implements ActionListener {

    private JTextField searchField;
    private JButton searchButton;

    public RechercherParCIN() {
        super("Recherche de patient par CIN");

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Numéro de CIN:"));
        searchField = new JTextField(15);
        searchPanel.add(searchField);

        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(this);
        searchPanel.add(searchButton);

        getContentPane().add(searchPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RechercherParCIN();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String cin = searchField.getText();

            File folder = new File("fiches_patients/");
            File[] files = folder.listFiles();

            boolean found = false;
            for (File file : files) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = reader.readLine();
                    while (line != null) {
                        if (line.startsWith("Numéro CIN :")) {
                            String patientCIN = line.substring(8).trim();
                            if (cin.equals(patientCIN)) {
                                found = true;
                                JOptionPane.showMessageDialog(null, "Le patient existe dans le fichier: " + file.getName());
                                break;
                            }
                        }
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Aucun patient avec le numéro de CIN " + cin + " trouvé.");
            }
        }
    }
}
