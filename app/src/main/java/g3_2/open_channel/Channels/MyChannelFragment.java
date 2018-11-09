package g3_2.open_channel.Channels;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel.ChannelGridFragment;
import g3_2.open_channel.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyChannelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyChannelFragment extends ChannelGridFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_channel, container, false);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this.getActivity(), getChildFragmentManager());
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        BottomNavigationView navigation = view.findViewById(R.id.bottomnav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;
    }

}
