package main.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Acceuil extends JFrame {

    public Acceuil() {
        setTitle("Cabinet Dentaire : Assistant");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaption);
        getContentPane().add(panel);
        panel.setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();

        JLabel lblNewLabel_8 = new JLabel("Cabinet dentaire");
        lblNewLabel_8.setForeground(SystemColor.windowBorder);
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // span across 4 columns
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblNewLabel_8, gbc);

        JButton CButton = new JButton("");
        ImageIcon logoutIcon = new ImageIcon(Acceuil.class.getResource("./images/icons8-logout-25.png"));
        if (logoutIcon.getImageLoadStatus() != MediaTracker.ERRORED) {
            CButton.setIcon(logoutIcon);
        }
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(CButton, gbc);
        CButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SeConnecter connect = new SeConnecter();
                setVisible(false);
                connect.setVisible(true);
            }
        });

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel panel1 = createButtonPanel("Ajouter Patient", "./images/icons8-add-user-group-woman-man-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AjouterPatient ajouterFrame = new AjouterPatient();
                ajouterFrame.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(panel1, gbc);

        JPanel panel_1_1 = createButtonPanel("Rechercher Patient", "./images/icons8-find-user-male-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RechercherPatient rechFrame = new RechercherPatient();
                rechFrame.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(panel_1_1, gbc);

        JPanel panel12 = createButtonPanel("Modifier Patient", "./images/icons8-edit-profile-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifierFichePatient modifierFrame = new ModifierFichePatient();
                modifierFrame.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(panel12, gbc);

        JPanel panel13 = createButtonPanel("Ajouter RDV", "./images/icons8-add-receipt-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AjouterRDV ajouterRDVframe = new AjouterRDV();
                ajouterRDVframe.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(panel13, gbc);

        JPanel panel14 = createButtonPanel("Modifier RDV", "./images/icons8-edit-property-64.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifierRendezVous modifierRDVFrame = new ModifierRendezVous();
                modifierRDVFrame.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(panel14, gbc);

        JPanel panel15 = createButtonPanel("Liste Patients", "./images/icons8-todo-list-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListePatients listeFrame = new ListePatients();
                listeFrame.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(panel15, gbc);

        JPanel panel16 = createButtonPanel("Annuler RDV", "./images/icons8-delete-document-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SupprimerRdvPatient suppRDVFrame = new SupprimerRdvPatient();
                suppRDVFrame.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(panel16, gbc);

        JPanel panel17 = createButtonPanel("Supprimer Patient", "./images/icons8-unfriend-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SupprimerPatient suppFrame = new SupprimerPatient();
                suppFrame.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(panel17, gbc);

        setVisible(true);
    }

    public static JPanel createButtonPanel(String buttonText, String iconPath, ActionListener actionListener) {
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();

        // Initialize the icon label
        var iconLabel = new JLabel();
        java.net.URL imgURL = Acceuil.class.getResource(iconPath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            if (icon.getImageLoadStatus() != MediaTracker.ERRORED) {
                iconLabel.setIcon(icon);
            } else {
                System.err.println("Error loading icon: " + iconPath);
            }
        } else {
            System.err.println("Icon path not found: " + iconPath);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(iconLabel, gbc);

        // Initialize the button
        var button = new JButton(buttonText);
        button.setFont(new Font("Tahoma", Font.ITALIC, 18));
        button.addActionListener(actionListener);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button, gbc);

        return panel;
    }


    public static void main(String[] args) {
        Acceuil frame = new Acceuil();
    }
}
