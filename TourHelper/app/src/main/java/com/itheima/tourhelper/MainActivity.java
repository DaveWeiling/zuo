package com.itheima.tourhelper;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.itheima.tourhelper.adapter.MainVpAdapter;
import com.itheima.tourhelper.adapter.MyPageChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.shouye)
    ImageView mShouye;
    @BindView(R.id.local)
    ImageView mLocal;
    @BindView(R.id.service)
    ImageView mService;
    @BindView(R.id.my)
    ImageView mMy;

    public List<DataBean> mList;
    String[] titles = {"巨石山", "西湖", "黄山", "天柱山", "野生动物园", "西溪湿地", "天安门广场"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //填充轮播图数据
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            int site = r.nextInt(7);
            int num = r.nextInt(100000);
            int currentNum = r.nextInt(300) + 200;
            int star = r.nextInt(5);
            DataBean bean = new DataBean(titles[site], num, currentNum,star, site);
            mList.add(bean);
        }
        //用ViewPager填充四个界面
        mVp.setAdapter(new MainVpAdapter(getSupportFragmentManager()));
        //把首页作为默认的选择状态
        mVp.setCurrentItem(0);
        mShouye.setSelected(true);

        mVp.setOnPageChangeListener(new MyPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        selectHome();
                        break;
                    case 1:
                        selectNearby();
                        break;
                    case 2:
                        selectServer();
                        break;
                    case 3:
                        selectMine();
                        break;
                }
            }
        });
    }

    @OnClick({R.id.shouye, R.id.local, R.id.service, R.id.my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shouye:
                selectHome();
                break;
            case R.id.local:
                selectNearby();
                break;
            case R.id.service:
                selectServer();
                break;
            case R.id.my:
                selectMine();
                break;
        }
    }

    private void cancalSeletedState() {
        mShouye.setSelected(false);
        mLocal.setSelected(false);
        mService.setSelected(false);
        mMy.setSelected(false);
    }

    private void selectMine() {
        cancalSeletedState();
        mMy.setSelected(true);
        mVp.setCurrentItem(3);
    }

    private void selectServer() {
        cancalSeletedState();
        mService.setSelected(true);
        mVp.setCurrentItem(2);
    }

    private void selectNearby() {
        cancalSeletedState();
        mLocal.setSelected(true);
        mVp.setCurrentItem(1);
    }

    private void selectHome() {
        cancalSeletedState();
        mShouye.setSelected(true);
        mVp.setCurrentItem(0);
    }
}
