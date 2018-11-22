package g3_2.open_channel_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel_app.channels.CardChannelAdapter;
import g3_2.open_channel_app.network.ChannelEntry;

public class MyChannelFragment extends Fragment {

    public static MyChannelFragment newInstance() {
        return new MyChannelFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_channel, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.my_channel_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        CardChannelAdapter adapter = new CardChannelAdapter(
                ChannelEntry.initProductEntryList(getResources(),R.raw.mychannels));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing_small);
        recyclerView.addItemDecoration(new ChannelGridDecoration(largePadding, smallPadding));

        return view;
    }

}
