package Assistante;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class ModifierFichePatient extends JFrame  {
	private JPanel contentPane;
	private JTextField fieldNom;
	private JTextField fieldPrenom;

	public static void main(String[] args) {
		ModifierFichePatient frame = new ModifierFichePatient();
		frame.setVisible(true);
		frame.setSize(800, 600);
		
	}

	public ModifierFichePatient() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 419);
		setTitle("Modifier fiche patient");
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(133, 11, 431, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Modifier Patient");
		lblNewLabel_7.setForeground(SystemColor.windowBorder);
		lblNewLabel_7.setBackground(SystemColor.windowBorder);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_7.setBounds(112, 11, 235, 28);
		panel.add(lblNewLabel_7);
		
		fieldNom = new JTextField();
		fieldNom.setBounds(223, 50, 185, 35);
		panel.add(fieldNom);
		fieldNom.setColumns(10);
		
		fieldPrenom = new JTextField();
		fieldPrenom.setColumns(10);
		fieldPrenom.setBounds(223, 96, 185, 35);
		panel.add(fieldPrenom);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(52, 60, 147, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom :");
		lblNewLabel_1.setBounds(52, 106, 147, 14);
		panel.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(33, 146, 371, 160);
		panel.add(textArea);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(239, 317, 147, 30);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            String nom = fieldNom.getText();
	            String prenom = fieldPrenom.getText();
	            String nomFichier = nom + "_" + prenom + ".txt";
	            try {
	                BufferedWriter writer = new BufferedWriter(new FileWriter("fiches_patients/" +nomFichier));
	                textArea.write(writer);
	                writer.close();
	                
	                JOptionPane.showMessageDialog(btnNewButton, "La fiche a été modifiée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
				
	            fieldNom.setText("");
	            fieldPrenom.setText("");
	            textArea.setText("");
	            }});
		
		
		JButton ModifierButton = new JButton("Ouvrir");
		ModifierButton.setBackground(SystemColor.activeCaption);
		ModifierButton.setBounds(61, 317, 147, 30);
		panel.add(ModifierButton);
		ModifierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String nom = fieldNom.getText();
		            String prenom = fieldPrenom.getText();
		            String nomFichier = nom + "_" + prenom + ".txt";
		            try {
		                BufferedReader reader = new BufferedReader(new FileReader("fiches_patients/" +nomFichier));
		                textArea.read(reader, null);
		                reader.close();
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
				
			}});
		JButton HomeButton = new JButton("");
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
