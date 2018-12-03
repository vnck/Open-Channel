package g3_2.open_channel_app.network;

import java.util.List;

/**
 * A product entry in the list of products.
 */
public class ChannelEntry {
    private static final String TAG = ChannelEntry.class.getSimpleName();

    public final String title;
    public final String url;
    public final String organisation;
    public final String description;
    public final List subscribers;

    public ChannelEntry(
            String title, String url, String organisation, String description, List subscribers) {
        this.title = title;
        this.url = url;
        this.organisation = organisation;
        this.description = description;
        this.subscribers = subscribers;
    }

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
     */
}