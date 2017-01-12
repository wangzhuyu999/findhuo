package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.findcar.findcar_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryStationBean;

import java.util.List;

/**
 * Created by  yxiaomin on 2017/1/4 0004.
 */

public class FindWorkHotTypeTaxiDetailLVAdapter extends BaseAdapter{

    private Context mContext;
    private LotteryStationBean stationBean;
    private List<FindWorkHotTypeTaxiDetailLVBean> datas;


    public FindWorkHotTypeTaxiDetailLVAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(List<FindWorkHotTypeTaxiDetailLVBean> datas) {
        this.datas = datas;
    }

    public void setStationBean(LotteryStationBean stationBean) {
        this.stationBean = stationBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       ViewHolderT viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_findwork_hot_type_taxi_detail_lv_item, parent, false);
            viewHolder = new ViewHolderT(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderT) convertView.getTag();
        }

        viewHolder.tvName.setText(datas.get(position).getName());
        viewHolder.tvAddr.setText(datas.get(position).getAddr());
        viewHolder.tvTime.setText(datas.get(position).getTime());
        return convertView;

    }

    public class ViewHolderT {
        private TextView tvName,tvAddr,tvTime;
        private ImageView ivImg;
        private RelativeLayout ll;

        public ViewHolderT(View itemView) {

            tvName = (TextView) itemView.findViewById(R.id.tv_findworke_hot_type_taxi_detail_item_name);
            tvAddr = (TextView) itemView.findViewById(R.id.tv_findworke_hot_type_taxi_detail_item_addr);
            tvTime = (TextView) itemView.findViewById(R.id.tv_findworke_hot_type_taxi_detail_item_time);
//            ll = (RelativeLayout) itemView.findViewById(R.id.rv_findwork_declare_new_item);


        }
    }

}
