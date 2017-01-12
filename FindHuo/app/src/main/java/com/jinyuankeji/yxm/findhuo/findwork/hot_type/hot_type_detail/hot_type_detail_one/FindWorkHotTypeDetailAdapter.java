package com.jinyuankeji.yxm.findhuo.findwork.hot_type.hot_type_detail.hot_type_detail_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewBean;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryStationBean;

import java.util.List;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class FindWorkHotTypeDetailAdapter extends BaseAdapter{
    private Context mContext;
    private LotteryStationBean stationBean;
    private List<FindWorkDeclareNewBean> datas;


    public FindWorkHotTypeDetailAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(List<FindWorkDeclareNewBean> datas) {
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

        ViewHolderLotteryStation viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_findwork_declare_new_item, parent, false);
            viewHolder = new ViewHolderLotteryStation(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderLotteryStation) convertView.getTag();
        }

        viewHolder.tvName.setText(datas.get(position).getName());
        viewHolder.tvJob.setText(datas.get(position).getJob());
        viewHolder.tvRange.setText(datas.get(position).getRange());
        viewHolder.tvPrice.setText(datas.get(position).getPrice());
        viewHolder.ivImg.setImageResource(datas.get(position).getImg());


        return convertView;

    }

    public class ViewHolderLotteryStation {
        private TextView tvName,tvJob,tvRange,tvPrice;
        private ImageView ivImg;
        private RelativeLayout ll;

        public ViewHolderLotteryStation(View itemView) {
            tvJob = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_job);
            tvName = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_name);
            tvRange = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_range);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_findwork_declare_new_price);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_findworke_declare_new_head);
            ll = (RelativeLayout) itemView.findViewById(R.id.rv_findwork_declare_new_item);


        }
    }

}
