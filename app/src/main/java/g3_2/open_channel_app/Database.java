package g3_2.open_channel_app;

import android.provider.ContactsContract;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Database object
 * Database.id returns ID of document
 * Databse.data returns HashMap of personal details
 */
@IgnoreExtraProperties
public class Database {
    String id;
    HashMap<String, Object> data;
    Database(){
    }

    Database(DocumentSnapshot documentSnapshot){
        id = documentSnapshot.get("id").toString();
        data = (HashMap<String,Object>) documentSnapshot.getData();
    }

    public void setId(DocumentSnapshot documentSnapshot){
        id = documentSnapshot.get("id").toString();
    }

    public void setData(DocumentSnapshot documentSnapshot){
        data = (HashMap<String,Object>) documentSnapshot.getData();
    }

    public void setAll(DocumentSnapshot documentSnapshot){
        id = documentSnapshot.get("id").toString();
        data = (HashMap<String,Object>) documentSnapshot.getData();
    }

    public String getId(){
        return this.id;
    }

    public HashMap<String, Object> getData(){
        return this.data;
    }
}
