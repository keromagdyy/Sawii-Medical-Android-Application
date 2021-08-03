package com.example.sawii;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdaptorFavorite extends FragmentPagerAdapter {


    public ViewPagerAdaptorFavorite(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FavPosts();
            case 1:
                return new FavAdvice();
            default:
                return new FavPosts();

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
                title = "Posts";
                break;
            case 1:
                title = "Advices";
                break;
        }
        return title;
    }
}
