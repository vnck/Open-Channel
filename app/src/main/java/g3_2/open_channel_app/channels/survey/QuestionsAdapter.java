package g3_2.open_channel_app.channels.survey;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.channels.CardChannelViewHolder;
import g3_2.open_channel_app.channels.ChannelActivity;
import g3_2.open_channel_app.network.ChannelEntry;
import g3_2.open_channel_app.network.ImageRequester;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder>  {
    private List<ChannelEntry> productList;
    private ImageRequester imageRequester;
    private Fragment fragment;
    private Context mContext;

    public QuestionsAdapter(Context context, List<ChannelEntry> productList, Fragment fragment) {
        this.productList = productList;
        this.fragment = fragment;
        imageRequester = ImageRequester.getInstance();
        mContext = context;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_question_card, parent, false);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(mContext, ChannelActivity.class);
                mContext.startActivity(intent);
            }
        });
        return new QuestionsViewHolder(layoutView, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ChannelEntry channel = productList.get(position);
            holder.channelTitle.setText(channel.title);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
