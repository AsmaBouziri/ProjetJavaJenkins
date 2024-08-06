//package test.java;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import org.bson.Document;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//
//import main.java.MongoDBUtil;
//import main.java.SeConnecter;
//
//class SeConnecterTest {
//
//    private SeConnecter seConnecter;
//    private MongoDatabase database;
//    private MongoCollection<Document> collection;
//
//    @BeforeEach
//    public void setUp() {
//        // Set up MongoDB client and database
//    	database = MongoDBUtil.getDatabase("CabinetDent");
//        collection = database.getCollection("LogIn");
//        
//        // Initialize the SeConnecter instance
//        seConnecter = new SeConnecter();
//        seConnecter.setSize(800, 450);
//        seConnecter.setVisible(true);
//    }
//
//
//    @Test
//    public void testComponentsInitialization() {
//        assertNotNull(seConnecter.txtEmail, "txtEmail should not be null");
//        assertNotNull(seConnecter.textField, "textField should not be null");
//        assertNotNull(seConnecter.btnSeConnecter, "btnNewButton should not be null");
//    }
//
//    @Test
//    public void testSuccessfulLogin() {
//        // Add a user for testing
//        Document user = new Document("user", "testUser")
//                            .append("pwd", "testPass");
//        collection.insertOne(user);
//
//        
//        // Set user credentials
//        seConnecter.txtEmail.setText("testUser");
//        seConnecter.textField.setText("testPass");
//
//        // Simulate button click
//        seConnecter.btnSeConnecter.doClick();
//
//
//    }
//}
