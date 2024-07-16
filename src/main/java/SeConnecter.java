package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;




public class SeConnecter extends JFrame{
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField textField;
	private MongoDatabase database;


	public static void main(String[] args) {
		
		var frame = new SeConnecter();
		frame.setVisible(true);
		frame.setSize(800, 450);
	

	}

	public SeConnecter() {
		this.database = MongoDBUtil.getDatabase("CabinetDent");
		MongoCollection<Document> collection = database.getCollection("LogIn");
		
		
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 419);
		setTitle("Se connecter");
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(89, 5, 597, 370);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 153, 153));
		panel_1.setBounds(0, 0, 282, 370);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(SeConnecter.class.getResource("./images/loginimg.png")));
		lblNewLabel_3.setBounds(40, 26, 272, 277);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Se Connecter ");
		lblNewLabel_2.setForeground(SystemColor.activeCaption);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_2.setBounds(331, 22, 256, 55);
		panel.add(lblNewLabel_2);
		
		
		txtEmail = new JTextField();
		txtEmail.setBounds(376, 113, 183, 29);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setBounds(376, 88, 183, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot De Passe");
		lblNewLabel_1.setBounds(376, 155, 86, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JPasswordField();
		textField.setBounds(376, 180, 183, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		final JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.setBackground(SystemColor.activeCaption);

		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String email = txtEmail.getText();
		        String mdp = textField.getText();
		       System.out.print(email);

		        // Créer un filtre pour le nom d'utilisateur et le mot de passe
		        Document filter = new Document("user", email).append("pwd", mdp);
		        // Rechercher le document correspondant au filtre
		        Document found = collection.find(filter).first();

		        if (found != null) {
		            JOptionPane.showMessageDialog(btnNewButton, "Connexion réussie!", "Succès", JOptionPane.INFORMATION_MESSAGE);

		            // Redirect based on the username
		            if (email.equals("assistante")) {
		                Acceuil dentiste = new Acceuil();
		               // dentiste.setVisible(true);
		            } else if (mdp.equals("dentiste")) {

		            } 
		            dispose(); // Close the current login window
		            return;
		        }else {
	                JOptionPane.showMessageDialog(btnNewButton, "Nom d'utilisateur ou mot de passe incorrect");
	            }

		    }
		});
	
		btnNewButton.setBounds(412, 232, 119, 38);
		panel.add(btnNewButton);
	
	}

}
