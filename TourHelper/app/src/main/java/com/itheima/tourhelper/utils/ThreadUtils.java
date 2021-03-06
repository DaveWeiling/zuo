package com.itheima.tourhelper.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/26.
 */
public class ThreadUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static Executor sExecutor = Executors.newSingleThreadExecutor();

    public static void runSubThread(Runnable runnable) {
        sExecutor.execute(runnable);
    }
    public static void runMainThread(Runnable runnable) {
        sHandler.post(runnable);
    }
}
