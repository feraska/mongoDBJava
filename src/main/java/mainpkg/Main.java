package mainpkg;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

public class Main {
    public void mongoDb(){
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
    public void fireBase(){

    }
    public static void main(String[] args) {

    }

}
