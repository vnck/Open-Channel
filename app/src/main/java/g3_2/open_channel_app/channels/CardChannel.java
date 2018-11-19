package g3_2.open_channel_app.card;

public class CardChannel {
    private String title;
    private String organisation;
    private String description;
    private final int imageResource;

    public CardChannel(String title, String organisation, String description, int imageResource) {
        this.title = title;
        this.organisation = organisation;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getDescription() {
        return description;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }
}
