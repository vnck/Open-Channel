package g3_2.open_channel_app.channels;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.toolbox.NetworkImageView;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.ImageRequester;

import static android.support.constraint.Constraints.TAG;

public class ChannelFragment extends Fragment {
    public NetworkImageView channelImage;
    public String id;
    public String url;
    public Bundle details;
    private ImageRequester imageRequester;

    public ChannelFragment() {
        Log.d(TAG, "no arg constructor channelfragment");
//        ChannelFragment frag = new ChannelFragment();
        this.id = "no find";
        this.url = "invalid url";
        Bundle x = new Bundle();
        this.details = x;
    }

    public ChannelFragment(Bundle bundle) {
        Log.d(TAG, "Channel Frag 28");
//        ChannelFragment frag = new ChannelFragment();
        this.id = bundle.getString("id");
        this.url = bundle.getString("url");
        this.details = bundle;
        Log.d(TAG, this.id + "In Channel Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channel, container, false);

        Log.d(TAG, "Channel Frag 42");
        Log.d(TAG,  details.toString());

//        ImageRequester.getInstance();
//
//        channelImage = view.findViewById(R.id.channelImage);
//        imageRequester.setImageFromUrl(channelImage, url);

//        ImageButton imageButton = view.findViewById(R.id.org_image);
//        imageButton.setImageResource(R.drawable.oc_logo);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this.getActivity(), getChildFragmentManager(),details);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
