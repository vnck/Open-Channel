package g3_2.open_channel_app.network;

/**
 * A product entry in the list of products.
 */
public class ActionNotificationEntry {
    private static final String TAG = ActionNotificationEntry.class.getSimpleName();

    public final String title;
    public final String channel;
    public final String date;
    public final String due;
    public final String id;

    public ActionNotificationEntry(
            String title, String channel, String date, String due, String id) {
        this.title = title;
        this.channel = channel;
        this.date = date;
        this.due = due;
        this.id = id;
    }
}