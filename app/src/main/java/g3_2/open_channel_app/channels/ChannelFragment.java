package g3_2.open_channel_app.channels;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.ImageRequester;

import static android.support.constraint.Constraints.TAG;

public class ChannelFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String id;
    private ImageRequester imageRequester;

    public String url;
    public static ChannelFragment newInstance(String id) {
        ChannelFragment frag = new ChannelFragment();
        frag.id = id;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "line 46 - channel activity");

        // Inflate the layout for this fragment
//        dataRetrieve();
        View view = inflater.inflate(R.layout.fragment_channel, container, false);
//        imageRequester = ImageRequester.getInstance();
        ImageButton imageButton = view.findViewById(R.id.channelImage);
        imageButton.setImageResource(R.drawable.oc_logo);
//        imageRequester.setImageFromUrl((NetworkImageView)view.findViewById(R.id.channelImage), url);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this.getActivity(), getChildFragmentManager());
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

        public void dataRetrieve() {
            db.collection("channel").document(id).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                url = (String) documentSnapshot.get("url");
                                Log.d(TAG, "Get Url");
                                Log.d(TAG, url);

                            } else {
                            }
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }

}
