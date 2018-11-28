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

import g3_2.open_channel_app.network.ActionNotificationEntry;
import g3_2.open_channel_app.network.DocNotificationEntry;
import g3_2.open_channel_app.notification.CardActionNotificationAdaptor;
import g3_2.open_channel_app.notification.CardDocNotificationAdaptor;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        int largePadding = getResources().getDimensionPixelSize(R.dimen.oc_notification_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.oc_notification_grid_spacing_small);

        RecyclerView docRecyclerView = view.findViewById(R.id.note_doc_recycler_view);
        docRecyclerView.setHasFixedSize(true);
        docRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        CardDocNotificationAdaptor docAdapter = new CardDocNotificationAdaptor(
                DocNotificationEntry.initProductEntryList(getResources()), HomeFragment.this);
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

}
