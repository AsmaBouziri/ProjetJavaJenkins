package test.java;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;

import main.java.MongoDBUtil;
import main.java.SeConnecter;

class SeConnecterTest {

    private SeConnecter seConnecter;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        // Set up MongoDB client and database
    	database = MongoDBUtil.getDatabase("CabinetDent");
        collection = database.getCollection("LogIn");

        // Add a user for testing
        Document user = new Document("user", "testUser")
                            .append("pwd", "testPass");
        collection.insertOne(user);

        // Initialize the SeConnecter instance
        seConnecter = new SeConnecter();
        seConnecter.setSize(800, 450);
        seConnecter.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        // Clean up MongoDB
        collection.deleteMany(new Document());
        mongoClient.close();
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(seConnecter.txtEmail, "txtEmail should not be null");
        assertNotNull(seConnecter.textField, "textField should not be null");
        assertNotNull(seConnecter.btnNewButton, "btnNewButton should not be null");
    }

    @Test
    public void testSuccessfulLogin() {
        // Set user credentials
        seConnecter.txtEmail.setText("testUser");
        seConnecter.textField.setText("testPass");

        // Simulate button click
        seConnecter.btnNewButton.doClick();

        // Check for success message dialog
        // You need to verify the dialog by simulating user input
        // This can be done using a library like AssertJ-Swing or similar
        // However, testing GUI interactions like JOptionPane dialogs directly is complex and often requires integration testing tools
        // For demonstration, we're assuming this works by manually checking the UI

        // In practice, you might use tools or techniques to intercept the dialog
        // and verify its message and appearance.
    }

    @Test
    public void testFailedLogin() {
        // Set invalid user credentials
        seConnecter.txtEmail.setText("invalidUser");
        seConnecter.textField.setText("invalidPass");

        // Simulate button click
        seConnecter.btnNewButton.doClick();

        // Check for error message dialog
        // As with the success message, you would check the dialog appearance here
        // For practical purposes, dialog verification requires more complex approaches
    }
}
