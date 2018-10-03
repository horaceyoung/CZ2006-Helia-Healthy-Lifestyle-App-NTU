package com.zy.helia;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class EventsPagerAdopter extends FragmentPagerAdapter{

    private int mNoOfTabs;

    public EventsPagerAdopter(FragmentManager fm, int NumberOfTab){
        super(fm);
        this.mNoOfTabs = NumberOfTab;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                InterestedEvents interestedEvents = new InterestedEvents();
                return interestedEvents;
            case 1:
                RegisteredEvents registeredEvents = new RegisteredEvents();
                return registeredEvents;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
