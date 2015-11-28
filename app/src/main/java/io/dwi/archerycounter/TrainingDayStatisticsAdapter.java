package io.dwi.archerycounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;

/**
 * Created by llllllllllll on 11/14/2015.
 */
public class TrainingDayStatisticsAdapter extends FragmentStatePagerAdapter {
    private Toolbar toolbar;

        public TrainingDayStatisticsAdapter(FragmentManager fragmentManager, Toolbar toolbar) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new TrainingDayStatisticsFragment();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(TrainingDayStatisticsFragment.ARG_TRAINING_DAY_INDEX, i);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }

}
