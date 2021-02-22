package com.jemplex.jemplex;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AssetTaggingAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public AssetTaggingAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ScanFragment scanFragment = new ScanFragment();
                return scanFragment;
            case 1:
                StatusInfoFragment statusInfoFragment = new StatusInfoFragment();
                return statusInfoFragment;
            case 2:
                ClassificationFragment classificationFragment = new ClassificationFragment();
                return classificationFragment;
            case 3:
                LocationFragment locationFragment = new LocationFragment();
                return locationFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
