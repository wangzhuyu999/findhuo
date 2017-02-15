package com.jinyuankeji.yxm.findhuo.lottery.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerBean;
import com.squareup.picasso.Picasso;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class LotteryMoreAdapter extends BaseAdapter{
    private Context mContext;
    private MoreBean stationBean;

    public void addData(MoreBean hotspotBean) {
        stationBean.getData().addAll(hotspotBean.getData());
        notifyDataSetChanged();
    }


    public LotteryMoreAdapter(Context context) {
        mContext = context;
    }



    public void setStationBean(MoreBean stationBean) {
        this.stationBean = stationBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return  stationBean.getData()!= null &&  stationBean.getData().size() > 0 ?  stationBean.getData().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return  stationBean.getData() == null ? null :  stationBean.getData().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderLotteryStation viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_lottery_rv_item, parent, false);
            viewHolder = new ViewHolderLotteryStation(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderLotteryStation) convertView.getTag();
        }

        viewHolder.tvName.setText( stationBean.getData().get(position).getLotteryname());
        viewHolder.tvAddr.setText( stationBean.getData().get(position).getAddress());
        Picasso.with(mContext).load( stationBean.getData().get(position).getImgsmall()).into(viewHolder.ivImg);
        return convertView;

    }

    public class ViewHolderLotteryStation {
        private TextView tvName,tvAddr;
        private ImageView ivImg;
        private RelativeLayout ll;

        public ViewHolderLotteryStation(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.tv_lottery_station_name);
            tvAddr = (TextView) itemView.findViewById(R.id.tv_lottery_station_addr);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_lottery_rv_item_icon);
            ll = (RelativeLayout) itemView.findViewById(R.id.rv_layout_lottery);

        }
    }

}
