package g3_2.open_channel_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import g3_2.open_channel_app.chatbot.MainChatbotActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView bottomNavigationView;

    private FirebaseFirestore firestoreDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        firestoreDB = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.oc_menu);
        bottomNavigationView = findViewById(R.id.bottomnav);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // close drawer when item is tapped
                mDrawerLayout.closeDrawers();

                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                case R.id.drawer_account:
                    selectedFragment = ProfileFragment.newInstance();
                    bottomNavigationView.getMenu().findItem(R.id.home).setChecked(false);
                    bottomNavigationView.getMenu().findItem(R.id.myChannels).setChecked(false);
                    bottomNavigationView.getMenu().findItem(R.id.allChannels).setChecked(false);
                    break;
                case R.id.drawer_logoff:
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    break;
                }
                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, selectedFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here

                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainChatbotActivity.class));
                bottomNavigationView.getMenu().findItem(R.id.home).setChecked(false);
                bottomNavigationView.getMenu().findItem(R.id.myChannels).setChecked(false);
                bottomNavigationView.getMenu().findItem(R.id.allChannels).setChecked(false);

            }
        });

        bottomNavigationView
                .setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                        case R.id.home:
                            Query queryAct = firestoreDB.collection("actionentry").limit(10);
                            Query queryDoc = firestoreDB.collection("documententry").limit(10);
                            selectedFragment = HomeFragment.newInstance(queryAct, queryDoc);
                            break;
                        case R.id.myChannels:
                            Query queryMyChannel = firestoreDB.collection("channel").limit(10);
                            selectedFragment = MyChannelFragment.newInstance(queryMyChannel);
                            break;
                        case R.id.allChannels:
                            Query queryAllChannel = firestoreDB.collection("channel").limit(10);
                            selectedFragment = AllChannelFragment.newInstance(queryAllChannel);
                            break;
                        }
                        if (selectedFragment != null) {
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragmentContainer, selectedFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        return true;

                    }
                });

        // Manually displaying the first fragment - one time only
        Query queryAct = firestoreDB.collection("actionentry").limit(10);
        Query queryDoc = firestoreDB.collection("documententry").limit(10);
        Fragment frag = HomeFragment.newInstance(queryAct, queryDoc);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, frag);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // @Override
    // public void onChildAdded(DataSnapShot snapshot, String previousChildKey){
    // String title = snapshot.child("title").getValue();
    // String channel = snapshot.child("channel").getValue();
    // String date = snapshot.child("date").getValue();
    // String url = snapshot.child("url").getValue();
    //
    // DocNotificationEntry notification = new DocNotificationEntry(title, channel,
    // date, url);
    // CardDocNotificationAdaptor.add(notification);
    // }
}
