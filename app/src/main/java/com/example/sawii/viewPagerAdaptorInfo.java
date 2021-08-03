package com.example.sawii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerAdaptorInfo extends FragmentPagerAdapter {

    public viewPagerAdaptorInfo(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new page1_info();

            case 1:
                return new page2_info();

            case 2:
                return new page3_info();

            default:
                return new page1_info();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
