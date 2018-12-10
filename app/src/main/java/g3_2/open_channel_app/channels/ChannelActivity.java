package g3_2.open_channel_app.channels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;

import g3_2.open_channel_app.R;

import static android.support.constraint.Constraints.TAG;

// UNUSED

public class ChannelActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FirebaseFirestore firestoreDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        Log.d(TAG, "Line 36");
        Bundle details = getIntent().getExtras();
//        String id = getIntent().getStringExtra("id");
        Log.d(TAG, details.toString());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ChannelFragment(details))
                    .commitNow();
        }

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Chatbot Feature Coming Soon", Snackbar.LENGTH_LONG)
//                        .setAction("Chat", null).show();
//            }
//        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadIntent(Intent intent){
        startActivity(intent);
    }


}
