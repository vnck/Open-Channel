package g3_2.open_channel_app.notification;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.network.DocNotificationEntry;

public class CardDocNotificationAdaptor extends RecyclerView.Adapter<CardDocNotificationViewHolder>  {
    private List<DocNotificationEntry> list;

    public CardDocNotificationAdaptor(List<DocNotificationEntry> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CardDocNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doc_notification, parent, false);
        return new CardDocNotificationViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDocNotificationViewHolder holder, int position) {
        if (list != null && position < list.size()) {
            DocNotificationEntry notification = list.get(position);
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
                Snackbar.make(v, "Document Download Feature Coming Soon", Snackbar.LENGTH_LONG)
                        .setAction("Document Download", null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}
