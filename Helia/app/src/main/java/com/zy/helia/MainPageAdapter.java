package com.zy.helia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainPageAdapter extends FragmentStatePagerAdapter {
    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int sectionPosition) {
        switch (sectionPosition) {
            case 0:
                return new HomePageFragment();
            case 1:
                return new EventFragment();
            case 2:
                return new MeFragment();
                default: return new HomePageFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
