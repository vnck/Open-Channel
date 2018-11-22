package g3_2.open_channel_app.notification;

public class CardNotification {
    private String title;
    private String channel;
    private String date;
    private String url;
    private String due;

    public CardNotification(String title, String channel, String date, String url, String due) {
        this.title = title;
        this.channel = channel;
        this.date = date;
        this.due = due;
        this.url =url;
    }

    public String getDate() {
        return date;
    }

    public String getDue() { return due; }

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
