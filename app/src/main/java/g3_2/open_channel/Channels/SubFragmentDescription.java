package g3_2.open_channel.Channels;

import android.os.Bundle;
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
 * {@link SubFragmentDescription.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubFragmentDescription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubFragmentDescription extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup                  container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.oc_login_fragment, container, false);

        return rootView;
    }


}
