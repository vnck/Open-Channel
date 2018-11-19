package g3_2.open_channel_app.channels;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import g3_2.open_channel_app.R;

public class CardChannelViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView channelImage;
    public TextView channelTitle;
    public TextView channelOrganisation;
    public TextView channelDescription;

    public CardChannelViewHolder(@NonNull View itemView) {
        super(itemView);
        channelImage = itemView.findViewById(R.id.channelImage);
        channelTitle = itemView.findViewById(R.id.title);
        channelOrganisation = itemView.findViewById(R.id.organisation);
        channelDescription = itemView.findViewById(R.id.description);
    }
}
