package g3_2.open_channel_app;

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

import g3_2.open_channel_app.network.DownloadImageTask;
import g3_2.open_channel_app.profile.ProfileEntry;
import g3_2.open_channel_app.profile.ProfileViewHolder;

public class ProfileFragment extends Fragment {

    private FirebaseFirestore firestoreDB;
    private Query profileQuery;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firestoreDB = FirebaseFirestore.getInstance();

        profileQuery = firestoreDB.collection("profiles").whereEqualTo("id", "user0");

        getDocumentsFromCollection(profileQuery, view);

        return view;
    }

    public void getDocumentsFromCollection(Query query, final View view){
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            ProfileEntry profile = null;

                            for (DocumentSnapshot document : task.getResult()) {

                                profile = new ProfileEntry(
                                        document.getData().get("firstname").toString(),
                                        document.getData().get("lastname").toString(),
                                        document.getData().get("dob").toString(),
                                        document.getData().get("address").toString(),
                                        document.getData().get("postalcode").toString(),
                                        document.getData().get("hp").toString(),
                                        document.getData().get("home").toString(),
                                        document.getData().get("nric").toString(),
                                        document.getData().get("email").toString(),
                                        document.getData().get("id").toString(),
                                        document.getData().get("url").toString()
                                );
                            }


                            if(profile != null){
                                ProfileViewHolder holder = new ProfileViewHolder(view);
                                holder.firstname.setText(profile.firstname);
                                holder.lastname.setText(profile.lastname);
                                holder.dob.setText(profile.dob);
                                holder.address.setText(profile.address);
                                holder.postalcode.setText(profile.postalcode);
                                holder.hpnumber.setText(profile.hpnumber);
                                holder.homenumber.setText(profile.homenumber);
                                holder.nric.setText(profile.nric);
                                holder.email.setText(profile.email);

                                new DownloadImageTask(holder.profilePic).execute(profile.url);

                            }

                        } else {
                            Log.d("My Error", "Error Getting Document");
                        }
                    }
                });
    }

}
