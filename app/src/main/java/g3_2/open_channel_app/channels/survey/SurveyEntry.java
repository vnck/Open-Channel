package g3_2.open_channel_app.channels.survey;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A product entry in the list of products.
 */
public class SurveyEntry {
    //private static final String TAG = SurveyEntry.class.getSimpleName();

    public String title;
    public ArrayList<Question> questions;

    public String id;
    public DocumentReference responses;


    SurveyEntry(Map<String, Object> getData) {
        this.title = (String) getData.get("title");
        getData.remove("title");
        ArrayList<Question> temp = new ArrayList<>();
        for (Map.Entry<String, Object> entry: getData.entrySet()){
            String key = entry.getKey();
            Map value = (Map) entry.getValue();
            if (key.contains("question")) {
                Map<String, String> questionMap = new HashMap<>();
                for (Object obj : value.keySet())
                    questionMap.put(obj.toString(), value.get(obj).toString());
                Log.i("testing.....", questionMap.toString());
                temp.add(buildQuestion(questionMap));

                Boolean isQn = temp.get(0) instanceof  Question;
                String s = "isQuestion: " + isQn.toString();
                Log.i("testing.....", s);
            }
        }
        Log.i("testing.....", "temp: "+temp.toString());
        this.questions = temp;

    }

    /*public SurveyEntry(String title, Map<String, String>... questionMaps) {
        this.title = title;
        ArrayList<Question> temp = new ArrayList<>();
        for (Map<String, String> questionMap : questionMaps)
            temp.add(buildQuestion(questionMap));
        this.questions = (Question[]) temp.toArray();
    }*/

    public String getTitle() { return title; }

    public ArrayList<Question> getQuestions() { return questions; }



    Question buildQuestion(Map<String, String> questionMap) {
        String type = questionMap.get("type");
        questionMap.remove("type");

        switch(type){
            case "multiple choice":
                return new MultipleChoiceQuestion(questionMap);
            case "open ended":
                return new OpenEndedQuestion(questionMap);
            default:
                return new Question(questionMap.get("question"));
        }
    }




    class Question {
        String question;

        Question() {}
        Question(String question) { this.question = question; }
    }


    class MultipleChoiceQuestion extends Question {
        ArrayList<String> answers;

        MultipleChoiceQuestion(Map<String, String> questionMap) {
            this.question = questionMap.get("question");
            this.answers = new ArrayList<>();
            questionMap.remove("question");
            Log.i("testing.....", questionMap.toString());

            for (String key : questionMap.keySet()){
                if (key.contains("answer"))
                    Log.i("testing.....", questionMap.get(key));
                answers.add(questionMap.get(key));
            }
        }
    }


    class OpenEndedQuestion extends Question {
        String answer;

        OpenEndedQuestion(Map<String, String> questionMap) {
            this.question = questionMap.get("question");
            this.answer = "";
        }
    }



}