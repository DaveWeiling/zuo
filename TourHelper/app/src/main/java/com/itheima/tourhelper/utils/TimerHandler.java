package com.itheima.tourhelper.utils;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2017/4/8.
 */
public class TimerHandler extends Handler {
    private int count = 0;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (count > Integer.MAX_VALUE) {
            count = 0;
        }

        int time = msg.what;
        this.sendEmptyMessageAtTime(time, time);
        mTimeCallback.onTimeUp(count++);
    }

    public interface TimeCallback {
        void onTimeUp(int count);
    }

    public TimeCallback mTimeCallback;

    public TimerHandler(TimeCallback timeCallback) {
        mTimeCallback = timeCallback;
    }

}
