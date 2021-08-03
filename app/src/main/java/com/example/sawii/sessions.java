package com.example.sawii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class sessions extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sessions");


        tabLayout = findViewById(R.id.tab_bar);
        viewPager = findViewById(R.id.view_pager);

        viewPagerAdaptorSessions adaptorSessions = new viewPagerAdaptorSessions(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdaptorSessionsDoctor adaptorSessionsDoctor = new viewPagerAdaptorSessionsDoctor(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        checkingIdentification ch = new checkingIdentification();

        if (ch.name.equals("doctor")){
            viewPager.setAdapter(adaptorSessionsDoctor);
        } else {
            viewPager.setAdapter(adaptorSessions);
        }

        tabLayout.setupWithViewPager(viewPager);
    }
}