package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerAdaptorSessionsDoctor extends FragmentPagerAdapter {
    public viewPagerAdaptorSessionsDoctor(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new finishedSessionsDoctor();

            case 1:
                return new upcommingSessionsDoctor();

            default:
                return new finishedSessionsDoctor();
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
