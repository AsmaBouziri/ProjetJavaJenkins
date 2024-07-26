package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import main.java.AjouterSoins;
import main.java.MongoDBUtil;
import main.java.RechercherPatient;

class RechercherPatientTest {

	  private RechercherPatient rechercherPatient;
	    private MongoDatabase database;
	    private MongoCollection<Document> collection;

	    @BeforeEach
	    public void setUp() {
	    	database = MongoDBUtil.getDatabase("CabinetDent");
	        collection = database.getCollection("Patient");
	        
	        rechercherPatient = new RechercherPatient();
	        rechercherPatient.setSize(800, 600);
	        rechercherPatient.setVisible(true);
	    }

	    @Test
	    public void testComponentsInitialization() {
	    	 assertNotNull(rechercherPatient.champNom);
	         assertNotNull(rechercherPatient.champPrenom);
	         assertNotNull(rechercherPatient.labelResultat);
	         assertNotNull(rechercherPatient.modifierButton);
	    }

}
