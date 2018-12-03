package g3_2.open_channel_app.profile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import g3_2.open_channel_app.R;

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView profilePic;
    public TextView firstname;
    public TextView lastname;
    public TextView dob;
    public TextView address;
    public TextView postalcode;
    public TextView hpnumber;
    public TextView homenumber;
    public TextView nric;
    public TextView email;


    public ProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        profilePic = itemView.findViewById(R.id.user_profile);
        firstname = itemView.findViewById(R.id.user_first_name);
        lastname = itemView.findViewById(R.id.user_last_name);
        address = itemView.findViewById(R.id.user_address);
        dob = itemView.findViewById(R.id.user_dob);
        postalcode = itemView.findViewById(R.id.user_postal_code);
        hpnumber = itemView.findViewById(R.id.user_hp_no);
        homenumber = itemView.findViewById(R.id.user_home_no);
        nric = itemView.findViewById(R.id.user_nric);
        email = itemView.findViewById(R.id.user_email);
    }
}
