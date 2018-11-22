package g3_2.open_channel_app.channels;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.ChannelEntry;
import g3_2.open_channel_app.network.ImageRequester;

public class CardChannelAdapter extends RecyclerView.Adapter<CardChannelViewHolder>  {
    private List<ChannelEntry> productList;
    private ImageRequester imageRequester;
    Button button;

    public CardChannelAdapter(List<ChannelEntry> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public CardChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_channel, parent, false);
        return new CardChannelViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardChannelViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ChannelEntry channel = productList.get(position);
            holder.channelTitle.setText(channel.title);
            holder.channelOrganisation.setText(channel.organisation);
            holder.channelDescription.setText(channel.description);
            imageRequester.setImageFromUrl(holder.channelImage, channel.url);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Channel Feature Coming Soon", Snackbar.LENGTH_LONG)
                            .setAction("Channel", null).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
