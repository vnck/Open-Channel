package g3_2.open_channel_app.notification;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.NotificationEntry;

public class CardNotificationAdaptor extends RecyclerView.Adapter<CardNotificationViewHolder>  {
    private List<NotificationEntry> list;

    public CardNotificationAdaptor(List<NotificationEntry> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CardNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doc_notification, parent, false);
        return new CardNotificationViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardNotificationViewHolder holder, int position) {
        if (list != null && position < list.size()) {
            NotificationEntry notification = list.get(position);
            holder.notificationTitle.setText(notification.title);
            holder.notificationChannel.setText(notification.channel);
            holder.notificationDate.setText(notification.date);

            setOnClick(holder.downloadButton, notification.url);
        }
    }

    private void setOnClick(final ImageButton btn, final String url){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: download function
                Toast.makeText(v.getContext(), url, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}
