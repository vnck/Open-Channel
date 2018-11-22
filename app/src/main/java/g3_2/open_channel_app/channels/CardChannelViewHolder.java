package g3_2.open_channel_app.channels;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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

    public CardChannelViewHolder(@NonNull View itemView) {
        super(itemView);
        channelImage = itemView.findViewById(R.id.channelImage);
        channelTitle = itemView.findViewById(R.id.title);
        channelOrganisation = itemView.findViewById(R.id.organisation);
        channelDescription = itemView.findViewById(R.id.description);
        button= (Button) itemView.findViewById(R.id.subscribe_button);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Snackbar.make(v, "Channel Feature Coming Soon", Snackbar.LENGTH_LONG)
                        .setAction("Channel", null).show();
            }
        });
    }
}
