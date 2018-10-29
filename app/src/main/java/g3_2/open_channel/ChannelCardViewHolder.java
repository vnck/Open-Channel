package g3_2.open_channel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class ChannelCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView channelImage;
    public TextView channelTitle;
    public TextView channelPrice;

    public ChannelCardViewHolder(@NonNull View itemView) {
        super(itemView);
        channelImage = itemView.findViewById(R.id.channel_image);
        channelTitle = itemView.findViewById(R.id.channel_title);
        channelPrice = itemView.findViewById(R.id.channel_price);

    }
}
