package main.java;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class SupprimerPatient extends JFrame {
	private JPanel contentPane;
	private JTextField nomtextField;
	private JTextField prenomtextField;

	public static void main(String[] args) {
		SupprimerPatient frame = new SupprimerPatient();
		frame.setVisible(true);
		frame.setSize(800, 600);
	}


	public SupprimerPatient() {
		setBackground(new Color(255, 255, 255));
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
		
		final JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(162, 272, 147, 41);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom = nomtextField.getText();
		        String prenom = prenomtextField.getText();
		        String fileName = nom + "_" + prenom + ".txt";
		        
		        boolean success = new File("fiches_patients/"+fileName).delete();
		        
		        if (success) {
		            JOptionPane.showMessageDialog(null, "La fiche du patient a été supprimée.");
		            nomtextField.setText("");
	                prenomtextField.setText("");
		        } else {
		            JOptionPane.showMessageDialog(null, "Impossible de supprimer la fiche du patient.");
		        }
		        
		        
				
			}});
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(49, 112, 70, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom :");
		lblNewLabel_1.setBounds(49, 192, 70, 14);
		panel.add(lblNewLabel_1);
		
		
		final JButton HomeButton = new JButton("");
		HomeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("/images/home.png")));
		HomeButton.setBounds(679, 11, 48, 41);
		contentPane.add(HomeButton);
		HomeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Acceuil acc = new Acceuil();
        		setVisible(false);
        		acc.setVisible(true);
        	}
        });
	}
}
