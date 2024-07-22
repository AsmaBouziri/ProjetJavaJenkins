//package test.java;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.bson.Document;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//
//import main.java.ModifierFichePatient;
//import main.java.MongoDBUtil;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.*;
//
//
//public class ModifierFichePatientTest {
//
//    private ModifierFichePatient frame;
//    private MongoDatabase database;
//    private MongoCollection<Document> collection;
//    private JPanel contentPane;
//    public JTextField nomTextField ;
//    public JTextField prenomTextField;
//    public JTextField cinTextField;
//    public JTextField adresseTextField;
//    public JTextField professionTextField;
//    public JTextField telTextField;
//    public JRadioButton hommeRadioButton;
//    public JRadioButton femmeRadioButton;
//    public JButton rechercherButton;
//    public JButton modifierButton;
//    public JButton annulerButton;
//    public JComboBox<Integer> jourComboBox;
//    public JComboBox<Integer> moisComboBox;
//    public JComboBox<Integer> anneeComboBox;
//
//    @BeforeEach
//    public void setUp() {
//        frame = new ModifierFichePatient();
//        frame.setSize(800, 600);
//        frame.setVisible(true);
//
//        database = MongoDBUtil.getDatabase("CabinetDent");
//        collection = database.getCollection("Patient");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        frame.dispose();
//        MongoDBUtil.close();
//    }
//
//    @Test
//    public void testFrameInitialisation() {
//        assertNotNull(frame);
//        assertNotNull(frame.nomTextField);
//        assertNotNull(frame.prenomTextField);
//        assertNotNull(frame.cinTextField);
//        assertNotNull(frame.adresseTextField);
//        assertNotNull(frame.professionTextField);
//        assertNotNull(frame.telTextField);
//        assertNotNull(frame.hommeRadioButton);
//        assertNotNull(frame.femmeRadioButton);
//        assertNotNull(frame.rechercherButton);
//        assertNotNull(frame.modifierButton);
//        assertNotNull(frame.annulerButton);
//        assertNotNull(frame.jourComboBox);
//        assertNotNull(frame.moisComboBox);
//        assertNotNull(frame.anneeComboBox);
//    }
//
//    @Test
//    public void testRechercherPatientNonTrouve() {
//        frame.nomTextField.setText("NomInexistant");
//        frame.prenomTextField.setText("PrenomInexistant");
//
//        ActionEvent e = new ActionEvent(frame.rechercherButton, ActionEvent.ACTION_PERFORMED, "Rechercher");
//        for (ActionListener al : frame.rechercherButton.getActionListeners()) {
//            al.actionPerformed(e);
//        }
//
//        assertEquals("", frame.cinTextField.getText());
//        assertEquals("", frame.adresseTextField.getText());
//        assertEquals("", frame.professionTextField.getText());
//        assertEquals("", frame.telTextField.getText());
//    }
//
//    @Test
//    public void testAnnulerButtonAction() {
//        frame.nomTextField.setText("TestNom");
//        frame.prenomTextField.setText("TestPrenom");
//        frame.cinTextField.setText("12345678");
//        frame.adresseTextField.setText("TestAdresse");
//        frame.professionTextField.setText("TestProfession");
//        frame.telTextField.setText("87654321");
//        frame.hommeRadioButton.setSelected(true);
//
//        ActionEvent e = new ActionEvent(frame.annulerButton, ActionEvent.ACTION_PERFORMED, "Annuler");
//        for (ActionListener al : frame.annulerButton.getActionListeners()) {
//            al.actionPerformed(e);
//        }
//
//        assertEquals("", frame.nomTextField.getText());
//        assertEquals("", frame.prenomTextField.getText());
//        assertEquals("", frame.cinTextField.getText());
//        assertEquals("", frame.adresseTextField.getText());
//        assertEquals("", frame.professionTextField.getText());
//        assertEquals("", frame.telTextField.getText());
//        assertTrue(frame.hommeRadioButton.isSelected());
//        assertFalse(frame.femmeRadioButton.isSelected());
//    }
//
//    @Test
//    public void testModifierButtonValidation() {
//        frame.nomTextField.setText("TestNom");
//        frame.prenomTextField.setText("TestPrenom");
//        frame.cinTextField.setText("1234"); // CIN incorrect
//        frame.adresseTextField.setText("TestAdresse");
//        frame.professionTextField.setText("TestProfession");
//        frame.telTextField.setText("87654321");
//
//        ActionEvent e = new ActionEvent(frame.modifierButton, ActionEvent.ACTION_PERFORMED, "Modifier");
//        for (ActionListener al : frame.modifierButton.getActionListeners()) {
//            al.actionPerformed(e);
//        }
//
//        assertEquals("1234", frame.cinTextField.getText()); // CIN should not be modified
//        assertEquals("TestAdresse", frame.adresseTextField.getText());
//        assertEquals("TestProfession", frame.professionTextField.getText());
//        assertEquals("87654321", frame.telTextField.getText());
//    }
//}
//
