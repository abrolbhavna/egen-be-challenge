package egen.service;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class TestData {


	private static final int port = 27017;
	private static final String host = "localhost";
    public static void metrics() {

        MongoClient client = mongoClient();
        DB db = client.getDB( "weightTracker");

        DBCollection metrics = db.getCollection("metrics");
        //metrics.drop();
        
        metrics.insert(dbObjectFromJson("{'_id' : '1',timeStamp: '1458062848731' value: '120'}"));
        metrics.insert(dbObjectFromJson("{'_id' : '2',timeStamp: '1458062848732' value: '121'}"));
        metrics.insert(dbObjectFromJson("{'_id' : '3',timeStamp: '1458062848733' value: '122'}"));
        metrics.insert(dbObjectFromJson("{'_id' : '4',timeStamp: '1458062848734' value: '123'}"));
        metrics.insert(dbObjectFromJson("{'_id' : '5',timeStamp: '1458062848735' value: '124'}"));
        metrics.insert(dbObjectFromJson("{'_id' : '6',timeStamp: '1458062848736' value: '125'}"));
        metrics.insert(dbObjectFromJson("{'_id' : '7',timeStamp: '1458062848737' value: '126'}"));
    }

    public static void alerts() {

        MongoClient client = mongoClient();
        DB db = client.getDB( "weightTracker");

        DBCollection alerts = db.getCollection("alerts");
        alerts.drop();
        
    }
        
    private static MongoClient mongoClient() {
        try {
            return new MongoClient( host , port );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    private static DBObject dbObjectFromJson(String json) {
        return (DBObject) JSON.parse(json);
        
    }
}