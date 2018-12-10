package g3_2.open_channel_app.notification;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.channels.survey.SurveyActivity;
import g3_2.open_channel_app.network.ActionNotificationEntry;

public class CardActionNotificationAdaptor extends RecyclerView.Adapter<CardActionNotificationViewHolder>  {
    private List<ActionNotificationEntry> list;
    private Fragment fragment;
    private Context mContext;

    public CardActionNotificationAdaptor(Context context,List<ActionNotificationEntry> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
        mContext = context;

    }

    @NonNull
    @Override
    public CardActionNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_action_notification, parent, false);
        return new CardActionNotificationViewHolder(layoutView, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull CardActionNotificationViewHolder holder, int position) {
        if (list != null && position < list.size()) {
            ActionNotificationEntry notification = list.get(position);
            holder.notificationTitle.setText(notification.title);
            holder.notificationChannel.setText(notification.channel);
            holder.notificationDate.setText(notification.date);
            holder.notificationDue.setText(notification.due);
            holder.id = notification.id;

            setOnClick(holder.downloadButton);
        }
    }

    private void setOnClick(final ImageButton btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: download function
                Intent intent = new Intent(fragment.getContext(), SurveyActivity.class);
                fragment.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}
