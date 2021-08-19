package com.example.motorshop.activity.warranty.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.motorshop.activity.warranty.fragment.ChooseSPFragment;
import com.example.motorshop.activity.warranty.fragment.ReviewInfoProductOfYouFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChooseSPFragment();
            case 1:
                return new ReviewInfoProductOfYouFragment();
            default:
                return new ChooseSPFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                return "Chọn xe";
            case 1:
                return "Xem lại";
            default:
                return "Chọn xe";
        }
    }
}
