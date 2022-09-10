package mainpkg;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import java.io.FileInputStream;

public class Main {
    public static void mongoDb(){
        try {

            MongoClient mongoClient = MongoClients.create("mongodb+srv://feras:123@react-db.5elia.mongodb.net/?retryWrites=true&w=majority");
            MongoDatabase db = mongoClient.getDatabase("shopping-cart");
            MongoCollection<Document> document = db.getCollection("users");
            System.out.println("successful connection");
            BasicDBObject basicBSONObject = new BasicDBObject();
            basicBSONObject.put("email","feras.94.kasabri@gmail.com");
            MongoCursor<Document> document1= document.find(basicBSONObject).iterator();
            while (document1.hasNext()){
                System.out.println(document1.next().getString("firstName"));
            }
        }
        catch (Exception e){

        }
    }
    public static void fireBase(){
        try {

            FileInputStream serviceAccount =
                    new FileInputStream("serviceAccountKey.json");


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://fir-21c88-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("connection succ");
            Firestore firestore = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> apiFuture=firestore.collection("users").get();
            System.out.println(apiFuture.get().getDocuments().get(0).get("email"));
            ApiFuture<QuerySnapshot> apiFuture1= firestore.collection("users").orderBy("email").get();
            System.out.println(apiFuture1.get().getDocuments().size());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        fireBase();
    }

}
