package com.zy.helia.Activities;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zy.helia.ME_IR_Interested;
import com.zy.helia.ME_IR_Registered;

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
                    ME_IR_Interested interestedEvents = new ME_IR_Interested();
                    return interestedEvents;
                case 1:
                    ME_IR_Registered registeredEvents = new ME_IR_Registered();
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

