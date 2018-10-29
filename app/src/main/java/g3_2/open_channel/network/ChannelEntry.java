package g3_2.open_channel.network;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import g3_2.open_channel.R;
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

/**
 * A channel entry in the list of channels.
 */
public class ChannelEntry {
    private static final String TAG = ChannelEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String url;
    public final String price;
    public final String description;

    public ChannelEntry(
            String title, String dynamicUrl, String url, String price, String description) {
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
        this.price = price;
        this.description = description;
    }

    /**
     * Loads a raw JSON at R.raw.channels and converts it into a list of ChannelEntry objects
     */
    public static List<ChannelEntry> initChannelEntryList(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.channels);
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
        String jsonChannelsString = writer.toString();
        Gson gson = new Gson();
        Type channelListType = new TypeToken<ArrayList<ChannelEntry>>() {
        }.getType();
        return gson.fromJson(jsonChannelsString, channelListType);
    }
}