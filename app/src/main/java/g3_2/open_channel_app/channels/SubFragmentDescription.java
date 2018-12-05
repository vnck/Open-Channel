package g3_2.open_channel_app.channels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import g3_2.open_channel_app.R;


public class SubFragmentDescription extends Fragment {
//ToDo implement Description fragment
    public String description;
    public static SubFragmentDescription newInstance(String description){
        SubFragmentDescription frag = new SubFragmentDescription();
        frag.description = description;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subfragment_description, container, false);
        TextView holder = rootView.findViewById(R.id.descriptionText);
        holder.setText(description);
        return rootView;
    }


}
