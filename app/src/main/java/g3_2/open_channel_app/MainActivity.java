package g3_2.open_channel_app;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import g3_2.open_channel_app.chatbot.MainChatbotActivity;
import g3_2.open_channel_app.network.DocNotificationEntry;

public class MainActivity extends AppCompatActivity implements IActivityPlus {

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView bottomNavigationView;

    final String TAG = "TAG";
    /**
     * Instantiate mDB hashmap
     */
    HashMap<String, Database> mDB = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSharedPreferences("pathToUser",0).getString("getPath",null) != null) {
            //TODO you need to add a listener to this
            //TODO send the hashmap to fragment
            Log.d(TAG, "getsharedpreferences success");
            getData(getSharedPreferences("pathToUser",0).getString("getPath",null));

            // print mDB
            Log.d(TAG, "size" + mDB.size());
            for (String key: mDB.keySet()){
                Log.d(TAG, "nihao" + mDB.get(key));
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "sorry bro", Toast.LENGTH_LONG).show();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.oc_menu);
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottomnav);


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

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.home:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.myChannels:
                                selectedFragment = MyChannelFragment.newInstance();
                                break;
                            case R.id.allChannels:
                                selectedFragment = AllChannelFragment.newInstance();
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

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle lol = new Bundle();
        lol.putSerializable("mDB",mDB);
        //TODO find a way to pass bundle with hashmap to fragment
        transaction.replace(R.id.fragmentContainer, HomeFragment.newInstance());
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

    /**
     * @param pathToUser
     *
     * Queries Firebase using the pathToUser input
     * After query is successful:
     *      Takes class variable mDB (instantiated above)
     *      mDB is a HashMap<String, Object>
     *          key: value
     *          profileID (ie johnny) : Database object
     *
     *      mDB can be accessed outside of this method if is refreshed
     *      use the data to access personal details
     * Database.id returns name of person
     * Database.getData returns HashMap of all personal details
     */

    @Override
    public void getData(String pathToUser) {
        FirebaseFirestore fdb = FirebaseFirestore.getInstance();
        DocumentReference profileRef = fdb.document(pathToUser);

        profileRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Database dbb = new Database(documentSnapshot);
                        Log.d(TAG, "yo, dbb " + dbb.getId()); // check if dateBase type created

                        String ds = documentSnapshot.getId();
                        Log.d(TAG, "yo, ds " + ds); // check ID

                        mDB.put(ds, dbb);
                        Log.d(TAG, "getData success");
                        Log.d(TAG, "size" + mDB.size()); // check is data has been added to mDB
                        for (String key: mDB.keySet()){
                            Log.d(TAG, "nihao" + mDB.get(key).getData()); // check mDB has been added correctly
                        }
                    }
                } else {
                    Log.d(TAG, "Get Data failed.");
                }
            }
        });
    }

//    @Override
//    public void onChildAdded(DataSnapShot snapshot, String previousChildKey){
//        String title = snapshot.child("title").getValue();
//        String channel = snapshot.child("channel").getValue();
//        String date = snapshot.child("date").getValue();
//        String url = snapshot.child("url").getValue();
//
//        DocNotificationEntry notification = new DocNotificationEntry(title, channel, date, url);
//        CardDocNotificationAdaptor.add(notification);
//    }
}
