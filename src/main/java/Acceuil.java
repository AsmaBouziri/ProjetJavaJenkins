package main.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Acceuil extends JFrame {
	public  JButton CButton; 
	
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

         CButton = new JButton("");
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

        panel.add(createButtonPanel("Ajouter Patient", "./images/icons8-add-user-group-woman-man-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AjouterPatient ajouterFrame = new AjouterPatient();
                ajouterFrame.setVisible(true);
                dispose();
            }
        }), createGbc(0, 1, gbc));

        panel.add(createButtonPanel("Rechercher Patient", "./images/icons8-find-user-male-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RechercherPatient rechFrame = new RechercherPatient();
                rechFrame.setVisible(true);
                dispose();
            }
        }), createGbc(1, 1, gbc));

        panel.add(createButtonPanel("Modifier Patient", "./images/icons8-edit-profile-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifierFichePatient modifierFrame = new ModifierFichePatient();
                modifierFrame.setVisible(true);
                dispose();
            }
        }), createGbc(2, 1, gbc));

        panel.add(createButtonPanel("Ajouter RDV", "./images/icons8-add-receipt-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AjouterRDV ajouterRDVframe = new AjouterRDV();
                ajouterRDVframe.setVisible(true);
                dispose();
            }
        }), createGbc(3, 1, gbc));

        panel.add(createButtonPanel("Modifier RDV", "/images/icons8-edit-property-64.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifierRendezVous modifierRDVFrame = new ModifierRendezVous();
                modifierRDVFrame.setVisible(true);
                dispose();
            }
        }), createGbc(0, 2, gbc));

        panel.add(createButtonPanel("Liste Patients", "./images/icons8-todo-list-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListePatients listeFrame = new ListePatients();
                listeFrame.setVisible(true);
                dispose();
            }
        }), createGbc(1, 2, gbc));

        panel.add(createButtonPanel("Annuler RDV", "./images/icons8-delete-document-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SupprimerRdvPatient suppRDVFrame = new SupprimerRdvPatient();
                suppRDVFrame.setVisible(true);
                dispose();
            }
        }), createGbc(2, 2, gbc));

        panel.add(createButtonPanel("Supprimer Patient", "./images/icons8-unfriend-skin-type-7-48.png", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SupprimerPatient suppFrame = new SupprimerPatient();
                suppFrame.setVisible(true);
                dispose();
            }
        }), createGbc(3, 2, gbc));

        setVisible(true);
    }

    private GridBagConstraints createGbc(int x, int y, GridBagConstraints gbc) {
        GridBagConstraints newGbc = (GridBagConstraints) gbc.clone();
        newGbc.gridx = x;
        newGbc.gridy = y;
        return newGbc;
    }

    public static JPanel createButtonPanel(String buttonText, String iconPath, ActionListener actionListener) {
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();

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
        SwingUtilities.invokeLater(() -> {
            new Acceuil();
        });
    }
}
