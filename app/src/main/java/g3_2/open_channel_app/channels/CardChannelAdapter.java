package g3_2.open_channel_app.channels;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.ChannelEntry;
import g3_2.open_channel_app.network.ImageRequester;

public class CardChannelAdapter extends RecyclerView.Adapter<CardChannelViewHolder>  {
    private List<ChannelEntry> productList;
    private ImageRequester imageRequester;
    private Fragment fragment;
    private Context mContext;

    public CardChannelAdapter(Context context,List<ChannelEntry> productList, Fragment fragment) {
        this.productList = productList;
        this.fragment = fragment;
        imageRequester = ImageRequester.getInstance();
        mContext = context;
    }

    @NonNull
    @Override
    public CardChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_channel, parent, false);
        return new CardChannelViewHolder(layoutView, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull CardChannelViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            final ChannelEntry channel = productList.get(position);
            holder.channelTitle.setText(channel.title);
            holder.channelOrganisation.setText(channel.organisation);
            holder.channelDescription.setText(channel.description);
            imageRequester.setImageFromUrl(holder.channelImage, channel.url);

            if (channel.subscribers.contains("user0")){
                holder.button.setText("following");
            }

            final String id = channel.id;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ChannelActivity.class);
                    intent.putExtra("Id",channel.id);
                    mContext.startActivity(intent);
                }});
            holder.button.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    Fragment selectedFragment = ChannelFragment.newInstance(id);
                    FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, selectedFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
            holder.currentItem = productList.get(position);
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
