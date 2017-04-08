package com.itheima.tourhelper.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.tourhelper.R;
import com.itheima.tourhelper.adapter.AniAdatpter;

public class SplashActivity extends BaseActivity {
    private LinearLayout ll_splash;
    Handler handler =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
        RotateAnimation animation =new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setFillAfter(true);

        AlphaAnimation alphaAnimation =new AlphaAnimation(0.2f,1.0f);

        alphaAnimation.setFillAfter(true);

        ScaleAnimation scaleAnimation =new ScaleAnimation(0.4f,1.0f,0.4f,1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setFillAfter(true);

        AnimationSet set =new AnimationSet(false);
        set.setDuration(1005);
        set.setFillAfter(true);
        set.addAnimation(alphaAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(animation);

        ll_splash.startAnimation(set);

        animation.setAnimationListener(new AniAdatpter(){
            @Override
            public void onAnimationEnd(Animation animation) {
//                SystemClock.sleep(2000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent =new Intent(SplashActivity.this,LoggingActivity.class);
                        startActivity(intent,true);
                    }
                },2000);

        }
        });

    }

}
