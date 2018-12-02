package g3_2.open_channel_app.network;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import g3_2.open_channel_app.Database;
import g3_2.open_channel_app.MainActivity;
import g3_2.open_channel_app.R;

/**
 * A product entry in the list of products.
 */
public class DocNotificationEntry{
    private static final String TAG = DocNotificationEntry.class.getSimpleName();
    public final String title;
    public final String url;
    public final String channel;
    public final String date;

    public DocNotificationEntry(
            String title, String url, String channel, String date) {
        this.title = title;
        this.url = url;
        this.channel = channel;
        this.date = date;
    }

    public DocNotificationEntry(DocumentSnapshot documentSnapshot) {
        this.channel = documentSnapshot.get("channel").toString();
        this.title = documentSnapshot.get("title").toString();
        this.url = documentSnapshot.get("url").toString();
        this.date = documentSnapshot.get("date").toString();
    }

    public static List<DocNotificationEntry> initProductEntryList(ArrayList<String> notifdoclist) {
        //TODO why isit not calling my getnotif method ??
        ArrayList<DocNotificationEntry> ans = new ArrayList<>();
        Log.d(TAG, "calling initproductentrylist");
        for (String notifdocid : notifdoclist) {
            getNotif(notifdocid, ans);
        }
        return ans;
    }


    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
     */
    public static List<DocNotificationEntry> initProductEntryList(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.notification_docs);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error closing the input stream.", exception);
            }
        }
        String jsonProductsString = writer.toString();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<DocNotificationEntry>>() {
        }.getType();
        return gson.fromJson(jsonProductsString, productListType);
    }

    /**
     * getNotif takes in the pathToNotif can queries Firebase
     * the information is then added to the List of DocNotificationEntry
     * @param pathToNotif
     * @param docEntry
     *
     * This can later be used in the initProductEntryList above (currently it takes resources)
     */
    public static void getNotif(String pathToNotif, final List<DocNotificationEntry> docEntry) {
        FirebaseFirestore fdb = FirebaseFirestore.getInstance();
        DocumentReference notifRef = fdb.document(pathToNotif);
        Log.d(TAG,"calling getNotif");
        notifRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Log.d(TAG, "notif ID " + documentSnapshot.getId());
                        DocNotificationEntry dbb = new DocNotificationEntry(documentSnapshot);
                        Log.d(TAG, "notif dbb + " + dbb.title);

                        String ds = documentSnapshot.getId();
                        Log.d(TAG, "yo, ds " + ds);

                        docEntry.add(dbb);
                        Log.d(TAG, "getData success");
                    }
                } else {
                    Log.d(TAG, "Get Data failed.");
                }
            }
        });
    }

}