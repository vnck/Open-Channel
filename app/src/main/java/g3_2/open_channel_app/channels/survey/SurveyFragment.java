package g3_2.open_channel_app.channels.survey;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import g3_2.open_channel_app.R;

public class SurveyFragment extends Fragment {

    private FirebaseFirestore firestoreDB;
    private Query surveyQuery;


    public static SurveyFragment newInstance() {
        return new SurveyFragment();
        /*Bundle args = new Bundle();

        SurveyFragment fragment = new SurveyFragment();
        fragment.setArguments(args);
        return fragment;*/
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_survey, container, false);
        firestoreDB = FirebaseFirestore.getInstance();
        surveyQuery = firestoreDB.collection("surveys").whereEqualTo("title", "National Graduate Employment Survey");
        getDocumentsFromCollection(surveyQuery, view);
        return view;
    }

    public void getDocumentsFromCollection(Query query, final View view) {
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            SurveyEntry survey;
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document:task.getResult()){
                        if (document.getData() != null)
                            Log.d("TAG", document.getData().toString());
                            survey = new SurveyEntry(document.getData());

                        /*Map question2 = (Map) document.getData().get("question2");
                        Log.i("TAG",question1.toString());

                        survey = new SurveyEntry(
                                document.getData().get("title").toString(),

                                survey.buildQuestion(
                                        question1.get("question").toString(),
                                        question1.get("type").toString(),
                                        question1.get("answer 1").toString(),
                                        question1.get("answer 2").toString()
                                        ),
                                survey.buildQuestion(
                                        question2.get("question").toString(),
                                        question2.get("type").toString()
                                ));*/

                        if (survey != null){
                            SurveyViewHolder holder = new SurveyViewHolder(view);
                            holder.survey_title.setText(survey.title);
                            SurveyEntry.MultipleChoiceQuestion mcq = (SurveyEntry.MultipleChoiceQuestion) survey.questions[0];
                            SurveyEntry.OpenEndedQuestion openended = (SurveyEntry.OpenEndedQuestion) survey.questions[1];
                            holder.q1_question.setText(mcq.question);
                            holder.q1_ans1.setText(mcq.answers.get(0));
                            holder.q1_ans2.setText(mcq.answers.get(1));
                            holder.q2_question.setText(openended.question);
                            holder.q2_answer.setText(openended.answer);
                        }
                    }


                } else { Log.d("My Error", "Error Getting Document"); }
            }
        });

    }



}
