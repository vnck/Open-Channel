package g3_2.open_channel_app.notification;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
    private Fragment fragment;
    private Context mContext;

    public CardDocNotificationAdaptor(Context context, List<DocNotificationEntry> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
        mContext = context;

    }

    @NonNull
    @Override
    public CardDocNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doc_notification, parent, false);
//        layoutView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                Intent intent = new Intent(mContext, ChannelActivity.class);
//                mContext.startActivity(intent);
//            }
//        });
        return new CardDocNotificationViewHolder(layoutView, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDocNotificationViewHolder holder, int position) {
        if (list != null && position < list.size()) {
            DocNotificationEntry notification = list.get(position);
            holder.notificationTitle.setText(notification.title);
            holder.notificationChannel.setText(notification.channel);
            holder.notificationDate.setText(notification.date);
            holder.id = notification.id;

            setOnClick(holder.downloadButton, notification.url);
        }
    }

    private void setOnClick(final ImageButton btn, final String url){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: get url and replace
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                v.getContext().startActivity(browserIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}
