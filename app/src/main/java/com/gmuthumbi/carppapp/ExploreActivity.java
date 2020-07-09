package com.gmuthumbi.carppapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        //Inflate the toolBar
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        //creates an instance of a tab layout and adds the tabs
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_3));
        //set the tab to fill the layout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //use the pager adapter to manage screens
        //create an instance of the view pager
        final ViewPager viewPager = findViewById(R.id.view_pager);
        //create an instance of the pagerAdapter
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        //set the adapter to the view pager
        viewPager.setAdapter(pagerAdapter);
        //set the listener for clicks
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
