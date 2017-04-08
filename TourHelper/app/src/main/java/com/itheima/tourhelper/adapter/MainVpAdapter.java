package com.itheima.tourhelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itheima.tourhelper.ui.fragment.FragmentFactory;

/**
 * Created by Administrator on 2017/4/6.
 */
public class MainVpAdapter extends FragmentPagerAdapter {
    public MainVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
