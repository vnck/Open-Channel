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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import g3_2.open_channel_app.R;

public class SurveyFragment extends Fragment {

    private FirebaseFirestore firestoreDB;
    private Query surveyQuery;

    private SurveyEntry survey;
    private SurveyViewHolder holder;

    private final String TAG = "SurveyFragment";

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
        surveyQuery = firestoreDB.collection("surveys").whereEqualTo("title", "National Graduate Employment Survey").limit(10);
        getDocumentsFromCollection(surveyQuery, view);
        return view;
    }

    public void getDocumentsFromCollection(Query query, final View view) {
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document:task.getResult()){
                        if (document.getData() != null)
                            survey = new SurveyEntry(document.getData());
                            Log.i(TAG, "change in SurveyEntry survey");

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
                            survey.id = document.getId();

                            holder = new SurveyViewHolder(view);
                            Log.i(TAG, "change in SurveyViewHolder holder");
                            holder.survey_title.setText(survey.title);
                            SurveyEntry.MultipleChoiceQuestion mcq = (SurveyEntry.MultipleChoiceQuestion) survey.questions.get(0);
                            SurveyEntry.OpenEndedQuestion openended = (SurveyEntry.OpenEndedQuestion) survey.questions.get(1);
                            holder.q1_question.setText(mcq.question);
                            holder.q1_ans1.setText(mcq.answers.get(1));
                            holder.q1_ans2.setText(mcq.answers.get(0));
                            holder.q2_question.setText(openended.question);
                            holder.q2_answer.setText(openended.answer);

                            // get responses docref
                            Query responses = firestoreDB.collection("responses").whereEqualTo("id", survey.id).limit(10);
                            responses.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (DocumentSnapshot document : task.getResult()) {
                                            survey.responses = document.getReference();
                                            Log.i(TAG, "change in DocumentReference responses");
                                            Log.i(TAG, "response id: "+survey.responses.getId());
                                        }
                                    } else { Log.d("My Error", "Error Getting Response Document"); }
                                }
                            });
                            Log.i(TAG,"out?");

                        }
                    }

                    buttonListener();
                } else { Log.d("My Error", "Error Getting Document"); }
            }
        });
        Log.i(TAG,"finish");
    }

    public void buttonListener () {

        holder.q1_ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicked "+holder.q1_ans1.getText().toString());
                survey.responses.update("question 1", FieldValue.arrayUnion(holder.q1_ans1.getText().toString()))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "response updated with "+holder.q1_ans1.getText().toString());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating response with "+holder.q1_ans1.getText().toString(), e);
                            }
                        });
            }
        });

        holder.q1_ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicked "+holder.q1_ans2.getText().toString());
                survey.responses.update("question 1", FieldValue.arrayUnion(holder.q1_ans2.getText().toString()))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "response updated with "+holder.q1_ans2.getText().toString());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating response with "+holder.q1_ans2.getText().toString(), e);
                            }
                        });
            }
        });
    }



}
