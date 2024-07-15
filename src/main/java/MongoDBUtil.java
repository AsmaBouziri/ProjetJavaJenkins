package main.java;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private MongoDBUtil() {
        // Private constructor to prevent instantiation
    }

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
                    MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
                    mongoClient = new MongoClient(uri);
                }
            
        if (database == null) {
            database = mongoClient.getDatabase(dbName);
        }
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
        }
    }
}
