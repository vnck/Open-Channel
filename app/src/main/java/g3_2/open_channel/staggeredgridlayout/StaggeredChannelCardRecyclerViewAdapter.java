package g3_2.open_channel.staggeredgridlayout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel.R;
import g3_2.open_channel.network.ImageRequester;
import g3_2.open_channel.network.ChannelEntry;

import java.util.List;

/**
 * Adapter used to show an asymmetric grid of channels, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
public class StaggeredChannelCardRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredChannelCardViewHolder> {

    private List<ChannelEntry> channelList;
    private ImageRequester imageRequester;

    public StaggeredChannelCardRecyclerViewAdapter(List<ChannelEntry> channelList) {
        this.channelList = channelList;
        imageRequester = ImageRequester.getInstance();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @NonNull
    @Override
    public StaggeredChannelCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.oc_staggered_channel_card_first;
        if (viewType == 1) {
            layoutId = R.layout.oc_staggered_channel_card_second;
        } else if (viewType == 2) {
            layoutId = R.layout.oc_staggered_channel_card_third;
        }

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new StaggeredChannelCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredChannelCardViewHolder holder, int position) {
        if (channelList != null && position < channelList.size()) {
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
