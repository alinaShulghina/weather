package com.example.alin_.weatherforecast.view.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.alin_.weatherforecast.R;
import com.example.alin_.weatherforecast.view.adapters.ViewPagerAdapter;
import com.example.alin_.weatherforecast.view.fragments.SavedCitiesFragment;
import com.example.alin_.weatherforecast.view.fragments.SearchFragment;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SearchFragment.newInstance(), "Search");
        adapter.addFragment(SavedCitiesFragment.newInstance(), "My Cities");
        viewPager.setAdapter(adapter);
    }

}


