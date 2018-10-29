package g3_2.open_channel.staggeredgridlayout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import g3_2.open_channel.R;

public class StaggeredChannelCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView channelImage;
    public TextView channelTitle;
    public TextView channelPrice;

    StaggeredChannelCardViewHolder(@NonNull View itemView) {
        super(itemView);
        channelImage = itemView.findViewById(R.id.channel_image);
        channelTitle = itemView.findViewById(R.id.channel_title);
        channelPrice = itemView.findViewById(R.id.channel_price);
    }
}
