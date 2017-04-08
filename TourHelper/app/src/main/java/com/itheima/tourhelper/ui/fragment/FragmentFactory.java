package com.itheima.tourhelper.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/2/4.
 */
public class FragmentFactory extends BaseFragment {
    private static ShouyeFragment sShouyeFragment;
    private static LocalFragment sLocalFragment;
    private static SeriverFragment sSeriverFragment;
    private static MyFragment sMyFragment;
    public static Fragment getFragment(int position) {
        BaseFragment baseFragment =null;
        switch (position) {
            case 0:
                if (sShouyeFragment ==null) {
                    sShouyeFragment =new ShouyeFragment();
                }
                baseFragment =sShouyeFragment;
                break;
            case 1:
                if (sLocalFragment ==null) {
                    sLocalFragment =new LocalFragment();
                }
                baseFragment =sLocalFragment;
                break;
            case 2:
                if (sSeriverFragment ==null) {
                    sSeriverFragment =new SeriverFragment();
                }
                baseFragment =sSeriverFragment;
                break;
            case 3:
                if (sMyFragment ==null) {
                    sMyFragment =new MyFragment();
                }
                baseFragment =sMyFragment;
                break;

        }
        return baseFragment;
    }
}
