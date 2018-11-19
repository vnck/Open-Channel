package g3_2.open_channel_app.channels;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.ChannelEntry;
import g3_2.open_channel_app.network.ImageRequester;

public class CardAdaptorChannel  extends RecyclerView.Adapter<CardChannelViewHolder>  {
    private List<ChannelEntry> productList;
    private ImageRequester imageRequester;

    public CardAdaptorChannel(List<ChannelEntry> productList) {
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
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

//        /**
//         * Handle click to show DetailActivity.
//         *
//         * @param view View that is clicked.
//         */
//    @Override
//    public void onClick(View view) {
//        CardChannel currentSport = mSportsData.get(getAdapterPosition());
//        Intent detailIntent = new Intent(mContext, DetailActivity.class);
//        detailIntent.putExtra("title", currentSport.getTitle());
//        detailIntent.putExtra("image_resource",
//                currentSport.getImageResource());
//        mContext.startActivity(detailIntent);
//    }


}
