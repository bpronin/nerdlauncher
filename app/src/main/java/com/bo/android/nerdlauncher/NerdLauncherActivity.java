package com.bo.android.nerdlauncher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class NerdLauncherActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewPager pager = new ViewPager(this);
        pager.setId(R.id.crime_view_pager);

        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new NerdLauncherFragment();
                } else {
                    return new NerdLauncherRunningFragment();
                }
            }

        });

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            public void onPageSelected(int position) {
                updateTitle(position);
            }
        });

        setContentView(pager);
        updateTitle(0);
    }

    private void updateTitle(int position) {
        String title = getString(R.string.app_name) + " [";
        if (position == 0) {
            title += getString(R.string.all_apps);
        } else {
            title += getString(R.string.running_apps);
        }
        title += "]";
        setTitle(title);
    }

}
