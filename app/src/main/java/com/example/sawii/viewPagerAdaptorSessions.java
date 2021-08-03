package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerAdaptorSessions extends FragmentPagerAdapter {

    public viewPagerAdaptorSessions(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new finishedSessions();

            case 1:
                return new upcommingSessions();

            default:
                return new finishedSessions();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";
        switch (position){
            case 0:
                title = "Finished";
                break;
            case 1:
                title = "Upcomming";
                break;
        }

        return title;
    }
}
