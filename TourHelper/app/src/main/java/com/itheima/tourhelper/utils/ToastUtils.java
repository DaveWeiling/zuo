package com.itheima.tourhelper.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/26.
 */
public class ToastUtils {
    private static Toast sToast;
    public static void showToast(Context context, String msg) {
        if (sToast ==null) {
            sToast = Toast.makeText(context.getApplicationContext(),msg, Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.show();
    }

}
