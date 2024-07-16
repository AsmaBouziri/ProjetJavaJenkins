package main.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AjouterPatient extends JFrame {
    private JPanel contentPane;
    public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setNomTextField(JTextField nomTextField) {
		this.nomTextField = nomTextField;
	}

	public void setPrenomTextField(JTextField prenomTextField) {
		this.prenomTextField = prenomTextField;
	}

	public void setCinTextField(JTextField cinTextField) {
		this.cinTextField = cinTextField;
	}

	public void setAdresseTextField(JTextField adresseTextField) {
		this.adresseTextField = adresseTextField;
	}

	public void setProfessionTextField(JTextField professionTextField) {
		this.professionTextField = professionTextField;
	}

	public void setTelTextField(JTextField telTextField) {
		this.telTextField = telTextField;
	}

	public void setHommeRadioButton(JRadioButton hommeRadioButton) {
		this.hommeRadioButton = hommeRadioButton;
	}

	public void setFemmeRadioButton(JRadioButton femmeRadioButton) {
		this.femmeRadioButton = femmeRadioButton;
	}

	public void setEnregistrerButton(JButton enregistrerButton) {
		this.enregistrerButton = enregistrerButton;
	}

	public void setAnnulerButton(JButton annulerButton) {
		this.annulerButton = annulerButton;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	private JTextField nomTextField;
    private JTextField prenomTextField;
    private JTextField cinTextField;
    private JTextField adresseTextField;
    private JTextField professionTextField;
    private JTextField telTextField;
    private JRadioButton hommeRadioButton, femmeRadioButton;
    public JButton enregistrerButton;
    private JButton annulerButton;
    private JList<String> list;
    private MongoDatabase database;

    public static void main(String[] args) {
        AjouterPatient frame = new AjouterPatient();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public AjouterPatient() {
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 753, 419);
        setTitle("Ajouter Patient");
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel Titre = new JLabel("Ajouter Patient");
        Titre.setForeground(SystemColor.windowBorder);
        Titre.setFont(new Font("Tahoma", Font.BOLD, 25));

        JLabel nomLabel = new JLabel("Nom :");
        nomTextField = new JTextField(20);

        JLabel prenomLabel = new JLabel("Prénom :");
        prenomTextField = new JTextField(20);

        JLabel cinLabel = new JLabel("CIN :");
        cinTextField = new JTextField(20);

        JLabel sexeLabel = new JLabel("Sexe :");
        ButtonGroup buttonGroupSexe = new ButtonGroup();
        hommeRadioButton = new JRadioButton("Homme");
        femmeRadioButton = new JRadioButton("Femme");
        buttonGroupSexe.add(hommeRadioButton);
        buttonGroupSexe.add(femmeRadioButton);

        JLabel adresseLabel = new JLabel("Adresse :");
        adresseTextField = new JTextField(20);

        JLabel professionLabel = new JLabel("Profession :");
        professionTextField = new JTextField(20);

        JLabel telLabel = new JLabel("Numéro de téléphone :");
        telTextField = new JTextField(20);

        JLabel dateNaissanceLabel = new JLabel("Date de Naissance :");
        JComboBox<Integer> jourComboBox = new JComboBox<>();
        JComboBox<Integer> moisComboBox = new JComboBox<>();
        JComboBox<Integer> anneeComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            jourComboBox.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            moisComboBox.addItem(i);
        }
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = currentYear - 100; i <= currentYear; i++) {
            anneeComboBox.addItem(i);
        }

        enregistrerButton = new JButton("Enregistrer");
        annulerButton = new JButton("Annuler");

        JButton homeButton = new JButton("");
        homeButton.setIcon(new ImageIcon(AjouterPatient.class.getResource("./images/home.png")));
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Acceuil acc = new Acceuil();
                setVisible(false);
                acc.setVisible(true);
            }
        });

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Titre)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(nomLabel)
                    .addComponent(prenomLabel)
                    .addComponent(cinLabel)
                    .addComponent(sexeLabel)
                    .addComponent(adresseLabel)
                    .addComponent(professionLabel)
                    .addComponent(telLabel)
                    .addComponent(dateNaissanceLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nomTextField)
                    .addComponent(prenomTextField)
                    .addComponent(cinTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hommeRadioButton)
                        .addComponent(femmeRadioButton))
                    .addComponent(adresseTextField)
                    .addComponent(professionTextField)
                    .addComponent(telTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jourComboBox)
                        .addComponent(moisComboBox)
                        .addComponent(anneeComboBox))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(enregistrerButton)
                .addComponent(annulerButton))
            .addComponent(homeButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(Titre)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nomLabel)
                .addComponent(nomTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(prenomLabel)
                .addComponent(prenomTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(cinLabel)
                .addComponent(cinTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(sexeLabel)
                .addComponent(hommeRadioButton)
                .addComponent(femmeRadioButton))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(adresseLabel)
                .addComponent(adresseTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(professionLabel)
                .addComponent(professionTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(telLabel)
                .addComponent(telTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(dateNaissanceLabel)
                .addComponent(jourComboBox)
                .addComponent(moisComboBox)
                .addComponent(anneeComboBox))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(enregistrerButton)
                .addComponent(annulerButton))
            .addComponent(homeButton)
        );

        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implémentation de l'action d'enregistrement
                if (nomTextField.getText().isEmpty() || prenomTextField.getText().isEmpty()) {
                    // Afficher un message d'erreur ou ne rien faire
                    return;
                }

                Document patientDocument = new Document()
                        .append("nom", nomTextField.getText())
                        .append("prenom", prenomTextField.getText())
                        .append("cin", cinTextField.getText())
                        .append("adresse", adresseTextField.getText())
                        .append("profession", professionTextField.getText())
                        .append("tel", telTextField.getText())
                        .append("sexe", hommeRadioButton.isSelected() ? "Homme" : "Femme")
                        .append("dateNaissance", ""); // Ajouter la date de naissance correctement

                database.getCollection("Patient").insertOne(patientDocument);
            }
        });
    }

    // Getters and setters for testing
    public JTextField getNomTextField() {
        return nomTextField;
    }

    public JTextField getPrenomTextField() {
        return prenomTextField;
    }

    public JTextField getCinTextField() {
        return cinTextField;
    }

    public JTextField getAdresseTextField() {
        return adresseTextField;
    }

    public JTextField getProfessionTextField() {
        return professionTextField;
    }

    public JTextField getTelTextField() {
        return telTextField;
    }

    public JRadioButton getHommeRadioButton() {
        return hommeRadioButton;
    }

    public JRadioButton getFemmeRadioButton() {
        return femmeRadioButton;
    }

    public JButton getEnregistrerButton() {
        return enregistrerButton;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JList<String> getList() {
        return list;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }
}
