package com.gmuthumbi.carppapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.gmuthumbi.carppapp.Fragments.account;
import com.gmuthumbi.carppapp.Fragments.explore;
import com.gmuthumbi.carppapp.Fragments.notifications;
import com.gmuthumbi.carppapp.Fragments.search;
import com.gmuthumbi.carppapp.R;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.gmuthumbi.carppapp.Activities.loginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(explore.newInstance("",""));
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        String name = mPreferences.getString("userName","");

        //checking if the user is logged in using shared preferences
        if (name.isEmpty()){
            Intent intent = new Intent(MainActivity.this,loginActivity.class);
            startActivity(intent);
        }

    }

    public void openFragment(Fragment fragment){
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_placeholder,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.explore_page:
                            openFragment(explore.newInstance("",""));
                            return true;
                        case R.id.search_page:
                            openFragment(search.newInstance("",""));
                            return true;
                        case R.id.notification_page:
                            openFragment(notifications.newInstance("",""));
                            return true;
                        case R.id.account_page:
                            openFragment(account.newInstance("",""));
                            return true;
                    }

                    return false;
                }
            };

    public void EditDetails(View view) {
        VolleyCallbacks volleyCallbacks = new VolleyCallbacks() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

            }

            @Override
            public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {

            }

            @Override
            public void onSuccess(JSONObject jsonObject, String name, String plate, Uri carImg, String carId,String userId,String mileage, String rating, String description) throws JSONException {

            }

        };


    }
}
