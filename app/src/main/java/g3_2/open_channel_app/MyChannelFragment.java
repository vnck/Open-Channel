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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import g3_2.open_channel_app.channels.CardChannelAdapter;
import g3_2.open_channel_app.network.ChannelEntry;

public class MyChannelFragment extends Fragment {

    private FirebaseFirestore myChannelBank;
    private Query myChannelQuery;


    public static MyChannelFragment newInstance() {
        return new MyChannelFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        myChannelBank = FirebaseFirestore.getInstance();
        myChannelQuery = myChannelBank.collection("channel").limit(10);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_channel, container, false);

        getDocumentsFromCollection(myChannelQuery, view);

        return view;
    }

    public void getDocumentsFromCollection(Query query, final View view){
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ChannelEntry> channelList = new ArrayList<>();

                            for (DocumentSnapshot document : task.getResult()) {
                                ChannelEntry channel = new ChannelEntry(
                                        document.getData().get("title").toString(),
                                        document.getData().get("url").toString(),
                                        document.getData().get("organisation").toString(),
                                        document.getData().get("description").toString()
                                );
                                channelList.add(channel);
                            }
                            RecyclerView recyclerView = view.findViewById(R.id.my_channel_recycler_view);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

                            CardChannelAdapter adapter = new CardChannelAdapter(
                                    getContext(),channelList, MyChannelFragment.this);
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
