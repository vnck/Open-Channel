package g3_2.open_channel_app.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.channels.ChannelActivity;

public class CardActionNotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView notificationTitle;
    public TextView notificationChannel;
    public TextView notificationDate;
    public TextView notificationDue;
    public ImageButton downloadButton;
    public String id;
    private final Fragment frag;

    public CardActionNotificationViewHolder(@NonNull View itemView, Fragment fragment) {
        super(itemView);

        downloadButton = itemView.findViewById(R.id.downloadDocButton);

        notificationTitle = itemView.findViewById(R.id.title);
        notificationChannel = itemView.findViewById(R.id.channel);
        notificationDate = itemView.findViewById(R.id.date);
        notificationDue = itemView.findViewById(R.id.due);
        frag = fragment;

        itemView.setOnClickListener(this);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                Fragment selectedFragment = ChannelFragment.newInstance(id);
//                if (selectedFragment != null) {
//                    FragmentTransaction transaction = frag.getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragmentContainer, selectedFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        FirebaseFirestore fire = FirebaseFirestore.getInstance();
        Task task = fire.collection("channel").whereEqualTo(FieldPath.documentId(), id).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {


                    Bundle bundle = new Bundle();

                    for (DocumentSnapshot document : task.getResult()) {
                        bundle.putString("title",document.getData().get("title").toString());
                        bundle.putString("id", document.getId());
                        bundle.putString("description", document.getData().get("description").toString());
                        bundle.putString("organisation", document.getData().get("organisation").toString());
                        bundle.putString("url", document.getData().get("url").toString());
                        bundle.putString("pdfurl", document.getData().get("pdfurl").toString());

                        Intent intent = new Intent(frag.getContext(), ChannelActivity.class);

                        intent.putExtras(bundle);
                        frag.getContext().startActivity(intent);
                    }


                } else {
                    Log.d("LOGCAT", "Error Getting Document");
                }
            }
        });
    }
}
