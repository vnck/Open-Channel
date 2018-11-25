package g3_2.open_channel_app.channels;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import g3_2.open_channel_app.MainActivity;
import g3_2.open_channel_app.R;

public class ChannelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ChannelFragment.newInstance())
                    .commitNow();
        }
    }

}
