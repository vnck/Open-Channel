package g3_2.open_channel_app.notification;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import g3_2.open_channel_app.R;
import g3_2.open_channel_app.channels.ChannelActivity;
import g3_2.open_channel_app.channels.ChannelFragment;

public class CardDocNotificationViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    public TextView notificationTitle;
    public TextView notificationChannel;
    public TextView notificationDate;
    public ImageButton downloadButton;
    public String id;
    private Fragment frag;

    public CardDocNotificationViewHolder(@NonNull View itemView, Fragment fragment) {
        super(itemView);

        downloadButton = (ImageButton) itemView.findViewById(R.id.downloadDocButton);

        notificationTitle = itemView.findViewById(R.id.title);
        notificationChannel = itemView.findViewById(R.id.channel);
        notificationDate = itemView.findViewById(R.id.date);
        frag = fragment;
//        itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    Intent intent = new Intent(mContext, ChannelActivity.class);
////                if (selectedFragment != null) {
////                    FragmentTransaction transaction = frag.getActivity().getSupportFragmentManager().beginTransaction();
////                    transaction.replace(R.id.fragmentContainer, selectedFragment);
////                    transaction.addToBackStack(null);
////                    transaction.commit();
////                }
//                }
//        });
    }

    @Override
    public void onClick(View v) {

    }
}
