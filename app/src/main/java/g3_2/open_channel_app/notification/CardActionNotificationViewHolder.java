package g3_2.open_channel_app.notification;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import g3_2.open_channel_app.R;

public class CardActionNotificationViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    public TextView notificationTitle;
    public TextView notificationChannel;
    public TextView notificationDate;
    public TextView notificationDue;
    public ImageButton downloadButton;

    public CardActionNotificationViewHolder(@NonNull View itemView) {
        super(itemView);

        downloadButton = (ImageButton) itemView.findViewById(R.id.downloadDocButton);

        notificationTitle = itemView.findViewById(R.id.title);
        notificationChannel = itemView.findViewById(R.id.channel);
        notificationDate = itemView.findViewById(R.id.date);
        notificationDue = itemView.findViewById(R.id.due);
    }

    @Override
    public void onClick(View v) {

    }
}
