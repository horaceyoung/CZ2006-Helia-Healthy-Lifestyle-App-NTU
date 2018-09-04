package com.zy.helia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPageAdapter extends FragmentPagerAdapter {

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int sectionPosition) {
        if (sectionPosition == 0) {
            return new HomePageFragment();
        } else {
            return new DiscorverFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
