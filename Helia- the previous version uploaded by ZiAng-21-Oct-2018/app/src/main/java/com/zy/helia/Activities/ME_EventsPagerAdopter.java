package com.zy.helia.Activities;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zy.helia.MeIrInterested;
import com.zy.helia.MeIrRegistered;


public class ME_EventsPagerAdopter extends FragmentPagerAdapter{

        private int mNoOfTabs;

        public ME_EventsPagerAdopter(FragmentManager fm, int NumberOfTab){
            super(fm);
            this.mNoOfTabs = NumberOfTab;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    MeIrInterested interestedEvents = new MeIrInterested();
                    return interestedEvents;
                case 1:
                    MeIrRegistered registeredEvents = new MeIrRegistered();
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

