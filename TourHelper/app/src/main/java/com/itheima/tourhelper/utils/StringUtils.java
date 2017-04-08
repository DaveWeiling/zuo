package com.itheima.tourhelper.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/12/26.
 */
public class StringUtils {
    //判断用户名是否合法
    public static boolean checkUsername(String username) {
        if(TextUtils.isEmpty(username)){
            return false;
        }
        //长度为3-20位，必须以字母开头
        return username.matches("^[a-zA-Z]\\w{2,19}$");
    }
    //判断密码是否合法
    public static boolean checkPsw(String psw) {
        if(TextUtils.isEmpty(psw)){
            return false;
        }
        //长度为3-20位字母
        return psw.matches("^[0-9]{3,20}$");
    }
    public static String getFirstLetter(String contact) {
        if (TextUtils.isEmpty(contact)){
            return contact;
        }else {
            return contact.substring(0,1).toUpperCase();
        }
    }
}
