package com.itheima.tourhelper.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.itheima.tourhelper.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/1/15.
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog =new ProgressDialog(this);
        mSharedPreferences =getSharedPreferences("user_info",MODE_PRIVATE);
        //Log.i("ddfdd", "onCreate: "+getClass().getSimpleName());
    }
    public void saveUser(String username,String pwd) {
        mSharedPreferences.edit()
                .putString("username",username)
                .putString("pwd",pwd)
                .commit();
    }
    public String getUsername(){
        return mSharedPreferences.getString("username", "");
    }

    public String getPwd(){
        return mSharedPreferences.getString("pwd", "");
    }

    public void startActivity(Intent intent, boolean isFinish) {
        if (isFinish) {
            finish();
        }
        startActivity(intent);
    }

    public  void showDialog(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();

    }

    public void dismissDialog() {
        mProgressDialog.dismiss();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public void showToast(String msg) {
        ToastUtils.showToast(this,msg);
    }
}
