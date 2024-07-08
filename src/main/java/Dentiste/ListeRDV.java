package main.java.Dentiste;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;


public class ListeRDV extends JFrame  {
	private JPanel contentPane;
	private JTable appointmentTable;
    private DefaultTableModel tableModel;
	 
	public static void main(String[] args) {
		ListeRDV frame = new ListeRDV();
		frame.setVisible(true);

	}

	public ListeRDV() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Planning des Rendez-Vous ");
		setBounds(100, 100, 753, 419);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(135, 11, 431, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Titre = new JLabel("Planning RDV");
		Titre.setForeground(SystemColor.windowBorder);
		Titre.setBackground(SystemColor.windowBorder);
		Titre.setFont(new Font("Tahoma", Font.BOLD, 25));
		Titre.setBounds(132, 11, 319, 28);
		panel.add(Titre);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 66, 411, 199);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		

        tableModel = new DefaultTableModel(new Object[]{"Nom ", "prénom","Date", "Heurs"}, 0);
        
        
        tableModel.insertRow(0, new Object[]{"Nom", "Prénom", "Date RDV", "Heure"});
        
        try (BufferedReader br = new BufferedReader(new FileReader("RDVS.txt"))) {
            String line;
	        while ((line = br.readLine()) != null) 
	        {
	            String[] parts = line.split(",");
	            String nom = parts[0].trim().split(":")[1].trim();
	            String prenom = parts[1].trim().split(":")[1].trim();
	            String dateRdv = parts[2].trim().split(":")[1].trim();
	            String heure = parts[3].trim().split(":")[1].trim();
	            
	            tableModel.addRow(new Object[]{nom, prenom, dateRdv, heure});
	        }
        }catch (IOException e1) {
            e1.printStackTrace();
        };
 
        final JComboBox comboBox = new JComboBox();
        comboBox.addItem("détartrage");
        comboBox.addItem("plombage");
        comboBox.addItem("dévitalisation dentaire");
        comboBox.addItem("extraction dentaire");
        comboBox.addItem("scellement des sillons");
        comboBox.addItem("Visite ");
        
        
        
        JScrollPane scrollPanee = new JScrollPane();
        scrollPanee.setBounds(0, 217, 408, -217);
        panel_1.add(scrollPanee, BorderLayout.CENTER);
        appointmentTable = new JTable(tableModel);
        appointmentTable.setBounds(0, 0, 408, 198);
        panel_1.add(appointmentTable);
        appointmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JButton btnOuvrir = new JButton("Ouvrir");
        btnOuvrir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = appointmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    String nom = (String) tableModel.getValueAt(selectedRow, 0);
                    String prenom = (String) tableModel.getValueAt(selectedRow, 1);
                    String dateRdv = (String) tableModel.getValueAt(selectedRow, 2);
                    String heure = (String) tableModel.getValueAt(selectedRow, 3);

                    openNewWindow(nom, prenom, dateRdv, heure);
                }
        	}
        });
        btnOuvrir.setBounds(143, 276, 139, 28);
        panel.add(btnOuvrir);
        
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	
            		AjouterSoins AjS = new AjouterSoins();
            		AjS.setVisible(true);
            		dispose();
            	
        	}
        });
        btnAjouter.setBounds(252, 313, 139, 28);
        panel.add(btnAjouter);
        
        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	
            		ModifierSoin MS = new ModifierSoin();
            		MS.setVisible(true);
            		dispose();
            	
        	}
        });
        btnModifier.setBounds(46, 315, 139, 28);
        panel.add(btnModifier);
        
        
        
		JButton HomeButton = new JButton("");
		HomeButton.setIcon(new ImageIcon(Accueil.class.getResource("/images/home.png")));
		HomeButton.setBounds(679, 11, 48, 41);
		contentPane.add(HomeButton);
		HomeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Accueil acc = new Accueil();
        		setVisible(false);
        		acc.setVisible(true);
        		dispose();
        	}
        });

	   setVisible(true);
	}
	
	
	 
	private void openNewWindow(String nom, String prenom, String dateRdv, String heure) {
	    JFrame newWindow = new JFrame("Détails Patient Sélectionné");
	    newWindow.setSize(750, 400);
	    newWindow.setLocationRelativeTo(null);

	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4, 2));
	    
	    
	    JList<String> list = null;
	    String filename = nom + "_" + prenom + ".txt";
	    File file = new File(filename);
	    //if (file.exists()) {
	        DefaultListModel<String> soins = new DefaultListModel<>();
	        try (BufferedReader br = new BufferedReader(new FileReader("Soins/" + filename))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(">");
	                if (parts.length >= 2) {
	                    String dateSoin = parts[0].trim().split(":")[1].trim();
	                    String soin = parts[1].trim().split(":")[1].trim();
	                    soins.addElement(soin);
	                }
	            }
	            list = new JList<>(soins);
	            list.setVisibleRowCount(2);

	            JScrollPane scrollPane = new JScrollPane(list); // Add scroll pane to the list
	            scrollPane.setBounds(392, 249, 200, 35);
	            
	          
	            panel.setLayout(null);
	            JLabel label = new JLabel("Nom: ");
	            label.setBounds(186, 42, 200, 50);
	            panel.add(label);
	            
	            JLabel lblPrnom = new JLabel("Prénom: ");
	            lblPrnom.setBounds(186, 103, 200, 50);
	            panel.add(lblPrnom);

	            JLabel lblDateRendezvous = new JLabel("Date Rendez-vous: ");
	            lblDateRendezvous.setBounds(186, 178, 200, 50);
	            panel.add(lblDateRendezvous);
	            
	            JLabel lblSoins = new JLabel("Soins:");
	            lblSoins.setBounds(186, 252, 200, 50);
	            panel.add(lblSoins);
	            
	            JLabel label_1 = new JLabel(nom);
	            label_1.setBounds(391, 50, 165, 35);
	            panel.add(label_1);
	            JLabel label_3 = new JLabel(prenom);
	            label_3.setBounds(382, 96, 392, 50);
	            panel.add(label_3);
	            JLabel label_5 = new JLabel(dateRdv);
	            label_5.setBounds(392, 186, 392, 35);
	            panel.add(label_5);
    
	            panel.add(new JLabel("Soins: "));
	            panel.add(scrollPane); // Add scroll pane to the panel

	            newWindow.getContentPane().add(panel);
	            newWindow.setVisible(true);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    //} else {
	    //    JOptionPane.showMessageDialog(null, "Ce patient n'a pas de soins réalisés !", "Erreur", JOptionPane.ERROR_MESSAGE);
	   // }
	}
}
