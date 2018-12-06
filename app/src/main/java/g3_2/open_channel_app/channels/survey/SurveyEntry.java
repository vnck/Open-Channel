package g3_2.open_channel_app.channels.survey;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

/**
 * A product entry in the list of products.
 */
public class SurveyEntry {
    //private static final String TAG = SurveyEntry.class.getSimpleName();

    public String title;
    public Question[] questions;


    public SurveyEntry(Map<String, Object> getData) {
        this.title = (String) getData.get("title");
        getData.remove("title");
        ArrayList<Question> temp = new ArrayList<>();
        for (Map.Entry<String, Object> entry: getData.entrySet()){
            String key = entry.getKey();
            Map val = (Map) entry.getValue();
            if (key.contains("question") && val != null && val.get(key) instanceof String) {
                temp.add(buildQuestion(val));
                Log.i("TAG", temp.toString());
            }
        }
        this.questions = (Question[]) temp.toArray();

    }

    public SurveyEntry(String title, Map<String, String>... questionMaps) {
        this.title = title;
        ArrayList<Question> temp = new ArrayList<>();
        for (Map<String, String> questionMap : questionMaps)
            temp.add(buildQuestion(questionMap));
        this.questions = (Question[]) temp.toArray();
    }

    public String getTitle() { return title; }

    public Question[] getQuestions() { return questions; }



    public Question buildQuestion(Map questionMap) {
        String type = questionMap.get("type");
        questionMap.remove("type");

        if (type.equals("multiple choice"))
            return new MultipleChoiceQuestion(questionMap);
        else if (type.equals("open ended"))
            return new OpenEndedQuestion(questionMap);
        else
            return new Question(questionMap.get("question"));
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
            questionMap.remove("question");

            for (String key : questionMap.keySet()){
                if (key.contains("answer"))
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