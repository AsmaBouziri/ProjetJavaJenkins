package main.java.Dentiste;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ListePatients extends JFrame  {
	  private JList<String> list;
		private JPanel contentPane;
		private DefaultListModel<String> listModel;
		  
		public static void main(String[] args) {
			ListePatients frame = new ListePatients();
			frame.setVisible(true);
			frame.setSize(800, 600);
		}
		
		public ListePatients() {
			setBackground(new Color(255, 255, 255));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Liste des Patients ");
			setBounds(100, 100, 753, 419);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.activeCaption);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			

			JPanel panel = new JPanel();
			panel.setBackground(new Color(240, 240, 240));
			panel.setBounds(133, 11, 431, 358);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_7 = new JLabel("Liste Des Patients");
			lblNewLabel_7.setForeground(SystemColor.windowBorder);
			lblNewLabel_7.setBackground(SystemColor.windowBorder);
			lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel_7.setBounds(102, 11, 319, 28);
			panel.add(lblNewLabel_7);
		
			 
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
	        listScrollPane.setLocation(18, 70);
	        listScrollPane.setSize(397, 230);
	        panel.add(listScrollPane, BorderLayout.CENTER);
			
			JButton btnNewButton = new JButton("Afficher Détails");
			btnNewButton.setBounds(250, 307, 139, 40);
			panel.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedFile = (String) list.getSelectedValue();
		            if (selectedFile != null) {
		                try {
		                    BufferedReader reader = new BufferedReader(new FileReader("fiches_patients/" + selectedFile));
		                    String line;
		                    String content = "";
		                    while ((line = reader.readLine()) != null) {
		                        content += line + "\n";
		                    }
		                    JOptionPane.showMessageDialog(btnNewButton, content, "Contenu du fichier " + selectedFile, JOptionPane.PLAIN_MESSAGE);
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(btnNewButton, "Erreur lors de la lecture du fichier " + selectedFile, "Erreur", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(btnNewButton, "Aucun fichier sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
		            }
		        }
			});
			
			
			JButton ModifierSoinBtn = new JButton("Modifier Soin");
			ModifierSoinBtn.setBounds(80, 307, 139, 40);
			panel.add(ModifierSoinBtn);
			ModifierSoinBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedFile = (String) list.getSelectedValue();
		            if (selectedFile != null) {
		                
		            } else {
		                JOptionPane.showMessageDialog(ModifierSoinBtn, "Aucun fichier sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
		            }
		        }
			});
			
			JButton HomeButton = new JButton("");
			HomeButton.setIcon(new ImageIcon(Accueil.class.getResource("/images/home.png")));
			HomeButton.setBounds(679, 11, 48, 41);
			contentPane.add(HomeButton);
			HomeButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Accueil acc = new Accueil();
	        		setVisible(false);
	        		acc.setVisible(true);
	        	}
	        });
		}
	}
