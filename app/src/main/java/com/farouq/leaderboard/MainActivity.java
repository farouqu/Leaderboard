package com.farouq.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.farouq.leaderboard.adapters.ListsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up view pager
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentPagerAdapter viewPagerAdapter = new ListsPagerAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        //Set up Tabs with view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        Button submitButton = (Button) findViewById(R.id.submit_btn);
        submitButton.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this,SubmitActivity.class);
            startActivity(intent);
        }));
    }
}
