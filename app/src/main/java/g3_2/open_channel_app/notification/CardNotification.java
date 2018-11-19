package g3_2.open_channel_app.notification;

public class CardNotification {
    private String title;
    private String channel;
    private String date;
    private String url;

    public CardNotification(String title, String channel, String date, String url) {
        this.title = title;
        this.channel = channel;
        this.date = date;
        this.url =url;
    }

    public String getDate() {
        return date;
    }

    public String getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }


    public String getUrl() {
        return url;
    }

}
