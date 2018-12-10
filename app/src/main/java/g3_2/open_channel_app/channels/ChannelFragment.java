package g3_2.open_channel_app.channels;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import g3_2.open_channel_app.R;

import static android.support.constraint.Constraints.TAG;

public class ChannelFragment extends Fragment {
    public NetworkImageView channelImage;
    public String id;
    public String url;
    public String pdfurl;
    public String title;
    public String description;
    public Bundle details;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public ChannelFragment() {
        Log.d(TAG, "no arg constructor channelfragment");
//        ChannelFragment frag = new ChannelFragment();
        this.id = "no find";
        this.url = "invalid url";
        this.pdfurl = "invalid pdfurl";
        this.title = "invalid title";
        this.description = "No description";
        Bundle x = new Bundle();
        this.details = x;
    }

    public ChannelFragment(Bundle bundle) {
        Log.d(TAG, "Channel Frag 28");
//        ChannelFragment frag = new ChannelFragment();
        this.id = bundle.getString("id");
        this.url = bundle.getString("url");
        this.pdfurl = bundle.getString("pdfurl");
        this.title = bundle.getString("title");
        this.description = bundle.getString("description");
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

        // ZL code
        mRequestQueue = Volley.newRequestQueue(getContext());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);
            }
        });

        channelImage = view.findViewById(R.id.channelImage);
        channelImage.setImageUrl(this.url,mImageLoader);

        TextView title = view.findViewById(R.id.channelTitle);
        title.setText(this.title);

        TextView description = view.findViewById(R.id.description);
        description.setText(this.description);


//        ImageRequester.getInstance();
//
//        channelImage = view.findViewById(R.id.channelImage);
//        imageRequester.setImageFromUrl(channelImage, url);

//        ImageButton imageButton = view.findViewById(R.id.org_image);
//        imageButton.setImageResource(R.drawable.oc_logo);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this.getActivity(), getChildFragmentManager(),details);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
