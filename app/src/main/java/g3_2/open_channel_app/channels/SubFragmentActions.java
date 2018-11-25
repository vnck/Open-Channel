package g3_2.open_channel_app.channels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubFragmentActions.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubFragmentActions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubFragmentActions extends Fragment {
//ToDo implement Action fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subfragment_actions, container, false);

        return rootView;
    }


}
