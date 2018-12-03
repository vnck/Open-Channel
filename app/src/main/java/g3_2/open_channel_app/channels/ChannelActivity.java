package g3_2.open_channel_app.channels;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import g3_2.open_channel_app.LoginActivity;
import g3_2.open_channel_app.ProfileFragment;
import g3_2.open_channel_app.R;

// UNUSED

public class ChannelActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ChannelFragment.newInstance())
                    .commitNow();
        }

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.oc_menu);


        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        Fragment selectedFragment = null;
                        switch (menuItem.getItemId()) {
                            case R.id.drawer_account:
                                selectedFragment = ProfileFragment.newInstance();
                                break;
                            case R.id.drawer_logoff:
                                startActivity(new Intent(ChannelActivity.this, LoginActivity.class));
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
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Chatbot Feature Coming Soon", Snackbar.LENGTH_LONG)
//                        .setAction("Chat", null).show();
//            }
//        });
//
//        BottomNavigationView bottomNavigationView = (BottomNavigationView)
//                findViewById(R.id.bottomnav);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener
//                (new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        Fragment selectedFragment = null;
//                        switch (item.getItemId()) {
//                            case R.id.home:
//                                selectedFragment = HomeFragment.newInstance();
//                                break;
//                            case R.id.myChannels:
//                                startActivity(new Intent(ChannelActivity.this, ChannelActivity.class));
//                                break;
//                            case R.id.allChannels:
//                                selectedFragment = AllChannelFragment.newInstance();
//                                break;
//                        }
//                        if (selectedFragment != null) {
//                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                            transaction.replace(R.id.fragmentContainer, selectedFragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
//                        }
//                        return true;
//
//                    }
//                });
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


}
