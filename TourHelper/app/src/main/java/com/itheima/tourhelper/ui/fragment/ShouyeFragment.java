package com.itheima.tourhelper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima.tourhelper.DataBean;
import com.itheima.tourhelper.MainActivity;
import com.itheima.tourhelper.R;
import com.itheima.tourhelper.adapter.HomeListViewAdapter;
import com.itheima.tourhelper.adapter.PagerSelectedAdapter;
import com.itheima.tourhelper.adapter.ShouYePagerAdapter;
import com.itheima.tourhelper.ui.activity.TourDetailActivity;
import com.itheima.tourhelper.utils.CommentUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouyeFragment extends BaseFragment {


    int[] ids = {R.drawable.site1, R.drawable.site2, R.drawable.site3, R.drawable.site4,
            R.drawable.site5, R.drawable.site6, R.drawable.site7};

    @BindView(R.id.lv_shouye)
    ListView rvShouye;
    ViewPager vpTour;
    LinearLayout llPointContainer;

    private int[] pagerPics = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
    private int prePostion = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            vpTour.setCurrentItem(vpTour.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(0, 3000);
        }
    };
    private View mHeaderView;
    private List<DataBean> mList;
    private HomeListViewAdapter mAdapter;
    private PtrFrameLayout mPtrFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptr);

        MainActivity ac = (MainActivity) getActivity();
        mList = ac.mList;

        ButterKnife.bind(this, view);
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, 30, 0, 30);
        header.setTextColor(0xffff0000);
        header.initWithString("TOUR HELPER");

        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setPtrHandler(
                new PtrHandler() {
                    @Override
                    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                    }

                    @Override
                    public void onRefreshBegin(PtrFrameLayout frame) {
                        mPtrFrameLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mPtrFrameLayout.refreshComplete();
                            }
                        }, 1500);
                    }
                });
        initData();
        return view;
    }

    public boolean isAdded = false;

    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.layout_con, null);
        vpTour = (ViewPager) mHeaderView.findViewById(R.id.vp_tour);
        llPointContainer = (LinearLayout) mHeaderView.findViewById(R.id.ll_point_container);
        //轮播图
        vpTour.setAdapter(new ShouYePagerAdapter(pagerPics));
        vpTour.addOnPageChangeListener(new PagerSelectedAdapter() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                llPointContainer.getChildAt(prePostion).setEnabled(false);
                llPointContainer.getChildAt(position % pagerPics.length).setEnabled(true);
                prePostion = position % pagerPics.length;
            }
        });
    }

    public void initData() {
        initHeaderView();

        initLlPoint(pagerPics.length, llPointContainer);
        handler.sendEmptyMessageDelayed(0, 3000);
        rvShouye.addHeaderView(mHeaderView);

        //填充RecycleView布局
        mAdapter = new HomeListViewAdapter(getContext(), mList);
        rvShouye.setAdapter(mAdapter);
        rvShouye.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int truePosition = position - rvShouye.getHeaderViewsCount();
                DataBean bean = mList.get(truePosition);
                Toast.makeText(getContext(), bean.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), TourDetailActivity.class);
                intent.putExtra("DATABEAN", bean);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isAdded = false;
        mList = null;
    }

    private void initLlPoint(int size, LinearLayout ll) {
        LinearLayout.LayoutParams params;
        ImageView v;
        for (int i = 0; i < size; i++) {
            v = new ImageView(getContext());
            v.setBackgroundResource(R.drawable.point_selector);
            int px = CommentUtils.dip2px(getContext(), 10);
            params = new LinearLayout.LayoutParams(px, px);
            if (i != 0) {
                params.leftMargin = px;
                v.setEnabled(false);
            }
            v.setLayoutParams(params);
            ll.addView(v);
        }
    }
}
