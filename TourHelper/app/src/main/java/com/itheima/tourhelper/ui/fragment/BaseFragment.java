package com.itheima.tourhelper.ui.fragment;

import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/1/16.
 */
public class BaseFragment extends Fragment {


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
//        MobclickAgent.onPageStart("MainScreen");
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
       // MobclickAgent.onPageEnd("MainScreen");
    }
}
