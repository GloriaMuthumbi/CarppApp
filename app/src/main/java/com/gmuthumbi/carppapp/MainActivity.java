package com.gmuthumbi.carppapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.gmuthumbi.carppapp.Fragments.account;
import com.gmuthumbi.carppapp.Fragments.explore;
import com.gmuthumbi.carppapp.Fragments.notifications;
import com.gmuthumbi.carppapp.Fragments.search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(explore.newInstance("",""));

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

}
