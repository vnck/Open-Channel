package g3_2.open_channel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel.network.ImageRequester;
import g3_2.open_channel.network.ChannelEntry;

import java.util.List;

/**
 * Adapter used to show a simple grid of channels.
 */
public class ChannelCardRecyclerViewAdapter extends RecyclerView.Adapter<ChannelCardViewHolder> {

    private List<ChannelEntry> channelList;
    private ImageRequester imageRequester;

    public ChannelCardRecyclerViewAdapter(List<ChannelEntry> channelList) {
        this.channelList = channelList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ChannelCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.oc_channel_card, parent, false);
        return new ChannelCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelCardViewHolder holder, int position) {
        if (channelList != null && position < channelList.size()){
            ChannelEntry channel = channelList.get(position);
            holder.channelTitle.setText(channel.title);
            holder.channelPrice.setText(channel.price);
            imageRequester.setImageFromUrl(holder.channelImage, channel.url);
        }
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }
}
