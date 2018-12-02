package g3_2.open_channel_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import g3_2.open_channel_app.network.ActionNotificationEntry;
import g3_2.open_channel_app.network.DocNotificationEntry;
import g3_2.open_channel_app.notification.CardActionNotificationAdaptor;
import g3_2.open_channel_app.notification.CardDocNotificationAdaptor;

public class HomeFragment extends Fragment {
    final ArrayList<DocNotificationEntry> toShow = new ArrayList<>();
    ArrayList<String> toPath = new ArrayList<>();
    String TAG = "TAG";

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //TODO setup bundle getter

        //HashMap<String, Object> he = (HashMap<String, Object>) getArguments().getSerializable("mDB");
        //for (String k: he.keySet()){
        //    Log.d(TAG, "THIS IS KEY " + k);
        //}
        //DocNotificationEntry doc1 = new DocNotificationEntry("titties","urlol","channish","ded");
        //toShow.add(doc1);
        toPath.add("notif/n0000");

        //TODO setup listener

        int largePadding = getResources().getDimensionPixelSize(R.dimen.oc_notification_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.oc_notification_grid_spacing_small);

        RecyclerView docRecyclerView = view.findViewById(R.id.note_doc_recycler_view);
        docRecyclerView.setHasFixedSize(true);
        docRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        CardDocNotificationAdaptor docAdapter = new CardDocNotificationAdaptor(
                DocNotificationEntry.initProductEntryList(toPath), HomeFragment.this);
        //TODO why does the above method mot call the correct method?
        docRecyclerView.setAdapter(docAdapter);
        docRecyclerView.addItemDecoration(new ChannelGridDecoration(largePadding, smallPadding));

        RecyclerView actionRecyclerView = view.findViewById(R.id.note_act_recycler_view);
        actionRecyclerView.setHasFixedSize(true);
        actionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        CardActionNotificationAdaptor adapter = new CardActionNotificationAdaptor(
                ActionNotificationEntry.initProductEntryList(getResources()), HomeFragment.this);
        actionRecyclerView.setAdapter(adapter);
        actionRecyclerView.addItemDecoration(new ChannelGridDecoration(largePadding, smallPadding));

        return view;
    }
    /**
    public void getNotifs(List<String> listPathToNotifs){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (String path: listPathToNotifs){
            db.document(path).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        toShow.add(task.getResult().toObject(DocNotificationEntry.class));
                    }
                    else {
                        Log.d(TAG, "you failed to getnotif");
                    }
                }
            });
        }
    }
     */
}
