package g3_2.open_channel_app.channels.survey;

import com.google.firebase.firestore.Exclude;

import java.util.Map;

public class SurveyQuestion {
    private String documentId;
    private String title;
    private String description;
    private int priority;
    Map<String, String> tags;

    public SurveyQuestion() {
        //public no-arg constructor needed
    }

    public SurveyQuestion(String title, String description, int priority, Map<String, String> tags) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.tags = tags;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}