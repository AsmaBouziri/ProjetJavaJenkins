//package test.java;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.bson.Document;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//
//import main.java.AjouterPatient;
//
//import javax.swing.JTextField;
//import javax.swing.JComboBox;
//import javax.swing.JRadioButton;
//import java.util.Calendar;
//
//public class AjoutPatientTest {
//
//    private AjouterPatient ajouterPatient;
//    private MongoDatabase mockDatabase;
//    private MongoCollection<Document> mockCollection;
//
//    @BeforeEach
//    public void setUp() {
//        ajouterPatient = new AjouterPatient();
//    }
//
//    @Test
//    public void testSavePatientSuccess() {
//        // Set up the UI components
//        ajouterPatient.getNomTextField().setText("Doe");
//        ajouterPatient.getPrenomTextField().setText("John");
//        ajouterPatient.getCinTextField().setText("12345678");
//        ajouterPatient.getAdresseTextField().setText("123 Street");
//        ajouterPatient.getProfessionTextField().setText("Dentist");
//        ajouterPatient.getTelTextField().setText("01234567");
//
//        ajouterPatient.jourComboBox.setSelectedItem(1);
//        ajouterPatient.moisComboBox.setSelectedItem(1);
//        ajouterPatient.anneeComboBox.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR));
//
//        ajouterPatient.hommeRadioButton.setSelected(true);
//
//        // Call the method
//        ajouterPatient.savePatient(mockCollection);
//
//        // Verify that insertOne was called with the correct document
//        Document expectedDocument = new Document("nom", "Doe")
//                .append("prenom", "John")
//                .append("cin", "12345678")
//                .append("sexe", "Homme")
//                .append("adresse", "123 Street")
//                .append("telephone", "01234567")
//                .append("dataNaiss", "1/1/" + Calendar.getInstance().get(Calendar.YEAR))
//                .append("profession", "Dentist");
//
//        verify(mockCollection).insertOne(expectedDocument);
//    }
//
//    @Test
//    public void testSavePatientFailureEmptyFields() {
//        // Set up the UI components with empty fields
//        ajouterPatient.getNomTextField().setText("");
//        ajouterPatient.getPrenomTextField().setText("");
//        ajouterPatient.getCinTextField().setText("");
//        ajouterPatient.getAdresseTextField().setText("");
//        ajouterPatient.getProfessionTextField().setText("");
//        ajouterPatient.getTelTextField().setText("");
//
//        // Call the method
//        ajouterPatient.savePatient(mockCollection);
//
//        // Verify that no document is inserted
//        verify(mockCollection, never()).insertOne(any(Document.class));
//    }
//
//    @Test
//    public void testSavePatientFailureInvalidTelephone() {
//        // Set up the UI components with invalid telephone number
//        ajouterPatient.getNomTextField().setText("Doe");
//        ajouterPatient.getPrenomTextField().setText("John");
//        ajouterPatient.getCinTextField().setText("12345678");
//        ajouterPatient.getAdresseTextField().setText("123 Street");
//        ajouterPatient.getProfessionTextField().setText("Dentist");
//        ajouterPatient.getTelTextField().setText("123");
//
//        // Call the method
//        ajouterPatient.savePatient(mockCollection);
//
//        // Verify that no document is inserted
//        verify(mockCollection, never()).insertOne(any(Document.class));
//    }
//
//    @Test
//    public void testClearFields() {
//        // Set up the UI components with values
//        ajouterPatient.getNomTextField().setText("Doe");
//        ajouterPatient.getPrenomTextField().setText("John");
//        ajouterPatient.getCinTextField().setText("12345678");
//        ajouterPatient.getAdresseTextField().setText("123 Street");
//        ajouterPatient.getProfessionTextField().setText("Dentist");
//        ajouterPatient.getTelTextField().setText("01234567");
//        ajouterPatient.jourComboBox.setSelectedIndex(1);
//        ajouterPatient.moisComboBox.setSelectedIndex(1);
//        ajouterPatient.anneeComboBox.setSelectedIndex(1);
//        ajouterPatient.hommeRadioButton.setSelected(true);
//
//        // Call the clearFields method
//        ajouterPatient.clearFields();
//
//        // Verify that all fields are cleared
//        assertEquals("", ajouterPatient.getNomTextField().getText());
//        assertEquals("", ajouterPatient.getPrenomTextField().getText());
//        assertEquals("", ajouterPatient.getCinTextField().getText());
//        assertEquals("", ajouterPatient.getAdresseTextField().getText());
//        assertEquals("", ajouterPatient.getProfessionTextField().getText());
//        assertEquals("", ajouterPatient.getTelTextField().getText());
//        assertEquals(0, ajouterPatient.jourComboBox.getSelectedIndex());
//        assertEquals(0, ajouterPatient.moisComboBox.getSelectedIndex());
//        assertEquals(0, ajouterPatient.anneeComboBox.getSelectedIndex());
//        assertTrue(ajouterPatient.hommeRadioButton.isSelected());
//    }
//}
