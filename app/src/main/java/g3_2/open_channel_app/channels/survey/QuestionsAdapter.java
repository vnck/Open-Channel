package g3_2.open_channel_app.channels.survey;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.channels.CardChannelViewHolder;
import g3_2.open_channel_app.channels.ChannelActivity;
import g3_2.open_channel_app.network.ChannelEntry;
import g3_2.open_channel_app.network.ImageRequester;

import static android.support.constraint.Constraints.TAG;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder>  {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<ChannelEntry> productList;
    private ImageRequester imageRequester;
    private Fragment fragment;
    private Context mContext;
    boolean value;
    int count = 0;

    private CollectionReference notebookRef = db.collection("Notebook/toapayoh/Survey");
    public QuestionsAdapter(Context context, List<ChannelEntry> productList, Fragment fragment) {
        this.productList = productList;
        this.fragment = fragment;
        imageRequester = ImageRequester.getInstance();
        mContext = context;
//        dataRetrieve();
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        String x = "Question" + count;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_question_open_ended, parent, false);
        return new QuestionsViewHolder(layoutView,fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        String x = "Question" + count;
        if(loadNotes(x)){
            holder.question.setText("Open Ended is true");
            holder.answer.setText("asd");
        }
        else{
            holder.question.setText("Open Ended is false");
            holder.answer.setText("asvklzd");
        }
        if (productList != null && position < productList.size()) {
            ChannelEntry channel = productList.get(position);
//            holder.channelTitle.setText(surveyQuestion.get("1_OpenEnded"));
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public boolean loadNotes(String questionNumber) {
        notebookRef.document(questionNumber)
        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.get("OpenEnded"));
                        value = document.getBoolean("OpenEnded");
                        Log.d(TAG, String.valueOf(value));

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException()); } }
        });
        return value;
    }
//    public void dataRetrieve() {
//        DocumentReference docRef = db.collection("channels").document("toapayoh");
////        docRef.get()
////                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
////                    @Override
////                    public void onSuccess(DocumentSnapshot documentSnapshot) {
////                        if(documentSnapshot.exists()){
////                            Map<String, Object> note = documentSnapshot.getData();
////                            Log.d(TAG, note.toString());
////                        }
////
////                        else{}
////                    }
////
////                })
////                .addOnFailureListener(new OnFailureListener() {
////                    @Override
////                    public void onFailure(@NonNull Exception e) {
////
////                    }
////                });
//
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.get("Survey"));
//                        SurveyQuestion surveyQuestion =  document.toObject(SurveyQuestion.class);
////                        Log.d(TAG, questionkey.toString());
//
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
//    }

}
