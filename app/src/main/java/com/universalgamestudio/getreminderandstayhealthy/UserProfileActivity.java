package com.universalgamestudio.getreminderandstayhealthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserProfileActivity extends AppCompatActivity {
    // Creating button.
    Button logout ;

    // Creating TextView.
    TextView userEmailShow ;

    // Creating FirebaseAuth.
    FirebaseAuth firebaseAuth ;

    // Creating FirebaseAuth.
    FirebaseUser firebaseUser;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("alarm");
        // Assigning ID's to button and TextView.

        //userEmailShow = (TextView)findViewById(R.id.user_email);

// Adding FirebaseAuth instance to FirebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();

        // On activity start check whether there is user previously logged in or not.
        if(firebaseAuth.getCurrentUser() == null){

            // Finishing current Profile activity.
            finish();

            // If user already not log in then Redirect to LoginActivity .
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            startActivity(intent);

            // Showing toast message.
            Toast.makeText(UserProfileActivity.this, "Please Log in to continue", Toast.LENGTH_LONG).show();

        }
        firebaseUser = firebaseAuth.getCurrentUser();
        //userEmailShow.setText("Successfully Logged In, Your Email = " + firebaseUser.getEmail());
        // Adding click listener on logout button.
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // Destroying login season.
//                firebaseAuth.signOut();
//
//                // Finishing current User Profile activity.
//                finish();
//
//                // Redirect to Login Activity after click on logout button.
//                Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
//                startActivity(intent);
//
//                // Showing toast message on logout.
//                Toast.makeText(UserProfileActivity.this, "Logged Out Successfully.", Toast.LENGTH_LONG).show();
//
//            }
//        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.alarm:
                    toolbar.setTitle("Alarm");
//                    fragment = new AlarmFragment();
//                    loadFragment(fragment);
                    Intent menuIntent = new Intent(UserProfileActivity.this, alarmactivity.class);
                    startActivity(menuIntent);
                    return true;


                case R.id.trace:

                    toolbar.setTitle("Trace Record");
                    fragment = new TraceFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.more:
                    toolbar.setTitle("More");
                    fragment = new MoreFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(UserProfileActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            // Destroying login season.
            firebaseAuth.signOut();

            // Finishing current User Profile activity.
            finish();

            // Redirect to Login Activity after click on logout button.
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            startActivity(intent);

            // Showing toast message on logout.
            Toast.makeText(UserProfileActivity.this, "Logged Out Successfully.", Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
