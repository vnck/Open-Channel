package g3_2.open_channel_app.channels.survey;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import g3_2.open_channel_app.R;
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
    int count = 10;
    int x = 0;
    HashMap<String,ArrayList<String>> hash;
    private CollectionReference notebookRef = db.collection("channels").document("toapayoh").collection("Survey");
    public QuestionsAdapter(Context context,List<ChannelEntry> productList, Fragment fragment) {
        imageRequester = ImageRequester.getInstance();
        mContext = context;
        Log.d(TAG, "Line50");

        loadQuestions();
//        dataRetrieve();
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_subfragment_open_ended, parent, false);

        return new QuestionsViewHolder(layoutView,fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        String x = "Question" + position;
        Log.d(TAG, "Line71");

        if(true){
            holder.question.setText("Open Ended is true");
            holder.answer.setText("asd");
        }
        else{
            holder.question.setText("Open Ended is false");
            holder.answer.setText("asvklzd");
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public void loadQuestions() {
        db.collection("channels").document("toapayoh").collection("Survey").document("Question1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "asd");
                        count = 20;
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException()); } }
        });
        for(int i = 1; i <= count; i++){
            x++;
//            Log.d(TAG, "line100" + count);
            db.collection("channels").document("toapayoh").collection("Survey").document("Question" + i)
        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Log.d(TAG, "line101213");
                if (task.isSuccessful()) {
                    Log.d(TAG, "line104");
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        ArrayList<String> tempList = new ArrayList();
                        value = document.getBoolean("OpenEnded");
                        tempList.add(String.valueOf(value));
                        Log.d(TAG, String.valueOf(value));
//                        hash.put("Question" + x,tempList);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException()); } }
        });
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
    }

}
