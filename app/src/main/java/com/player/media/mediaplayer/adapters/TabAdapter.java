package com.player.media.mediaplayer.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.player.media.mediaplayer.R;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> listFragment;

    private List<String> tabTitles;
    private List<Integer> tabIconResources;

    private ImageView imgTabIcon;
    private TextView tvTabTitle;



    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        listFragment = new ArrayList<>();
        tabTitles = new ArrayList<>();
        tabIconResources = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    public void addViewFragment(Fragment fragment, String tabTitle){
        listFragment.add(fragment);
        tabTitles.add(tabTitle);
//        tabIconResources.add(tabIconResource);
        notifyDataSetChanged();
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item, null);
//        imgTabIcon = (ImageView) view.findViewById(R.id.img_tab_icon);
        tvTabTitle = (TextView) view.findViewById(R.id.tv_tab_title);
//        imgTabIcon.setImageResource(tabIconResources.get(position));
        if(position==0){
            tvTabTitle.setTextColor(Color.parseColor("#fdf9f8"));
        }
        tvTabTitle.setText(tabTitles.get(position));
        return view;
    }

}
