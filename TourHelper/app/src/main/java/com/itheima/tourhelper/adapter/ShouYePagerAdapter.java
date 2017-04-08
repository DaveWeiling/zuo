package com.itheima.tourhelper.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.tourhelper.R;
import com.itheima.tourhelper.ui.fragment.ShouyeFragment;

/**
 * Created by Administrator on 2017/3/17.
 */
public class ShouYePagerAdapter extends PagerAdapter {
    private int[] pids;
    public ShouYePagerAdapter( int [] ids) {
        super();
        pids =ids;
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView =new ImageView(container.getContext());
        position =position % pids.length;
        imageView.setImageResource(pids[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
