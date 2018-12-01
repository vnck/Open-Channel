package g3_2.open_channel_app.channels.survey;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.ChannelEntry;

public class QuestionsViewHolder extends RecyclerView.ViewHolder {

    public TextView channelTitle;

    public QuestionsViewHolder(@NonNull View itemView, Fragment fragment) {
        super(itemView);
        channelTitle = itemView.findViewById(R.id.questions);


    }
}
