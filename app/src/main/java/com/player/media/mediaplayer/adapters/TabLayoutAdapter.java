package com.player.media.mediaplayer.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.ArrayList;

public class TabLayoutAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> listTab;
    private ArrayList<Fragment> listFrag;

    public TabLayoutAdapter(FragmentManager fm, Context context) {
        super(fm);
        listTab = new ArrayList<>();
        listFrag = new ArrayList<>();

    }

    @Override
    public Fragment getItem(int position) {

        return listFrag.get(position);
    }

    public void addViewFragment(Fragment fragment, String tabTitle){
        listFrag.add(fragment);
        listTab.add(tabTitle);
//        tabIconResources.add(tabIconResource);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listTab.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab.get(position);
    }

}
