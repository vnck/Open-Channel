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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import g3_2.open_channel_app.network.ActionNotificationEntry;
import g3_2.open_channel_app.network.DocNotificationEntry;
import g3_2.open_channel_app.notification.CardActionNotificationAdaptor;
import g3_2.open_channel_app.notification.CardDocNotificationAdaptor;

public class HomeFragment extends Fragment {

    public Query queryAct;
    public Query queryDoc;

    public static HomeFragment newInstance(Query queryAct, Query queryDoc) {
        HomeFragment frag = new HomeFragment();
        frag.queryAct = queryAct;
        frag.queryDoc = queryDoc;
        return frag;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getDocumentsFromCollection(queryDoc, view);
        getActionsFromCollection(queryAct, view);

        return view;
    }

    public void getDocumentsFromCollection(Query query, final View view){
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocNotificationEntry> notificationList = new ArrayList<>();

                            for (DocumentSnapshot document : task.getResult()) {
                                DocNotificationEntry notification = new DocNotificationEntry(
                                        document.getData().get("title").toString(),
                                        document.getData().get("channel").toString(),
                                        document.getData().get("date").toString(),
                                        document.getData().get("url").toString(),
                                        document.getId()
                                );
                                notificationList.add(notification);
                            }


                            RecyclerView recyclerView = view.findViewById(R.id.note_doc_recycler_view);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

                            CardDocNotificationAdaptor adapter = new CardDocNotificationAdaptor(getContext(),
                                    notificationList, HomeFragment.this);
                            recyclerView.setAdapter(adapter);
                            int largePadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing);
                            int smallPadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing_small);
                            recyclerView.addItemDecoration(new ChannelGridDecoration(largePadding, smallPadding));

                        } else {
                            Log.d("My Error", "Error Getting Document");
                        }
                    }
                });
    }

    public void getActionsFromCollection(Query query, final View view){
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ActionNotificationEntry> notificationList = new ArrayList<>();

                            for (DocumentSnapshot document : task.getResult()) {
                                ActionNotificationEntry notification = new ActionNotificationEntry(
                                        document.getData().get("title").toString(),
                                        document.getData().get("channel").toString(),
                                        document.getData().get("date").toString(),
                                        document.getData().get("due").toString(),
                                        document.getData().get("channelid").toString()
                                );
                                notificationList.add(notification);
                            }
                            RecyclerView recyclerView = view.findViewById(R.id.note_act_recycler_view);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

                            CardActionNotificationAdaptor adapter = new CardActionNotificationAdaptor(getContext(),
                                    notificationList, HomeFragment.this);
                            recyclerView.setAdapter(adapter);
                            int largePadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing);
                            int smallPadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing_small);
                            recyclerView.addItemDecoration(new ChannelGridDecoration(largePadding, smallPadding));

                        } else {
                            Log.d("My Error", "Error Getting Document");
                        }
                    }
                });
    }

}
