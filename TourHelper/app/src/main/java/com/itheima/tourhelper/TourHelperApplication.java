package com.itheima.tourhelper;

import android.app.Application;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/1/14.
 */
public class TourHelperApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,"3MDgCeXwxu8diDUXrEVSkBUn-gzGzoHsz","6asp6CBLEKN2jCFoYyMns11H");

        //友盟统计,禁止默认的页面统计
        MobclickAgent.openActivityDurationTrack(false);

    }
}
