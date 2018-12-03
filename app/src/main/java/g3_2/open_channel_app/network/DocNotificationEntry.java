package g3_2.open_channel_app.network;

/**
 * A product entry in the list of products.
 */
public class DocNotificationEntry {
    private static final String TAG = DocNotificationEntry.class.getSimpleName();

    public final String title;
    public final String channel;
    public final String date;
    public final String url;
    public final String id;

    public DocNotificationEntry(
            String title, String channel, String date, String url, String id) {
        this.title = title;
        this.url = url;
        this.channel = channel;
        this.date = date;
        this.id = id;
    }
}