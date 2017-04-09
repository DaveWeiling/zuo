package com.itheima.tourhelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.tourhelper.DataBean;
import com.itheima.tourhelper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TourDetailActivity extends AppCompatActivity {


    int[] ids = {R.drawable.site1, R.drawable.site2, R.drawable.site3, R.drawable.site4,
            R.drawable.site5, R.drawable.site6, R.drawable.site7};
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.id_viewpager)
    ViewPager mIdViewpager;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(R.id.tv_know)
    TextView mTvKnow;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.join)
    Button mJoin;

    List<String> abouts = new ArrayList<>();

    List<String> knows = new ArrayList<>();

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int tourLineNum = mDatabean.getTourLineNum();
            if (tourLineNum > 0) {
                tourLineNum = tourLineNum - 1;
                mDatabean.setTourLineNum(tourLineNum);
            }
            mTv.setText(tourLineNum + "");
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };
    private DataBean mDatabean;
    private TextView mTv;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        ButterKnife.bind(this);

        mTv = (TextView) findViewById(R.id.tv_tourlinenum);

        initAbouts();
        initKnows();
        Intent intent = getIntent();
        mDatabean = intent.getParcelableExtra("DATABEAN");
        mPosition = intent.getIntExtra("POSITION", -1);
        mTvTitle.setText(mDatabean.getTourSite());
        mPhone.setText("联系电话" + 12345555);
        mTvAbout.setText(abouts.get(0));
        mTvKnow.setText(knows.get(0));
        mJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TourDetailActivity.this, "加入排队", Toast.LENGTH_SHORT).show();
            }
        });
        mTv.setText(mDatabean.getTourLineNum() + "");

    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBackPressed() {
        if (mPosition != -1) {
            Intent data = new Intent();
            data.putExtra("RESULT",mDatabean);
            data.putExtra("POSITION",mPosition);
            setResult(0,data);
        }
        finish();

    }

    private void initKnows() {
        knows.add("免票政策\n" +
                "\n" +
                "a. 1.2米（不含）以下的儿童免票。\n" +
                "b. 65周岁（含）以上的老人（凭身份证有效证件）免票。\n" +
                "优惠政策\n" +
                "\n" +
                "a. 1.2米（含）～1.4米（含）的儿童享景区半价45元优惠价。\n" +
                "b. 60周岁（含）～65周岁（含）的老人（凭身份证有效证件）享景区半价45元优惠价。\n" +
                "c. 军人、学生（凭军人证、学生证等相关有效证件）享景区半价45元优惠价。\n" +
                "开具发票\n" +
                "\n" +
                "1. 请致电10106060，告知客服专员发票抬头和邮寄地址，" +
                "我司在收到完整信息后向您寄送发票。为避免因发生不可抗力或意" +
                "外事项致实际消费额与发票开具的相应数额不符，建议您在游玩归来后" +
                "两个月内索领取发票。（在线支付）");
    }

    private void initAbouts() {
        abouts.add("巨石山生态文化旅游区，位于安徽省安庆市宜秀区长江北岸的菜子湖畔，" +
                "总面积43平方公里。这里是牛郎织女爱情故事起源地，也是黄梅戏大师严凤英的家乡所在地，" +
                "景区致力于打造为高级爱情旅游新地标——“爱情朝圣地、浪漫巨石山”。" +
                "+\n" + "巨石奇峰\n" +
                "巨石山因石称奇，千姿百态，曲径通幽，别有洞天。在绿波荡漾的碧莲池畔经由神奇" +
                "的龙宫密道一路探险而上，须臾柳暗花明，至高点——龙头峰赫然在目。兀立峰顶，烟波" +
                "浩渺的菜子湖尽收眼底。"
                + "巨石秀水\n" +
                "山是水之魂，水是山之韵。巨石山" +
                "之所以这样清幽灵动，就是因为它浸润着水的清纯。它濒临菜子湖" +
                "，山环水绕，刚柔兼济。凤溪婉延曲折，流水潺潺，那是凤姑献给小龙的浅" +
                "吟低唱；碧莲池澄碧深邃，绿波盈盈，那是织女呈给牛郎的深情厚意！"
                + "白玉兰\n" +
                "巨石山作为中国野生白玉兰基地，野生玉兰节自2009年以来已连续" +
                "成功举办四届，已成为安徽旅游行业具有较高影响力的节庆活动。本次盛会为第四" +
                "届野生玉兰节，在安庆市委市政府大力支持下，由安庆市林业局、安庆市旅游局、宜" +
                "秀区政府与安徽永先集团联合主办，节庆主题为“龙年龙山龙头抬，情缘福地玉兰开”。"
                + "状元书屋\n" +
                "位于织女峰下，两块山石耸立相合，形成门洞，洞内宽阔平坦，四面石壁拥立，洞顶为一" +
                "巨石覆盖，后侧露出一片天光。洞中清爽幽静，洞外竹涛阵阵，真是读书之地。");
    }

}
