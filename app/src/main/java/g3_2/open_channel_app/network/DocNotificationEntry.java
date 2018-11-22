package g3_2.open_channel_app.network;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

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

import g3_2.open_channel_app.R;

/**
 * A product entry in the list of products.
 */
public class NotificationEntry {
    private static final String TAG = NotificationEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String url;
    public final String channel;
    public final String date;

    public NotificationEntry(
            String title, String dynamicUrl, String url, String channel, String date) {
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
        this.channel = channel;
        this.date = date;
    }

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
     */
    public static List<NotificationEntry> initProductEntryList(Resources resources) {
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
        Type productListType = new TypeToken<ArrayList<NotificationEntry>>() {
        }.getType();
        return gson.fromJson(jsonProductsString, productListType);
    }
}