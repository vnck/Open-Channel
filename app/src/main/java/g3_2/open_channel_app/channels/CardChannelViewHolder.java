package g3_2.open_channel_app.channels;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.ChannelEntry;

public class CardChannelViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView channelImage;
    public TextView channelTitle;
    public TextView channelOrganisation;
    public TextView channelDescription;
    public Button button;
    public ChannelEntry currentItem;
    private final Fragment frag;


    public CardChannelViewHolder(@NonNull View itemView, Fragment fragment) {
        super(itemView);
        channelImage = itemView.findViewById(R.id.channelImage);
        channelTitle = itemView.findViewById(R.id.title);
        channelOrganisation = itemView.findViewById(R.id.organisation);
        channelDescription = itemView.findViewById(R.id.description);
        button= (Button) itemView.findViewById(R.id.subscribe_button);
        this.frag = fragment;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Fragment selectedFragment = ChannelFragment.newInstance();
                if (selectedFragment != null) {
                    FragmentTransaction transaction = frag.getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, selectedFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

    }
}
