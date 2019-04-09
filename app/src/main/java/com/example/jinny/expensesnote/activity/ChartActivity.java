package com.example.jinny.expensesnote.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.jinny.expensesnote.R;
import com.example.jinny.expensesnote.fragment.ChartFragement;
import com.google.android.material.tabs.TabLayout;

public class ChartActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 2;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tlChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tlChart = findViewById(R.id.tl_chart);
        tlChart.setupWithViewPager(viewPager);
        tlChart.getTabAt(0).setIcon(R.drawable.ic_woman);
        tlChart.getTabAt(1).setIcon(R.drawable.ic_male);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            ChartFragement chartFragement = new ChartFragement();
            Bundle bundle = new Bundle();
            if (position == 0)
                bundle.putBoolean("sex", false);
            else
                bundle.putBoolean("sex", true);
            chartFragement.setArguments(bundle);
            return chartFragement;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
