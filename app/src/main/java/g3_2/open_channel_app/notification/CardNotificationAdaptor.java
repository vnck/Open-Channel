package g3_2.open_channel_app.notification;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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
