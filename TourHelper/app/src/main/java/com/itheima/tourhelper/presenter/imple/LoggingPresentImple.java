package com.itheima.tourhelper.presenter.imple;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.itheima.tourhelper.presenter.LogingPresenter;
import com.itheima.tourhelper.ui.activity.LogingView;

/**
 * Created by Administrator on 2017/1/15.
 */
public class LoggingPresentImple implements LogingPresenter {
    private LogingView mLogingView;

    public LoggingPresentImple(LogingView mLogingView) {
        this.mLogingView = mLogingView;
    }

    @Override
    public void logining(String uesername, final String pwd) {
        Thread.currentThread().getName();
        AVUser.logInInBackground(uesername, pwd, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e==null) {
                    mLogingView .onLoging(avUser.getUsername(),pwd,true,null);
                }else {
                    mLogingView.onLoging(avUser.getUsername(),pwd,false,e.getMessage());
                }
            }
        });
    }
}
