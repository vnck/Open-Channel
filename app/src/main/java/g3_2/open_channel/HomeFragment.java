package g3_2.open_channel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel.network.ChannelEntry;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.oc_home_fragment, container, false);

        setUpToolbar(view);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        ChannelCardRecyclerViewAdapter adapter = new ChannelCardRecyclerViewAdapter(
                ChannelEntry.initChannelEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing_small);
        recyclerView.addItemDecoration(new ChannelGridItemDecoration(largePadding, smallPadding));

        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.bottomnav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null){
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        menuInflater.inflate(R.menu.oc_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    ((NavigationHost) getActivity()).navigateTo(new HomeFragment(), false);
                    return true;
                case R.id.myChannels:
                    ((NavigationHost) getActivity()).navigateTo(new MyChannelGridFragment(), false);
                    return true;
                case R.id.allChannels:
                    ((NavigationHost) getActivity()).navigateTo(new ChannelGridFragment(), false);
                    return true;
                case R.id.account:
                    ((NavigationHost) getActivity()).navigateTo(new ProfileFragment(), false);
                    return true;
            }
            return false;
        }
    };


}