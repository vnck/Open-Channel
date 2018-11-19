package g3_2.open_channel_app.notification;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import g3_2.open_channel_app.R;

public class CardNotificationViewHolder extends RecyclerView.ViewHolder {

    public TextView notificationTitle;
    public TextView notificationChannel;
    public TextView notificationDate;

    public CardNotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        notificationTitle = itemView.findViewById(R.id.title);
        notificationChannel = itemView.findViewById(R.id.channel);
        notificationDate = itemView.findViewById(R.id.date);
    }
}
