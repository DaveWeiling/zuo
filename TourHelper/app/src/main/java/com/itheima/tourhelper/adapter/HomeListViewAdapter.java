package com.itheima.tourhelper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.tourhelper.DataBean;
import com.itheima.tourhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class HomeListViewAdapter extends BaseAdapter {

    Context mContext;
    private View mView;
    private List<DataBean> list;

    public HomeListViewAdapter(Context context, List<DataBean> data) {
        mContext = context;
        list = data;
    }

    int[] ids = {R.drawable.site1, R.drawable.site2, R.drawable.site3, R.drawable.site4,
            R.drawable.site5, R.drawable.site6, R.drawable.site7};
    private ViewHolder viewHolder;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View mView = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            mView = View.inflate(mContext, R.layout.rv_home_item, null);
            viewHolder.imageView = (ImageView) mView.findViewById(R.id.iv_site);
            viewHolder.tourSite = (TextView) mView.findViewById(R.id.tour_site);
            viewHolder.tourNum = (TextView) mView.findViewById(R.id.tour_num);
            viewHolder.tourLineNum = (TextView) mView.findViewById(R.id.tour_line_num);
            viewHolder.star = (TextView) mView.findViewById(R.id.star);
            mView.setTag(viewHolder);
        } else {
            mView = convertView;
            viewHolder = (ViewHolder) mView.getTag();
        }
        viewHolder.setData(position);
        return mView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {

        TextView tourSite;
        TextView tourNum;
        TextView tourLineNum;
        ImageView imageView;
        TextView star;

        public void setData(int postiton) {
            DataBean bean = list.get(postiton);
            tourSite.setText(bean.getTourSite());
            imageView.setImageResource(ids[bean.getImage()]);
            tourNum.setText("已有" + bean.getTourNum() + "人去过");
            tourLineNum.setText("已有" + bean.getTourLineNum() + "人正在排队");
            String a = null;
            switch (bean.getStar()){
                case 0:
                    a = "A";
                    break;
                case 1:
                    a = "AA";
                    break;
                case 2:
                    a = "AAA";
                    break;
                case 3:
                    a = "AAAA";
                    break;
                case 4:
                    a = "AAAAA";
                    break;
            }
            star.setText("国家" + a +"级风景区");
        }
    }
}
